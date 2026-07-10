package pe.edu.practica.dbmanager;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBManager {
    private static DBManager instance = null;
    private Connection connection = null;

    // Variables para capturar tus properties
    private String host;
    private String puerto;
    private String esquema;
    private String usuario;
    private String password;

    // Aquí guardaremos la URL final ya armada
    private String urlCompleta;

    private DBManager() {
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
            properties.load(is);

            // Leemos exactamente los nombres que pusiste en tu properties
            this.host = properties.getProperty("db.url");
            this.puerto = properties.getProperty("db.puerto");
            this.esquema = properties.getProperty("db.esquema");
            this.usuario = properties.getProperty("db.usuario");
            this.password = properties.getProperty("db.password");

            // ¡MAGIA APLICADA! Armamos la cadena JDBC desactivando el SSL problemático de GlassFish
            this.urlCompleta = "jdbc:mysql://" + this.host + ":" + this.puerto + "/" + this.esquema + "?useSSL=false&allowPublicKeyRetrieval=true";

            // Cargar el driver para GlassFish
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Error al leer db.properties: " + e.getMessage());
        }
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Le pasamos la URL ya armada y tus credenciales
                connection = DriverManager.getConnection(urlCompleta, usuario, password);
            }
        } catch (Exception ex) {
            System.err.println("Error al conectar a la BD: " + ex.getMessage());
        }
        return connection;
    }
}