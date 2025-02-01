package es.uvigo.mei.incendios.daos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.uvigo.mei.incendios.entidades.EstadoIncendio;
import es.uvigo.mei.incendios.entidades.Incendio;

public interface IncendioDAO extends JpaRepository<Incendio, Long> {
    public List<Incendio> findByFecha(Date fecha);
    public List<Incendio> findByDescripcion(String descripcion);
    public List<Incendio> findByEstado(EstadoIncendio estado);

    @Query("SELECT i FROM Incendio i JOIN i.brigadasAtienden b WHERE b.IdBrigada = :idBrigada")
    public List<Incendio> findByBrigadasAtienden_IdBrigada(@Param("idBrigada") Long idBrigada);


    @Query("SELECT i FROM Incendio i JOIN i.areasExtension a WHERE a.IdArea = :idArea")
    public List<Incendio> findByAreasExtension_Id(@Param("idArea") Long idArea);
}