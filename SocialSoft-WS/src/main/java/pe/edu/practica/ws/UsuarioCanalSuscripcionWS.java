package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.UsuarioCanalSuscripcionBL;
import pe.edu.practica.model.UsuarioCanalSuscripcion;
import pe.edu.practica.model.PagoSuscripcion;

import java.util.List;

@WebService(serviceName = "UsuarioCanalSuscripcionWS")
public class UsuarioCanalSuscripcionWS {
    private UsuarioCanalSuscripcionBL suscripcionBL;

    public UsuarioCanalSuscripcionWS() {
        this.suscripcionBL = new UsuarioCanalSuscripcionBL();
    }

    @WebMethod(operationName = "insertarSuscripcion")
    public int insertar(@WebParam(name = "suscripcion") UsuarioCanalSuscripcion suscripcion) {
        return suscripcionBL.insertar(suscripcion);
    }

    @WebMethod(operationName = "registrarSuscripcionConPago")
    public int registrarSuscripcionConPago(@WebParam(name = "suscripcion") UsuarioCanalSuscripcion suscripcion, @WebParam(name = "pago") PagoSuscripcion pago) {
        return suscripcionBL.registrarSuscripcionConPago(suscripcion, pago);
    }

    @WebMethod(operationName = "listarSuscripcionesPorUsuario")
    public List<UsuarioCanalSuscripcion> listarPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return suscripcionBL.listarSuscripcionesPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "buscarSuscripcionPorId")
    public UsuarioCanalSuscripcion buscarPorId(@WebParam(name = "id") int id) {
        return suscripcionBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarSuscripcionesActivas")
    public List<UsuarioCanalSuscripcion> listarActivas() {
        return suscripcionBL.listarActivas();
    }

    @WebMethod(operationName = "listarSuscripcionesPorEstado")
    public List<UsuarioCanalSuscripcion> listarPorEstado(@WebParam(name = "estado") String estado) {
        return suscripcionBL.listarPorEstado(estado);
    }

    @WebMethod(operationName = "cambiarEstadoSuscripcion")
    public int cambiarEstado(@WebParam(name = "id") int id, @WebParam(name = "nuevoEstado") String nuevoEstado) {
        return suscripcionBL.cambiarEstado(id, nuevoEstado);
    }

    @WebMethod(operationName = "actualizarSuscripcion")
    public int actualizar(@WebParam(name = "suscripcion") UsuarioCanalSuscripcion suscripcion) {
        return suscripcionBL.actualizar(suscripcion);
    }

    @WebMethod(operationName = "eliminarSuscripcion")
    public int eliminar(@WebParam(name = "id") int id) {
        return suscripcionBL.eliminar(id);
    }
}