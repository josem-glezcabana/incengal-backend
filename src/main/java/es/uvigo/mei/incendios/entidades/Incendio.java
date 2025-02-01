package es.uvigo.mei.incendios.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Incendio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdIncendio;

    private Date fecha;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoIncendio estado;

    @ManyToMany
    private List<Brigada> brigadasAtienden = new ArrayList<>();

    @ManyToMany
    private List<Area> areasExtension = new ArrayList<>();

    public Incendio() {
    }

    public Incendio(Date fecha, String descripcion, EstadoIncendio estado, List<Brigada> brigadas, List<Area> areas) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
        this.brigadasAtienden = brigadas;
        this.areasExtension = areas;
    }

    public Long getIdIncendio() {
        return IdIncendio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoIncendio getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncendio estado) {
        this.estado = estado;
    }

    public List<Brigada> getBrigadasAtienden() {
        return brigadasAtienden;
    }

    public void setBrigadasAtienden(List<Brigada> brigadas) {
        this.brigadasAtienden = brigadas;
    }

    public List<Area> getAreasExtension() {
        return areasExtension;
    }

    public void setAreasExtension(List<Area> areas) {
        this.areasExtension = areas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IdIncendio == null) ? 0 : IdIncendio.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((brigadasAtienden == null) ? 0 : brigadasAtienden.hashCode());
        result = prime * result + ((areasExtension == null) ? 0 : areasExtension.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Incendio other = (Incendio) obj;
        if (IdIncendio == null) {
            if (other.IdIncendio != null)
                return false;
        } else if (!IdIncendio.equals(other.IdIncendio))
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (estado != other.estado)
            return false;
        if (brigadasAtienden == null) {
            if (other.brigadasAtienden != null)
                return false;
        } else if (!brigadasAtienden.equals(other.brigadasAtienden))
            return false;
        if (areasExtension == null) {
            if (other.areasExtension != null)
                return false;
        } else if (!areasExtension.equals(other.areasExtension))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Incendio { IdIncendio = " + IdIncendio + ", fecha = " + fecha + ", descripción = " + descripcion
                + ", estado = " + estado + ", brigadas = " + brigadasAtienden + ", areas = " + areasExtension + " }";
    }

}
