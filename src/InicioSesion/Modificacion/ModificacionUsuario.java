package InicioSesion.Modificacion;

import Conexion.ConexionDB;
import Conexion.Parametro;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificacionUsuario {

    private ConexionDB conexionDB;

    // Constructor que inicializa la conexión
    public ModificacionUsuario() {
        this.conexionDB = new ConexionDB();
    }

    // Método para modificar el nombre del usuario
    public boolean modificarNombreUsuario(int idUsuario, String nuevoNombre) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(idUsuario);   // Primer parámetro (ID del usuario)
        parametros.agregarParametro(nuevoNombre); // Segundo parámetro (nuevo nombre)

        // Llamamos al procedimiento almacenado 'modificarNombreUsuario'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("modificarNombreUsuario", parametros);

        return procesarResultado(resultSet);
    }

    // Método para modificar el email del usuario
    public boolean modificarEmailUsuario(int idUsuario, String nuevoEmail) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(idUsuario);   // Primer parámetro (ID del usuario)
        parametros.agregarParametro(nuevoEmail);  // Segundo parámetro (nuevo email)

        // Llamamos al procedimiento almacenado 'modificarEmailUsuario'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("modificarEmailUsuario", parametros);

        return procesarResultado(resultSet);
    }

    // Método para modificar la contraseña del usuario
    public boolean modificarContraseniaUsuario(int idUsuario, String nuevaContrasenia) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(idUsuario);      // Primer parámetro (ID del usuario)
        parametros.agregarParametro(nuevaContrasenia); // Segundo parámetro (nueva contraseña)

        // Llamamos al procedimiento almacenado 'modificarContraseniaUsuario'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("modificarContraseniaUsuario", parametros);

        return procesarResultado(resultSet);
    }

    // Método para modificar el tipo de usuario (cliente o empleado)
    public boolean modificarTipoUsuario(int idUsuario, int nuevoTipoUsuario) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(idUsuario);       // Primer parámetro (ID del usuario)
        parametros.agregarParametro(nuevoTipoUsuario); // Segundo parámetro (nuevo tipo de usuario)

        // Llamamos al procedimiento almacenado 'modificarTipoUsuario'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("modificarTipoUsuario", parametros);

        return procesarResultado(resultSet);
    }

    // Método auxiliar para procesar el resultado de la modificación
    private boolean procesarResultado(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt(1) == 1; // Suponemos que el SP devuelve 1 si se realizó correctamente
            }
        } catch (SQLException e) {
            // Agregar bitacora despues
        } finally {
            conexionDB.cerrarConexion();
        }
        return false;
    }
}
