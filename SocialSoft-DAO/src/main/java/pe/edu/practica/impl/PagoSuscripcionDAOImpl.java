package pe.edu.practica.dao.impl;

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
            PreparedStatement pst = conexion.prepareStatement(sql);

            pst.setInt(1, pago.getSuscripcion().getId());
            pst.setInt(2, pago.getMetodoPago().getId());
            pst.setDouble(3, pago.getMonto());
            pst.setDate(4, new java.sql.Date(pago.getFechaPago().getTime()));
            pst.setString(5, pago.getEstado());

            resultado = pst.executeUpdate();
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
            while (rs.next()) {
                PagoSuscripcion p = new PagoSuscripcion();
                p.setId(rs.getInt("id"));
                p.setMonto(rs.getDouble("monto"));
                p.setFechaPago(rs.getDate("fecha_pago"));
                p.setEstado(rs.getString("estado"));

                // Instanciamos solo con los IDs para mantener el performance
                UsuarioCanalSuscripcion ucs = new UsuarioCanalSuscripcion();
                ucs.setId(rs.getInt("id_usuario_canal_suscripcion"));
                p.setSuscripcion(ucs);

                MetodoPago mp = new MetodoPago();
                mp.setId(rs.getInt("id_metodo_pago"));
                p.setMetodoPago(mp);

                lista.add(p);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar pagos: " + ex.getMessage());
        }
        return lista;
    }
}