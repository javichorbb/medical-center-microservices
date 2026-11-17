package pe.edu.utp.microcestaanalisis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {

    private final List<AnalisisCesta> cesta = new ArrayList<>();

    public List<AnalisisCesta> getCesta() {
        return cesta;
    }
}
