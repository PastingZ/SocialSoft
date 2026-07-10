package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.PlanSuscripcionBL;
import pe.edu.practica.model.PlanSuscripcion;

import java.util.List;

@WebService(serviceName = "PlanSuscripcionWS")
public class PlanSuscripcionWS {
    private PlanSuscripcionBL planBL;

    public PlanSuscripcionWS() {
        this.planBL = new PlanSuscripcionBL();
    }

    @WebMethod(operationName = "insertarPlan")
    public int insertarPlan(@WebParam(name = "plan") PlanSuscripcion plan) {
        return planBL.insertar(plan);
    }

    @WebMethod(operationName = "listarPlanes")
    public List<PlanSuscripcion> listarPlanes() {
        return planBL.listarTodos();
    }

    @WebMethod(operationName = "buscarPlanPorId")
    public PlanSuscripcion buscarPlanPorId(@WebParam(name = "id") int id) {
        return planBL.buscarPorId(id);
    }

    @WebMethod(operationName = "actualizarPlan")
    public int actualizarPlan(@WebParam(name = "plan") PlanSuscripcion plan) {
        return planBL.actualizar(plan);
    }

    @WebMethod(operationName = "eliminarPlan")
    public int eliminarPlan(@WebParam(name = "id") int id) {
        return planBL.eliminar(id);
    }
}
