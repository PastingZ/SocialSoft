package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.DispositivoUsuarioBL;
import pe.edu.practica.model.DispositivoUsuario;

import java.util.List;

@WebService(serviceName = "DispositivoUsuarioWS")
public class DispositivoUsuarioWS {
    private DispositivoUsuarioBL dispositivoBL;

    public DispositivoUsuarioWS() {
        this.dispositivoBL = new DispositivoUsuarioBL();
    }

    @WebMethod(operationName = "insertarDispositivo")
    public int insertarDispositivo(@WebParam(name = "dispositivo") DispositivoUsuario dispositivo) {
        return dispositivoBL.insertar(dispositivo);
    }

    @WebMethod(operationName = "listarDispositivosPorUsuario")
    public List<DispositivoUsuario> listarDispositivosPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return dispositivoBL.listarPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "buscarDispositivoPorId")
    public DispositivoUsuario buscarDispositivoPorId(@WebParam(name = "id") int id) {
        return dispositivoBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarDispositivosActivos")
    public List<DispositivoUsuario> listarDispositivosActivos(@WebParam(name = "idUsuario") int idUsuario) {
        return dispositivoBL.listarActivosPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "actualizarDispositivo")
    public int actualizarDispositivo(@WebParam(name = "dispositivo") DispositivoUsuario dispositivo) {
        return dispositivoBL.actualizar(dispositivo);
    }

    @WebMethod(operationName = "eliminarDispositivo")
    public int eliminarDispositivo(@WebParam(name = "id") int id) {
        return dispositivoBL.eliminar(id);
    }
}