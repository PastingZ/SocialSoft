package pe.edu.practica.impl;

import pe.edu.practica.dao.PagoSuscripcionDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.MetodoPago;
import pe.edu.practica.model.PagoSuscripcion;
import pe.edu.practica.model.UsuarioCanalSuscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PagoSuscripcionDAOImpl implements PagoSuscripcionDAO {

    @Override
    public int insertar(PagoSuscripcion pago) {
        int resultado = 0;
        String sql = "INSERT INTO pago_suscripcion (id_usuario_canal_suscripcion, id_metodo_pago, monto, fecha_pago, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, pago.getSuscripcion().getId());
            pst.setInt(2, pago.getMetodoPago().getId());
            pst.setDouble(3, pago.getMonto());
            pst.setDate(4, new java.sql.Date(pago.getFechaPago().getTime()));
            pst.setString(5, pago.getEstado());
            pst.executeUpdate();
            
            java.sql.ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
                pago.setId(resultado);
            }
        } catch (Exception ex) {
            System.err.println("Error al insertar pago: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public List<PagoSuscripcion> listarPorSuscripcion(int idSuscripcion) {
        List<PagoSuscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago_suscripcion WHERE id_usuario_canal_suscripcion = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idSuscripcion);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (Exception ex) {
            System.err.println("Error al listar pagos por suscripcion: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public PagoSuscripcion buscarPorId(int id) {
        PagoSuscripcion pago = null;
        String sql = "SELECT * FROM pago_suscripcion WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) { pago = mapear(rs); }
        } catch (Exception ex) {
            System.err.println("Error al buscar pago por id: " + ex.getMessage());
        }
        return pago;
    }

    @Override
    public List<PagoSuscripcion> listarPorUsuario(int idUsuario) {
        List<PagoSuscripcion> lista = new ArrayList<>();
        String sql = "SELECT p.* FROM pago_suscripcion p " +
                     "INNER JOIN usuario_canal_suscripcion ucs ON p.id_usuario_canal_suscripcion = ucs.id " +
                     "WHERE ucs.id_usuario = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (Exception ex) {
            System.err.println("Error al listar pagos por usuario: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public List<PagoSuscripcion> listarPorEstado(String estado) {
        List<PagoSuscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago_suscripcion WHERE estado = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, estado);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (Exception ex) {
            System.err.println("Error al listar pagos por estado: " + ex.getMessage());
        }
        return lista;
    }

    private PagoSuscripcion mapear(ResultSet rs) throws Exception {
        PagoSuscripcion p = new PagoSuscripcion();
        p.setId(rs.getInt("id"));
        p.setMonto(rs.getDouble("monto"));
        p.setFechaPago(rs.getDate("fecha_pago"));
        p.setEstado(rs.getString("estado"));
        UsuarioCanalSuscripcion ucs = new UsuarioCanalSuscripcion();
        ucs.setId(rs.getInt("id_usuario_canal_suscripcion"));
        p.setSuscripcion(ucs);
        MetodoPago mp = new MetodoPago();
        mp.setId(rs.getInt("id_metodo_pago"));
        p.setMetodoPago(mp);
        return p;
    }
}