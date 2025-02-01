package es.uvigo.mei.incendios.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.incendios.daos.BrigadistaDAO;
import es.uvigo.mei.incendios.entidades.Brigadista;

@Service
public class BrigadistaServiceImpl implements BrigadistaService {
    @Autowired
    BrigadistaDAO brigadistaDAO;

    @Override
    public Brigadista crear(Brigadista brigadista) {
        return brigadistaDAO.save(brigadista);
    }

    @Override
    public Brigadista modificar(Brigadista brigadista) {
        return brigadistaDAO.save(brigadista);
    }

    @Override
    public void eliminar(Brigadista brigadista) {
        brigadistaDAO.delete(brigadista);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brigadista> buscarPorId(Long id) {
        return brigadistaDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brigadista> buscarTodas() {
        return brigadistaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brigadista> buscarPorDNI(String DNI) {
        return brigadistaDAO.findByDNI(DNI);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brigadista> buscarPorNombre(String nombre) {
        return brigadistaDAO.findByNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brigadista> buscarPorApellido(String apellido) {
        return brigadistaDAO.findByApellido(apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brigadista> buscarPorTelefono(String telefono) {
        return brigadistaDAO.findByTelefono(telefono);
    }

    
}
