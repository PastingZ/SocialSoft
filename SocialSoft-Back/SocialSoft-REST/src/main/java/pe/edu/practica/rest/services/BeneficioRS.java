package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.BeneficioBL;
import pe.edu.practica.model.Beneficio;

import java.util.List;

@Path("/beneficios")
public class BeneficioRS {
    private BeneficioBL beneficioBL;

    public BeneficioRS() {
        this.beneficioBL = new BeneficioBL();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int insertar(Beneficio beneficio) {
        return beneficioBL.insertar(beneficio);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Beneficio> listarTodos() {
        return beneficioBL.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Beneficio buscarPorId(@PathParam("id") int id) {
        return beneficioBL.buscarPorId(id);
    }

    @GET
    @Path("/plan/{idPlan}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Beneficio> listarPorPlan(@PathParam("idPlan") int idPlan) {
        return beneficioBL.listarPorPlan(idPlan);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int eliminar(@PathParam("id") int id) {
        return beneficioBL.eliminar(id);
    }
}
