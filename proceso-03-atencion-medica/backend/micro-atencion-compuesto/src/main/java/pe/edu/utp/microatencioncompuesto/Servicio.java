package pe.edu.utp.microatencioncompuesto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    // MANEJO DE CESTAS EN MEMORIA (Usando la única clase Entrada)
    // =========================================================================

    public List<MedicamentoCesta> agregarMedicamento(Entrada ent) {
        if (ent == null || ent.getIdMedicamento() == null || ent.getIdMedicamento() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de medicamento inválido");
        }
        Medicamento med = buscarMedicamento(ent.getIdMedicamento());
        if (med == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El medicamento no existe: " + ent.getIdMedicamento());
        }
        MedicamentoCesta item = new MedicamentoCesta(
                ent.getIdMedicamento(),
                med.getNombre(),
                med.getPresentacion(),
                ent.getDosis(),
                ent.getCantidad()
        );
        MedicamentoCesta[] res = rt.postForObject("http://micro-cesta-receta/cesta-receta/agregar", item, MedicamentoCesta[].class);
        return res != null ? Arrays.asList(res) : List.of();
    }

    public List<MedicamentoCesta> verCestaReceta() {
        try {
            MedicamentoCesta[] res = rt.getForObject("http://micro-cesta-receta/cesta-receta/listar", MedicamentoCesta[].class);
            return res != null ? Arrays.asList(res) : List.of();
        } catch (RestClientException ex) {
            return List.of();
        }
    }

    public List<MedicamentoCesta> quitarMedicamento(Long idMedicamento) {
        rt.delete("http://micro-cesta-receta/cesta-receta/quitar/" + idMedicamento);
        return verCestaReceta();
    }

    public List<AnalisisCesta> agregarAnalisis(Entrada ent) {
        if (ent == null || ent.getIdAnalisis() == null || ent.getIdAnalisis() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de análisis inválido");
        }
        AnalisisClinico ana = buscarAnalisis(ent.getIdAnalisis());
        if (ana == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El análisis no existe: " + ent.getIdAnalisis());
        }
        AnalisisCesta item = new AnalisisCesta(
                ent.getIdAnalisis(),
                ana.getDescripcion(),
                ana.getTipo(),
                ent.getIndicacion()
        );
        AnalisisCesta[] res = rt.postForObject("http://micro-cesta-analisis/cesta-analisis/agregar", item, AnalisisCesta[].class);
        return res != null ? Arrays.asList(res) : List.of();
    }

    public List<AnalisisCesta> verCestaAnalisis() {
        try {
            AnalisisCesta[] res = rt.getForObject("http://micro-cesta-analisis/cesta-analisis/listar", AnalisisCesta[].class);
            return res != null ? Arrays.asList(res) : List.of();
        } catch (RestClientException ex) {
            return List.of();
        }
    }

    public List<AnalisisCesta> quitarAnalisis(Long idAnalisis) {
        rt.delete("http://micro-cesta-analisis/cesta-analisis/quitar/" + idAnalisis);
        return verCestaAnalisis();
    }

    // =========================================================================
    // GRABAR Y CONSULTAR ATENCIÓN MÉDICA (fecha y hora automáticas)
    // =========================================================================

    public Salida grabarAtencion(Entrada ent) {
        if (ent == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La solicitud no puede estar vacía");
        }
        if (ent.getIdMedico() == null || ent.getIdMedico() <= 0 || buscarMedico(ent.getIdMedico()) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El médico no existe o es inválido");
        }
        if (ent.getIdHistoria() == null || ent.getIdHistoria() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de historia clínica inválido");
        }
        if (ent.getDiagnostico() == null || ent.getDiagnostico().isBlank()
                || ent.getTratamiento() == null || ent.getTratamiento().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Diagnóstico y tratamiento son obligatorios");
        }

        List<MedicamentoCesta> medicamentos = verCestaReceta();
        List<AnalisisCesta> analisis = verCestaAnalisis();

        Map<String, Object> payload = new HashMap<>();
        payload.put("idCita", ent.getIdCita());
        payload.put("idHistoria", ent.getIdHistoria());
        payload.put("idMedico", ent.getIdMedico());
        payload.put("fecha", LocalDate.now().toString());
        payload.put("hora", LocalTime.now().withNano(0).toString());
        payload.put("diagnostico", ent.getDiagnostico().trim());
        payload.put("tratamiento", ent.getTratamiento().trim());
        payload.put("listaMedicamentos", medicamentos);
        payload.put("listaAnalisis", analisis);

        Map<?, ?> atencionGuardada = rt.postForObject("http://micro-atencion/atencion/grabar", payload, Map.class);
        if (atencionGuardada == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al persistir la atención médica");
        }

        limpiarCestas();
        return armarSalida(atencionGuardada);
    }

    public Salida verSalida(Long idAtencion) {
        Map<?, ?> raw = rt.getForObject("http://micro-atencion/atencion/buscar/" + idAtencion, Map.class);
        return raw != null ? armarSalida(raw) : null;
    }

    public List<Salida> buscarPorHistoria(Long idHistoria) {
        Map<?, ?>[] array = rt.getForObject("http://micro-atencion/atencion/historia/" + idHistoria, Map[].class);
        return armarListaSalida(array);
    }

    public List<Salida> listarTodas() {
        Map<?, ?>[] array = rt.getForObject("http://micro-atencion/atencion/listar", Map[].class);
        return armarListaSalida(array);
    }

    // =========================================================================
    // MÉTODOS PRIVADOS DE SOPORTE
    // =========================================================================

    private void limpiarCestas() {
        try { rt.delete("http://micro-cesta-receta/cesta-receta/limpiar"); } catch (Exception ignored) {}
        try { rt.delete("http://micro-cesta-analisis/cesta-analisis/limpiar"); } catch (Exception ignored) {}
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

    private MedicamentoCesta crearMedicamentoCesta(Long idMed, String dosis, Integer cantidad) {
        Medicamento cat = idMed != null ? buscarMedicamento(idMed) : null;
        return new MedicamentoCesta(
                idMed,
                cat != null ? cat.getNombre() : "Medicamento #" + idMed,
                cat != null ? cat.getPresentacion() : null,
                dosis,
                cantidad);
    }

    private AnalisisCesta crearAnalisisCesta(Long idAna, String indicacion) {
        AnalisisClinico cat = idAna != null ? buscarAnalisis(idAna) : null;
        return new AnalisisCesta(
                idAna,
                cat != null ? cat.getDescripcion() : "Análisis #" + idAna,
                cat != null ? cat.getTipo() : null,
                indicacion);
    }

    private List<Salida> armarListaSalida(Map<?, ?>[] array) {
        if (array == null) {
            return List.of();
        }
        List<Salida> resultado = new ArrayList<>();
        for (Map<?, ?> item : array) {
            Salida s = armarSalida(item);
            if (s != null) {
                resultado.add(s);
            }
        }
        return resultado;
    }

    private Salida armarSalida(Map<?, ?> raw) {
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

        List<MedicamentoCesta> recetaMedica = new ArrayList<>();
        List<?> recetaItems = (List<?>) raw.get("receta");
        if (recetaItems != null) {
            for (Object obj : recetaItems) {
                if (obj instanceof Map<?, ?> item) {
                    recetaMedica.add(crearMedicamentoCesta(
                            toLong(item.get("idMedicamento")),
                            toStr(item.get("dosis")),
                            toInteger(item.get("cantidad"))));
                }
            }
        }

        List<AnalisisCesta> analisisClinicos = new ArrayList<>();
        List<?> analisisItems = (List<?>) raw.get("analisis");
        if (analisisItems != null) {
            for (Object obj : analisisItems) {
                if (obj instanceof Map<?, ?> item) {
                    analisisClinicos.add(crearAnalisisCesta(
                            toLong(item.get("idAnalisis")),
                            toStr(item.get("indicacion"))));
                }
            }
        }

        return new Salida(
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
