package pe.edu.practica.impl;

import pe.edu.practica.dao.PlanSuscripcionDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.PlanSuscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlanSuscripcionDAOImpl implements PlanSuscripcionDAO {

    @Override
    public int insertar(PlanSuscripcion plan) {
        int resultado = 0;
        String sql = "INSERT INTO plan_suscripcion (nombre, costo_mensual) VALUES (?, ?)";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, plan.getNombre());
            pst.setDouble(2, plan.getCostoMensual());
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al insertar plan: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public List<PlanSuscripcion> listarTodos() {
        List<PlanSuscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM plan_suscripcion";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar planes: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public PlanSuscripcion buscarPorId(int id) {
        PlanSuscripcion plan = null;
        String sql = "SELECT * FROM plan_suscripcion WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                plan = mapear(rs);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar plan por id: " + ex.getMessage());
        }
        return plan;
    }

    @Override
    public int actualizar(PlanSuscripcion plan) {
        int resultado = 0;
        String sql = "UPDATE plan_suscripcion SET nombre = ?, costo_mensual = ? WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, plan.getNombre());
            pst.setDouble(2, plan.getCostoMensual());
            pst.setInt(3, plan.getId());
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al actualizar plan: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM plan_suscripcion WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al eliminar plan: " + ex.getMessage());
        }
        return resultado;
    }

    private PlanSuscripcion mapear(ResultSet rs) throws Exception {
        PlanSuscripcion p = new PlanSuscripcion();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setCostoMensual(rs.getDouble("costo_mensual"));
        return p;
    }
}
