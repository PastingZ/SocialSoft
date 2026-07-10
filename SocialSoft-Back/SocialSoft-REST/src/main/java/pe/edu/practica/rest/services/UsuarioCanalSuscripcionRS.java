package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
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

    @POST
    @Path("/registro-con-pago")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int registrarSuscripcionConPago(pe.edu.practica.model.RegistroSuscripcionDTO dto) {
        return suscripcionBL.registrarSuscripcionConPago(dto.getSuscripcion(), dto.getPago());
    }

    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioCanalSuscripcion> listarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        return suscripcionBL.listarSuscripcionesPorUsuario(idUsuario);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioCanalSuscripcion buscarPorId(@PathParam("id") int id) {
        return suscripcionBL.buscarPorId(id);
    }

    @GET
    @Path("/activas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioCanalSuscripcion> listarActivas() {
        return suscripcionBL.listarActivas();
    }

    @GET
    @Path("/estado/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioCanalSuscripcion> listarPorEstado(@PathParam("estado") String estado) {
        return suscripcionBL.listarPorEstado(estado);
    }

    @PUT
    @Path("/{id}/estado/{nuevoEstado}")
    @Produces(MediaType.APPLICATION_JSON)
    public int cambiarEstado(@PathParam("id") int id, @PathParam("nuevoEstado") String nuevoEstado) {
        return suscripcionBL.cambiarEstado(id, nuevoEstado);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int actualizar(UsuarioCanalSuscripcion suscripcion) {
        return suscripcionBL.actualizar(suscripcion);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int eliminar(@PathParam("id") int id) {
        return suscripcionBL.eliminar(id);
    }

}