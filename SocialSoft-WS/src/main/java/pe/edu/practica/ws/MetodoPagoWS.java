package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.MetodoPagoBL;
import pe.edu.practica.model.MetodoPago;

import java.util.List;

@WebService(serviceName = "MetodoPagoWS")
public class MetodoPagoWS {
    private MetodoPagoBL metodoPagoBL;

    public MetodoPagoWS() {
        this.metodoPagoBL = new MetodoPagoBL();
    }

    @WebMethod(operationName = "listarMetodosPago")
    public List<MetodoPago> listarTodos() {
        return metodoPagoBL.listarTodos();
    }

    @WebMethod(operationName = "buscarMetodoPagoPorId")
    public MetodoPago buscarPorId(@WebParam(name = "id") int id) {
        return metodoPagoBL.buscarPorId(id);
    }
}
