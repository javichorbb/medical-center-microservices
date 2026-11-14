package clinica.microhistoriaclinicacompuesto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate restTemplate;

    // 1. Validar / Login de Enfermera (Estilo Proceso 1: retorna SalidaEnfermera sin password o null)
    public SalidaEnfermera validarEnfermera(EntradaEnfermera ent) {
        if (ent == null || ent.getUsername() == null || ent.getPassword() == null) {
            return null;
        }
        try {
            Map<?, ?> enf = restTemplate.postForObject(
                    "http://MICROVALIDARENFERMERA/validar",
                    ent,
                    Map.class
            );
            if (enf != null && enf.get("idEnfermera") != null) {
                SalidaEnfermera salida = new SalidaEnfermera();
                salida.setIdEnfermera(Long.valueOf(enf.get("idEnfermera").toString()));
                salida.setNombre((String) enf.get("nombre"));
                salida.setDni((String) enf.get("dni"));
                salida.setUsername((String) enf.get("username"));
                return salida;
            }
        } catch (Exception e) {
            System.err.println("Error al validar enfermera: " + e.getMessage());
        }
        return null;
    }

    // 2. Registrar Paciente en su Primera Cita y Generar su Historia Clínica
    public SalidaHistoria registrarPaciente(EntradaPaciente ent) {
        if (ent == null || ent.getDni() == null || ent.getDni().isBlank()) {
            return null;
        }

        // Verificar si el paciente ya existe por su DNI
        Map<?, ?> pacienteExistente = consultarMicroservicio("http://MICROPACIENTE/pacientes/dni/" + ent.getDni());
        if (pacienteExistente != null && pacienteExistente.get("idPaciente") != null) {
            // Ya existe: no es su primera cita, devolvemos su historia completa existente
            SalidaPaciente paciente = mapearSalidaPaciente(pacienteExistente);
            return armarHistoriaCompleta(paciente.getIdPaciente(), paciente);
        }

        // Si no existe, es su primera cita: Registrar nuevo paciente en MicroPaciente
        Map<?, ?> nuevoPaciente = restTemplate.postForObject(
                "http://MICROPACIENTE/pacientes/grabar",
                ent,
                Map.class
        );

        if (nuevoPaciente == null || nuevoPaciente.get("idPaciente") == null) {
            return null;
        }

        SalidaPaciente pacienteGuardado = mapearSalidaPaciente(nuevoPaciente);
        Long idPaciente = pacienteGuardado.getIdPaciente();

        // Generar automáticamente su historia clínica en MicroHistoria (la fecha se asigna automáticamente)
        EntradaHistoria entradaHistoria = new EntradaHistoria(idPaciente);
        Map<?, ?> nuevaHistoria = restTemplate.postForObject(
                "http://MICROHISTORIA/historia/registrar",
                entradaHistoria,
                Map.class
        );

        Long idHistoria = null;
        LocalDate fechaApertura = LocalDate.now();
        if (nuevaHistoria != null && nuevaHistoria.get("idHistoria") != null) {
            idHistoria = Long.valueOf(nuevaHistoria.get("idHistoria").toString());
            fechaApertura = parsearFecha(nuevaHistoria.get("fechaApertura"));
        }

        // Retornar la nueva historia clínica aperturada (con lista de atenciones vacía)
        return new SalidaHistoria(idHistoria, fechaApertura != null ? fechaApertura : LocalDate.now(), pacienteGuardado, List.of());
    }

    // 3. Obtener Historia Clínica Completa por DNI del Paciente
    public SalidaHistoria obtenerHistoriaPorDni(String dni) {
        if (dni == null || dni.isBlank()) {
            return null;
        }

        Map<?, ?> datosPaciente = consultarMicroservicio("http://MICROPACIENTE/pacientes/dni/" + dni);
        if (datosPaciente == null || datosPaciente.isEmpty() || datosPaciente.get("idPaciente") == null) {
            return null;
        }

        SalidaPaciente paciente = mapearSalidaPaciente(datosPaciente);
        return armarHistoriaCompleta(paciente.getIdPaciente(), paciente);
    }

    // 4. Obtener Historia Clínica Completa por ID de Paciente
    public SalidaHistoria obtenerHistoriaPorPaciente(Long idPaciente) {
        if (idPaciente == null || idPaciente <= 0) {
            return null;
        }

        Map<?, ?> datosPaciente = consultarMicroservicio("http://MICROPACIENTE/pacientes/" + idPaciente);
        if (datosPaciente == null || datosPaciente.isEmpty() || datosPaciente.get("idPaciente") == null) {
            return null;
        }

        SalidaPaciente paciente = mapearSalidaPaciente(datosPaciente);
        return armarHistoriaCompleta(idPaciente, paciente);
    }

    // Método orquestador que consolida Paciente + Historia + Atenciones de MicroAtencionMedica
    private SalidaHistoria armarHistoriaCompleta(Long idPaciente, SalidaPaciente paciente) {
        Map<?, ?> datosHistoria = consultarMicroservicio("http://MICROHISTORIA/historia/paciente/" + idPaciente);
        if (datosHistoria == null || datosHistoria.isEmpty() || datosHistoria.get("idHistoria") == null) {
            datosHistoria = consultarMicroservicio("http://micro-historia/historia/paciente/" + idPaciente);
        }

        if (datosHistoria == null || datosHistoria.isEmpty() || datosHistoria.get("idHistoria") == null) {
            return new SalidaHistoria(null, null, paciente, List.of());
        }

        Long idHistoria = Long.valueOf(datosHistoria.get("idHistoria").toString());
        LocalDate fechaApertura = parsearFecha(datosHistoria.get("fechaApertura"));

        // Consultar atenciones médicas al microservicio local MicroAtencionMedica
        List<AtencionMedica> atenciones = consultarAtencionesMedicas(idHistoria);

        return new SalidaHistoria(idHistoria, fechaApertura, paciente, atenciones);
    }

    private List<AtencionMedica> consultarAtencionesMedicas(Long idHistoria) {
        try {
            AtencionMedica[] atenciones = restTemplate.getForObject(
                    "http://MICROATENCIONMEDICA/atencion/historia/" + idHistoria,
                    AtencionMedica[].class
            );
            return atenciones != null ? Arrays.asList(atenciones) : List.of();
        } catch (RestClientException ex) {
            try {
                AtencionMedica[] atenciones = restTemplate.getForObject(
                        "http://MicroAtencionMedica/atencion/historia/" + idHistoria,
                        AtencionMedica[].class
                );
                return atenciones != null ? Arrays.asList(atenciones) : List.of();
            } catch (RestClientException ignored) {
                return List.of();
            }
        }
    }

    private SalidaPaciente mapearSalidaPaciente(Map<?, ?> raw) {
        SalidaPaciente p = new SalidaPaciente();
        p.setIdPaciente(Long.valueOf(raw.get("idPaciente").toString()));
        p.setNombre((String) raw.get("nombre"));
        p.setDni((String) raw.get("dni"));
        p.setGenero((String) raw.get("genero"));
        p.setTelefono((String) raw.get("telefono"));
        return p;
    }

    private LocalDate parsearFecha(Object val) {
        if (val == null) return null;
        try {
            return LocalDate.parse(val.toString());
        } catch (Exception e) {
            return null;
        }
    }

    private Map<?, ?> consultarMicroservicio(String url) {
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (RestClientException ex) {
            return null;
        }
    }
}
