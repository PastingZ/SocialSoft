package pe.edu.practica.model;

import java.util.Date;

public class Canal {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private int numeroSeguidores;
    private String categoria;

    public Canal() {
    }

    public Canal(int id, String nombre, String descripcion, Date fechaCreacion, int numeroSeguidores, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.numeroSeguidores = numeroSeguidores;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public int getNumeroSeguidores() { return numeroSeguidores; }
    public void setNumeroSeguidores(int numeroSeguidores) { this.numeroSeguidores = numeroSeguidores; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}