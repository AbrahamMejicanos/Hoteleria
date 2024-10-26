package Conexion;

import java.util.ArrayList;
import java.util.List;

public class Parametro {

    private List<Object> parametros;

    // Constructor
    public Parametro() {
        parametros = new ArrayList<>();
    }

    // Método para agregar un parámetro
    public void agregarParametro(Object valor) {
        parametros.add(valor);
    }

    // Método para obtener todos los parámetros
    public List<Object> obtenerParametros() {
        return parametros;
    }

    // Método para obtener el número de parámetros
    public int contarParametros() {
        return parametros.size();
    }

    // Método para obtener un parámetro por su índice
    public Object obtenerParametro(int indice) {
        if (indice >= 0 && indice < parametros.size()) {
            return parametros.get(indice);
        } else {
            // agregar bitacora despues
        }
        return null;
    }
}
