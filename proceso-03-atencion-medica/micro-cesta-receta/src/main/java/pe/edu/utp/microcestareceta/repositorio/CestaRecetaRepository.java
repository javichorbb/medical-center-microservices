package pe.edu.utp.microcestareceta.repositorio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import pe.edu.utp.microcestareceta.dto.MedicamentoCestaDTO;

@Repository
public class CestaRecetaRepository {

    private final ConcurrentHashMap<String,
            ConcurrentHashMap<Long, MedicamentoCestaDTO>> cestas =
            new ConcurrentHashMap<>();

    // Agregar o actualizar un medicamento
    public MedicamentoCestaDTO agregar(
            String operacionId,
            MedicamentoCestaDTO item) {

        MedicamentoCestaDTO copia =
            new MedicamentoCestaDTO(
                item.getIdMedicamento(),
                item.getDosis(),
                item.getCantidad());

        cestas.computeIfAbsent(
            operacionId,
            clave -> new ConcurrentHashMap<>()
        ).put(copia.getIdMedicamento(), copia);

        return copia;
    }

    // Quitar un medicamento de una cesta
    public boolean quitar(
            String operacionId,
            Long idMedicamento) {

        ConcurrentHashMap<Long, MedicamentoCestaDTO> cesta =
                cestas.get(operacionId);

        if (cesta == null) {
            return false;
        }

        return cesta.remove(idMedicamento) != null;
    }

    // Obtener medicamentos seleccionados
    public List<MedicamentoCestaDTO> listar(
            String operacionId) {

        ConcurrentHashMap<Long, MedicamentoCestaDTO> cesta =
                cestas.get(operacionId);

        if (cesta == null) {
            return List.of();
        }

        List<MedicamentoCestaDTO> resultado =
                new ArrayList<>();

        for (MedicamentoCestaDTO item : cesta.values()) {
            resultado.add(new MedicamentoCestaDTO(
                item.getIdMedicamento(),
                item.getDosis(),
                item.getCantidad()));
        }

        resultado.sort(
            Comparator.comparing(
                MedicamentoCestaDTO::getIdMedicamento));

        return resultado;
    }

    // Vaciar una cesta completa
    public void limpiar(String operacionId) {
        cestas.remove(operacionId);
    }
}
