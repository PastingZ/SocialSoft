package pe.edu.practica.dao;

import pe.edu.practica.model.PlanSuscripcion;
import java.util.List;

public interface PlanSuscripcionDAO {
    int insertar(PlanSuscripcion plan);
    List<PlanSuscripcion> listarTodos();
    PlanSuscripcion buscarPorId(int id);
    int actualizar(PlanSuscripcion plan);
    int eliminar(int id);
}
