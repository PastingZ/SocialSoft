package pe.edu.practica.bl;

import pe.edu.practica.dao.BeneficioDAO;
import pe.edu.practica.impl.BeneficioDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Beneficio;

import java.sql.Connection;
import java.util.List;

public class BeneficioBL {
    private BeneficioDAO beneficioDAO;

    public BeneficioBL() {
        this.beneficioDAO = new BeneficioDAOImpl();
    }

    public int insertar(Beneficio beneficio) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = beneficioDAO.insertar(beneficio);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar beneficio: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<Beneficio> listarTodos() {
        List<Beneficio> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = beneficioDAO.listarTodos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar beneficios: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public Beneficio buscarPorId(int id) {
        Beneficio beneficio = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            beneficio = beneficioDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar beneficio: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return beneficio;
    }

    public List<Beneficio> listarPorPlan(int idPlan) {
        List<Beneficio> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = beneficioDAO.listarPorPlan(idPlan);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar beneficios por plan: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public int eliminar(int id) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = beneficioDAO.eliminar(id);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al eliminar beneficio: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }
}
