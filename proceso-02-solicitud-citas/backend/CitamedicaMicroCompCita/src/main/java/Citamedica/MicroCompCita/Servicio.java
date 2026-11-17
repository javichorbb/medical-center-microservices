package Citamedica.MicroCompCita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // 1. Grabar Cita Médica (valida Horario y Paciente primero, registra cita RESERVADA y retorna Salida consolidada)
    public Salida grabarCita(Entrada ent) {
        if (ent == null || ent.getIdHorario() == null || ent.getIdPaciente() == null) {
            return null;
        }

        // Validar que el Horario exista en MicroCatalogoHorario
        Horario horario = null;
        try {
            horario = rt.getForObject("http://MicroCatalogoHorario/buscar/" + ent.getIdHorario(), Horario.class);
        } catch (Exception e) {
            System.err.println("Error al validar horario: " + e.getMessage());
        }
        if (horario == null) {
            return null;
        }

        // Validar que el Paciente exista en MicroValidar
        Paciente paciente = null;
        try {
            paciente = rt.getForObject("http://MicroValidar/buscarPorId/" + ent.getIdPaciente(), Paciente.class);
        } catch (Exception e) {
            System.err.println("Error al validar paciente: " + e.getMessage());
        }
        if (paciente == null) {
            return null;
        }

        // Obtener datos del Médico desde MicroCatalogoMedico
        Medico medico = null;
        if (horario.getIdMedico() != null) {
            try {
                medico = rt.getForObject("http://MicroCatalogoMedico/buscar/" + horario.getIdMedico(), Medico.class);
            } catch (Exception e) {
                System.err.println("Error al obtener medico: " + e.getMessage());
            }
        }

        // Grabar cita en MicroRegistroCita (idCita autogenerado, estado RESERVADA automático)
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

        return new Salida(
            citaGuardada.getIdCita(),
            citaGuardada.getEstado(),
            paciente,
            medico,
            horario
        );
    }

    // 2. Consultar Salida consolidada de una cita médica por su ID
    public Salida verSalida(Long idCita) {
        if (idCita == null || idCita <= 0) {
            return null;
        }
        try {
            CitaMedica cita = rt.getForObject("http://MicroRegistroCita/buscar/" + idCita, CitaMedica.class);
            if (cita == null) {
                return null;
            }
            return armarSalida(cita);
        } catch (Exception e) {
            System.err.println("Error al buscar cita por ID: " + e.getMessage());
            return null;
        }
    }

    // 3. Listar todas las citas médicas consolidadas
    public List<Salida> listarTodas() {
        List<Salida> resultado = new ArrayList<>();
        try {
            CitaMedica[] citas = rt.getForObject("http://MicroRegistroCita/listar", CitaMedica[].class);
            if (citas != null) {
                for (CitaMedica c : citas) {
                    Salida s = armarSalida(c);
                    if (s != null) {
                        resultado.add(s);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar citas: " + e.getMessage());
        }
        return resultado;
    }

    // Consultas de apoyo de médicos y especialidades
    public List<String> listarEspecialidades() {
        try {
            String[] esp = rt.getForObject("http://MicroCatalogoMedico/listarEspecialidades", String[].class);
            return esp != null ? Arrays.asList(esp) : List.of();
        } catch (Exception e) {
            System.err.println("Error al listar especialidades: " + e.getMessage());
            return List.of();
        }
    }

    public List<Medico> listarMedicosPorEspecialidad(String esp) {
        try {
            Medico[] medicos = rt.getForObject("http://MicroCatalogoMedico/listarPorEspecialidad/" + esp, Medico[].class);
            return medicos != null ? Arrays.asList(medicos) : List.of();
        } catch (Exception e) {
            System.err.println("Error al listar medicos por especialidad: " + e.getMessage());
            return List.of();
        }
    }

    // Helper privado para componer Salida usando exclusivamente los IDs guardados en CitaMedica
    private Salida armarSalida(CitaMedica cita) {
        if (cita == null) {
            return null;
        }

        Paciente paciente = null;
        if (cita.getIdPaciente() != null) {
            try {
                paciente = rt.getForObject("http://MicroValidar/buscarPorId/" + cita.getIdPaciente(), Paciente.class);
            } catch (Exception ignored) {}
        }

        Horario horario = null;
        if (cita.getIdHorario() != null) {
            try {
                horario = rt.getForObject("http://MicroCatalogoHorario/buscar/" + cita.getIdHorario(), Horario.class);
            } catch (Exception ignored) {}
        }

        Medico medico = null;
        if (horario != null && horario.getIdMedico() != null) {
            try {
                medico = rt.getForObject("http://MicroCatalogoMedico/buscar/" + horario.getIdMedico(), Medico.class);
            } catch (Exception ignored) {}
        }

        return new Salida(
            cita.getIdCita(),
            cita.getEstado(),
            paciente,
            medico,
            horario
        );
    }
}
