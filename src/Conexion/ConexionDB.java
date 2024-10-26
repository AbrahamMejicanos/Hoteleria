package Conexion;

import java.sql.*;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos"; // Cambia por tu URL y base de datos
    private static final String USUARIO = "root"; // Cambia por tu usuario
    private static final String CONTRASENA = ""; // Cambia por tu contraseña

    private Connection conexion;

    // Método para establecer la conexión con la base de datos
    public Connection obtenerConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println("Conexión establecida con MySQL");
            }
        } catch (SQLException e) {
            // despues agregar bitacora
        }
        return conexion;
    }

    // Método para ejecutar un Stored Procedure que recibe un objeto Parametro
    public ResultSet ejecutarProcedimiento(String nombreSP, Parametro parametros) {
        ResultSet resultado = null;
        CallableStatement callableStatement = null;
        try {
            Connection conn = obtenerConexion();

            // Preparar la llamada al stored procedure de forma segura
            callableStatement = conn.prepareCall("{CALL " + nombreSP + "(" + obtenerPlaceholders(parametros.contarParametros()) + ")}");

            // Asignamos los parámetros al SP
            for (int i = 0; i < parametros.contarParametros(); i++) {
                callableStatement.setObject(i + 1, parametros.obtenerParametro(i)); // Establecemos el parámetro de forma genérica
            }

            // Ejecutamos y obtenemos los resultados
            resultado = callableStatement.executeQuery();
        } catch (SQLException e) {
            // Agregar bitacora despues
        }
        return resultado;
    }

    // Método auxiliar para crear los placeholders (?)
    private String obtenerPlaceholders(int numeroParametros) {
        if (numeroParametros == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeroParametros; i++) {
            sb.append("?");
            if (i < numeroParametros - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            // Agregar bitacora despues
        }
    }
}
