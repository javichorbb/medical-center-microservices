package pe.edu.utp.microatencion;

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

@Service
public class Servicio {

    @Autowired
    private Repositorio repo;

    @Autowired
    private RepositorioReceta repoReceta;

    @Autowired
    private RepositorioAnalisis repoAnalisis;

    @Transactional
    public ResultadoAtencion grabar(EntradaAtencion dto) {
        if (dto == null
                || dto.getIdMedico() == null || dto.getIdMedico() <= 0
                || dto.getIdHistoria() == null || dto.getIdHistoria() <= 0) {
            throw new IllegalArgumentException("Médico e Historia Clínica son obligatorios");
        }
        if (dto.getDiagnostico() == null || dto.getDiagnostico().isBlank()
                || dto.getTratamiento() == null || dto.getTratamiento().isBlank()) {
            throw new IllegalArgumentException("Diagnóstico y tratamiento son obligatorios");
        }

        List<MedicamentoItem> medicamentos =
                dto.getListaMedicamentos() == null ? List.of() : dto.getListaMedicamentos();
        List<AnalisisItem> analisis =
                dto.getListaAnalisis() == null ? List.of() : dto.getListaAnalisis();

        Set<Long> codigosMedicamentos = new HashSet<>();
        for (MedicamentoItem item : medicamentos) {
            if (item == null || item.getIdMedicamento() == null || item.getIdMedicamento() <= 0
                    || item.getCantidad() == null || item.getCantidad() <= 0
                    || item.getDosis() == null || item.getDosis().isBlank()) {
                throw new IllegalArgumentException("Datos de medicamento inválidos");
            }
            if (!codigosMedicamentos.add(item.getIdMedicamento())) {
                throw new IllegalArgumentException("Medicamento repetido en la receta: " + item.getIdMedicamento());
            }
        }

        Set<Long> codigosAnalisis = new HashSet<>();
        for (AnalisisItem item : analisis) {
            if (item == null || item.getIdAnalisis() == null || item.getIdAnalisis() <= 0
                    || item.getIndicacion() == null || item.getIndicacion().isBlank()) {
                throw new IllegalArgumentException("Datos de análisis inválidos");
            }
            if (!codigosAnalisis.add(item.getIdAnalisis())) {
                throw new IllegalArgumentException("Análisis repetido en la orden: " + item.getIdAnalisis());
            }
        }

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

        List<DetalleReceta> recetaGuardada = new ArrayList<>();
        for (MedicamentoItem item : medicamentos) {
            DetalleReceta detalle = new DetalleReceta(
                    idAtencion,
                    item.getIdMedicamento(),
                    item.getDosis(),
                    item.getCantidad());
            recetaGuardada.add(repoReceta.save(detalle));
        }

        List<DetalleAnalisis> analisisGuardados = new ArrayList<>();
        for (AnalisisItem item : analisis) {
            DetalleAnalisis detalle = new DetalleAnalisis(
                    idAtencion,
                    item.getIdAnalisis(),
                    item.getIndicacion());
            analisisGuardados.add(repoAnalisis.save(detalle));
        }

        return new ResultadoAtencion(atencion, recetaGuardada, analisisGuardados);
    }

    @Transactional(readOnly = true)
    public ResultadoAtencion buscar(Long idAtencion) {
        AtencionMedica atencion = repo.findById(idAtencion).orElse(null);
        if (atencion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La atención médica no existe");
        }
        return armarResultado(atencion);
    }

    @Transactional(readOnly = true)
    public List<ResultadoAtencion> buscarPorHistoria(Long idHistoria) {
        return armarListaResultados(repo.findByIdHistoria(idHistoria));
    }

    @Transactional(readOnly = true)
    public List<ResultadoAtencion> listar() {
        return armarListaResultados(repo.findAll());
    }

    private ResultadoAtencion armarResultado(AtencionMedica atencion) {
        Long idAtencion = atencion.getIdAtencion();
        List<DetalleReceta> receta = repoReceta.findByIdAtencion(idAtencion);
        List<DetalleAnalisis> analisis = repoAnalisis.findByIdAtencion(idAtencion);
        return new ResultadoAtencion(atencion, receta, analisis);
    }

    private List<ResultadoAtencion> armarListaResultados(List<AtencionMedica> atenciones) {
        List<ResultadoAtencion> resultado = new ArrayList<>();
        for (AtencionMedica atencion : atenciones) {
            resultado.add(armarResultado(atencion));
        }
        return resultado;
    }
}
