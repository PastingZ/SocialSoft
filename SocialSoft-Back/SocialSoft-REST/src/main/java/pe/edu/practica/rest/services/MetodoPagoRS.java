package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.MetodoPagoBL;
import pe.edu.practica.model.MetodoPago;

import java.util.List;

@Path("/metodos-pago")
public class MetodoPagoRS {
    
    private MetodoPagoBL metodoPagoBL;

    public MetodoPagoRS() {
        this.metodoPagoBL = new MetodoPagoBL();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MetodoPago> listarTodos() {
        return metodoPagoBL.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MetodoPago buscarPorId(@PathParam("id") int id) {
        return metodoPagoBL.buscarPorId(id);
    }
}
