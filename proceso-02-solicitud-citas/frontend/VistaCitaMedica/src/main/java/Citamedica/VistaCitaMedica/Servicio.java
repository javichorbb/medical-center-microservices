package Citamedica.VistaCitaMedica;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    private final String URL_COMPUESTO = "http://localhost:9999";

    @Autowired
    private RestTemplate rt;

    // 1. Paso 1: Listar todas las especialidades disponibles
    public List<String> listarEspecialidades() {
        try {
            String[] esp = rt.getForObject(URL_COMPUESTO + "/especialidades", String[].class);
            return esp != null ? Arrays.asList(esp) : List.of();
        } catch (Exception e) {
            System.err.println("Error al obtener especialidades: " + e.getMessage());
            return List.of();
        }
    }

    // 2. Paso 2: Listar médicos especialistas por especialidad seleccionada
    public List<Medico> listarMedicosPorEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            return List.of();
        }
        try {
            Medico[] medicos = rt.getForObject(URL_COMPUESTO + "/medicos/" + especialidad, Medico[].class);
            return medicos != null ? Arrays.asList(medicos) : List.of();
        } catch (Exception e) {
            System.err.println("Error al obtener médicos por especialidad: " + e.getMessage());
            return List.of();
        }
    }

    // 3. Paso 3: Obtener ficha unificada del médico (datos + turnos)
    public SalidaMedico consultarFichaMedico(Long idMedico) {
        if (idMedico == null) {
            return null;
        }
        try {
            EntradaMedico ent = new EntradaMedico(idMedico);
            return rt.postForObject(URL_COMPUESTO + "/consultarFichaMedico", ent, SalidaMedico.class);
        } catch (Exception e) {
            System.err.println("Error al consultar ficha del medico: " + e.getMessage());
            return null;
        }
    }

    // 4. Paso 4: Validar Paciente por DNI
    public Paciente validarPaciente(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return null;
        }
        try {
            EntradaPaciente ent = new EntradaPaciente(dni.trim());
            return rt.postForObject(URL_COMPUESTO + "/validarPaciente", ent, Paciente.class);
        } catch (Exception e) {
            System.err.println("Error al validar paciente: " + e.getMessage());
            return null;
        }
    }

    // 5. Paso 5: Registrar Cita Médica (recibe Comprobante Detallado SalidaCita)
    public SalidaCita registrarCita(Long idHorario, Long idPaciente) {
        if (idHorario == null || idPaciente == null) {
            return null;
        }
        try {
            EntradaCita ent = new EntradaCita(idHorario, idPaciente);
            return rt.postForObject(URL_COMPUESTO + "/registrarCita", ent, SalidaCita.class);
        } catch (Exception e) {
            System.err.println("Error al registrar cita: " + e.getMessage());
            return null;
        }
    }
}