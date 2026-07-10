package pe.edu.practica.dao;

import pe.edu.practica.model.Canal;
import pe.edu.practica.model.ReporteCanalDTO;
import java.util.List;

public interface CanalDAO {
    int insertar(Canal canal);
    List<Canal> listarTodos();
    Canal buscarPorId(int id);
    List<Canal> listarPorCategoria(String categoria);
    int actualizar(Canal canal);
    List<ReporteCanalDTO> listarTopCanalesActivos();
    int eliminar(int id);
}