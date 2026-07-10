package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.UsuarioCanalSuscripcionBL;
import pe.edu.practica.model.UsuarioCanalSuscripcion;
import pe.edu.practica.model.PagoSuscripcion;
import pe.edu.practica.model.RegistroSuscripcionDTO;

import java.util.List;

@WebService(serviceName = "UsuarioCanalSuscripcionWS")
public class UsuarioCanalSuscripcionWS {
    private UsuarioCanalSuscripcionBL suscripcionBL;

    public UsuarioCanalSuscripcionWS() {
        this.suscripcionBL = new UsuarioCanalSuscripcionBL();
    }

    @WebMethod(operationName = "insertarSuscripcion")
    public int insertarSuscripcion(@WebParam(name = "suscripcion") UsuarioCanalSuscripcion suscripcion) {
        return suscripcionBL.insertar(suscripcion);
    }

    @WebMethod(operationName = "registrarSuscripcionConPago")
    public int registrarSuscripcionConPago(@WebParam(name = "registro") RegistroSuscripcionDTO registro) {
        return suscripcionBL.registrarSuscripcionConPago(registro.getSuscripcion(), registro.getPago());
    }

    @WebMethod(operationName = "listarSuscripcionesPorUsuario")
    public List<UsuarioCanalSuscripcion> listarSuscripcionesPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return suscripcionBL.listarSuscripcionesPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "buscarSuscripcionPorId")
    public UsuarioCanalSuscripcion buscarSuscripcionPorId(@WebParam(name = "id") int id) {
        return suscripcionBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarSuscripcionesActivas")
    public List<UsuarioCanalSuscripcion> listarSuscripcionesActivas() {
        return suscripcionBL.listarActivas();
    }

    @WebMethod(operationName = "listarSuscripcionesPorEstado")
    public List<UsuarioCanalSuscripcion> listarSuscripcionesPorEstado(@WebParam(name = "estado") String estado) {
        return suscripcionBL.listarPorEstado(estado);
    }

    @WebMethod(operationName = "cambiarEstadoSuscripcion")
    public int cambiarEstadoSuscripcion(@WebParam(name = "id") int id, @WebParam(name = "nuevoEstado") String nuevoEstado) {
        return suscripcionBL.cambiarEstado(id, nuevoEstado);
    }

    @WebMethod(operationName = "actualizarSuscripcion")
    public int actualizarSuscripcion(@WebParam(name = "suscripcion") UsuarioCanalSuscripcion suscripcion) {
        return suscripcionBL.actualizar(suscripcion);
    }

    @WebMethod(operationName = "eliminarSuscripcion")
    public int eliminarSuscripcion(@WebParam(name = "id") int id) {
        return suscripcionBL.eliminar(id);
    }
}