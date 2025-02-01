package es.uvigo.mei.incendios.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.incendios.daos.AreaDAO;
import es.uvigo.mei.incendios.entidades.Area;
import es.uvigo.mei.incendios.entidades.Riesgo;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaDAO areaDAO;

    @Override
    @Transactional
    public Area crear(Area area) {
        return areaDAO.save(area);
    }

    @Override
    @Transactional
    public Area modificar(Area area) {
        return areaDAO.save(area);
    }

    @Override
    @Transactional
    public void eliminar(Area area) {
        areaDAO.delete(area);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Area> buscarPorId(Long id) {
        return areaDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Area> buscarTodas() {
        return areaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Area> buscarPorNombre(String nombre) {
        return areaDAO.findByNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Area> buscarPorRiesgo(Riesgo riesgo) {
        return areaDAO.findByRiesgo(riesgo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Area> buscarPorBrigada(Long idBrigada) {
        return areaDAO.findByIdBrigada(idBrigada);
    }
}
