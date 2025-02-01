package es.uvigo.mei.incendios.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.mei.incendios.entidades.Brigada;

public interface BrigadaDAO extends JpaRepository<Brigada, Long> {
    public List<Brigada> findByNombre(String nombre);
    public List<Brigada> findByBase(String base);
}
