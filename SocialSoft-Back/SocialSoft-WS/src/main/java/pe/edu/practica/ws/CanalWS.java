package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.CanalBL;
import pe.edu.practica.model.Canal;

import java.util.List;

@WebService(serviceName = "CanalWS")
public class CanalWS {
    private CanalBL canalBL;

    public CanalWS() {
        this.canalBL = new CanalBL();
    }

    @WebMethod(operationName = "insertarCanal")
    public int insertarCanal(@WebParam(name = "canal") Canal canal) {
        return canalBL.insertar(canal);
    }

    @WebMethod(operationName = "listarCanales")
    public List<Canal> listarCanales() {
        return canalBL.listarTodos();
    }

    @WebMethod(operationName = "buscarCanalPorId")
    public Canal buscarCanalPorId(@WebParam(name = "id") int id) {
        return canalBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarCanalesPorCategoria")
    public List<Canal> listarCanalesPorCategoria(@WebParam(name = "categoria") String categoria) {
        return canalBL.listarPorCategoria(categoria);
    }

    @WebMethod(operationName = "actualizarCanal")
    public int actualizarCanal(@WebParam(name = "canal") Canal canal) {
        return canalBL.actualizar(canal);
    }

    @WebMethod(operationName = "eliminarCanal")
    public int eliminarCanal(@WebParam(name = "id") int id) {
        return canalBL.eliminar(id);
    }
}