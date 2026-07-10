package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.BeneficioBL;
import pe.edu.practica.model.Beneficio;

import java.util.List;

@WebService(serviceName = "BeneficioWS")
public class BeneficioWS {
    private BeneficioBL beneficioBL;

    public BeneficioWS() {
        this.beneficioBL = new BeneficioBL();
    }

    @WebMethod(operationName = "insertarBeneficio")
    public int insertarBeneficio(@WebParam(name = "beneficio") Beneficio beneficio) {
        return beneficioBL.insertar(beneficio);
    }

    @WebMethod(operationName = "listarBeneficios")
    public List<Beneficio> listarBeneficios() {
        return beneficioBL.listarTodos();
    }

    @WebMethod(operationName = "buscarBeneficioPorId")
    public Beneficio buscarBeneficioPorId(@WebParam(name = "id") int id) {
        return beneficioBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarBeneficiosPorPlan")
    public List<Beneficio> listarBeneficiosPorPlan(@WebParam(name = "idPlan") int idPlan) {
        return beneficioBL.listarPorPlan(idPlan);
    }

    @WebMethod(operationName = "eliminarBeneficio")
    public int eliminarBeneficio(@WebParam(name = "id") int id) {
        return beneficioBL.eliminar(id);
    }
}
