package pe.edu.practica.rest.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarTodos() {
        return usuarioBL.listarTodos();
    }
}