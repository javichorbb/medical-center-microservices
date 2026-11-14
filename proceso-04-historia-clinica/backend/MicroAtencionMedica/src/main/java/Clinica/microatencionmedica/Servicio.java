package Clinica.microatencionmedica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    @Transactional(readOnly = true)
    public AtencionMedica buscar(Long idAtencion) {
        AtencionMedica at = repositorio.findById(idAtencion).orElse(null);
        inicializarDetalles(at);
        return at;
    }

    @Transactional(readOnly = true)
    public List<AtencionMedica> listar() {
        List<AtencionMedica> lista = repositorio.findAll();
        lista.forEach(this::inicializarDetalles);
        return lista;
    }

    @Transactional(readOnly = true)
    public List<AtencionMedica> buscarPorHistoria(Long idHistoria) {
        List<AtencionMedica> lista = repositorio.findByIdHistoria(idHistoria);
        lista.forEach(this::inicializarDetalles);
        return lista;
    }

    private void inicializarDetalles(AtencionMedica at) {
        if (at != null) {
            at.getRecetaMedica().size();
            at.getAnalisisClinicos().size();
        }
    }
}
