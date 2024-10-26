package InicioSesion.RegistrarUsuario;

import Conexion.ConexionDB;
import Conexion.Parametro;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Registro {

    private ConexionDB conexionDB;

    // Constructor que inicializa la conexión
    public Registro() {
        this.conexionDB = new ConexionDB();
    }

    // Método para registrar un usuario en la base de datos
    public String registrarUsuario(String nombre, String email, String contrasenia, int idTipoUsuario) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(nombre);         // Primer parámetro (nombre)
        parametros.agregarParametro(email);          // Segundo parámetro (email)
        parametros.agregarParametro(contrasenia);    // Tercer parámetro (contraseña)
        parametros.agregarParametro(idTipoUsuario);  // Cuarto parámetro (id del tipo de usuario)

        // Llamamos al procedimiento almacenado 'registrarUsuario'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("registrarUsuario", parametros);

        try {
            if (resultSet != null && resultSet.next()) {
                return "Usuario registrado con éxito.";
            } else {
                return "Error al registrar el usuario.";
            }
        } catch (SQLException e) {
            return "Ocurrio un error inesperado";
            // Agregar Bitacora despues
        } finally {
            conexionDB.cerrarConexion(); // Cerramos la conexión después de ejecutar
        }
    }

    // Método para registrar un cliente en la base de datos
    public String registrarCliente(String nombre, String email, String telefono) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(nombre);   // Primer parámetro (nombre)
        parametros.agregarParametro(email);    // Segundo parámetro (email)
        parametros.agregarParametro(telefono); // Tercer parámetro (teléfono)

        // Llamamos al procedimiento almacenado 'registrarCliente'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("registrarCliente", parametros);

        try {
            if (resultSet != null && resultSet.next()) {
                return "Cliente registrado con éxito.";
            } else {
                return "Error al registrar el cliente.";
            }
        } catch (SQLException e) {
            return "Error al procesar los resultados del registro.";
        } finally {
            conexionDB.cerrarConexion(); // Cerramos la conexión después de ejecutar
        }
    }

    // Método para registrar un empleado en la base de datos
    public String registrarEmpleado(String nombre, String email, int idUsuario, int idHotel) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(nombre);   // Primer parámetro (nombre)
        parametros.agregarParametro(email);    // Segundo parámetro (email)
        parametros.agregarParametro(idUsuario); // Tercer parámetro (id del usuario)
        parametros.agregarParametro(idHotel);   // Cuarto parámetro (id del hotel)

        // Llamamos al procedimiento almacenado 'registrarEmpleado'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("registrarEmpleado", parametros);

        try {
            if (resultSet != null && resultSet.next()) {
                return "Empleado registrado con éxito.";
            } else {
                return "Error al registrar el empleado.";
            }
        } catch (SQLException e) {
            return "Error al procesar los resultados del registro.";
        } finally {
            conexionDB.cerrarConexion(); // Cerramos la conexión después de ejecutar
        }
    }

    // Método para registrar un usuario según su tipo (cliente o empleado)
    public void registrarUsuarioConTipo(String nombre, String email, String contrasenia, int idTipoUsuario, String telefono, int idHotel) {
        // Registramos al usuario en la tabla usuario
        registrarUsuario(nombre, email, contrasenia, idTipoUsuario);

        // Si es un cliente
        if (idTipoUsuario == 2) { // Suponemos que 2 es el tipo 'Cliente'
            registrarCliente(nombre, email, telefono);
        }
        // Si es un empleado
        else if (idTipoUsuario == 1) { // Suponemos que 1 es el tipo 'Empleado'
            int idUsuario = obtenerIdUsuario(email); // Obtenemos el ID del usuario recién creado
            registrarEmpleado(nombre, email, idUsuario, idHotel);
        }
    }

    // Método auxiliar para obtener el ID de usuario a partir del email
    private int obtenerIdUsuario(String email) {
        Parametro parametros = new Parametro();
        parametros.agregarParametro(email); // Agregamos el email como parámetro

        // Llamamos al procedimiento almacenado 'obtenerIdUsuarioPorEmail'
        ResultSet resultSet = conexionDB.ejecutarProcedimiento("obtenerIdUsuarioPorEmail", parametros);

        int idUsuario = -1;

        try {
            if (resultSet != null && resultSet.next()) {
                idUsuario = resultSet.getInt("idUsuario");
            }
        } catch (SQLException e) {
            // Agregar bitacora despues
        } finally {
            conexionDB.cerrarConexion(); // Cerramos la conexión después de ejecutar
        }

        return idUsuario;
    }
}
