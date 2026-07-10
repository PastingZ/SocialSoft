package pe.edu.practica.model;

public class PlanSuscripcion {
    private int id;
    private String nombre;
    private double costoMensual;

    public PlanSuscripcion() {
    }

    public PlanSuscripcion(int id, String nombre, double costoMensual) {
        this.id = id;
        this.nombre = nombre;
        this.costoMensual = costoMensual;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getCostoMensual() { return costoMensual; }
    public void setCostoMensual(double costoMensual) { this.costoMensual = costoMensual; }
}