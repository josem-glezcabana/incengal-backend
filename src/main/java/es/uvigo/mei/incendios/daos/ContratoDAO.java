package es.uvigo.mei.incendios.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.uvigo.mei.incendios.entidades.Brigada;
import es.uvigo.mei.incendios.entidades.Brigadista;
import es.uvigo.mei.incendios.entidades.Contrato;
import java.util.List;
import java.util.Date;


public interface ContratoDAO extends JpaRepository<Contrato, Long> {
    public List<Contrato> findByFechaContratacion(Date fechaContratacion);
    public List<Contrato> findByFechaFin(Date fechaFin);

    public List<Contrato> findByBrigada(Brigada brigada);
    public List<Contrato> findByBrigadista(Brigadista brigadista);

    @Query("SELECT c FROM Contrato c JOIN c.brigada b WHERE b.IdBrigada = :idBrigada")
    public List<Contrato> findByIdBrigada(@Param("idBrigada") Long idBrigada);

    @Query("SELECT c FROM Contrato c JOIN c.brigadista br WHERE br.IdBrigadista = :idBrigadista")
    public List<Contrato> findByIdBrigadista(@Param("idBrigadista") Long idBrigadista);    
}
