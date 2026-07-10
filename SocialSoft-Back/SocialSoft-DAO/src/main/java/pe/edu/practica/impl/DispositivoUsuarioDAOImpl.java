package pe.edu.practica.impl;

import pe.edu.practica.dao.DispositivoUsuarioDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.DispositivoUsuario;
import pe.edu.practica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DispositivoUsuarioDAOImpl implements DispositivoUsuarioDAO {

    @Override
    public int insertar(DispositivoUsuario dispositivo) {
        int resultado = 0;
        String sql = "INSERT INTO dispositivo_usuario (id_usuario, nombre, tipo, sistema_operativo, activo) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, dispositivo.getUsuario().getId());
            pst.setString(2, dispositivo.getNombre());
            pst.setString(3, dispositivo.getTipo());
            pst.setString(4, dispositivo.getSistemaOperativo());
            pst.setBoolean(5, dispositivo.isActivo());
            pst.executeUpdate();
            
            java.sql.ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
                dispositivo.setId(resultado);
            }
        } catch (Exception ex) {
            System.err.println("Error al insertar dispositivo: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public List<DispositivoUsuario> listarPorUsuario(int idUsuario) {
        List<DispositivoUsuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM dispositivo_usuario WHERE id_usuario = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (Exception ex) {
            System.err.println("Error al listar dispositivos por usuario: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public DispositivoUsuario buscarPorId(int id) {
        DispositivoUsuario dispositivo = null;
        String sql = "SELECT * FROM dispositivo_usuario WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) { dispositivo = mapear(rs); }
        } catch (Exception ex) {
            System.err.println("Error al buscar dispositivo por id: " + ex.getMessage());
        }
        return dispositivo;
    }

    @Override
    public List<DispositivoUsuario> listarActivosPorUsuario(int idUsuario) {
        List<DispositivoUsuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM dispositivo_usuario WHERE id_usuario = ? AND activo = 1";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (Exception ex) {
            System.err.println("Error al listar dispositivos activos: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int actualizar(DispositivoUsuario dispositivo) {
        int resultado = 0;
        String sql = "UPDATE dispositivo_usuario SET nombre = ?, tipo = ?, sistema_operativo = ?, activo = ? WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, dispositivo.getNombre());
            pst.setString(2, dispositivo.getTipo());
            pst.setString(3, dispositivo.getSistemaOperativo());
            pst.setBoolean(4, dispositivo.isActivo());
            pst.setInt(5, dispositivo.getId());
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al actualizar dispositivo: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM dispositivo_usuario WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al eliminar dispositivo: " + ex.getMessage());
        }
        return resultado;
    }

    private DispositivoUsuario mapear(ResultSet rs) throws Exception {
        DispositivoUsuario d = new DispositivoUsuario();
        d.setId(rs.getInt("id"));
        d.setNombre(rs.getString("nombre"));
        d.setTipo(rs.getString("tipo"));
        d.setSistemaOperativo(rs.getString("sistema_operativo"));
        d.setActivo(rs.getBoolean("activo"));
        Usuario u = new Usuario();
        u.setId(rs.getInt("id_usuario"));
        d.setUsuario(u);
        return d;
    }
}