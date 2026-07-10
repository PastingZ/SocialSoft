package pe.edu.practica.impl;

import pe.edu.practica.dao.UsuarioCanalSuscripcionDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Canal;
import pe.edu.practica.model.PlanSuscripcion;
import pe.edu.practica.model.Usuario;
import pe.edu.practica.model.UsuarioCanalSuscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCanalSuscripcionDAOImpl implements UsuarioCanalSuscripcionDAO {

    @Override
    public int insertar(UsuarioCanalSuscripcion suscripcion) {
        int resultado = 0;
        String sql = "INSERT INTO usuario_canal_suscripcion (id_usuario, id_canal, id_plan_suscripcion, fecha_registro, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, suscripcion.getUsuario().getId());
            pst.setInt(2, suscripcion.getCanal().getId());
            pst.setInt(3, suscripcion.getPlanSuscripcion().getId());
            pst.setDate(4, new java.sql.Date(suscripcion.getFechaRegistro().getTime()));
            pst.setString(5, suscripcion.getEstado());
            pst.executeUpdate();
            
            java.sql.ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
                suscripcion.setId(resultado);
            }
        } catch (Exception ex) {
            System.err.println("Error al insertar suscripcion: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public List<UsuarioCanalSuscripcion> listarSuscripcionesPorUsuario(int idUsuario) {
        List<UsuarioCanalSuscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario_canal_suscripcion WHERE id_usuario = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (Exception ex) {
            System.err.println("Error al listar suscripciones por usuario: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public UsuarioCanalSuscripcion buscarPorId(int id) {
        UsuarioCanalSuscripcion ucs = null;
        String sql = "SELECT * FROM usuario_canal_suscripcion WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) { ucs = mapear(rs); }
        } catch (Exception ex) {
            System.err.println("Error al buscar suscripcion por id: " + ex.getMessage());
        }
        return ucs;
    }

    @Override
    public List<UsuarioCanalSuscripcion> listarActivas() {
        return listarPorEstado("activa");
    }

    @Override
    public List<UsuarioCanalSuscripcion> listarPorEstado(String estado) {
        List<UsuarioCanalSuscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario_canal_suscripcion WHERE estado = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, estado);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (Exception ex) {
            System.err.println("Error al listar suscripciones por estado: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int actualizar(UsuarioCanalSuscripcion suscripcion) {
        int resultado = 0;
        String sql = "UPDATE usuario_canal_suscripcion SET id_plan_suscripcion = ?, estado = ? WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, suscripcion.getPlanSuscripcion().getId());
            pst.setString(2, suscripcion.getEstado());
            pst.setInt(3, suscripcion.getId());
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al actualizar suscripcion: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int cambiarEstado(int id, String nuevoEstado) {
        int resultado = 0;
        String sql = "UPDATE usuario_canal_suscripcion SET estado = ? WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, nuevoEstado);
            pst.setInt(2, id);
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al cambiar estado de suscripcion: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM usuario_canal_suscripcion WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al eliminar suscripcion: " + ex.getMessage());
        }
        return resultado;
    }

    private UsuarioCanalSuscripcion mapear(ResultSet rs) throws Exception {
        UsuarioCanalSuscripcion ucs = new UsuarioCanalSuscripcion();
        ucs.setId(rs.getInt("id"));
        ucs.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        ucs.setEstado(rs.getString("estado"));
        Usuario u = new Usuario(); u.setId(rs.getInt("id_usuario"));
        Canal c = new Canal(); c.setId(rs.getInt("id_canal"));
        PlanSuscripcion ps = new PlanSuscripcion(); ps.setId(rs.getInt("id_plan_suscripcion"));
        ucs.setUsuario(u);
        ucs.setCanal(c);
        ucs.setPlanSuscripcion(ps);
        return ucs;
    }
}