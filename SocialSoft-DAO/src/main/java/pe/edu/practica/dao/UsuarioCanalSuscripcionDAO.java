package pe.edu.practica.dao;

import pe.edu.practica.model.UsuarioCanalSuscripcion;
import java.util.List;

public interface UsuarioCanalSuscripcionDAO {
    int insertar(UsuarioCanalSuscripcion suscripcion);
    List<UsuarioCanalSuscripcion> listarSuscripcionesPorUsuario(int idUsuario);
    UsuarioCanalSuscripcion buscarPorId(int id);
    List<UsuarioCanalSuscripcion> listarActivas();
    List<UsuarioCanalSuscripcion> listarPorEstado(String estado);
    int actualizar(UsuarioCanalSuscripcion suscripcion);
    int cambiarEstado(int id, String nuevoEstado);
    int eliminar(int id);
}