package clinica.microhistoriaclinicacompuesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // 1. Grabar / Aperturar Historia Clínica (valida Enfermera y Paciente, crea Historia y retorna Salida consolidada)
    public Salida grabarHistoria(Entrada ent) {
        if (ent == null || ent.getIdEnfermera() == null || ent.getIdPaciente() == null) {
            return null;
        }

        // Validar que la Enfermera exista en MicroValidarEnfermera
        Enfermera enfermera = null;
        try {
            enfermera = rt.getForObject("http://MICROVALIDARENFERMERA/enfermeras/" + ent.getIdEnfermera(), Enfermera.class);
        } catch (Exception e) {
            System.err.println("Error al validar enfermera: " + e.getMessage());
        }
        if (enfermera == null) {
            return null;
        }

        // Validar que el Paciente exista en MicroPaciente
        Paciente paciente = null;
        try {
            paciente = rt.getForObject("http://MICROPACIENTE/pacientes/" + ent.getIdPaciente(), Paciente.class);
        } catch (Exception e) {
            System.err.println("Error al validar paciente: " + e.getMessage());
        }
        if (paciente == null) {
            return null;
        }

        // Si el paciente ya tiene historia clínica aperturada, retornarla consolidada
        try {
            Historia existente = rt.getForObject("http://MICROHISTORIA/historia/paciente/" + ent.getIdPaciente(), Historia.class);
            if (existente != null && existente.getIdHistoria() != null) {
                return armarSalida(existente, null, paciente);
            }
        } catch (Exception ignored) {}

        // Registrar nueva Historia en MicroHistoria (idHistoria y fechaApertura automáticos)
        Historia nuevaHistoria = new Historia(null, ent.getIdEnfermera(), ent.getIdPaciente(), LocalDate.now());
        Historia historiaGuardada = null;
        try {
            historiaGuardada = rt.postForObject(
                "http://MICROHISTORIA/historia/registrar",
                nuevaHistoria,
                Historia.class
            );
        } catch (Exception e) {
            System.err.println("Error al registrar historia: " + e.getMessage());
            return null;
        }

        if (historiaGuardada == null) {
            return null;
        }

        return armarSalida(historiaGuardada, enfermera, paciente);
    }

    // 2. Consultar Historia Clínica consolidada por su ID de Historia
    public Salida verSalida(Long idHistoria) {
        if (idHistoria == null || idHistoria <= 0) {
            return null;
        }

        Historia historia = null;
        try {
            historia = rt.getForObject("http://MICROHISTORIA/historia/buscar/" + idHistoria, Historia.class);
        } catch (Exception e) {
            System.err.println("Error al buscar historia por ID: " + e.getMessage());
            return null;
        }

        if (historia == null) {
            return null;
        }

        return armarSalida(historia, null, null);
    }

    // 3. Consultar Historia Clínica consolidada por ID de Paciente
    public Salida buscarPorPaciente(Long idPaciente) {
        if (idPaciente == null || idPaciente <= 0) {
            return null;
        }

        Historia historia = null;
        try {
            historia = rt.getForObject("http://MICROHISTORIA/historia/paciente/" + idPaciente, Historia.class);
        } catch (Exception e) {
            System.err.println("Error al buscar historia por paciente: " + e.getMessage());
            return null;
        }

        if (historia == null) {
            return null;
        }

        return armarSalida(historia, null, null);
    }

    // 4. Listar Todas las Historias Clínicas consolidadas
    public List<Salida> listarTodas() {
        List<Salida> resultado = new ArrayList<>();
        try {
            Historia[] historias = rt.getForObject("http://MICROHISTORIA/historia/listar", Historia[].class);
            if (historias != null) {
                for (Historia h : historias) {
                    Salida s = armarSalida(h, null, null);
                    if (s != null) {
                        resultado.add(s);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar todas las historias: " + e.getMessage());
        }
        return resultado;
    }

    // Helper para componer Salida usando exclusivamente los IDs almacenados en Historia
    private Salida armarSalida(Historia historia, Enfermera enfermeraPreCargada, Paciente pacientePreCargado) {
        Enfermera enfermera = enfermeraPreCargada;
        if (enfermera == null && historia.getIdEnfermera() != null) {
            try {
                enfermera = rt.getForObject("http://MICROVALIDARENFERMERA/enfermeras/" + historia.getIdEnfermera(), Enfermera.class);
            } catch (Exception ignored) {}
        }

        Paciente paciente = pacientePreCargado;
        if (paciente == null && historia.getIdPaciente() != null) {
            try {
                paciente = rt.getForObject("http://MICROPACIENTE/pacientes/" + historia.getIdPaciente(), Paciente.class);
            } catch (Exception ignored) {}
        }

        List<AtencionMedica> atenciones = List.of();
        if (historia.getIdHistoria() != null) {
            try {
                AtencionMedica[] arr = rt.getForObject(
                    "http://MICROATENCIONMEDICA/atencion/historia/" + historia.getIdHistoria(),
                    AtencionMedica[].class
                );
                if (arr != null) {
                    atenciones = Arrays.asList(arr);
                }
            } catch (Exception ignored) {}
        }

        return new Salida(
            historia.getIdHistoria(),
            historia.getFechaApertura(),
            enfermera,
            paciente,
            atenciones
        );
    }
}
