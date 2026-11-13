package pe.edu.utp.microatencioncompuesto.servicio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.utp.microatencioncompuesto.dto.*;

@Service
public class AtencionCompuestoService {

    @Autowired
    private RestTemplate restTemplate;

    // 1. REGISTRAR ATENCIÓN MÉDICA COMPLETA CON CESTAS
    public Map<String, Object> registrarAtencion(String idAtencion, EntradaAtencionDTO entrada) {
        if (entrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La solicitud no puede estar vacía");
        }

        // Recuperar medicamentos y análisis desde las cestas referenciadas por idAtencion
        List<MedicamentoItemDTO> medicamentos = entrada.getListaMedicamentos();
        if ((medicamentos == null || medicamentos.isEmpty()) && idAtencion != null && !idAtencion.isBlank()) {
            medicamentos = obtenerCestaReceta(idAtencion);
        }

        List<AnalisisItemDTO> analisis = entrada.getListaAnalisis();
        if ((analisis == null || analisis.isEmpty()) && idAtencion != null && !idAtencion.isBlank()) {
            analisis = obtenerCestaAnalisis(idAtencion);
        }

        // Validaciones previas de integridad referencial
        EntradaAtencionDTO datosAValidar = new EntradaAtencionDTO();
        datosAValidar.setIdCita(entrada.getIdCita());
        datosAValidar.setIdMedico(entrada.getIdMedico());
        datosAValidar.setIdHistoria(entrada.getIdHistoria());
        datosAValidar.setFecha(entrada.getFecha());
        datosAValidar.setHora(entrada.getHora());
        datosAValidar.setDiagnostico(entrada.getDiagnostico());
        datosAValidar.setTratamiento(entrada.getTratamiento());
        datosAValidar.setListaMedicamentos(medicamentos);
        datosAValidar.setListaAnalisis(analisis);

        SalidaAtencionDTO validacion = validarAtencion(datosAValidar);
        if (!validacion.isValido()) {
            Map<String, Object> errorRes = new LinkedHashMap<>();
            errorRes.put("registrado", false);
            errorRes.put("mensaje", "No se pudo registrar la atención debido a errores de validación");
            errorRes.put("errores", validacion.getErrores());
            return errorRes;
        }

        // Preparar payload para persistencia en micro-atencion
        Map<String, Object> payloadAtencion = new LinkedHashMap<>();
        payloadAtencion.put("idCita", datosAValidar.getIdCita());
        payloadAtencion.put("idHistoria", datosAValidar.getIdHistoria());
        payloadAtencion.put("idMedico", datosAValidar.getIdMedico());
        payloadAtencion.put("fecha", datosAValidar.getFecha());
        payloadAtencion.put("hora", datosAValidar.getHora());
        payloadAtencion.put("diagnostico", datosAValidar.getDiagnostico());
        payloadAtencion.put("tratamiento", datosAValidar.getTratamiento());
        payloadAtencion.put("listaMedicamentos", medicamentos);
        payloadAtencion.put("listaAnalisis", analisis);

        // Guardar de forma transaccional en micro-atencion
        Map<?, ?> atencionGuardada = restTemplate.postForObject(
                "http://micro-atencion/atencion/grabar-completa",
                payloadAtencion,
                Map.class
        );

        if (atencionGuardada == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al persistir la atención médica");
        }

        // Vaciar cestas al finalizar con éxito
        if (idAtencion != null && !idAtencion.isBlank()) {
            limpiarCestas(idAtencion);
        }

        // Retornar la atención enriquecida y estructurada
        return enriquecerAtencion(atencionGuardada);
    }

    // 2. BUSCAR ATENCIÓN MÉDICA POR ID
    public Map<String, Object> buscarCompleta(Long idAtencion) {
        String url = "http://micro-atencion/atencion/buscar-completa/" + idAtencion;
        Map<?, ?> raw = restTemplate.getForObject(url, Map.class);
        return raw != null ? enriquecerAtencion(raw) : null;
    }

