package pe.edu.utp.microcestaanalisis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {

    private final ConcurrentHashMap<String, ConcurrentHashMap<Long, AnalisisCesta>> cestas =
            new ConcurrentHashMap<>();

    public AnalisisCesta agregar(String operacionId, AnalisisCesta item) {
        AnalisisCesta copia = new AnalisisCesta(
                item.getIdAnalisis(),
                item.getIndicacion());

        cestas.computeIfAbsent(operacionId, clave -> new ConcurrentHashMap<>())
                .put(copia.getIdAnalisis(), copia);

        return copia;
    }

    public boolean quitar(String operacionId, Long idAnalisis) {
        ConcurrentHashMap<Long, AnalisisCesta> cesta = cestas.get(operacionId);
        if (cesta == null) {
            return false;
        }
        return cesta.remove(idAnalisis) != null;
    }

    public List<AnalisisCesta> listar(String operacionId) {
        ConcurrentHashMap<Long, AnalisisCesta> cesta = cestas.get(operacionId);
        if (cesta == null) {
            return List.of();
        }

        List<AnalisisCesta> resultado = new ArrayList<>();
        for (AnalisisCesta item : cesta.values()) {
            resultado.add(new AnalisisCesta(
                    item.getIdAnalisis(),
                    item.getIndicacion()));
        }

        resultado.sort(Comparator.comparing(AnalisisCesta::getIdAnalisis));
        return resultado;
    }

    public void limpiar(String operacionId) {
        cestas.remove(operacionId);
    }
}
