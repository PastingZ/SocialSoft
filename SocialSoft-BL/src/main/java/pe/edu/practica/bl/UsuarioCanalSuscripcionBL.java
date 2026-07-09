package pe.edu.practica.bl;

import pe.edu.practica.dao.UsuarioCanalSuscripcionDAO;
import pe.edu.practica.dao.impl.UsuarioCanalSuscripcionDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.UsuarioCanalSuscripcion;

import java.sql.Connection;
import java.util.List;

public class UsuarioCanalSuscripcionBL {
    private UsuarioCanalSuscripcionDAO suscripcionDAO;

    public UsuarioCanalSuscripcionBL() {
        this.suscripcionDAO = new UsuarioCanalSuscripcionDAOImpl();
    }

    public int insertar(UsuarioCanalSuscripcion suscripcion) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);

            resultado = suscripcionDAO.insertar(suscripcion);

            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar suscripcion: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<UsuarioCanalSuscripcion> listarSuscripcionesPorUsuario(int idUsuario) {
        List<UsuarioCanalSuscripcion> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = suscripcionDAO.listarSuscripcionesPorUsuario(idUsuario);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar suscripciones: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }
}