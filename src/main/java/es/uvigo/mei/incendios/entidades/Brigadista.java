package es.uvigo.mei.incendios.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Brigadista implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdBrigadista;

    private String DNI;
    private String nombre;
    private String apellido;
    private String telefono;


    public Brigadista() {
    }

    public Brigadista(String DNI, String nombre, String apellido, String telefono) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Long getIdBrigadista() {
        return IdBrigadista;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IdBrigadista == null) ? 0 : IdBrigadista.hashCode());
        result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
        Brigadista other = (Brigadista) obj;
        if (IdBrigadista == null) {
            if (other.IdBrigadista != null)
                return false;
        } else if (!IdBrigadista.equals(other.IdBrigadista))
            return false;
        if (DNI == null) {
            if (other.DNI != null)
                return false;
        } else if (!DNI.equals(other.DNI))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (apellido == null) {
            if (other.apellido != null)
                return false;
        } else if (!apellido.equals(other.apellido))
            return false;
        if (telefono == null) {
            if (other.telefono != null)
                return false;
        } else if (!telefono.equals(other.telefono))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Brigadista { IdBrigadista = " + IdBrigadista + ", DNI = " + DNI + ", nombre = " + nombre + ", apellido = "
                + apellido + ", telefono = " + telefono + " }";
    }
    
}
