package ProgramacionHorario.MicroCompProgramacionHorario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // Paso 1: Autenticación / Login de Usuario Asistencial o Administrador
    @PostMapping("/validarUsuario")
    public SalidaUsuario validarUsuario(@RequestBody EntradaUsuario ent) {
        return servicio.validarUsuario(ent);
    }

    // Paso 2: Consultar Lista de Especialidades Médicas
    @GetMapping("/medicos/especialidades")
    public List<String> listarEspecialidades() {
        return servicio.listarEspecialidades();
    }

    // Paso 3: Consultar Médicos Programados por Especialidad (con su programación y franjas)
    @GetMapping("/medicos/especialidad/{especialidad}")
    public List<SalidaMedicoProgramado> listarMedicosPorEspecialidad(@PathVariable String especialidad) {
        return servicio.listarMedicosPorEspecialidad(especialidad);
    }

    // Paso 4: Registrar Cabecera de Programación de Horario (Devuelve datos con ID y Médico)
    @PostMapping("/programaciones/grabar")
    public SalidaProgramacion registrarProgramacion(@RequestBody EntradaProgramacion ent) {
        return servicio.registrarProgramacion(ent);
    }

    // Paso 5: Registrar Franja Horaria (1 a 1, máx 20 minutos; Devuelve Franja con Programación y Médico)
    @PostMapping("/franjas/grabar")
    public SalidaFranja registrarFranja(@RequestBody EntradaFranja ent) {
        return servicio.registrarFranja(ent);
    }

    // Paso 6: Visualizar Programación por ID (Incluye Médico y Lista de Franjas)
    @GetMapping("/programaciones/{id}")
    public SalidaDetalleProgramacion buscarProgramacionPorId(@PathVariable Long id) {
        return servicio.buscarProgramacionPorId(id);
    }

    // Paso 7: Editar Programación por ID (Devuelve Programación actualizada con Médico y Franjas)
    @PutMapping("/programaciones/{id}")
    public SalidaDetalleProgramacion actualizarProgramacion(@PathVariable Long id, @RequestBody EntradaProgramacion ent) {
        return servicio.actualizarProgramacion(id, ent);
    }

    // Paso 8: Editar Franja Horaria por ID (Devuelve Franja actualizada con Programación y Médico)
    @PutMapping("/franjas/{id}")
    public SalidaFranja actualizarFranja(@PathVariable Long id, @RequestBody EntradaFranja ent) {
        return servicio.actualizarFranja(id, ent);
    }

    // Paso 9: Consultar Ficha Completa del Médico (Datos del Médico + Todas sus Programaciones y Franjas)
    @PostMapping("/consultarFichaMedicoProgramado")
    public SalidaMedicoProgramado consultarFichaMedicoProgramado(@RequestBody EntradaMedico ent) {
        return servicio.consultarFichaMedicoProgramado(ent);
    }
}
