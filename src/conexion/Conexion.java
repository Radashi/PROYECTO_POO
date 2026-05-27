package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    // Datos necesarios que cambian de acuerdo a tu servidor MySQL
    private final String Servidor = "localhost";
    private final String Database = "tiendita";
    private final String Usuario = "root"; // "root" es el usuario por defecto en XAMPP/MySQL
    private final String Password = ""; // Déjalo vacío si usas XAMPP en Windows, o pon tu contraseña si definiste una

    // Datos de la ruta de conexión
    private final String Puerto = "3306"; // Puerto por defecto de MySQL
    private final String Url = "jdbc:mysql://" + Servidor + ":" + Puerto + "/" + Database;

    // Variable que guardará la conexión
    private static Connection Con;

    // Constructor
    public Conexion() {
        try {
            // Llama al driver JDBC que instalaste
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intenta establecer la conexión con los datos proporcionados
            Con = (Connection) DriverManager.getConnection(Url, Usuario, Password);
            System.out.println("Conexión establecida con la base de datos " + Database);

            // Si quieres que salga una ventanita confirmando la conexión cada que inicias, descomenta la siguiente línea:
            // JOptionPane.showMessageDialog(null, "Conexión establecida con la base de datos " + Database);

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e);
        }
    }

    // Método que nos permite obtener la conexión desde otras clases (como ConsultasUsuario o ConsultasProducto)
    public Connection getConexion() {
        return Con;
    }
}