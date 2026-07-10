package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.ResolucionBL;
import pe.edu.practica.model.Resolucion;

import java.util.List;

@WebService(serviceName = "ResolucionWS")
public class ResolucionWS {
    private ResolucionBL resolucionBL;

    public ResolucionWS() {
        this.resolucionBL = new ResolucionBL();
    }

    @WebMethod(operationName = "listarResoluciones")
    public List<Resolucion> listarTodos() {
        return resolucionBL.listarTodos();
    }

    @WebMethod(operationName = "buscarResolucionPorId")
    public Resolucion buscarPorId(@WebParam(name = "id") int id) {
        return resolucionBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarResolucionesPorPlan")
    public List<Resolucion> listarPorPlan(@WebParam(name = "idPlan") int idPlan) {
        return resolucionBL.listarPorPlan(idPlan);
    }
}
