package pe.edu.practica.impl;

import pe.edu.practica.dao.MetodoPagoDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.MetodoPago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoDAOImpl implements MetodoPagoDAO {

    @Override
    public List<MetodoPago> listarTodos() {
        List<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT * FROM metodo_pago";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar metodos de pago: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public MetodoPago buscarPorId(int id) {
        MetodoPago metodo = null;
        String sql = "SELECT * FROM metodo_pago WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                metodo = mapear(rs);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar metodo de pago por id: " + ex.getMessage());
        }
        return metodo;
    }

    private MetodoPago mapear(ResultSet rs) throws Exception {
        MetodoPago mp = new MetodoPago();
        mp.setId(rs.getInt("id"));
        mp.setNombre(rs.getString("nombre"));
        return mp;
    }
}
