package es.uvigo.mei.incendios.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Area implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdArea;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Riesgo riesgo;

    @ManyToOne
    private Brigada brigada;


    public Area() {
    }

    public Area(String nombre, Riesgo riesgo, Brigada brigada) {
        this.nombre = nombre;
        this.riesgo = riesgo;
        this.brigada = brigada;
    }

    public Long getIdArea() {
        return IdArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }

    public Brigada getBrigada() {
        return brigada;
    }

    public void setBrigada(Brigada brigada) {
        this.brigada = brigada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IdArea == null) ? 0 : IdArea.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((riesgo == null) ? 0 : riesgo.hashCode());
        result = prime * result + ((brigada == null) ? 0 : brigada.hashCode());
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
        Area other = (Area) obj;
        if (IdArea == null) {
            if (other.IdArea != null)
                return false;
        } else if (!IdArea.equals(other.IdArea))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (riesgo != other.riesgo)
            return false;
        if (brigada == null) {
            if (other.brigada != null)
                return false;
        } else if (!brigada.equals(other.brigada))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Area { IdArea = " + IdArea + ", nombre = " + nombre + ", riesgo = " + riesgo + ", brigada = " + brigada + " }";
    }
        
}
