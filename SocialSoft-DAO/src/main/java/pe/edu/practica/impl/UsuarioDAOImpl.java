package pe.edu.practica.impl;

import pe.edu.practica.dao.UsuarioDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public int insertar(Usuario usuario) {
        int resultado = 0;
        // El id es AUTO_INCREMENT, no lo mandamos
        String sql = "INSERT INTO usuario (nombre_completo, dni, edad, ciudad, fecha_nacimiento, telefono, correo, profesion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Obtenemos la conexión, pero el BL hará el commit/rollback
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);

            pst.setString(1, usuario.getNombreCompleto());
            pst.setString(2, usuario.getDni());
            pst.setInt(3, usuario.getEdad());
            pst.setString(4, usuario.getCiudad());

            // Conversión táctica de util.Date a sql.Date
            pst.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));

            pst.setString(6, usuario.getTelefono());
            pst.setString(7, usuario.getCorreo());
            pst.setString(8, usuario.getProfesion());

            resultado = pst.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Error al insertar usuario: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setDni(rs.getString("dni"));
                u.setEdad(rs.getInt("edad"));
                u.setCiudad(rs.getString("ciudad"));
                u.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                u.setTelefono(rs.getString("telefono"));
                u.setCorreo(rs.getString("correo"));
                u.setProfesion(rs.getString("profesion"));

                lista.add(u);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar usuarios: " + ex.getMessage());
        }
        return lista;
    }
}