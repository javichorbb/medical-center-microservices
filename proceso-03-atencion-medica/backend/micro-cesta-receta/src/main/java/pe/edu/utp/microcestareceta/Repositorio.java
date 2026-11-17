package pe.edu.utp.microcestareceta;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {

    private final List<MedicamentoCesta> cesta = new ArrayList<>();

    public List<MedicamentoCesta> getCesta() {
        return cesta;
    }
}
