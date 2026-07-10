package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.PagoSuscripcionBL;
import pe.edu.practica.model.PagoSuscripcion;

import java.util.List;

@Path("/pagos")
public class PagoSuscripcionRS {
    private PagoSuscripcionBL pagoBL;

    public PagoSuscripcionRS() {
        this.pagoBL = new PagoSuscripcionBL();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int insertar(PagoSuscripcion pago) {
        return pagoBL.insertar(pago);
    }

    @GET
    @Path("/suscripcion/{idSuscripcion}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PagoSuscripcion> listarPorSuscripcion(@PathParam("idSuscripcion") int idSuscripcion) {
        return pagoBL.listarPorSuscripcion(idSuscripcion);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PagoSuscripcion buscarPorId(@PathParam("id") int id) {
        return pagoBL.buscarPorId(id);
    }

    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PagoSuscripcion> listarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        return pagoBL.listarPorUsuario(idUsuario);
    }

    @GET
    @Path("/estado/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PagoSuscripcion> listarPorEstado(@PathParam("estado") String estado) {
        return pagoBL.listarPorEstado(estado);
    }
}