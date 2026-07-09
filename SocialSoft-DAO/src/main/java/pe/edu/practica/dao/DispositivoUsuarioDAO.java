package pe.edu.practica.dao;

import pe.edu.practica.model.DispositivoUsuario;
import java.util.List;

public interface DispositivoUsuarioDAO {
    int insertar(DispositivoUsuario dispositivo);
    List<DispositivoUsuario> listarPorUsuario(int idUsuario);
}