package Citamedica.MicroCompCita;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // Endpoint Paso 1: Consultar Especialidades Médicas desde MicroCatalogoMedico
    public List<String> listarEspecialidades() {
        try {
            String[] esp = rt.getForObject("http://MicroCatalogoMedico/listarEspecialidades", String[].class);
            return esp != null ? Arrays.asList(esp) : List.of();
        } catch (Exception e) {
            System.err.println("Error al listar especialidades: " + e.getMessage());
            return List.of();
        }
    }

    // Endpoint Paso 2: Consultar Médicos por Especialidad desde MicroCatalogoMedico
    public List<Medico> listarMedicosPorEspecialidad(String esp) {
        try {
            Medico[] medicos = rt.getForObject("http://MicroCatalogoMedico/listarPorEspecialidad/" + esp, Medico[].class);
            return medicos != null ? Arrays.asList(medicos) : List.of();
        } catch (Exception e) {
            System.err.println("Error al listar medicos por especialidad: " + e.getMessage());
            return List.of();
        }
    }

    // Endpoint Paso 3: Consultar Ficha del Médico (Médico + Horarios) componiendo MicroCatalogoMedico y MicroCatalogoHorario
    public SalidaMedico consultarFichaMedico(Long idMedico) {
        if (idMedico == null) {
            return null;
        }

        Medico med = rt.getForObject(
            "http://MicroCatalogoMedico/buscar/" + idMedico,
            Medico.class
        );

        Horario[] horarios = rt.getForObject(
            "http://MicroCatalogoHorario/porMedico/" + idMedico,
            Horario[].class
        );

        List<Horario> listaHorarios = horarios != null ? Arrays.asList(horarios) : List.of();

        return new SalidaMedico(
            idMedico,
            med != null ? med.getNombre() : "No encontrado",
            med != null ? med.getEspecialidad() : "",
            listaHorarios
        );
    }

    // Endpoint Adicional Paso 3: Consultar Horarios Disponibles del Médico desde MicroCatalogoHorario
    public List<Horario> listarHorariosDisponibles(Long idMedico) {
        try {
            Horario[] horarios = rt.getForObject(
                "http://MicroCatalogoHorario/disponibles/" + idMedico,
                Horario[].class
            );
            return horarios != null ? Arrays.asList(horarios) : List.of();
        } catch (Exception e) {
            System.err.println("Error al listar horarios disponibles: " + e.getMessage());
            return List.of();
        }
    }

    // Endpoint Paso 4: Validar Paciente por DNI con MicroValidar
    public Paciente validarPaciente(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return null;
        }
        try {
            return rt.postForObject(
                "http://MicroValidar/validar",
                new EntradaPaciente(dni.trim()),
                Paciente.class
            );
        } catch (Exception e) {
            System.err.println("Error al validar paciente: " + e.getMessage());
            return null;
        }
    }

    // Endpoint Paso 5: Registrar Cita Médica con comprobante detallado (compone los 4 microservicios)
    public SalidaCita registrarCita(EntradaCita ent) {
        if (ent == null || ent.getIdHorario() == null || ent.getIdPaciente() == null) {
            return null;
        }

        // 1. Grabar cita en MicroRegistroCita
        CitaMedica citaGuardada = null;
        try {
            CitaMedica nuevaCita = new CitaMedica(null, ent.getIdHorario(), ent.getIdPaciente(), "RESERVADA");
            citaGuardada = rt.postForObject(
                "http://MicroRegistroCita/registrar",
                nuevaCita,
                CitaMedica.class
            );
        } catch (Exception e) {
            System.err.println("Error al registrar cita: " + e.getMessage());
            return null;
        }

        if (citaGuardada == null) {
            return null;
        }

        // 2. Obtener datos completos del paciente desde MicroValidar
        Paciente paciente = null;
        try {
            paciente = rt.getForObject("http://MicroValidar/buscarPorId/" + ent.getIdPaciente(), Paciente.class);
        } catch (Exception e) {
            System.err.println("Error al obtener datos del paciente: " + e.getMessage());
        }

        // 3. Obtener datos del horario desde MicroCatalogoHorario
        Horario horario = null;
        try {
            horario = rt.getForObject("http://MicroCatalogoHorario/buscar/" + ent.getIdHorario(), Horario.class);
        } catch (Exception e) {
            System.err.println("Error al obtener datos del horario: " + e.getMessage());
        }

        // 4. Obtener datos del médico desde MicroCatalogoMedico
        Medico medico = null;
        if (horario != null && horario.getIdMedico() != null) {
            try {
                medico = rt.getForObject("http://MicroCatalogoMedico/buscar/" + horario.getIdMedico(), Medico.class);
            } catch (Exception e) {
                System.err.println("Error al obtener datos del médico: " + e.getMessage());
            }
        }

        // 5. Retornar el comprobante completo de la cita
        return new SalidaCita(
            citaGuardada.getIdCita(),
            citaGuardada.getEstado(),
            paciente,
            medico,
            horario
        );
    }

    // Consultar una cita médica detallada por su ID
    public SalidaCita buscarCita(Long idCita) {
        if (idCita == null || idCita <= 0) {
            return null;
        }
        try {
            CitaMedica cita = rt.getForObject("http://MicroRegistroCita/buscar/" + idCita, CitaMedica.class);
            if (cita == null) {
                return null;
            }
            return armarSalidaCita(cita);
        } catch (Exception e) {
            System.err.println("Error al buscar cita por ID: " + e.getMessage());
            return null;
        }
    }

    // Consultar todas las citas de un paciente por DNI
    public List<SalidaCita> buscarCitasPorDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return List.of();
        }

        Paciente paciente = validarPaciente(dni.trim());
        if (paciente == null || paciente.getIdPaciente() == null) {
            return List.of();
        }

        try {
            CitaMedica[] citas = rt.getForObject("http://MicroRegistroCita/listar", CitaMedica[].class);
            if (citas == null) {
                return List.of();
            }

            List<SalidaCita> resultado = new java.util.ArrayList<>();
            for (CitaMedica c : citas) {
                if (paciente.getIdPaciente().equals(c.getIdPaciente())) {
                    SalidaCita sc = armarSalidaCita(c);
                    if (sc != null) {
                        resultado.add(sc);
                    }
                }
            }
            return resultado;
        } catch (Exception e) {
            System.err.println("Error al listar citas por DNI: " + e.getMessage());
            return List.of();
        }
    }

    // Helper privado para componer SalidaCita con Paciente, Médico y Horario
    private SalidaCita armarSalidaCita(CitaMedica cita) {
        if (cita == null) {
            return null;
        }

        Paciente paciente = null;
        try {
            paciente = rt.getForObject("http://MicroValidar/buscarPorId/" + cita.getIdPaciente(), Paciente.class);
        } catch (Exception ignored) {}

        Horario horario = null;
        try {
            horario = rt.getForObject("http://MicroCatalogoHorario/buscar/" + cita.getIdHorario(), Horario.class);
        } catch (Exception ignored) {}

        Medico medico = null;
        if (horario != null && horario.getIdMedico() != null) {
            try {
                medico = rt.getForObject("http://MicroCatalogoMedico/buscar/" + horario.getIdMedico(), Medico.class);
            } catch (Exception ignored) {}
        }

        return new SalidaCita(
            cita.getIdCita(),
            cita.getEstado(),
            paciente,
            medico,
            horario
        );
    }
}