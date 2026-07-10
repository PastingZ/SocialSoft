package pe.edu.practica.impl;

import pe.edu.practica.dao.CanalDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Canal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CanalDAOImpl implements CanalDAO {

    @Override
    public int insertar(Canal canal) {
        int resultado = 0;
        String sql = "INSERT INTO canal (nombre, descripcion, fecha_creacion, numero_seguidores, categoria) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, canal.getNombre());
            pst.setString(2, canal.getDescripcion());
            pst.setDate(3, new java.sql.Date(canal.getFechaCreacion().getTime()));
            pst.setInt(4, canal.getNumeroSeguidores());
            pst.setString(5, canal.getCategoria());
            pst.executeUpdate();
            
            java.sql.ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
                canal.setId(resultado);
            }
        } catch (Exception ex) {
            System.err.println("Error al insertar canal: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public List<Canal> listarTodos() {
        List<Canal> lista = new ArrayList<>();
        String sql = "SELECT * FROM canal";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar canales: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Canal buscarPorId(int id) {
        Canal canal = null;
        String sql = "SELECT * FROM canal WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                canal = mapear(rs);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar canal por id: " + ex.getMessage());
        }
        return canal;
    }

    @Override
    public List<Canal> listarPorCategoria(String categoria) {
        List<Canal> lista = new ArrayList<>();
        String sql = "SELECT * FROM canal WHERE categoria = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, categoria);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar canales por categoria: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int actualizar(Canal canal) {
        int resultado = 0;
        String sql = "UPDATE canal SET nombre = ?, descripcion = ?, fecha_creacion = ?, numero_seguidores = ?, categoria = ? WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, canal.getNombre());
            pst.setString(2, canal.getDescripcion());
            pst.setDate(3, new java.sql.Date(canal.getFechaCreacion().getTime()));
            pst.setInt(4, canal.getNumeroSeguidores());
            pst.setString(5, canal.getCategoria());
            pst.setInt(6, canal.getId());
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al actualizar canal: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM canal WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al eliminar canal: " + ex.getMessage());
        }
        return resultado;
    }

    private Canal mapear(ResultSet rs) throws Exception {
        Canal c = new Canal();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setFechaCreacion(rs.getDate("fecha_creacion"));
        c.setNumeroSeguidores(rs.getInt("numero_seguidores"));
        c.setCategoria(rs.getString("categoria"));
        return c;
    }
}