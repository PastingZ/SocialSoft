package pe.edu.practica.dao.impl;

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
    public List<Canal> listarTodos() {
        return ejecutarConsulta("SELECT * FROM canal");
    }

    @Override
    public List<Canal> listarPorCategoria(String categoria) {
        return ejecutarConsulta("SELECT * FROM canal WHERE categoria = '" + categoria + "'");
    }

    // Método privado GigaChad para no repetir código en los SELECTs
    private List<Canal> ejecutarConsulta(String sql) {
        List<Canal> lista = new ArrayList<>();
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Canal c = new Canal();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setFechaCreacion(rs.getDate("fecha_creacion"));
                c.setNumeroSeguidores(rs.getInt("numero_seguidores"));
                c.setCategoria(rs.getString("categoria"));
                lista.add(c);
            }
        } catch (Exception ex) {
            System.err.println("Error al consultar canales: " + ex.getMessage());
        }
        return lista;
    }
}