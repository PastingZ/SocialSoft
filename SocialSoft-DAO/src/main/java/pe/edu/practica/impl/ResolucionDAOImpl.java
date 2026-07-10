package pe.edu.practica.impl;

import pe.edu.practica.dao.ResolucionDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Resolucion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResolucionDAOImpl implements ResolucionDAO {

    @Override
    public List<Resolucion> listarTodos() {
        List<Resolucion> lista = new ArrayList<>();
        String sql = "SELECT * FROM resolucion";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar resoluciones: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Resolucion buscarPorId(int id) {
        Resolucion resolucion = null;
        String sql = "SELECT * FROM resolucion WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                resolucion = mapear(rs);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar resolucion por id: " + ex.getMessage());
        }
        return resolucion;
    }

    @Override
    public List<Resolucion> listarPorPlan(int idPlan) {
        List<Resolucion> lista = new ArrayList<>();
        String sql = "SELECT r.* FROM resolucion r " +
                     "INNER JOIN plan_resolucion pr ON r.id = pr.id_resolucion " +
                     "WHERE pr.id_plan_suscripcion = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idPlan);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar resoluciones por plan: " + ex.getMessage());
        }
        return lista;
    }

    private Resolucion mapear(ResultSet rs) throws Exception {
        Resolucion r = new Resolucion();
        r.setId(rs.getInt("id"));
        r.setNombre(rs.getString("nombre"));
        r.setDescripcion(rs.getString("descripcion"));
        return r;
    }
}
