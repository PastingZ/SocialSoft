package pe.edu.practica.dao.impl;

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
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, suscripcion.getUsuario().getId());
            pst.setInt(2, suscripcion.getCanal().getId());
            pst.setInt(3, suscripcion.getPlanSuscripcion().getId());
            pst.setDate(4, new java.sql.Date(suscripcion.getFechaRegistro().getTime()));
            pst.setString(5, suscripcion.getEstado());
            resultado = pst.executeUpdate();
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
            while (rs.next()) {
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

                lista.add(ucs);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar suscripciones: " + ex.getMessage());
        }
        return lista;
    }
}