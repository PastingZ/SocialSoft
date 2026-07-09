package pe.edu.practica.rest.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.UsuarioCanalSuscripcionBL;
import pe.edu.practica.model.UsuarioCanalSuscripcion;

import java.util.List;

@Path("/suscripciones")
public class UsuarioCanalSuscripcionRS {
    private UsuarioCanalSuscripcionBL suscripcionBL;

    public UsuarioCanalSuscripcionRS() {
        this.suscripcionBL = new UsuarioCanalSuscripcionBL();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int insertar(UsuarioCanalSuscripcion suscripcion) {
        return suscripcionBL.insertar(suscripcion);
    }

    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioCanalSuscripcion> listarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        return suscripcionBL.listarSuscripcionesPorUsuario(idUsuario);
    }
}