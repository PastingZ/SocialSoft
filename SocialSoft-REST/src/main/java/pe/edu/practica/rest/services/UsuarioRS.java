package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.UsuarioBL;
import pe.edu.practica.model.Usuario;

import java.util.List;

@Path("/usuarios")
public class UsuarioRS {
    private UsuarioBL usuarioBL;

    public UsuarioRS() {
        this.usuarioBL = new UsuarioBL();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int insertar(Usuario usuario) {
        return usuarioBL.insertar(usuario);
    }

    @POST
    @Path("/registro-con-dispositivo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int registrarUsuarioConDispositivo(pe.edu.practica.model.RegistroUsuarioDTO dto) {
        return usuarioBL.registrarUsuarioConDispositivo(dto.getUsuario(), dto.getDispositivo());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarTodos() {
        return usuarioBL.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario buscarPorId(@PathParam("id") int id) {
        return usuarioBL.buscarPorId(id);
    }

    @GET
    @Path("/dni/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario buscarPorDni(@PathParam("dni") String dni) {
        return usuarioBL.buscarPorDni(dni);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int actualizar(Usuario usuario) {
        return usuarioBL.actualizar(usuario);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int eliminar(@PathParam("id") int id) {
        return usuarioBL.eliminar(id);
    }
}