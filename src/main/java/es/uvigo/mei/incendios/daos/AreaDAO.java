package es.uvigo.mei.incendios.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.uvigo.mei.incendios.entidades.Area;
import es.uvigo.mei.incendios.entidades.Riesgo;

public interface AreaDAO extends JpaRepository<Area, Long> {
    public List<Area> findByNombre(String nombre);
    public List<Area> findByRiesgo(Riesgo riesgo);

    @Query("SELECT a FROM Area a JOIN a.brigada b WHERE b.IdBrigada = :idBrigada")
    public List<Area> findByIdBrigada(@Param("idBrigada") Long idBrigada);

}
