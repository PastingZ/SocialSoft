package pe.edu.practica.bl;

import pe.edu.practica.dao.ResolucionDAO;
import pe.edu.practica.impl.ResolucionDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Resolucion;

import java.sql.Connection;
import java.util.List;

public class ResolucionBL {
    private ResolucionDAO resolucionDAO;

    public ResolucionBL() {
        this.resolucionDAO = new ResolucionDAOImpl();
    }

    public List<Resolucion> listarTodos() {
        List<Resolucion> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = resolucionDAO.listarTodos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar resoluciones: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public Resolucion buscarPorId(int id) {
        Resolucion resolucion = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            resolucion = resolucionDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar resolucion: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resolucion;
    }

    public List<Resolucion> listarPorPlan(int idPlan) {
        List<Resolucion> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = resolucionDAO.listarPorPlan(idPlan);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar resoluciones por plan: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }
}
