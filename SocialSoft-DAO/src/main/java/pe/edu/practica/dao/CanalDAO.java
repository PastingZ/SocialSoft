package pe.edu.practica.dao;

import pe.edu.practica.model.Canal;
import java.util.List;

public interface CanalDAO {
    List<Canal> listarTodos();
    List<Canal> listarPorCategoria(String categoria);
}