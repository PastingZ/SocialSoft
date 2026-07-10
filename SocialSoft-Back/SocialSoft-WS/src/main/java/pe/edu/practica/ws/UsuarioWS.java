package pe.edu.practica.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.practica.bl.UsuarioBL;
import pe.edu.practica.model.Usuario;
import pe.edu.practica.model.DispositivoUsuario;
import pe.edu.practica.model.RegistroUsuarioDTO;

import java.util.List;

@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {
    private UsuarioBL usuarioBL;

    public UsuarioWS() {
        this.usuarioBL = new UsuarioBL();
    }

    @WebMethod(operationName = "insertarUsuario")
    public int insertarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        return usuarioBL.insertar(usuario);
    }

    @WebMethod(operationName = "registrarUsuarioConDispositivo")
    public int registrarUsuarioConDispositivo(@WebParam(name = "registro") RegistroUsuarioDTO registro) {
        return usuarioBL.registrarUsuarioConDispositivo(registro.getUsuario(), registro.getDispositivo());
    }

    @WebMethod(operationName = "listarUsuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioBL.listarTodos();
    }

    @WebMethod(operationName = "buscarUsuarioPorId")
    public Usuario buscarUsuarioPorId(@WebParam(name = "id") int id) {
        return usuarioBL.buscarPorId(id);
    }

    @WebMethod(operationName = "buscarUsuarioPorDni")
    public Usuario buscarUsuarioPorDni(@WebParam(name = "dni") String dni) {
        return usuarioBL.buscarPorDni(dni);
    }

    @WebMethod(operationName = "actualizarUsuario")
    public int actualizarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        return usuarioBL.actualizar(usuario);
    }

    @WebMethod(operationName = "eliminarUsuario")
    public int eliminarUsuario(@WebParam(name = "id") int id) {
        return usuarioBL.eliminar(id);
    }
}