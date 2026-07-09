package pe.edu.practica.bl;

import pe.edu.practica.dao.PagoSuscripcionDAO;
import pe.edu.practica.dao.impl.PagoSuscripcionDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.PagoSuscripcion;

import java.sql.Connection;
import java.util.List;

public class PagoSuscripcionBL {
    private PagoSuscripcionDAO pagoDAO;

    public PagoSuscripcionBL() {
        this.pagoDAO = new PagoSuscripcionDAOImpl();
    }

    public int insertar(PagoSuscripcion pago) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);

            resultado = pagoDAO.insertar(pago);

            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar pago: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<PagoSuscripcion> listarPorSuscripcion(int idSuscripcion) {
        List<PagoSuscripcion> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = pagoDAO.listarPorSuscripcion(idSuscripcion);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar pagos: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }
}