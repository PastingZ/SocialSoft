package pe.edu.practica.bl;

import pe.edu.practica.dao.MetodoPagoDAO;
import pe.edu.practica.impl.MetodoPagoDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.MetodoPago;

import java.sql.Connection;
import java.util.List;

public class MetodoPagoBL {
    private MetodoPagoDAO metodoPagoDAO;

    public MetodoPagoBL() {
        this.metodoPagoDAO = new MetodoPagoDAOImpl();
    }

    public List<MetodoPago> listarTodos() {
        List<MetodoPago> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = metodoPagoDAO.listarTodos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar metodos de pago: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public MetodoPago buscarPorId(int id) {
        MetodoPago metodo = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            metodo = metodoPagoDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar metodo de pago: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return metodo;
    }
}
