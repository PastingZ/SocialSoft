package pe.edu.practica.dao;

import pe.edu.practica.model.Canal;
import java.util.List;

public interface CanalDAO {
    int insertar(Canal canal);
    List<Canal> listarTodos();
    Canal buscarPorId(int id);
    List<Canal> listarPorCategoria(String categoria);
    int actualizar(Canal canal);
    int eliminar(int id);
}