package pe.edu.practica.model;

import java.util.Date;

public class PagoSuscripcion {
    private int id;
    private UsuarioCanalSuscripcion suscripcion;
    private MetodoPago metodoPago;
    private double monto;
    private Date fechaPago;
    private String estado;

    public PagoSuscripcion() {}

    public PagoSuscripcion(int id, UsuarioCanalSuscripcion suscripcion, MetodoPago metodoPago, double monto, Date fechaPago, String estado) {
        this.id = id;
        this.suscripcion = suscripcion;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public UsuarioCanalSuscripcion getSuscripcion() { return suscripcion; }
    public void setSuscripcion(UsuarioCanalSuscripcion suscripcion) { this.suscripcion = suscripcion; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}