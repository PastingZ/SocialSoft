package pe.edu.practica.model;

import java.util.Date;

public class Usuario {
    private int id;
    private String nombreCompleto;
    private String dni;
    private int edad;
    private String ciudad;
    private Date fechaNacimiento;
    private String telefono;
    private String correo;
    private String profesion;

    // Constructor vacío (Obligatorio para que funcione bien con los servicios REST/SOAP)
    public Usuario() {
    }

    // Constructor con todos los parámetros
    public Usuario(int id, String nombreCompleto, String dni, int edad, String ciudad, Date fechaNacimiento, String telefono, String correo, String profesion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.edad = edad;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.profesion = profesion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}