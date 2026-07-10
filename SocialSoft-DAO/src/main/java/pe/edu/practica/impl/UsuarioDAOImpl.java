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
        String sql = "INSERT INTO usuario (nombre_completo, dni, edad, ciudad, fecha_nacimiento, telefono, correo, profesion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, usuario.getNombreCompleto());
            pst.setString(2, usuario.getDni());
            pst.setInt(3, usuario.getEdad());
            pst.setString(4, usuario.getCiudad());
            pst.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            pst.setString(6, usuario.getTelefono());
            pst.setString(7, usuario.getCorreo());
            pst.setString(8, usuario.getProfesion());
            pst.executeUpdate();
            
            java.sql.ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
                usuario.setId(resultado);
            }
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
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar usuarios: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Usuario buscarPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = mapear(rs);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar usuario por id: " + ex.getMessage());
        }
        return usuario;
    }

    @Override
    public Usuario buscarPorDni(String dni) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE dni = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = mapear(rs);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar usuario por dni: " + ex.getMessage());
        }
        return usuario;
    }

    @Override
    public int actualizar(Usuario usuario) {
        int resultado = 0;
        String sql = "UPDATE usuario SET nombre_completo = ?, dni = ?, edad = ?, ciudad = ?, fecha_nacimiento = ?, telefono = ?, correo = ?, profesion = ? WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, usuario.getNombreCompleto());
            pst.setString(2, usuario.getDni());
            pst.setInt(3, usuario.getEdad());
            pst.setString(4, usuario.getCiudad());
            pst.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            pst.setString(6, usuario.getTelefono());
            pst.setString(7, usuario.getCorreo());
            pst.setString(8, usuario.getProfesion());
            pst.setInt(9, usuario.getId());
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al actualizar usuario: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al eliminar usuario: " + ex.getMessage());
        }
        return resultado;
    }

    private Usuario mapear(ResultSet rs) throws Exception {
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
        return u;
    }
}