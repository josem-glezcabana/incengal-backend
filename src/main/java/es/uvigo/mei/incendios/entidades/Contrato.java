package es.uvigo.mei.incendios.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contrato implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdContrato;

    private Date fechaContratacion;
    private Date fechaFin;

    @ManyToOne
    private Brigada brigada;

    @ManyToOne
    private Brigadista brigadista;

    public Contrato() {
    }

    public Contrato(Date fechaContratacion, Brigada brigada, Brigadista brigadista) {
        this.fechaContratacion = fechaContratacion;
        this.brigada = brigada;
        this.brigadista = brigadista;
    }

    public Contrato(Date fechaContratacion, Date fechaFin, Brigada brigada, Brigadista brigadista) {
        this.fechaContratacion = fechaContratacion;
        this.fechaFin = fechaFin;
        this.brigada = brigada;
        this.brigadista = brigadista;
    }

    public Long getIdContrato() {
        return IdContrato;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Brigada getBrigada() {
        return brigada;
    }

    public void setBrigada(Brigada brigada) {
        this.brigada = brigada;
    }

    public Brigadista getBrigadista() {
        return brigadista;
    }

    public void setBrigadista(Brigadista brigadista) {
        this.brigadista = brigadista;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IdContrato == null) ? 0 : IdContrato.hashCode());
        result = prime * result + ((fechaContratacion == null) ? 0 : fechaContratacion.hashCode());
        result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
        result = prime * result + ((brigada == null) ? 0 : brigada.hashCode());
        result = prime * result + ((brigadista == null) ? 0 : brigadista.hashCode());
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
        Contrato other = (Contrato) obj;
        if (IdContrato == null) {
            if (other.IdContrato != null)
                return false;
        } else if (!IdContrato.equals(other.IdContrato))
            return false;
        if (fechaContratacion == null) {
            if (other.fechaContratacion != null)
                return false;
        } else if (!fechaContratacion.equals(other.fechaContratacion))
            return false;
        if (fechaFin == null) {
            if (other.fechaFin != null)
                return false;
        } else if (!fechaFin.equals(other.fechaFin))
            return false;
        if (brigada == null) {
            if (other.brigada != null)
                return false;
        } else if (!brigada.equals(other.brigada))
            return false;
        if (brigadista == null) {
            if (other.brigadista != null)
                return false;
        } else if (!brigadista.equals(other.brigadista))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Contrato { IdContrato = " + IdContrato + ", fechaContratacion = " + fechaContratacion + ", fechaFin = "
                + fechaFin + ", brigada = " + brigada + ", brigadista = " + brigadista + " }";
    }

    
}
