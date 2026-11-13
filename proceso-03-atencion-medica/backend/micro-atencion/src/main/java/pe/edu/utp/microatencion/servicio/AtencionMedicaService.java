package pe.edu.utp.microatencion.servicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.utp.microatencion.entidad.AtencionMedica;
import pe.edu.utp.microatencion.entidad.DetalleReceta;
import pe.edu.utp.microatencion.entidad.DetalleAnalisis;

import pe.edu.utp.microatencion.dto.RegistroAtencionDTO;
import pe.edu.utp.microatencion.dto.ResultadoAtencionDTO;
import pe.edu.utp.microatencion.dto.MedicamentoItemDTO;
import pe.edu.utp.microatencion.dto.AnalisisItemDTO;

import pe.edu.utp.microatencion.repositorio.AtencionMedicaRepository;
import pe.edu.utp.microatencion.repositorio.DetalleRecetaRepository;
import pe.edu.utp.microatencion.repositorio.DetalleAnalisisRepository;

@Service
public class AtencionMedicaService {

    @Autowired
    private AtencionMedicaRepository repo;

    @Autowired
    private DetalleRecetaRepository recetaRepo;

    @Autowired
    private DetalleAnalisisRepository analisisRepo;

    public AtencionMedica grabar(AtencionMedica atencion) {
        return repo.save(atencion);
    }

    public AtencionMedica buscar(Long idAtencion) {
        return repo.findById(idAtencion).orElse(null);
    }

    public List<AtencionMedica> listar() {
        return repo.findAll();
    }

    // Registrar atención médica completa con receta y análisis
    @Transactional
    public ResultadoAtencionDTO grabarCompleta(RegistroAtencionDTO dto) {

        if (dto == null
                || dto.getIdMedico() == null || dto.getIdMedico() <= 0
                || dto.getIdHistoria() == null || dto.getIdHistoria() <= 0) {
            throw new IllegalArgumentException("Médico e Historia Clínica son obligatorios");
        }

        if (dto.getDiagnostico() == null || dto.getDiagnostico().isBlank()
                || dto.getTratamiento() == null || dto.getTratamiento().isBlank()) {
            throw new IllegalArgumentException("Diagnóstico y tratamiento son obligatorios");
        }

        List<MedicamentoItemDTO> medicamentos =
                dto.getListaMedicamentos() == null ? List.of() : dto.getListaMedicamentos();

        List<AnalisisItemDTO> analisis =
                dto.getListaAnalisis() == null ? List.of() : dto.getListaAnalisis();

        // Validar medicamentos
        Set<Long> codigosMedicamentos = new HashSet<>();
        for (MedicamentoItemDTO item : medicamentos) {
            if (item == null || item.getIdMedicamento() == null || item.getIdMedicamento() <= 0
                    || item.getCantidad() == null || item.getCantidad() <= 0
                    || item.getDosis() == null || item.getDosis().isBlank()) {
                throw new IllegalArgumentException("Datos de medicamento inválidos");
            }
            if (!codigosMedicamentos.add(item.getIdMedicamento())) {
                throw new IllegalArgumentException("Medicamento repetido en la receta: " + item.getIdMedicamento());
            }
        }

        // Validar análisis
        Set<Long> codigosAnalisis = new HashSet<>();
        for (AnalisisItemDTO item : analisis) {
            if (item == null || item.getIdAnalisis() == null || item.getIdAnalisis() <= 0
                    || item.getIndicacion() == null || item.getIndicacion().isBlank()) {
                throw new IllegalArgumentException("Datos de análisis inválidos");
            }
            if (!codigosAnalisis.add(item.getIdAnalisis())) {
                throw new IllegalArgumentException("Análisis repetido en la orden: " + item.getIdAnalisis());
            }
        }

        // Guardar atención principal
        AtencionMedica atencion = new AtencionMedica();
        atencion.setIdCita(dto.getIdCita());
        atencion.setIdHistoria(dto.getIdHistoria());
        atencion.setIdMedico(dto.getIdMedico());
        atencion.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());
        atencion.setHora(dto.getHora() != null ? dto.getHora() : LocalTime.now());
        atencion.setDiagnostico(dto.getDiagnostico());
        atencion.setTratamiento(dto.getTratamiento());

