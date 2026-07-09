package pe.edu.practica.dao;

import pe.edu.practica.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    int insertar(Usuario usuario);
    List<Usuario> listarTodos();
}