package Hotel;

import Conexion.ConexionDB;
import Conexion.Parametro;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Habitacion {

    private ConexionDB conexionDB;

    // Constructor que inicializa la conexión
    public Habitacion() {
        this.conexionDB = new ConexionDB();
    }

    // Método para registrar una habitación en la base de datos
    public String registrarHabitacion(String numeroHabitacion, String descripcion, int idHotel, int idTipoHabitacion) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(numeroHabitacion);  // Primer parámetro (número de habitación)
        parametros.agregarParametro(descripcion);       // Segundo parámetro (descripción)
        parametros.agregarParametro(idHotel);           // Tercer parámetro (id del hotel)
        parametros.agregarParametro(idTipoHabitacion);  // Cuarto parámetro (id del tipo de habitación)

        // Llamamos al procedimiento almacenado 'registrarHabitacion'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("registrarHabitacion", parametros);

        try {
            if (resultSet != null && resultSet.next()) {
                return "Habitación registrada con éxito.";
            } else {
                return "Error al registrar la habitación.";
            }
        } catch (SQLException e) {
            return "Error inesperado al registrar la habitación.";
        } finally {
            conexionDB.cerrarConexion(); // Cerramos la conexión después de ejecutar
        }
    }

    // Método para obtener la lista de habitaciones de un hotel
    public ResultSet obtenerHabitacionesPorHotel(int idHotel) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(idHotel);  // Agregamos el id del hotel como parámetro

        // Llamamos al procedimiento almacenado 'obtenerHabitacionesPorHotel'
        return conexionDB.ejecutarProcedimiento("obtenerHabitacionesPorHotel", parametros);
    }
}
