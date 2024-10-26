package Hotel;

import Conexion.ConexionDB;
import Conexion.Parametro;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hotel {

    private ConexionDB conexionDB;

    // Constructor que inicializa la conexión
    public Hotel() {
        this.conexionDB = new ConexionDB();
    }

    // Método para registrar un hotel en la base de datos
    public String registrarHotel(String nombre, String direccion) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(nombre);    // Primer parámetro (nombre del hotel)
        parametros.agregarParametro(direccion); // Segundo parámetro (dirección del hotel)

        // Llamamos al procedimiento almacenado 'registrarHotel'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("registrarHotel", parametros);

        try {
            if (resultSet != null && resultSet.next()) {
                return "Hotel registrado con éxito.";
            } else {
                return "Error al registrar el hotel.";
            }
        } catch (SQLException e) {
            return "Error inesperado al registrar el hotel.";
        } finally {
            conexionDB.cerrarConexion(); // Cerramos la conexión después de ejecutar
        }
    }

    // Método para obtener la lista de hoteles
    public ResultSet obtenerHoteles() {
        // Llamamos al procedimiento almacenado 'obtenerHoteles'
        return conexionDB.ejecutarProcedimiento("obtenerHoteles", new Parametro());
    }
}
