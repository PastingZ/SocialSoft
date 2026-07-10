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
    public int insertar(@WebParam(name = "dispositivo") DispositivoUsuario dispositivo) {
        return dispositivoBL.insertar(dispositivo);
    }

    @WebMethod(operationName = "listarDispositivosPorUsuario")
    public List<DispositivoUsuario> listarPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return dispositivoBL.listarPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "buscarDispositivoPorId")
    public DispositivoUsuario buscarPorId(@WebParam(name = "id") int id) {
        return dispositivoBL.buscarPorId(id);
    }

    @WebMethod(operationName = "listarDispositivosActivos")
    public List<DispositivoUsuario> listarActivosPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return dispositivoBL.listarActivosPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "actualizarDispositivo")
    public int actualizar(@WebParam(name = "dispositivo") DispositivoUsuario dispositivo) {
        return dispositivoBL.actualizar(dispositivo);
    }

    @WebMethod(operationName = "eliminarDispositivo")
    public int eliminar(@WebParam(name = "id") int id) {
        return dispositivoBL.eliminar(id);
    }
}