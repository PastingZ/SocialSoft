package pe.edu.practica.bl;

import pe.edu.practica.dao.CanalDAO;
import pe.edu.practica.dao.impl.CanalDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Canal;

import java.sql.Connection;
import java.util.List;

public class CanalBL {
    private CanalDAO canalDAO;

    public CanalBL() {
        this.canalDAO = new CanalDAOImpl();
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
}