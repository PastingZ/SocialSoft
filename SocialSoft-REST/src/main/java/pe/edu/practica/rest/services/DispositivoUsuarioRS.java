package pe.edu.practica.rest.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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

    // El ID viaja directamente en la ruta
    @GET
    @Path("/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DispositivoUsuario> listarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        return dispositivoBL.listarPorUsuario(idUsuario);
    }
}