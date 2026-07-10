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
    public int insertar(@WebParam(name = "canal") Canal canal) {
        return canalBL.insertar(canal);
    }

    @WebMethod(operationName = "listarCanales")
    public List<Canal> listarTodos() {
        return canalBL.listarTodos();
    }

    @WebMethod(operationName = "buscarCanalPorId")
    public Canal buscarPorId(@WebParam(name = "id") int id) {
        return canalBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarCanalesPorCategoria")
    public List<Canal> listarPorCategoria(@WebParam(name = "categoria") String categoria) {
        return canalBL.listarPorCategoria(categoria);
    }

    @WebMethod(operationName = "actualizarCanal")
    public int actualizar(@WebParam(name = "canal") Canal canal) {
        return canalBL.actualizar(canal);
    }

    @WebMethod(operationName = "eliminarCanal")
    public int eliminar(@WebParam(name = "id") int id) {
        return canalBL.eliminar(id);
    }
}