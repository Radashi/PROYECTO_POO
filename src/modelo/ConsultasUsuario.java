package modelo;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ModeloUsuario;

public class ConsultasUsuario extends Conexion {

    // Se obtiene la conexión de la clase padre (Fig. 13)
    Connection Con = getConexion();

    public boolean buscarLogin(ModeloUsuario Modelo) {
        try {
            // Variable para preparar la consulta SQL
            PreparedStatement Ps;
            // Consulta SQL para validar usuario y password (Fig. 13)
            String SQL = "select * from usuarios where usuario = ? and password = ?";

            Ps = Con.prepareStatement(SQL);

            // Reemplaza los "?" por los datos del modelo (Fig. 13)
            Ps.setString(1, Modelo.getUsuario());
            Ps.setString(2, Modelo.getPassword());

            // Ejecuta la consulta
            ResultSet Rs = Ps.executeQuery();

            if (Rs.next()) {
                // Si lo encuentra, llena los datos faltantes en el modelo (Nombre y Tipo)
                Modelo.setNombre(Rs.getString("Nombre"));
                Modelo.setTipo(Rs.getString("Tipo"));
                return true; // Inicio de sesión exitoso
            }

            return false; // No se encontró el usuario

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
            return false;
        }
    }
}