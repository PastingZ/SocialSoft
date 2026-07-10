package pe.edu.practica.bl;

import pe.edu.practica.dao.DispositivoUsuarioDAO;
import pe.edu.practica.impl.DispositivoUsuarioDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.DispositivoUsuario;

import java.sql.Connection;
import java.util.List;

public class DispositivoUsuarioBL {
    private DispositivoUsuarioDAO dispositivoDAO;

    public DispositivoUsuarioBL() {
        this.dispositivoDAO = new DispositivoUsuarioDAOImpl();
    }

    public int insertar(DispositivoUsuario dispositivo) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = dispositivoDAO.insertar(dispositivo);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar dispositivo: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<DispositivoUsuario> listarPorUsuario(int idUsuario) {
        List<DispositivoUsuario> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = dispositivoDAO.listarPorUsuario(idUsuario);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar dispositivos: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public DispositivoUsuario buscarPorId(int id) {
        DispositivoUsuario dispositivo = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            dispositivo = dispositivoDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar dispositivo por id: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return dispositivo;
    }

    public List<DispositivoUsuario> listarActivosPorUsuario(int idUsuario) {
        List<DispositivoUsuario> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = dispositivoDAO.listarActivosPorUsuario(idUsuario);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar dispositivos activos: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public int actualizar(DispositivoUsuario dispositivo) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = dispositivoDAO.actualizar(dispositivo);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al actualizar dispositivo: " + ex.getMessage());
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
            resultado = dispositivoDAO.eliminar(id);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al eliminar dispositivo: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }
}