package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.PagoSuscripcionBL;
import pe.edu.practica.model.PagoSuscripcion;

import java.util.List;

@WebService(serviceName = "PagoSuscripcionWS")
public class PagoSuscripcionWS {
    private PagoSuscripcionBL pagoBL;

    public PagoSuscripcionWS() {
        this.pagoBL = new PagoSuscripcionBL();
    }

    @WebMethod(operationName = "insertarPago")
    public int insertar(@WebParam(name = "pago") PagoSuscripcion pago) {
        return pagoBL.insertar(pago);
    }

    @WebMethod(operationName = "listarPagosPorSuscripcion")
    public List<PagoSuscripcion> listarPorSuscripcion(@WebParam(name = "idSuscripcion") int idSuscripcion) {
        return pagoBL.listarPorSuscripcion(idSuscripcion);
    }

    @WebMethod(operationName = "buscarPagoPorId")
    public PagoSuscripcion buscarPorId(@WebParam(name = "id") int id) {
        return pagoBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarPagosPorUsuario")
    public List<PagoSuscripcion> listarPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return pagoBL.listarPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "listarPagosPorEstado")
    public List<PagoSuscripcion> listarPorEstado(@WebParam(name = "estado") String estado) {
        return pagoBL.listarPorEstado(estado);
    }
}