package pe.edu.practica.bl;

import pe.edu.practica.dao.UsuarioDAO;
import pe.edu.practica.impl.UsuarioDAOImpl;
import pe.edu.practica.dao.DispositivoUsuarioDAO;
import pe.edu.practica.impl.DispositivoUsuarioDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Usuario;
import pe.edu.practica.model.DispositivoUsuario;

import java.sql.Connection;
import java.util.List;

public class UsuarioBL {
    private UsuarioDAO usuarioDAO;
    private DispositivoUsuarioDAO dispositivoDAO;

    public UsuarioBL() {
        this.usuarioDAO = new UsuarioDAOImpl();
        this.dispositivoDAO = new DispositivoUsuarioDAOImpl();
    }

    public int registrarUsuarioConDispositivo(Usuario usuario, DispositivoUsuario dispositivo) {
        int idUsuarioGenerado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);

            // 1. Insertamos el usuario
            idUsuarioGenerado = usuarioDAO.insertar(usuario);

            if (idUsuarioGenerado > 0) {
                // Asignamos el ID de usuario generado al dispositivo
                usuario.setId(idUsuarioGenerado);
                dispositivo.setUsuario(usuario);

                // 2. Insertamos el dispositivo
                int idDispositivoGenerado = dispositivoDAO.insertar(dispositivo);
                if (idDispositivoGenerado > 0) {
                    conexion.commit();
                } else {
                    throw new Exception("Error al insertar el dispositivo del usuario, se abortará la transacción.");
                }
            } else {
                throw new Exception("Error al insertar el usuario, se abortará la transacción.");
            }
        } catch (Exception ex) {
            System.err.println("Error en BL al registrar usuario con dispositivo (Se ejecuta ROLLBACK): " + ex.getMessage());
            idUsuarioGenerado = 0;
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return idUsuarioGenerado;
    }

    public int insertar(Usuario usuario) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = usuarioDAO.insertar(usuario);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar usuario: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            lista = usuarioDAO.listarTodos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar usuarios: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return lista;
    }

    public Usuario buscarPorId(int id) {
        Usuario usuario = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            usuario = usuarioDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar usuario por id: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return usuario;
    }

    public Usuario buscarPorDni(String dni) {
        Usuario usuario = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            usuario = usuarioDAO.buscarPorDni(dni);
        } catch (Exception ex) {
            System.err.println("Error en BL al buscar usuario por DNI: " + ex.getMessage());
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return usuario;
    }

    public int actualizar(Usuario usuario) {
        int resultado = 0;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            conexion.setAutoCommit(false);
            resultado = usuarioDAO.actualizar(usuario);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al actualizar usuario: " + ex.getMessage());
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
            resultado = usuarioDAO.eliminar(id);
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al eliminar usuario: " + ex.getMessage());
            try { if (conexion != null) conexion.rollback(); } catch (Exception e) {}
        } finally {
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }
        return resultado;
    }
}