    // 3. BUSCAR ATENCIONES POR HISTORIA CLÍNICA (Para el Proceso 4)
    public List<Map<String, Object>> buscarPorHistoria(Long idHistoria) {
        String url = "http://micro-atencion/atencion/historia/" + idHistoria;
        Map[] array = restTemplate.getForObject(url, Map[].class);
        if (array == null) {
            return List.of();
        }

        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Map item : array) {
            resultado.add(enriquecerAtencion(item));
        }
        return resultado;
    }

    // 4. LISTAR TODAS LAS ATENCIONES MÉDICAS
    public List<Map<String, Object>> listarCompletas() {
        String url = "http://micro-atencion/atencion/listar-completas";
        Map[] array = restTemplate.getForObject(url, Map[].class);
        if (array == null) {
            return List.of();
        }

        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Map item : array) {
            resultado.add(enriquecerAtencion(item));
        }
        return resultado;
    }

    // =========================================================================
    // MÉTODOS PRIVADOS DE SOPORTE Y VALIDACIÓN
    // =========================================================================

    private SalidaAtencionDTO validarAtencion(EntradaAtencionDTO entrada) {
        SalidaAtencionDTO salida = new SalidaAtencionDTO();
        List<String> errores = new ArrayList<>();

        if (entrada.getIdMedico() == null || entrada.getIdMedico() <= 0) {
            errores.add("Código de médico inválido");
        } else {
            verificarExistencia(
                    "http://micro-medico/medico/buscar/" + entrada.getIdMedico(),
                    "El médico no existe: " + entrada.getIdMedico(),
                    "No se pudo verificar el médico",
                    errores);
        }

        if (entrada.getIdHistoria() == null || entrada.getIdHistoria() <= 0) {
            errores.add("Número de historia clínica inválido");
        } else {
            verificarExistencia(
                    "http://micro-historia/historia/buscar/" + entrada.getIdHistoria(),
                    "La historia clínica no existe: " + entrada.getIdHistoria(),
                    "No se pudo verificar la historia clínica",
                    errores);
        }

        if (entrada.getDiagnostico() == null || entrada.getDiagnostico().isBlank()) {
            errores.add("El diagnóstico es obligatorio");
        }
        if (entrada.getTratamiento() == null || entrada.getTratamiento().isBlank()) {
            errores.add("El tratamiento es obligatorio");
        }

        // Validar existencia de cada medicamento
        Set<Long> medicamentosRevisados = new HashSet<>();
        if (entrada.getListaMedicamentos() != null) {
            for (MedicamentoItemDTO item : entrada.getListaMedicamentos()) {
                if (item == null || item.getIdMedicamento() == null || item.getIdMedicamento() <= 0) {
                    errores.add("Código de medicamento inválido");
                    continue;
                }
                Long idMed = item.getIdMedicamento();
                if (!medicamentosRevisados.add(idMed)) {
                    errores.add("Medicamento repetido en receta: " + idMed);
                }
                if (item.getDosis() == null || item.getDosis().isBlank()
                        || item.getCantidad() == null || item.getCantidad() <= 0) {
                    errores.add("Dosis o cantidad inválida en medicamento: " + idMed);
                }
                verificarExistencia(
                        "http://micro-medicamento/medicamento/buscar/" + idMed,
                        "El medicamento no existe: " + idMed,
                        "No se pudo verificar el medicamento: " + idMed,
                        errores);
            }
        }

        // Validar existencia de cada análisis clínico
        Set<Long> analisisRevisados = new HashSet<>();
        if (entrada.getListaAnalisis() != null) {
            for (AnalisisItemDTO item : entrada.getListaAnalisis()) {
                if (item == null || item.getIdAnalisis() == null || item.getIdAnalisis() <= 0) {
                    errores.add("Código de análisis inválido");
                    continue;
                }
                Long idAna = item.getIdAnalisis();
                if (!analisisRevisados.add(idAna)) {
                    errores.add("Análisis repetido en orden: " + idAna);
                }
                if (item.getIndicacion() == null || item.getIndicacion().isBlank()) {
                    errores.add("Indicación inválida en análisis: " + idAna);
                }
                verificarExistencia(
                        "http://micro-analisis/analisis/buscar/" + idAna,
                        "El análisis no existe: " + idAna,
                        "No se pudo verificar el análisis: " + idAna,
                        errores);
            }
        }

        salida.setErrores(errores);
        salida.setValido(errores.isEmpty());
        return salida;
    }

    private void verificarExistencia(String url, String mensajeNoExiste, String mensajeNoDisponible, List<String> errores) {
        try {
            Map<?, ?> respuesta = restTemplate.getForObject(url, Map.class);
            if (respuesta == null || respuesta.isEmpty()) {
                errores.add(mensajeNoExiste);
            }
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                errores.add(mensajeNoExiste);
            } else {
                errores.add(mensajeNoDisponible);
            }
        } catch (RestClientException ex) {
            errores.add(mensajeNoDisponible);
        }
    }

    private List<MedicamentoItemDTO> obtenerCestaReceta(String idAtencion) {
        validarIdAtencion(idAtencion);
        String url = "http://micro-cesta-receta/cesta-receta/" + idAtencion + "/listar";
        try {
            MedicamentoItemDTO[] respuesta = restTemplate.getForObject(url, MedicamentoItemDTO[].class);
            return respuesta == null ? List.of() : new ArrayList<>(List.of(respuesta));
        } catch (RestClientException ex) {
            return List.of();
        }
    }

    private List<AnalisisItemDTO> obtenerCestaAnalisis(String idAtencion) {
        validarIdAtencion(idAtencion);
        String url = "http://micro-cesta-analisis/cesta-analisis/" + idAtencion + "/listar";
        try {
            AnalisisItemDTO[] respuesta = restTemplate.getForObject(url, AnalisisItemDTO[].class);
            return respuesta == null ? List.of() : new ArrayList<>(List.of(respuesta));
        } catch (RestClientException ex) {
            return List.of();
        }
    }

    private void limpiarCestas(String idAtencion) {
        try {
            restTemplate.delete("http://micro-cesta-receta/cesta-receta/" + idAtencion + "/limpiar");
        } catch (Exception ignored) {}
        try {
            restTemplate.delete("http://micro-cesta-analisis/cesta-analisis/" + idAtencion + "/limpiar");
        } catch (Exception ignored) {}
    }

    private void validarIdAtencion(String idAtencion) {
        if (idAtencion == null || idAtencion.isBlank() || idAtencion.length() > 100 || !idAtencion.matches("[a-zA-Z0-9_-]+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificador de atención médica inválido");
        }
    }

    private Map<?, ?> consultarCatalogo(String url) {
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (RestClientException ex) {
            return null;
        }
    }

    private Map<String, Object> enriquecerAtencion(Map<?, ?> raw) {
        Map<String, Object> res = new LinkedHashMap<>();

        Map<?, ?> atencion = (Map<?, ?>) raw.get("atencion");
        if (atencion == null) {
            return res;
        }

        res.put("idAtencion", atencion.get("idAtencion"));
        res.put("idCita", atencion.get("idCita"));
        res.put("idHistoria", atencion.get("idHistoria"));
        res.put("fecha", atencion.get("fecha"));
        res.put("hora", atencion.get("hora"));
        res.put("diagnostico", atencion.get("diagnostico"));
        res.put("tratamiento", atencion.get("tratamiento"));

        // Enriquecer Médico
        Object idMedico = atencion.get("idMedico");
        if (idMedico != null) {
            Map<?, ?> medico = consultarCatalogo("http://micro-medico/medico/buscar/" + idMedico);
            res.put("medico", medico != null ? medico : Map.of("idMedico", idMedico));
        } else {
            res.put("medico", null);
        }

        // Enriquecer Receta Médica como objetos estructurados
        List<Map<String, Object>> recetaMedicaList = new ArrayList<>();
        List<?> recetaItems = (List<?>) raw.get("receta");
        if (recetaItems != null) {
            for (Object obj : recetaItems) {
                if (obj instanceof Map<?, ?> item) {
                    Map<String, Object> det = new LinkedHashMap<>();
                    Object idMed = item.get("idMedicamento");
                    det.put("idMedicamento", idMed);

                    Map<?, ?> cat = consultarCatalogo("http://micro-medicamento/medicamento/buscar/" + idMed);
                    if (cat != null) {
                        det.put("nombre", cat.get("nombre"));
                        det.put("presentacion", cat.get("presentacion"));
                    } else {
                        det.put("nombre", "Medicamento #" + idMed);
                        det.put("presentacion", null);
                    }
                    det.put("dosis", item.get("dosis"));
                    det.put("cantidad", item.get("cantidad"));
                    recetaMedicaList.add(det);
                }
            }
        }
        res.put("recetaMedica", recetaMedicaList);

        // Enriquecer Análisis Clínicos como objetos estructurados
        List<Map<String, Object>> analisisClinicosList = new ArrayList<>();
        List<?> analisisItems = (List<?>) raw.get("analisis");
        if (analisisItems != null) {
            for (Object obj : analisisItems) {
                if (obj instanceof Map<?, ?> item) {
                    Map<String, Object> det = new LinkedHashMap<>();
                    Object idAna = item.get("idAnalisis");
                    det.put("idAnalisis", idAna);

                    Map<?, ?> cat = consultarCatalogo("http://micro-analisis/analisis/buscar/" + idAna);
                    if (cat != null) {
                        det.put("descripcion", cat.get("descripcion"));
                        det.put("tipo", cat.get("tipo"));
                    } else {
                        det.put("descripcion", "Análisis #" + idAna);
                        det.put("tipo", null);
                    }
                    det.put("indicacion", item.get("indicacion"));
                    analisisClinicosList.add(det);
                }
            }
        }
        res.put("analisisClinicos", analisisClinicosList);

        return res;
    }
}
