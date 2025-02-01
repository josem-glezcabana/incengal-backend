package es.uvigo.mei.incendios.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.mei.incendios.entidades.Brigada;

public interface BrigadaService {
    public Brigada crear(Brigada brigada);
    public Brigada modificar(Brigada brigada);
    public void eliminar(Brigada brigada);
    public Optional<Brigada> buscarPorId(Long id);
    public List<Brigada> buscarTodas();
    public List<Brigada> buscarPorNombre(String nombre);
    public List<Brigada> buscarPorBase(String base);
}
