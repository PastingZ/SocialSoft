package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.PlanSuscripcionBL;
import pe.edu.practica.model.PlanSuscripcion;

import java.util.List;

@Path("/planes")
public class PlanSuscripcionRS {
    private PlanSuscripcionBL planBL;

    public PlanSuscripcionRS() {
        this.planBL = new PlanSuscripcionBL();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int insertar(PlanSuscripcion plan) {
        return planBL.insertar(plan);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanSuscripcion> listarTodos() {
        return planBL.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlanSuscripcion buscarPorId(@PathParam("id") int id) {
        return planBL.buscarPorId(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int actualizar(PlanSuscripcion plan) {
        return planBL.actualizar(plan);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int eliminar(@PathParam("id") int id) {
        return planBL.eliminar(id);
    }
}
