package modelo;

import conexion.Conexion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConsultasUsuario extends Conexion {

    // Se obtiene la conexión de la clase padre
    Connection Con = getConexion();

    // Método nativo para encriptar cualquier contraseña en SHA-256
    public String encriptarSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Error en el algoritmo de encriptación: " + e);
            return null;
        }
    }

    public boolean buscarLogin(ModeloUsuario Modelo) {
        try {
            PreparedStatement Ps;
            // Consulta SQL para validar usuario y password
            String SQL = "select * from usuarios where usuario = ? and password = ?";

            Ps = Con.prepareStatement(SQL);

            // Reemplaza los "?" por los datos del modelo
            Ps.setString(1, Modelo.getUsuario());

            // Encriptamos el password que el usuario escribió para poder compararlo con el hash de la BD
            String passEncriptado = encriptarSHA256(Modelo.getPassword());
            Ps.setString(2, passEncriptado);
            // AGREGA ESTA LÍNEA PARA VER QUÉ ESTÁ PASANDO:
            System.out.println("Intentando iniciar sesión con hash: " + passEncriptado);

            // Ejecuta la consulta
            ResultSet Rs = Ps.executeQuery();

            if (Rs.next()) {
                // Si lo encuentra, llena los datos faltantes en el modelo (Nombre y Tipo)
                Modelo.setNombre(Rs.getString("Nombre"));
                Modelo.setTipo(Rs.getString("Tipo"));
                return true; // Inicio de sesión exitoso
            }

            return false; // No se encontró el usuario o la contraseña no coincide

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
            return false;
        }
    }
}