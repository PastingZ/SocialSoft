package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.DispositivoUsuarioBL;
import pe.edu.practica.model.DispositivoUsuario;

import java.util.List;

@Path("/dispositivos")
public class DispositivoUsuarioRS {
    private DispositivoUsuarioBL dispositivoBL;

    public DispositivoUsuarioRS() {
        this.dispositivoBL = new DispositivoUsuarioBL();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int insertar(DispositivoUsuario dispositivo) {
        return dispositivoBL.insertar(dispositivo);
    }

    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DispositivoUsuario> listarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        return dispositivoBL.listarPorUsuario(idUsuario);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DispositivoUsuario buscarPorId(@PathParam("id") int id) {
        return dispositivoBL.buscarPorId(id);
    }

    @GET
    @Path("/activos/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DispositivoUsuario> listarActivosPorUsuario(@PathParam("idUsuario") int idUsuario) {
        return dispositivoBL.listarActivosPorUsuario(idUsuario);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int actualizar(DispositivoUsuario dispositivo) {
        return dispositivoBL.actualizar(dispositivo);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int eliminar(@PathParam("id") int id) {
        return dispositivoBL.eliminar(id);
    }
}