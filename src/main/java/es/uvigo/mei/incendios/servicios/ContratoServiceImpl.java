package es.uvigo.mei.incendios.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.incendios.daos.ContratoDAO;
import es.uvigo.mei.incendios.entidades.Contrato;

@Service
public class ContratoServiceImpl implements ContratoService {
    @Autowired
    ContratoDAO contratoDAO;

    @Override
    public Contrato crear(Contrato contrato) {
        return contratoDAO.save(contrato);
    }

    @Override
    public Contrato modificar(Contrato contrato) {
        return contratoDAO.save(contrato);
    }

    @Override
    public void eliminar(Contrato contrato) {
        contratoDAO.delete(contrato);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contrato> buscarPorId(Long id) {
        return contratoDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrato> buscarTodas() {
        return contratoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrato> buscarPorFechaContratacion(Date fechaContratacion) {
        return contratoDAO.findByFechaContratacion(fechaContratacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrato> buscarPorFechaFin(Date fechaFin) {
        return contratoDAO.findByFechaFin(fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrato> buscarPorBrigada(Long idBrigada) {
        return contratoDAO.findByIdBrigada(idBrigada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrato> buscarPorBrigadista(Long idBrigadista) {
        return contratoDAO.findByIdBrigadista(idBrigadista);
    }
    
}
