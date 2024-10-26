package InicioSesion.InicioSesion;

import Conexion.ConexionDB;
import Conexion.Parametro;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesion {

    private ConexionDB conexionDB;

    // Constructor que inicializa la conexión
    public InicioSesion() {
        this.conexionDB = new ConexionDB();
    }

    // Método para verificar el inicio de sesión de un usuario
    public String verificarCredenciales(String email, String contrasenia) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(email);          // Primer parámetro (email)
        parametros.agregarParametro(contrasenia);    // Segundo parámetro (contraseña)

        // Llamamos al procedimiento almacenado 'verificarCredenciales'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("verificarCredenciales", parametros);

        try {
            if (resultSet != null && resultSet.next()) {
                // Supongamos que el stored procedure devuelve el nombre del usuario si las credenciales son correctas
                return resultSet.getString("nombre");
            }
        } catch (SQLException e) {
            // Agregar bitacora despues
        } finally {
            conexionDB.cerrarConexion();
        }
        return null;  // Si las credenciales no son válidas, devolvemos null
    }

    // Método para obtener el tipo de usuario (cliente, empleado, etc.)
    public String obtenerTipoUsuario(String email) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(email); // Primer parámetro (email)

        // Llamamos al procedimiento almacenado 'obtenerTipoUsuario'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("obtenerTipoUsuario", parametros);

        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getString("tipoUsuario");
            }
        } catch (SQLException e) {
            // Agregar Bitacora despues
        } finally {
            conexionDB.cerrarConexion();
        }
        return null;  // Si no se encuentra el tipo de usuario, devolvemos null
    }
}
