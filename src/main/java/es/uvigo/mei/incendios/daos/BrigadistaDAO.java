package es.uvigo.mei.incendios.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.mei.incendios.entidades.Brigadista;
import java.util.List;
import java.util.Optional;


public interface BrigadistaDAO extends JpaRepository<Brigadista, Long> {
    public Optional<Brigadista> findByDNI(String dni);
    public List<Brigadista> findByNombre(String nombre);
    public List<Brigadista> findByApellido(String apellido);
    public List<Brigadista> findByTelefono(String telefono);
}
