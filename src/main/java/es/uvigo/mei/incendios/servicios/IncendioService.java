package es.uvigo.mei.incendios.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import es.uvigo.mei.incendios.entidades.EstadoIncendio;
import es.uvigo.mei.incendios.entidades.Incendio;

public interface IncendioService {
    public Incendio crear(Incendio incendio);
    public Incendio modificar(Incendio incendio);
    public void eliminar(Incendio incendio);
    public Optional<Incendio> buscarPorId(Long id);
    public List<Incendio> buscarTodos();
    public List<Incendio> buscarPorFecha(Date fecha);
    public List<Incendio> buscarPorDescripcion(String descripcion);
    public List<Incendio> buscarPorEstado(EstadoIncendio estado);
    public List<Incendio> buscarPorIdArea(Long idArea);
    public List<Incendio> buscarPorIdBrigada(Long idBrigada);
}