        atencion = repo.save(atencion);
        Long idAtencion = atencion.getIdAtencion();

        // Guardar receta
        List<DetalleReceta> recetaGuardada = new ArrayList<>();
        for (MedicamentoItemDTO item : medicamentos) {
            DetalleReceta detalle = new DetalleReceta(
                    idAtencion,
                    item.getIdMedicamento(),
                    item.getDosis(),
                    item.getCantidad()
            );
            recetaGuardada.add(recetaRepo.save(detalle));
        }

        // Guardar análisis
        List<DetalleAnalisis> analisisGuardados = new ArrayList<>();
        for (AnalisisItemDTO item : analisis) {
            DetalleAnalisis detalle = new DetalleAnalisis(
                    idAtencion,
                    item.getIdAnalisis(),
                    item.getIndicacion()
            );
            analisisGuardados.add(analisisRepo.save(detalle));
        }

        return new ResultadoAtencionDTO(atencion, recetaGuardada, analisisGuardados);
    }

    // Consultar una atención con receta y análisis
    @Transactional(readOnly = true)
    public ResultadoAtencionDTO buscarCompleta(Long idAtencion) {
        AtencionMedica atencion = repo.findById(idAtencion).orElse(null);
        if (atencion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La atención médica no existe");
        }

        List<DetalleReceta> receta = recetaRepo.findByIdAtencion(idAtencion);
        List<DetalleAnalisis> analisis = analisisRepo.findByIdAtencion(idAtencion);

        return new ResultadoAtencionDTO(atencion, receta, analisis);
    }

    // Consultar todas las atenciones pertenecientes a una Historia Clínica (Requerido por Proceso 4)
    @Transactional(readOnly = true)
    public List<ResultadoAtencionDTO> buscarPorHistoria(Long idHistoria) {
        List<AtencionMedica> atenciones = repo.findByIdHistoria(idHistoria);
        List<ResultadoAtencionDTO> resultado = new ArrayList<>();

        for (AtencionMedica atencion : atenciones) {
            Long idAtencion = atencion.getIdAtencion();
            List<DetalleReceta> receta = recetaRepo.findByIdAtencion(idAtencion);
            List<DetalleAnalisis> analisis = analisisRepo.findByIdAtencion(idAtencion);
            resultado.add(new ResultadoAtencionDTO(atencion, receta, analisis));
        }

        return resultado;
    }

    // Consultar atención por cita
    @Transactional(readOnly = true)
    public ResultadoAtencionDTO buscarPorCita(Long idCita) {
        AtencionMedica atencion = repo.findByIdCita(idCita).orElse(null);
        if (atencion == null) {
            return null;
        }

        Long idAtencion = atencion.getIdAtencion();
        List<DetalleReceta> receta = recetaRepo.findByIdAtencion(idAtencion);
        List<DetalleAnalisis> analisis = analisisRepo.findByIdAtencion(idAtencion);

        return new ResultadoAtencionDTO(atencion, receta, analisis);
    }

    // Listar todas las atenciones completas
    @Transactional(readOnly = true)
    public List<ResultadoAtencionDTO> listarCompletas() {
        List<AtencionMedica> atenciones = repo.findAll();
        List<ResultadoAtencionDTO> resultado = new ArrayList<>();

        for (AtencionMedica atencion : atenciones) {
            Long idAtencion = atencion.getIdAtencion();
            List<DetalleReceta> receta = recetaRepo.findByIdAtencion(idAtencion);
            List<DetalleAnalisis> analisis = analisisRepo.findByIdAtencion(idAtencion);
            resultado.add(new ResultadoAtencionDTO(atencion, receta, analisis));
        }

        return resultado;
    }
}
