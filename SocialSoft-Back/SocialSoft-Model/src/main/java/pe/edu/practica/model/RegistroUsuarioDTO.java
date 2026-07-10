package pe.edu.practica.model;

import java.io.Serializable;

public class RegistroUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private DispositivoUsuario dispositivo;

    public RegistroUsuarioDTO() {}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DispositivoUsuario getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoUsuario dispositivo) {
        this.dispositivo = dispositivo;
    }
}
