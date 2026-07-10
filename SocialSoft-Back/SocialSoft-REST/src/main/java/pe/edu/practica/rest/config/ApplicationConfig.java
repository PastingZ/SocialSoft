package pe.edu.practica.rest.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// Esta etiqueta define la ruta base de todos tus servicios REST.
// Ejemplo: http://localhost:8080/SocialSoft-REST/api/...
@ApplicationPath("/")
public class ApplicationConfig extends Application {
    // ¡Literalmente la clase va vacía!
    // Solo con existir y heredar de Application, Jakarta EE hace toda la magia por detrás.
}