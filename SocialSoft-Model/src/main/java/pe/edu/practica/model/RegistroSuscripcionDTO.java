package pe.edu.practica.model;

import java.io.Serializable;

public class RegistroSuscripcionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UsuarioCanalSuscripcion suscripcion;
    private PagoSuscripcion pago;

    public RegistroSuscripcionDTO() {}

    public UsuarioCanalSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(UsuarioCanalSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public PagoSuscripcion getPago() {
        return pago;
    }

    public void setPago(PagoSuscripcion pago) {
        this.pago = pago;
    }
}
