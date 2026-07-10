package pe.edu.practica.dao;

import pe.edu.practica.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    int insertar(Usuario usuario);
    List<Usuario> listarTodos();
    Usuario buscarPorId(int id);
    Usuario buscarPorDni(String dni);
    int actualizar(Usuario usuario);
    int eliminar(int id);
}