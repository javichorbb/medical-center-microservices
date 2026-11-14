package ProgramacionHorario.MicroCompProgramacionHorario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // 1. Login / Validar Usuario en MicroValidarUsuario
    public SalidaUsuario validarUsuario(EntradaUsuario ent) {
        if (ent == null || ent.getUsername() == null || ent.getPassword() == null) {
            return null;
        }
        try {
            Usuario u = rt.postForObject(
                "http://MICROVALIDARUSUARIO/validar",
                ent,
                Usuario.class
            );
            if (u != null && u.getIdUsuario() != null) {
                return new SalidaUsuario(u.getIdUsuario(), u.getNombre(), u.getUsername(), u.getRol());
            }
        } catch (Exception e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        }
        return null;
    }

    // 2. Listar Especialidades Médicas desde MicroMedico
    public List<String> listarEspecialidades() {
        try {
            String[] esp = rt.getForObject("http://MICROMEDICO/medicos/especialidades", String[].class);
            return esp != null ? Arrays.asList(esp) : List.of();
        } catch (Exception e) {
            System.err.println("Error al listar especialidades: " + e.getMessage());
            return List.of();
        }
    }

    // 3. Consultar Médicos por Especialidad con su Programación y Franjas Horarias
    public List<SalidaMedicoProgramado> listarMedicosPorEspecialidad(String especialidad) {
        List<SalidaMedicoProgramado> resultado = new ArrayList<>();
        try {
            Medico[] medicos = rt.getForObject(
                "http://MICROMEDICO/medicos/especialidad/" + especialidad,
                Medico[].class
            );
            if (medicos != null) {
                for (Medico m : medicos) {
                    List<SalidaProgramacion> progsConFranjas = obtenerProgramacionesConFranjasPorMedico(m.getIdMedico());
                    resultado.add(new SalidaMedicoProgramado(
                        m.getIdMedico(),
                        m.getNombre(),
                        m.getEspecialidad(),
                        progsConFranjas
                    ));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar medicos por especialidad: " + e.getMessage());
        }
        return resultado;
    }

    // 4. Crear Cabecera de Programación de Horario
    public SalidaProgramacion registrarProgramacion(EntradaProgramacion ent) {
        if (ent == null || ent.getIdMedico() == null) {
            return null;
        }

        // Obtener datos del médico
        Medico medico = null;
        try {
            medico = rt.getForObject("http://MICROMEDICO/medicos/" + ent.getIdMedico(), Medico.class);
        } catch (Exception e) {
            System.err.println("Error al obtener medico: " + e.getMessage());
        }

        // Guardar cabecera de programación en MicroProgramacionHorario
        ProgramacionHorario nuevaProg = new ProgramacionHorario(
            null,
            ent.getIdMedico(),
            ent.getFecha(),
            ent.getConsultorio(),
            "ACTIVO"
        );

        ProgramacionHorario progGuardada = null;
        try {
            progGuardada = rt.postForObject(
                "http://MICROPROGRAMACIONHORARIO/programaciones/grabar",
                nuevaProg,
                ProgramacionHorario.class
            );
        } catch (Exception e) {
            System.err.println("Error al registrar programacion: " + e.getMessage());
            return null;
        }

        if (progGuardada == null) {
            return null;
        }

        return new SalidaProgramacion(
            progGuardada.getIdProgramacion(),
            progGuardada.getIdMedico(),
            progGuardada.getFecha(),
            progGuardada.getConsultorio(),
            progGuardada.getEstado(),
            List.of()
        );
    }

    // 5. Crear Franja Horaria (1 a 1, máx 20 min) y retornar SalidaProgramacion actualizada
    public SalidaProgramacion registrarFranja(EntradaFranja ent) {
        if (ent == null || ent.getIdProgramacion() == null) {
            return null;
        }

        FranjaHoraria nuevaFranja = new FranjaHoraria(
            null,
            ent.getIdProgramacion(),
            ent.getHoraInicio(),
            ent.getHoraFin(),
            "DISPONIBLE"
        );

        FranjaHoraria franjaGuardada = null;
        try {
            franjaGuardada = rt.postForObject(
                "http://MICROFRANJAHORARIA/franjas/grabar",
                nuevaFranja,
                FranjaHoraria.class
            );
        } catch (Exception e) {
            System.err.println("Error al registrar franja horaria: " + e.getMessage());
            return null;
        }

        if (franjaGuardada == null) {
            return null;
        }

        return buscarProgramacionPorId(franjaGuardada.getIdProgramacion());
    }

    // 6. Visualizar Programación por ID (con Médico y Franjas)
    public SalidaProgramacion buscarProgramacionPorId(Long idProgramacion) {
        if (idProgramacion == null) {
            return null;
        }

        ProgramacionHorario prog = null;
        try {
            prog = rt.getForObject(
                "http://MICROPROGRAMACIONHORARIO/programaciones/" + idProgramacion,
                ProgramacionHorario.class
            );
        } catch (Exception e) {
            System.err.println("Error al buscar programacion: " + e.getMessage());
            return null;
        }

        if (prog == null) {
            return null;
        }

        // Médico
        Medico medico = null;
        if (prog.getIdMedico() != null) {
            try {
                medico = rt.getForObject("http://MICROMEDICO/medicos/" + prog.getIdMedico(), Medico.class);
            } catch (Exception e) {
                System.err.println("Error al obtener medico: " + e.getMessage());
            }
        }

        // Franjas de esa programación
        List<FranjaHoraria> franjas = List.of();
        try {
            FranjaHoraria[] franjasArray = rt.getForObject(
                "http://MICROFRANJAHORARIA/franjas/programacion/" + idProgramacion,
                FranjaHoraria[].class
            );
            if (franjasArray != null) {
                franjas = Arrays.asList(franjasArray);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener franjas: " + e.getMessage());
        }

        return new SalidaProgramacion(
            prog.getIdProgramacion(),
            prog.getIdMedico(),
            prog.getFecha(),
            prog.getConsultorio(),
            prog.getEstado(),
            franjas
        );
    }

    // 7. Editar Programación por ID (Devuelve la programación actualizada con médico y franjas)
    public SalidaProgramacion actualizarProgramacion(Long idProgramacion, EntradaProgramacion ent) {
        if (idProgramacion == null || ent == null) {
            return null;
        }

        String estado = (ent.getEstado() != null && !ent.getEstado().isBlank()) ? ent.getEstado() : "ACTIVO";
        ProgramacionHorario progActualizar = new ProgramacionHorario(
            idProgramacion,
            ent.getIdMedico(),
            ent.getFecha(),
            ent.getConsultorio(),
            estado
        );

        try {
            HttpEntity<ProgramacionHorario> requestEntity = new HttpEntity<>(progActualizar);
            ResponseEntity<ProgramacionHorario> response = rt.exchange(
                "http://MICROPROGRAMACIONHORARIO/programaciones/" + idProgramacion,
                HttpMethod.PUT,
                requestEntity,
                ProgramacionHorario.class
            );
        } catch (Exception e) {
            System.err.println("Error al actualizar programacion: " + e.getMessage());
            return null;
        }

        // Retorna la vista completa y actualizada de la programación
        return buscarProgramacionPorId(idProgramacion);
    }

    // 8. Editar Franja Horaria por ID (Devuelve SalidaProgramacion actualizada con médico y franjas)
    public SalidaProgramacion actualizarFranja(Long idFranja, EntradaFranja ent) {
        if (idFranja == null || ent == null) {
            return null;
        }

        FranjaHoraria franjaActualizar = new FranjaHoraria(
            idFranja,
            ent.getIdProgramacion(),
            ent.getHoraInicio(),
            ent.getHoraFin(),
            "DISPONIBLE"
        );

        FranjaHoraria franjaActualizada = null;
        try {
            HttpEntity<FranjaHoraria> requestEntity = new HttpEntity<>(franjaActualizar);
            ResponseEntity<FranjaHoraria> response = rt.exchange(
                "http://MICROFRANJAHORARIA/franjas/" + idFranja,
                HttpMethod.PUT,
                requestEntity,
                FranjaHoraria.class
            );
            franjaActualizada = response.getBody();
        } catch (Exception e) {
            System.err.println("Error al actualizar franja: " + e.getMessage());
            return null;
        }

        if (franjaActualizada == null) {
            return null;
        }

        return buscarProgramacionPorId(franjaActualizada.getIdProgramacion());
    }

    // 9. Consultar Ficha Completa del Médico (Datos del Médico + Programaciones + Franjas)
    public SalidaMedicoProgramado consultarFichaMedicoProgramado(Long idMedico) {
        if (idMedico == null) {
            return null;
        }

        Medico medico = null;
        try {
            medico = rt.getForObject("http://MICROMEDICO/medicos/" + idMedico, Medico.class);
        } catch (Exception e) {
            System.err.println("Error al obtener medico: " + e.getMessage());
            return null;
        }

        if (medico == null) {
            return null;
        }

        List<SalidaProgramacion> listaProgramaciones = obtenerProgramacionesConFranjasPorMedico(idMedico);
        return new SalidaMedicoProgramado(
            medico.getIdMedico(),
            medico.getNombre(),
            medico.getEspecialidad(),
            listaProgramaciones
        );
    }

    // Método auxiliar privado para reutilizar la consulta jerárquica de programaciones y franjas
    private List<SalidaProgramacion> obtenerProgramacionesConFranjasPorMedico(Long idMedico) {
        List<SalidaProgramacion> listaProgramaciones = new ArrayList<>();
        try {
            ProgramacionHorario[] progs = rt.getForObject(
                "http://MICROPROGRAMACIONHORARIO/programaciones/medico/" + idMedico,
                ProgramacionHorario[].class
            );

            if (progs != null) {
                for (ProgramacionHorario p : progs) {
                    List<FranjaHoraria> franjasProg = List.of();
                    try {
                        FranjaHoraria[] franjasArray = rt.getForObject(
                            "http://MICROFRANJAHORARIA/franjas/programacion/" + p.getIdProgramacion(),
                            FranjaHoraria[].class
                        );
                        if (franjasArray != null) {
                            franjasProg = Arrays.asList(franjasArray);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al obtener franjas de programacion " + p.getIdProgramacion() + ": " + e.getMessage());
                    }

                    listaProgramaciones.add(new SalidaProgramacion(
                        p.getIdProgramacion(),
                        p.getIdMedico(),
                        p.getFecha(),
                        p.getConsultorio(),
                        p.getEstado(),
                        franjasProg
                    ));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar programaciones del medico " + idMedico + ": " + e.getMessage());
        }
        return listaProgramaciones;
    }
}
