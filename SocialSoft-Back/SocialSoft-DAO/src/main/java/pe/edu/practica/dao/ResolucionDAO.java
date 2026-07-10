package pe.edu.practica.dao;

import pe.edu.practica.model.Resolucion;
import java.util.List;

public interface ResolucionDAO {
    List<Resolucion> listarTodos();
    Resolucion buscarPorId(int id);
    List<Resolucion> listarPorPlan(int idPlan);
}
