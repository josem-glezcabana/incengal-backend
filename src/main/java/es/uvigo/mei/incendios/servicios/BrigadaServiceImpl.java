package es.uvigo.mei.incendios.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.incendios.daos.BrigadaDAO;
import es.uvigo.mei.incendios.entidades.Brigada;

@Service
public class BrigadaServiceImpl implements BrigadaService {
    @Autowired
    BrigadaDAO brigadaDAO;

    @Override
    public Brigada crear(Brigada brigada) {
        return brigadaDAO.save(brigada);
    }

    @Override
    public Brigada modificar(Brigada brigada) {
        return brigadaDAO.save(brigada);
    }

    @Override
    public void eliminar(Brigada brigada) {
        brigadaDAO.delete(brigada);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brigada> buscarPorId(Long id) {
        return brigadaDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brigada> buscarTodas() {
        return brigadaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brigada> buscarPorNombre(String nombre) {
        return brigadaDAO.findByNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brigada> buscarPorBase(String base) {
        return brigadaDAO.findByBase(base);
    }
    
}
