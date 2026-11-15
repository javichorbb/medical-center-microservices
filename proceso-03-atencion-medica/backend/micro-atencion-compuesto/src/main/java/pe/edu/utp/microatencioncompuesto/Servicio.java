package pe.edu.utp.microatencioncompuesto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // =========================================================================
    // CONSULTA DE CATÁLOGOS
    // =========================================================================

    public List<Medico> listarMedicos() {
        Medico[] res = rt.getForObject("http://micro-medico/medico/listar", Medico[].class);
        return res != null ? Arrays.asList(res) : List.of();
    }

    public List<Medicamento> listarMedicamentos() {
        Medicamento[] res = rt.getForObject("http://micro-medicamento/medicamento/listar", Medicamento[].class);
        return res != null ? Arrays.asList(res) : List.of();
    }

    public List<AnalisisClinico> listarAnalisis() {
        AnalisisClinico[] res = rt.getForObject("http://micro-analisis/analisis/listar", AnalisisClinico[].class);
        return res != null ? Arrays.asList(res) : List.of();
    }

    // =========================================================================
    // MANEJO DE CESTAS EN MEMORIA (RESPUESTAS ENRIQUECIDAS CON CATÁLOGO)
    // =========================================================================

    public List<SalidaRecetaItem> agregarMedicamentoCesta(String idAtencion, EntradaReceta item) {
        validarIdAtencion(idAtencion);
        if (item == null || item.getIdMedicamento() == null || item.getIdMedicamento() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de medicamento inválido");
        }
        Medicamento med = buscarMedicamento(item.getIdMedicamento());
        if (med == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El medicamento no existe: " + item.getIdMedicamento());
        }
        rt.postForObject(
                "http://micro-cesta-receta/cesta-receta/" + idAtencion + "/agregar",
                item,
                EntradaReceta.class);
        return listarCestaReceta(idAtencion);
    }

    public List<SalidaRecetaItem> listarCestaReceta(String idAtencion) {
        return enriquecerRecetaCesta(obtenerCestaReceta(idAtencion));
    }

    public List<SalidaRecetaItem> quitarMedicamentoCesta(String idAtencion, Long idMedicamento) {
        validarIdAtencion(idAtencion);
        rt.delete("http://micro-cesta-receta/cesta-receta/" + idAtencion + "/quitar/" + idMedicamento);
        return listarCestaReceta(idAtencion);
    }

    public List<SalidaAnalisisItem> agregarAnalisisCesta(String idAtencion, EntradaAnalisis item) {
        validarIdAtencion(idAtencion);
        if (item == null || item.getIdAnalisis() == null || item.getIdAnalisis() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de análisis inválido");
        }
        AnalisisClinico ana = buscarAnalisis(item.getIdAnalisis());
        if (ana == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El análisis no existe: " + item.getIdAnalisis());
        }
        rt.postForObject(
                "http://micro-cesta-analisis/cesta-analisis/" + idAtencion + "/agregar",
                item,
                EntradaAnalisis.class);
        return listarCestaAnalisis(idAtencion);
    }

    public List<SalidaAnalisisItem> listarCestaAnalisis(String idAtencion) {
        return enriquecerAnalisisCesta(obtenerCestaAnalisis(idAtencion));
    }

    public List<SalidaAnalisisItem> quitarAnalisisCesta(String idAtencion, Long idAnalisis) {
        validarIdAtencion(idAtencion);
        rt.delete("http://micro-cesta-analisis/cesta-analisis/" + idAtencion + "/quitar/" + idAnalisis);
        return listarCestaAnalisis(idAtencion);
    }

    // =========================================================================
    // REGISTRO Y CONSULTA DE ATENCIÓN MÉDICA COMPLETA
    // =========================================================================

    public SalidaAtencion registrarAtencion(String idAtencion, EntradaAtencion entrada) {
        if (entrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La solicitud no puede estar vacía");
        }

        List<EntradaReceta> medicamentos = entrada.getListaMedicamentos();
        if ((medicamentos == null || medicamentos.isEmpty()) && idAtencion != null && !idAtencion.isBlank()) {
            medicamentos = obtenerCestaReceta(idAtencion);
        }

        List<EntradaAnalisis> analisis = entrada.getListaAnalisis();
        if ((analisis == null || analisis.isEmpty()) && idAtencion != null && !idAtencion.isBlank()) {
            analisis = obtenerCestaAnalisis(idAtencion);
        }

        EntradaAtencion payload = new EntradaAtencion();
        payload.setIdCita(entrada.getIdCita());
        payload.setIdHistoria(entrada.getIdHistoria());
        payload.setIdMedico(entrada.getIdMedico());
        payload.setFecha(entrada.getFecha());
        payload.setHora(entrada.getHora());
        payload.setDiagnostico(entrada.getDiagnostico());
        payload.setTratamiento(entrada.getTratamiento());
        payload.setListaMedicamentos(medicamentos != null ? medicamentos : List.of());
        payload.setListaAnalisis(analisis != null ? analisis : List.of());

        validarAtencion(payload);

        Map<?, ?> atencionGuardada = rt.postForObject(
                "http://micro-atencion/atencion/grabar-completa",
                payload,
                Map.class);

        if (atencionGuardada == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al persistir la atención médica");
        }

        if (idAtencion != null && !idAtencion.isBlank()) {
            limpiarCestas(idAtencion);
        }

        return armarSalidaAtencion(atencionGuardada);
    }

    public SalidaAtencion buscarCompleta(Long idAtencion) {
        Map<?, ?> raw = rt.getForObject("http://micro-atencion/atencion/buscar-completa/" + idAtencion, Map.class);
        return raw != null ? armarSalidaAtencion(raw) : null;
    }

    public List<SalidaAtencion> buscarPorHistoria(Long idHistoria) {
        Map<?, ?>[] array = rt.getForObject("http://micro-atencion/atencion/historia/" + idHistoria, Map[].class);
        return armarListaSalidaAtencion(array);
    }

    public List<SalidaAtencion> listarCompletas() {
        Map<?, ?>[] array = rt.getForObject("http://micro-atencion/atencion/listar-completas", Map[].class);
        return armarListaSalidaAtencion(array);
    }

    // =========================================================================
    // MÉTODOS PRIVADOS DE SOPORTE, VALIDACIÓN Y ENRIQUECIMIENTO
    // =========================================================================

    private void validarAtencion(EntradaAtencion entrada) {
        List<String> errores = new ArrayList<>();

        if (entrada.getIdMedico() == null || entrada.getIdMedico() <= 0) {
            errores.add("Código de médico inválido");
        } else if (buscarMedico(entrada.getIdMedico()) == null) {
            errores.add("El médico no existe: " + entrada.getIdMedico());
        }

        if (entrada.getIdHistoria() == null || entrada.getIdHistoria() <= 0) {
            errores.add("Número de historia clínica inválido");
        }

        if (entrada.getDiagnostico() == null || entrada.getDiagnostico().isBlank()) {
            errores.add("El diagnóstico es obligatorio");
        }
        if (entrada.getTratamiento() == null || entrada.getTratamiento().isBlank()) {
            errores.add("El tratamiento es obligatorio");
        }

        Set<Long> medsRevisados = new HashSet<>();
        for (EntradaReceta item : entrada.getListaMedicamentos()) {
            if (item == null || item.getIdMedicamento() == null || item.getIdMedicamento() <= 0) {
                errores.add("Código de medicamento inválido");
                continue;
            }
            Long idMed = item.getIdMedicamento();
            if (!medsRevisados.add(idMed)) {
                errores.add("Medicamento repetido en receta: " + idMed);
            }
            if (item.getDosis() == null || item.getDosis().isBlank()
                    || item.getCantidad() == null || item.getCantidad() <= 0) {
                errores.add("Dosis o cantidad inválida en medicamento: " + idMed);
            }
            if (buscarMedicamento(idMed) == null) {
                errores.add("El medicamento no existe: " + idMed);
            }
        }

        Set<Long> anaRevisados = new HashSet<>();
        for (EntradaAnalisis item : entrada.getListaAnalisis()) {
            if (item == null || item.getIdAnalisis() == null || item.getIdAnalisis() <= 0) {
                errores.add("Código de análisis inválido");
                continue;
            }
            Long idAna = item.getIdAnalisis();
            if (!anaRevisados.add(idAna)) {
                errores.add("Análisis repetido en orden: " + idAna);
            }
            if (item.getIndicacion() == null || item.getIndicacion().isBlank()) {
                errores.add("Indicación inválida en análisis: " + idAna);
            }
            if (buscarAnalisis(idAna) == null) {
                errores.add("El análisis no existe: " + idAna);
            }
        }

        if (!errores.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join("; ", errores));
        }
    }

    private List<EntradaReceta> obtenerCestaReceta(String idAtencion) {
        validarIdAtencion(idAtencion);
        String url = "http://micro-cesta-receta/cesta-receta/" + idAtencion + "/listar";
        try {
            EntradaReceta[] res = rt.getForObject(url, EntradaReceta[].class);
            return res != null ? Arrays.asList(res) : List.of();
        } catch (RestClientException ex) {
            return List.of();
        }
    }

    private List<EntradaAnalisis> obtenerCestaAnalisis(String idAtencion) {
        validarIdAtencion(idAtencion);
        String url = "http://micro-cesta-analisis/cesta-analisis/" + idAtencion + "/listar";
        try {
            EntradaAnalisis[] res = rt.getForObject(url, EntradaAnalisis[].class);
            return res != null ? Arrays.asList(res) : List.of();
        } catch (RestClientException ex) {
            return List.of();
        }
    }

    private void limpiarCestas(String idAtencion) {
        try {
            rt.delete("http://micro-cesta-receta/cesta-receta/" + idAtencion + "/limpiar");
        } catch (Exception ignored) {}
        try {
            rt.delete("http://micro-cesta-analisis/cesta-analisis/" + idAtencion + "/limpiar");
        } catch (Exception ignored) {}
    }

    private void validarIdAtencion(String idAtencion) {
        if (idAtencion == null || idAtencion.isBlank() || idAtencion.length() > 100 || !idAtencion.matches("[a-zA-Z0-9_-]+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificador de atención médica inválido");
        }
    }

    private Medico buscarMedico(Long idMedico) {
        try {
            return rt.getForObject("http://micro-medico/medico/buscar/" + idMedico, Medico.class);
        } catch (RestClientException ex) {
            return null;
        }
    }

    private Medicamento buscarMedicamento(Long idMedicamento) {
        try {
            return rt.getForObject("http://micro-medicamento/medicamento/buscar/" + idMedicamento, Medicamento.class);
        } catch (RestClientException ex) {
            return null;
        }
    }

    private AnalisisClinico buscarAnalisis(Long idAnalisis) {
        try {
            return rt.getForObject("http://micro-analisis/analisis/buscar/" + idAnalisis, AnalisisClinico.class);
        } catch (RestClientException ex) {
            return null;
        }
    }

    private List<SalidaRecetaItem> enriquecerRecetaCesta(List<EntradaReceta> items) {
        List<SalidaRecetaItem> lista = new ArrayList<>();
        for (EntradaReceta item : items) {
            lista.add(crearSalidaRecetaItem(item.getIdMedicamento(), item.getDosis(), item.getCantidad()));
        }
        return lista;
    }

    private List<SalidaAnalisisItem> enriquecerAnalisisCesta(List<EntradaAnalisis> items) {
        List<SalidaAnalisisItem> lista = new ArrayList<>();
        for (EntradaAnalisis item : items) {
            lista.add(crearSalidaAnalisisItem(item.getIdAnalisis(), item.getIndicacion()));
        }
        return lista;
    }

    private SalidaRecetaItem crearSalidaRecetaItem(Long idMed, String dosis, Integer cantidad) {
        Medicamento cat = idMed != null ? buscarMedicamento(idMed) : null;
        return new SalidaRecetaItem(
                idMed,
                cat != null ? cat.getNombre() : "Medicamento #" + idMed,
                cat != null ? cat.getPresentacion() : null,
                dosis,
                cantidad);
    }

    private SalidaAnalisisItem crearSalidaAnalisisItem(Long idAna, String indicacion) {
        AnalisisClinico cat = idAna != null ? buscarAnalisis(idAna) : null;
        return new SalidaAnalisisItem(
                idAna,
                cat != null ? cat.getDescripcion() : "Análisis #" + idAna,
                cat != null ? cat.getTipo() : null,
                indicacion);
    }

    private List<SalidaAtencion> armarListaSalidaAtencion(Map<?, ?>[] array) {
        if (array == null) {
            return List.of();
        }
        List<SalidaAtencion> resultado = new ArrayList<>();
        for (Map<?, ?> item : array) {
            SalidaAtencion salida = armarSalidaAtencion(item);
            if (salida != null) {
                resultado.add(salida);
            }
        }
        return resultado;
    }

    private SalidaAtencion armarSalidaAtencion(Map<?, ?> raw) {
        if (raw == null) {
            return null;
        }
        Map<?, ?> atencion = (Map<?, ?>) raw.get("atencion");
        if (atencion == null) {
            return null;
        }

        Long idMedico = toLong(atencion.get("idMedico"));
        Medico medico = null;
        if (idMedico != null) {
            medico = buscarMedico(idMedico);
            if (medico == null) {
                medico = new Medico(idMedico, null, null);
            }
        }

        List<SalidaRecetaItem> recetaMedica = new ArrayList<>();
        List<?> recetaItems = (List<?>) raw.get("receta");
        if (recetaItems != null) {
            for (Object obj : recetaItems) {
                if (obj instanceof Map<?, ?> item) {
                    recetaMedica.add(crearSalidaRecetaItem(
                            toLong(item.get("idMedicamento")),
                            toStr(item.get("dosis")),
                            toInteger(item.get("cantidad"))));
                }
            }
        }

        List<SalidaAnalisisItem> analisisClinicos = new ArrayList<>();
        List<?> analisisItems = (List<?>) raw.get("analisis");
        if (analisisItems != null) {
            for (Object obj : analisisItems) {
                if (obj instanceof Map<?, ?> item) {
                    analisisClinicos.add(crearSalidaAnalisisItem(
                            toLong(item.get("idAnalisis")),
                            toStr(item.get("indicacion"))));
                }
            }
        }

        return new SalidaAtencion(
                toLong(atencion.get("idAtencion")),
                toLong(atencion.get("idCita")),
                toLong(atencion.get("idHistoria")),
                toLocalDate(atencion.get("fecha")),
                toLocalTime(atencion.get("hora")),
                toStr(atencion.get("diagnostico")),
                toStr(atencion.get("tratamiento")),
                medico,
                recetaMedica,
                analisisClinicos);
    }

    private Long toLong(Object val) {
        return val instanceof Number n ? n.longValue() : null;
    }

    private Integer toInteger(Object val) {
        return val instanceof Number n ? n.intValue() : null;
    }

    private String toStr(Object val) {
        return val != null ? val.toString() : null;
    }

    private LocalDate toLocalDate(Object val) {
        return val != null ? LocalDate.parse(val.toString()) : null;
    }

    private LocalTime toLocalTime(Object val) {
        return val != null ? LocalTime.parse(val.toString()) : null;
    }
}
