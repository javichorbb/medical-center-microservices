package pe.edu.utp.microcestaanalisis.repositorio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import pe.edu.utp.microcestaanalisis.dto.AnalisisCestaDTO;

@Repository
public class CestaAnalisisRepository {

    private final ConcurrentHashMap<String,
            ConcurrentHashMap<Long, AnalisisCestaDTO>> cestas =
            new ConcurrentHashMap<>();

    // Agregar o actualizar un analisis
    public AnalisisCestaDTO agregar(
            String operacionId,
            AnalisisCestaDTO item) {

        AnalisisCestaDTO copia =
            new AnalisisCestaDTO(
                item.getIdAnalisis(),
                item.getIndicacion());

        cestas.computeIfAbsent(
            operacionId,
            clave -> new ConcurrentHashMap<>()
        ).put(copia.getIdAnalisis(), copia);

        return copia;
    }

    // Quitar un analisis
    public boolean quitar(
            String operacionId,
            Long idAnalisis) {

        ConcurrentHashMap<Long, AnalisisCestaDTO> cesta =
                cestas.get(operacionId);

        if (cesta == null) {
            return false;
        }

        return cesta.remove(idAnalisis) != null;
    }

    // Listar los analisis de una operacion
    public List<AnalisisCestaDTO> listar(
            String operacionId) {

        ConcurrentHashMap<Long, AnalisisCestaDTO> cesta =
                cestas.get(operacionId);

        if (cesta == null) {
            return List.of();
        }

        List<AnalisisCestaDTO> resultado =
                new ArrayList<>();

        for (AnalisisCestaDTO item : cesta.values()) {
            resultado.add(new AnalisisCestaDTO(
                item.getIdAnalisis(),
                item.getIndicacion()));
        }

        resultado.sort(
            Comparator.comparing(
                AnalisisCestaDTO::getIdAnalisis));

        return resultado;
    }

    // Vaciar una cesta
    public void limpiar(String operacionId) {
        cestas.remove(operacionId);
    }
}
