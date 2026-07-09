package pe.edu.practica.rest.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.practica.bl.CanalBL;
import pe.edu.practica.model.Canal;

import java.util.List;

// 1. @Path define la URL específica de este servicio (ej: /api/canales)
@Path("/canales")
public class CanalRS {

    private CanalBL canalBL;

    public CanalRS() {
        // Conectamos el servicio web con tu capa de Negocio (BL)
        this.canalBL = new CanalBL();
    }

    // 2. @GET indica que a este método se entra por el navegador o haciendo un GET request
    @GET
    // 3. @Produces obliga a Jakarta a convertir tu lista de Java en texto JSON automáticamente
    @Produces(MediaType.APPLICATION_JSON)
    public List<Canal> listarTodos() {
        return canalBL.listarTodos();
    }
}