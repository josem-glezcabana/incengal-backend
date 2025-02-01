package es.uvigo.mei.incendios.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.incendios.daos.IncendioDAO;
import es.uvigo.mei.incendios.entidades.EstadoIncendio;
import es.uvigo.mei.incendios.entidades.Incendio;

@Service
public class IncendioServiceImpl implements IncendioService {
    @Autowired
    IncendioDAO incendioDAO;

    @Override
    public Incendio crear(Incendio incendio) {
        return incendioDAO.save(incendio);
    }

    @Override
    public Incendio modificar(Incendio incendio) {
        return incendioDAO.save(incendio);
    }

    @Override
    public void eliminar(Incendio incendio) {
        incendioDAO.delete(incendio);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Incendio> buscarPorId(Long id) {
        return incendioDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incendio> buscarTodos() {
        return incendioDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incendio> buscarPorFecha(Date fecha) {
        return incendioDAO.findByFecha(fecha);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incendio> buscarPorDescripcion(String descripcion) {
        return incendioDAO.findByDescripcion(descripcion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incendio> buscarPorEstado(EstadoIncendio estado) {
        return incendioDAO.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incendio> buscarPorIdArea(Long idArea) {
        return incendioDAO.findByAreasExtension_Id(idArea);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incendio> buscarPorIdBrigada(Long idBrigada) {
        return incendioDAO.findByBrigadasAtienden_IdBrigada(idBrigada);
    }
}
