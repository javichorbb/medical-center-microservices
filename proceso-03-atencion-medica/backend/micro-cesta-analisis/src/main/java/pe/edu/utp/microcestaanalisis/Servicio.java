package pe.edu.utp.microcestaanalisis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    private List<AnalisisCesta> cesta = new ArrayList<>();

    @Autowired
    private Repositorio repo;

    public List<AnalisisCesta> agregar(AnalisisCesta item) {
        cesta = repo.getCesta();
        cesta.removeIf(a -> a.getIdAnalisis() != null && a.getIdAnalisis().equals(item.getIdAnalisis()));
        cesta.add(item);
        return cesta;
    }

    public List<AnalisisCesta> quitar(Long idAnalisis) {
        cesta = repo.getCesta();
        cesta.removeIf(a -> a.getIdAnalisis() != null && a.getIdAnalisis().equals(idAnalisis));
        return cesta;
    }

    public List<AnalisisCesta> listar() {
        cesta = repo.getCesta();
        return cesta;
    }

    public void limpiar() {
        cesta = repo.getCesta();
        cesta.clear();
    }
}
