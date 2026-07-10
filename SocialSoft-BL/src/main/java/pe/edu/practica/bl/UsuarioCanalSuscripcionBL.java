package pe.edu.practica.bl;

import pe.edu.practica.dao.UsuarioCanalSuscripcionDAO;
import pe.edu.practica.impl.UsuarioCanalSuscripcionDAOImpl;
import pe.edu.practica.dao.PagoSuscripcionDAO;
import pe.edu.practica.impl.PagoSuscripcionDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.UsuarioCanalSuscripcion;
import pe.edu.practica.model.PagoSuscripcion;

import java.sql.Connection;
import java.util.List;

public class UsuarioCanalSuscripcionBL {
    private UsuarioCanalSuscripcionDAO suscripcionDAO;
    private PagoSuscripcionDAO pagoDAO;

    public UsuarioCanalSuscripcionBL() {
        this.suscripcionDAO = new UsuarioCanalSuscripcionDAOImpl();
        this.pagoDAO = new PagoSuscripcionDAOImpl();
    }

    public int registrarSuscripcionConPago(UsuarioCanalSuscripcion suscripcion, PagoSuscripcion pago) {
        int idSuscripcionGenerado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            
            // 1. Insertamos la suscripción
            idSuscripcionGenerado = suscripcionDAO.insertar(suscripcion);
            
            if (idSuscripcionGenerado > 0) {
                // Asignamos el ID generado de la suscripción al objeto Pago
                suscripcion.setId(idSuscripcionGenerado);
                pago.setSuscripcion(suscripcion);
                
                // 2. Insertamos el pago
                int idPagoGenerado = pagoDAO.insertar(pago);
                if (idPagoGenerado > 0) {
                    conexion.commit();
                } else {
                    throw new Exception("Error al insertar el pago de la suscripción, se abortará la transacción.");
                }
            } else {
                throw new Exception("Error al insertar la suscripción, se abortará la transacción.");
            }
        } catch (Exception ex) {
            System.err.println("Error en BL al registrar suscripción con pago (Se ejecuta ROLLBACK): " + ex.getMessage());
            idSuscripcionGenerado = 0;
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return idSuscripcionGenerado;
    }

    public int insertar(UsuarioCanalSuscripcion suscripcion) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = suscripcionDAO.insertar(suscripcion);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar suscripcion: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<UsuarioCanalSuscripcion> listarSuscripcionesPorUsuario(int idUsuario) {
        List<UsuarioCanalSuscripcion> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = suscripcionDAO.listarSuscripcionesPorUsuario(idUsuario);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar suscripciones: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public UsuarioCanalSuscripcion buscarPorId(int id) {
        UsuarioCanalSuscripcion ucs = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            ucs = suscripcionDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar suscripcion: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return ucs;
    }

    public List<UsuarioCanalSuscripcion> listarActivas() {
        return listarPorEstado("activa");
    }

    public List<UsuarioCanalSuscripcion> listarPorEstado(String estado) {
        List<UsuarioCanalSuscripcion> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = suscripcionDAO.listarPorEstado(estado);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar suscripciones por estado: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public int cambiarEstado(int id, String nuevoEstado) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = suscripcionDAO.cambiarEstado(id, nuevoEstado);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al cambiar estado de suscripcion: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public int actualizar(UsuarioCanalSuscripcion suscripcion) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = suscripcionDAO.actualizar(suscripcion);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al actualizar suscripcion: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public int eliminar(int id) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = suscripcionDAO.eliminar(id);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al eliminar suscripcion: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }
}