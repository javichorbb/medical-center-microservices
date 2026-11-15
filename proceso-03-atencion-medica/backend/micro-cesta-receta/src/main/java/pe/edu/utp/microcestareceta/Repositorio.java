package pe.edu.utp.microcestareceta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {

    private final ConcurrentHashMap<String, ConcurrentHashMap<Long, MedicamentoCesta>> cestas =
            new ConcurrentHashMap<>();

    public MedicamentoCesta agregar(String operacionId, MedicamentoCesta item) {
        MedicamentoCesta copia = new MedicamentoCesta(
                item.getIdMedicamento(),
                item.getDosis(),
                item.getCantidad());

        cestas.computeIfAbsent(operacionId, clave -> new ConcurrentHashMap<>())
                .put(copia.getIdMedicamento(), copia);

        return copia;
    }

    public boolean quitar(String operacionId, Long idMedicamento) {
        ConcurrentHashMap<Long, MedicamentoCesta> cesta = cestas.get(operacionId);
        if (cesta == null) {
            return false;
        }
        return cesta.remove(idMedicamento) != null;
    }

    public List<MedicamentoCesta> listar(String operacionId) {
        ConcurrentHashMap<Long, MedicamentoCesta> cesta = cestas.get(operacionId);
        if (cesta == null) {
            return List.of();
        }

        List<MedicamentoCesta> resultado = new ArrayList<>();
        for (MedicamentoCesta item : cesta.values()) {
            resultado.add(new MedicamentoCesta(
                    item.getIdMedicamento(),
                    item.getDosis(),
                    item.getCantidad()));
        }

        resultado.sort(Comparator.comparing(MedicamentoCesta::getIdMedicamento));
        return resultado;
    }

    public void limpiar(String operacionId) {
        cestas.remove(operacionId);
    }
}
