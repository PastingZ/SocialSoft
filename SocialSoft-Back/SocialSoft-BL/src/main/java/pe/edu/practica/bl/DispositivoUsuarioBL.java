package pe.edu.practica.bl;

import pe.edu.practica.dao.DispositivoUsuarioDAO;
import pe.edu.practica.impl.DispositivoUsuarioDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.DispositivoUsuario;

import java.sql.Connection;
import java.util.List;

public class DispositivoUsuarioBL {
    private DispositivoUsuarioDAO dispositivoDAO;

    public DispositivoUsuarioBL() {
        this.dispositivoDAO = new DispositivoUsuarioDAOImpl();
    }

    public int insertar(DispositivoUsuario dispositivo) {
        int resultado = 0;
        Connection conexion = null;
        try {
            // Validacion 2: Límite de dispositivos dinámico según el plan del usuario
            pe.edu.practica.dao.UsuarioCanalSuscripcionDAO suscripcionDAO = new pe.edu.practica.impl.UsuarioCanalSuscripcionDAOImpl();
            pe.edu.practica.dao.PlanSuscripcionDAO planDAO = new pe.edu.practica.impl.PlanSuscripcionDAOImpl();
            
            int maxDispositivosPermitidos = 0;
            java.util.List<pe.edu.practica.model.UsuarioCanalSuscripcion> suscripciones = suscripcionDAO.listarSuscripcionesPorUsuario(dispositivo.getUsuario().getId());
            
            for (pe.edu.practica.model.UsuarioCanalSuscripcion sub : suscripciones) {
                if ("ACTIVO".equalsIgnoreCase(sub.getEstado()) || "ACTIVA".equalsIgnoreCase(sub.getEstado())) {
                    pe.edu.practica.model.PlanSuscripcion plan = planDAO.buscarPorId(sub.getPlanSuscripcion().getId());
                    if (plan != null) {
                        int limitePlan = 0;
                        String nombrePlan = plan.getNombre().toUpperCase();
                        if (nombrePlan.contains("VIP")) {
                            limitePlan = 4;
                        } else if (nombrePlan.contains("PREMIUM")) {
                            limitePlan = 2;
                        } else if (nombrePlan.contains("BASIC") || nombrePlan.contains("BÁSIC")) {
                            limitePlan = 1;
                        }
                        if (limitePlan > maxDispositivosPermitidos) {
                            maxDispositivosPermitidos = limitePlan;
                        }
                    }
                }
            }

            if (maxDispositivosPermitidos == 0) {
                throw new Exception("El usuario no tiene planes activos que permitan registrar dispositivos.");
            }

            int activos = dispositivoDAO.contarDispositivosActivos(dispositivo.getUsuario().getId());
            if (activos >= maxDispositivosPermitidos) {
                throw new Exception("Se alcanzó el límite máximo de dispositivos (" + maxDispositivosPermitidos + ") según sus planes contratados.");
            }

            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = dispositivoDAO.insertar(dispositivo);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar dispositivo: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<DispositivoUsuario> listarPorUsuario(int idUsuario) {
        List<DispositivoUsuario> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = dispositivoDAO.listarPorUsuario(idUsuario);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar dispositivos: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public DispositivoUsuario buscarPorId(int id) {
        DispositivoUsuario dispositivo = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            dispositivo = dispositivoDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar dispositivo por id: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return dispositivo;
    }

    public List<DispositivoUsuario> listarActivosPorUsuario(int idUsuario) {
        List<DispositivoUsuario> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = dispositivoDAO.listarActivosPorUsuario(idUsuario);
        } catch (Exception ex) {
            System.err.println("Error en BL al listar dispositivos activos: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public int actualizar(DispositivoUsuario dispositivo) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = dispositivoDAO.actualizar(dispositivo);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al actualizar dispositivo: " + ex.getMessage());
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
            resultado = dispositivoDAO.eliminar(id);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al eliminar dispositivo: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }
}