package pe.edu.practica.bl;

import pe.edu.practica.dao.CanalDAO;
import pe.edu.practica.impl.CanalDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Canal;
import pe.edu.practica.model.ReporteCanalDTO;

import java.sql.Connection;
import java.util.List;

public class CanalBL {
    private CanalDAO canalDAO;

    public CanalBL() {
        this.canalDAO = new CanalDAOImpl();
    }

    public int insertar(Canal canal) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = canalDAO.insertar(canal);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar canal: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<Canal> listarTodos() {
        List<Canal> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = canalDAO.listarTodos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar canales: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public Canal buscarPorId(int id) {
        Canal canal = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            canal = canalDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar canal por id: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return canal;
    }

    public List<Canal> listarPorCategoria(String categoria) {
        List<Canal> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = canalDAO.listarPorCategoria(categoria);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar canales por categoria: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public int actualizar(Canal canal) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = canalDAO.actualizar(canal);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al actualizar canal: " + ex.getMessage());
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
            resultado = canalDAO.eliminar(id);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al eliminar canal: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<ReporteCanalDTO> listarTopCanalesActivos() {
        List<ReporteCanalDTO> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = canalDAO.listarTopCanalesActivos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar top canales activos: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }
}