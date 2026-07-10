package pe.edu.practica.model;

public class DispositivoUsuario {
    private int id;
    private Usuario usuario; // Relación con la clase Usuario que ya hiciste
    private String nombre;
    private String tipo;
    private String sistemaOperativo;
    private boolean activo; // En MySQL es TINYINT(1), en Java lo mapeamos como boolean

    public DispositivoUsuario() {
    }

    public DispositivoUsuario(int id, Usuario usuario, String nombre, String tipo, String sistemaOperativo, boolean activo) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
        this.sistemaOperativo = sistemaOperativo;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getSistemaOperativo() { return sistemaOperativo; }
    public void setSistemaOperativo(String sistemaOperativo) { this.sistemaOperativo = sistemaOperativo; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}