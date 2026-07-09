package pe.edu.practica.dao;

import pe.edu.practica.model.PagoSuscripcion;
import java.util.List;

public interface PagoSuscripcionDAO {
    int insertar(PagoSuscripcion pago);
    List<PagoSuscripcion> listarPorSuscripcion(int idSuscripcion);
}