package ProgramacionHorario.MicroCompProgramacionHorario;

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

    // 1. Grabar nueva Programación de Horario (valida Usuario y Médico, crea cabecera ACTIVO y retorna Salida con franjas vacías)
    public Salida grabarProgramacion(Entrada ent) {
        if (ent == null || ent.getIdUsuario() == null || ent.getIdMedico() == null
                || ent.getFecha() == null || ent.getConsultorio() == null || ent.getConsultorio().isBlank()) {
            return null;
        }

        // Validar que el Usuario administrativo exista en MicroValidarUsuario
        Usuario usuario = null;
        try {
            usuario = rt.getForObject("http://MICROVALIDARUSUARIO/usuarios/" + ent.getIdUsuario(), Usuario.class);
        } catch (Exception e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        }
        if (usuario == null) {
            return null;
        }

        // Validar que el Médico exista en MicroMedico
        Medico medico = null;
        try {
            medico = rt.getForObject("http://MICROMEDICO/medicos/" + ent.getIdMedico(), Medico.class);
        } catch (Exception e) {
            System.err.println("Error al validar medico: " + e.getMessage());
        }
        if (medico == null) {
            return null;
        }

        // Registrar cabecera en MicroProgramacionHorario (idProgramacion autogenerado, estado ACTIVO automático)
        ProgramacionHorario nuevaProg = new ProgramacionHorario(
            null,
            ent.getIdUsuario(),
            ent.getIdMedico(),
            ent.getFecha(),
            ent.getConsultorio().trim(),
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
            System.err.println("Error al grabar programacion: " + e.getMessage());
            return null;
        }

        if (progGuardada == null) {
            return null;
        }

        // Armar la Salida completa (incluyendo cualquier franja si existiera para ese idProgramacion)
        return armarSalida(progGuardada, usuario, medico);
    }

    // 2. Consultar Salida consolidada de una programación existente por su ID
    public Salida verSalida(Long idProgramacion) {
        if (idProgramacion == null || idProgramacion <= 0) {
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

        return armarSalida(prog);
    }

    // 3. Listar Programaciones por Especialidad Médica (cada una con sus 3 objetos completos)
    public List<Salida> listarPorEspecialidad(String especialidad) {
        List<Salida> resultado = new ArrayList<>();
        try {
            Medico[] medicos = rt.getForObject(
                "http://MICROMEDICO/medicos/especialidad/" + especialidad,
                Medico[].class
            );
            if (medicos != null) {
                for (Medico m : medicos) {
                    ProgramacionHorario[] progs = rt.getForObject(
                        "http://MICROPROGRAMACIONHORARIO/programaciones/medico/" + m.getIdMedico(),
                        ProgramacionHorario[].class
                    );
                    if (progs != null) {
                        for (ProgramacionHorario p : progs) {
                            Salida s = armarSalida(p);
                            if (s != null) {
                                resultado.add(s);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar por especialidad: " + e.getMessage());
        }
        return resultado;
    }

    // 4. Listar Todas las Programaciones existentes en el sistema
    public List<Salida> listarTodas() {
        List<Salida> resultado = new ArrayList<>();
        try {
            ProgramacionHorario[] progs = rt.getForObject(
                "http://MICROPROGRAMACIONHORARIO/programaciones/listar",
                ProgramacionHorario[].class
            );
            if (progs != null) {
                for (ProgramacionHorario p : progs) {
                    Salida s = armarSalida(p);
                    if (s != null) {
                        resultado.add(s);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar todas las programaciones: " + e.getMessage());
        }
        return resultado;
    }

    // Helper para componer Salida usando exclusivamente los IDs almacenados en ProgramacionHorario
    private Salida armarSalida(ProgramacionHorario prog) {
        return armarSalida(prog, null, null);
    }

    private Salida armarSalida(ProgramacionHorario prog, Usuario usuarioPreCargado, Medico medicoPreCargado) {
        Usuario usuario = usuarioPreCargado;
        if (usuario == null && prog.getIdUsuario() != null) {
            try {
                usuario = rt.getForObject("http://MICROVALIDARUSUARIO/usuarios/" + prog.getIdUsuario(), Usuario.class);
            } catch (Exception ignored) {}
        }

        Medico medico = medicoPreCargado;
        if (medico == null && prog.getIdMedico() != null) {
            try {
                medico = rt.getForObject("http://MICROMEDICO/medicos/" + prog.getIdMedico(), Medico.class);
            } catch (Exception ignored) {}
        }

        List<FranjaHoraria> franjas = List.of();
        try {
            FranjaHoraria[] franjasArray = rt.getForObject(
                "http://MICROFRANJAHORARIA/franjas/programacion/" + prog.getIdProgramacion(),
                FranjaHoraria[].class
            );
            if (franjasArray != null) {
                franjas = Arrays.asList(franjasArray);
            }
        } catch (Exception ignored) {}

        return new Salida(
            prog.getIdProgramacion(),
            prog.getFecha(),
            prog.getConsultorio(),
            prog.getEstado(),
            usuario,
            medico,
            franjas
        );
    }
}
