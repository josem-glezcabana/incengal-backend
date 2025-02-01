package es.uvigo.mei.incendios.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.mei.incendios.entidades.Brigadista;

public interface BrigadistaService {
    public Brigadista crear(Brigadista brigadista);
    public Brigadista modificar(Brigadista brigadista);
    public void eliminar(Brigadista brigadista);
    public Optional<Brigadista> buscarPorId(Long id);
    public List<Brigadista> buscarTodas();
    public Optional<Brigadista> buscarPorDNI(String DNI);
    public List<Brigadista> buscarPorNombre(String nombre);
    public List<Brigadista> buscarPorApellido(String apellido);
    public List<Brigadista> buscarPorTelefono(String telefono);
}
