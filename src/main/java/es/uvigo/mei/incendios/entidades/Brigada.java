package es.uvigo.mei.incendios.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Brigada implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdBrigada;

    private String nombre;
    private String base;


    public Brigada() {
    }

    public Brigada(String nombre, String base) {
        this.nombre = nombre;
        this.base = base;
    }

    public Long getIdBrigada() {
        return IdBrigada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public int hashCode() {
        if (this.IdBrigada != null) {
            return this.IdBrigada.hashCode();
        }

        final int prime = 31;
        int hash = 1;
        hash = prime * hash + ((nombre == null) ? 0 : nombre.hashCode());
        hash = prime * hash + ((base == null) ? 0 : base.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Brigada other = (Brigada) obj;
        if (IdBrigada == null) {
            if (other.IdBrigada != null)
                return false;
        } else if (!IdBrigada.equals(other.IdBrigada))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (base == null) {
            if (other.base != null)
                return false;
        } else if (!base.equals(other.base))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Brigada { IdBrigada = " + IdBrigada + ", nombre = " + nombre + ", base = " + base + " }";
    }
    
}
