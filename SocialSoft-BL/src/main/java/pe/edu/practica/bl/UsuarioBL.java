package pe.edu.practica.bl;

import pe.edu.practica.dao.UsuarioDAO;
import pe.edu.practica.impl.UsuarioDAOImpl;
import pe.edu.practica.dbmanager.DBManager;
import pe.edu.practica.model.Usuario;

import java.sql.Connection;
import java.util.List;

public class UsuarioBL {
    private UsuarioDAO usuarioDAO;

    public UsuarioBL() {
        // Instanciamos el DAO
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public int insertar(Usuario usuario) {
        int resultado = 0;
        Connection conexion = null;
        try {
            // 1. Obtenemos la conexión desde el DBManager
            conexion = DBManager.getInstance().getConnection();

            // 2. APAGAMOS EL AUTO-COMMIT (La regla de oro del profe)
            conexion.setAutoCommit(false);

            // 3. Ejecutamos el DAO (que solo hace el INSERT, no cierra nada)
            resultado = usuarioDAO.insertar(usuario);

            // 4. Si todo salió bien, hacemos el COMMIT definitivo
            conexion.commit();
        } catch (Exception ex) {
            System.err.println("Error en BL al insertar: " + ex.getMessage());
            try {
                // 5. Si algo explotó, hacemos ROLLBACK para proteger la base de datos
                if (conexion != null) conexion.rollback();
            } catch (Exception e) {
                System.err.println("Error al hacer rollback: " + e.getMessage());
            }
        } finally {
            try {
                // 6. SIEMPRE cerramos la conexión al final del proceso en el BL
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return resultado;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = null;
        Connection conexion = null;
        try {
            conexion = DBManager.getInstance().getConnection();
            // Para SELECTs simples no hace falta el control transaccional estricto
            lista = usuarioDAO.listarTodos();
        } catch (Exception ex) {
            System.err.println("Error en BL al listar: " + ex.getMessage());
        } finally {
            try {
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return lista;
    }
}