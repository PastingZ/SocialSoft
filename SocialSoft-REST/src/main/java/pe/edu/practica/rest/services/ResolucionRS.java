package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.ResolucionBL;
import pe.edu.practica.model.Resolucion;

import java.util.List;

@Path("/resoluciones")
public class ResolucionRS {
    private ResolucionBL resolucionBL;

    public ResolucionRS() {
        this.resolucionBL = new ResolucionBL();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Resolucion> listarTodos() {
        return resolucionBL.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Resolucion buscarPorId(@PathParam("id") int id) {
        return resolucionBL.buscarPorId(id);
    }

    @GET
    @Path("/plan/{idPlan}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Resolucion> listarPorPlan(@PathParam("idPlan") int idPlan) {
        return resolucionBL.listarPorPlan(idPlan);
    }
}
