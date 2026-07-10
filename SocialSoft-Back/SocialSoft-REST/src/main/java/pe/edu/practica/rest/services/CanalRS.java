package pe.edu.practica.rest.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.CanalBL;
import pe.edu.practica.model.Canal;

import java.util.List;

@Path("/canales")
public class CanalRS {
    private CanalBL canalBL;

    public CanalRS() {
        this.canalBL = new CanalBL();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int insertar(Canal canal) {
        return canalBL.insertar(canal);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Canal> listarTodos() {
        return canalBL.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Canal buscarPorId(@PathParam("id") int id) {
        return canalBL.buscarPorId(id);
    }

    @GET
    @Path("/categoria/{categoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Canal> listarPorCategoria(@PathParam("categoria") String categoria) {
        return canalBL.listarPorCategoria(categoria);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int actualizar(Canal canal) {
        return canalBL.actualizar(canal);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int eliminar(@PathParam("id") int id) {
        return canalBL.eliminar(id);
    }
}