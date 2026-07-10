package pe.edu.practica.impl;

import pe.edu.practica.dao.BeneficioDAO;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Beneficio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeneficioDAOImpl implements BeneficioDAO {

    @Override
    public int insertar(Beneficio beneficio) {
        int resultado = 0;
        String sql = "INSERT INTO beneficio (nombre, descripcion) VALUES (?, ?)";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, beneficio.getNombre());
            pst.setString(2, beneficio.getDescripcion());
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al insertar beneficio: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public List<Beneficio> listarTodos() {
        List<Beneficio> lista = new ArrayList<>();
        String sql = "SELECT * FROM beneficio";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar beneficios: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Beneficio buscarPorId(int id) {
        Beneficio beneficio = null;
        String sql = "SELECT * FROM beneficio WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                beneficio = mapear(rs);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar beneficio por id: " + ex.getMessage());
        }
        return beneficio;
    }

    @Override
    public List<Beneficio> listarPorPlan(int idPlan) {
        List<Beneficio> lista = new ArrayList<>();
        String sql = "SELECT b.* FROM beneficio b " +
                     "INNER JOIN plan_beneficio pb ON b.id = pb.id_beneficio " +
                     "WHERE pb.id_plan_suscripcion = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idPlan);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar beneficios por plan: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM beneficio WHERE id = ?";
        try {
            Connection conexion = DBManager.getInstance().getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            resultado = pst.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al eliminar beneficio: " + ex.getMessage());
        }
        return resultado;
    }

    private Beneficio mapear(ResultSet rs) throws Exception {
        Beneficio b = new Beneficio();
        b.setId(rs.getInt("id"));
        b.setNombre(rs.getString("nombre"));
        b.setDescripcion(rs.getString("descripcion"));
        return b;
    }
}
