package es.uvigo.mei.incendios.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.mei.incendios.entidades.Area;
import es.uvigo.mei.incendios.entidades.Riesgo;

public interface AreaService {
    public Area crear(Area area);
    public Area modificar(Area area);
    public void eliminar(Area area);
    public Optional<Area> buscarPorId(Long id);
    public List<Area> buscarTodas();
    public List<Area> buscarPorNombre(String nombre);
    public List<Area> buscarPorRiesgo(Riesgo riesgo);
    public List<Area> buscarPorBrigada(Long idBrigada);
}