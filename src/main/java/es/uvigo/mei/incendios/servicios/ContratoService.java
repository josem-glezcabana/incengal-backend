package es.uvigo.mei.incendios.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import es.uvigo.mei.incendios.entidades.Contrato;

public interface ContratoService {
    public Contrato crear(Contrato contrato);
    public Contrato modificar(Contrato contrato);
    public void eliminar(Contrato contrato);
    public Optional<Contrato> buscarPorId(Long id);
    public List<Contrato> buscarTodas();
    public List<Contrato> buscarPorFechaContratacion(Date fechaContratacion);
    public List<Contrato> buscarPorFechaFin(Date fechaFin);
    public List<Contrato> buscarPorBrigada(Long idBrigada);
    public List<Contrato> buscarPorBrigadista(Long idBrigadista);
}
