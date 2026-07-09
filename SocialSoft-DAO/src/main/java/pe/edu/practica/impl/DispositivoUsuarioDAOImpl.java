package pe.edu.practica.dao.impl;

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
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, dispositivo.getUsuario().getId());
            pst.setString(2, dispositivo.getNombre());
            pst.setString(3, dispositivo.getTipo());
            pst.setString(4, dispositivo.getSistemaOperativo());
            pst.setBoolean(5, dispositivo.isActivo());
            resultado = pst.executeUpdate();
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
            while (rs.next()) {
                DispositivoUsuario d = new DispositivoUsuario();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setTipo(rs.getString("tipo"));
                d.setSistemaOperativo(rs.getString("sistema_operativo"));
                d.setActivo(rs.getBoolean("activo"));

                // Solo seteamos el ID del usuario para no sobrecargar la memoria
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                d.setUsuario(u);

                lista.add(d);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar dispositivos: " + ex.getMessage());
        }
        return lista;
    }
}