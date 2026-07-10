package pe.edu.practica.dao;

import pe.edu.practica.model.DispositivoUsuario;
import java.util.List;

public interface DispositivoUsuarioDAO {
    int insertar(DispositivoUsuario dispositivo);
    List<DispositivoUsuario> listarPorUsuario(int idUsuario);
    DispositivoUsuario buscarPorId(int id);
    List<DispositivoUsuario> listarActivosPorUsuario(int idUsuario);
    int actualizar(DispositivoUsuario dispositivo);
    int contarDispositivosActivos(int idUsuario);
    int eliminar(int id);
}