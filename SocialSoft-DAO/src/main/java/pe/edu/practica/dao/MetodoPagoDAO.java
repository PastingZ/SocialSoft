package pe.edu.practica.dao;

import pe.edu.practica.model.MetodoPago;
import java.util.List;

public interface MetodoPagoDAO {
    List<MetodoPago> listarTodos();
    MetodoPago buscarPorId(int id);
}
