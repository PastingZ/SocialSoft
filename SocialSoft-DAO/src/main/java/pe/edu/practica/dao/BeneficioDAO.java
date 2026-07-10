package pe.edu.practica.dao;

import pe.edu.practica.model.Beneficio;
import java.util.List;

public interface BeneficioDAO {
    int insertar(Beneficio beneficio);
    List<Beneficio> listarTodos();
    Beneficio buscarPorId(int id);
    List<Beneficio> listarPorPlan(int idPlan);
    int eliminar(int id);
}
