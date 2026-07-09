package pe.edu.practica.dao;

import pe.edu.practica.model.UsuarioCanalSuscripcion;
import java.util.List;

public interface UsuarioCanalSuscripcionDAO {
    int insertar(UsuarioCanalSuscripcion suscripcion);
    List<UsuarioCanalSuscripcion> listarSuscripcionesPorUsuario(int idUsuario);
}