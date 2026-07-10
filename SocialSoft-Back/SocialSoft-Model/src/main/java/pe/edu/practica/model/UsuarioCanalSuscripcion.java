package pe.edu.practica.model;

import java.util.Date;

public class UsuarioCanalSuscripcion {
    private int id;
    private Usuario usuario;
    private Canal canal;
    private PlanSuscripcion planSuscripcion;
    private Date fechaRegistro;
    private String estado;

    public UsuarioCanalSuscripcion() {}

    public UsuarioCanalSuscripcion(int id, Usuario usuario, Canal canal, PlanSuscripcion planSuscripcion, Date fechaRegistro, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.canal = canal;
        this.planSuscripcion = planSuscripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Canal getCanal() { return canal; }
    public void setCanal(Canal canal) { this.canal = canal; }
    public PlanSuscripcion getPlanSuscripcion() { return planSuscripcion; }
    public void setPlanSuscripcion(PlanSuscripcion planSuscripcion) { this.planSuscripcion = planSuscripcion; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}