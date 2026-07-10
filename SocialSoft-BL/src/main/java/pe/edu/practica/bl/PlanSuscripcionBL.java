package pe.edu.practica.bl;

import pe.edu.practica.dao.PlanSuscripcionDAO;
import pe.edu.practica.impl.PlanSuscripcionDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.PlanSuscripcion;

import java.sql.Connection;
import java.util.List;

public class PlanSuscripcionBL {
    private PlanSuscripcionDAO planDAO;

    public PlanSuscripcionBL() {
        this.planDAO = new PlanSuscripcionDAOImpl();
    }

    public int insertar(PlanSuscripcion plan) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = planDAO.insertar(plan);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar plan: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<PlanSuscripcion> listarTodos() {
        List<PlanSuscripcion> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = planDAO.listarTodos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar planes: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public PlanSuscripcion buscarPorId(int id) {
        PlanSuscripcion plan = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            plan = planDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar plan: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return plan;
    }

    public int actualizar(PlanSuscripcion plan) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = planDAO.actualizar(plan);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al actualizar plan: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public int eliminar(int id) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = planDAO.eliminar(id);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al eliminar plan: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }
}
