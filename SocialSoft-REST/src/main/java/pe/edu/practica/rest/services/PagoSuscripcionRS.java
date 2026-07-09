package pe.edu.practica.rest.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
}