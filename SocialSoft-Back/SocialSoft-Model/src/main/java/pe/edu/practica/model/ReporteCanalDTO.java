package pe.edu.practica.model;

import java.io.Serializable;

public class ReporteCanalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreCanal;
    private int cantidadSuscripciones;

    public ReporteCanalDTO() {}

    public ReporteCanalDTO(String nombreCanal, int cantidadSuscripciones) {
        this.nombreCanal = nombreCanal;
        this.cantidadSuscripciones = cantidadSuscripciones;
    }

    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    public int getCantidadSuscripciones() {
        return cantidadSuscripciones;
    }

    public void setCantidadSuscripciones(int cantidadSuscripciones) {
        this.cantidadSuscripciones = cantidadSuscripciones;
    }
}
