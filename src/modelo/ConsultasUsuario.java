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
    // ==========================================
    // 1. ALTA (GUARDAR USUARIO CON SHA-256)
    // ==========================================
    public boolean registrar(ModeloUsuario Modelo) {
        PreparedStatement Ps = null;
        Connection Con = getConexion();
        String SQL = "INSERT INTO usuarios (Usuario, Password, Nombre, Tipo) VALUES (?, ?, ?, ?)";

        try {
            Ps = Con.prepareStatement(SQL);
            Ps.setString(1, Modelo.getUsuario());

            // ENCRIPTAMOS LA CONTRASEÑA ANTES DE GUARDAR
            String passEncriptado = encriptarSHA256(Modelo.getPassword());
            Ps.setString(2, passEncriptado);

            Ps.setString(3, Modelo.getNombre());
            Ps.setString(4, Modelo.getTipo());

            Ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // 2. BUSCAR
    // ==========================================
    public boolean buscar(ModeloUsuario Modelo) {
        PreparedStatement Ps = null;
        ResultSet Rs = null;
        Connection Con = getConexion();
        String SQL = "SELECT * FROM usuarios WHERE Usuario = ?";

        try {
            Ps = Con.prepareStatement(SQL);
            Ps.setString(1, Modelo.getUsuario());
            Rs = Ps.executeQuery();

            if (Rs.next()) {
                Modelo.setPassword(Rs.getString("Password")); // Trae el hash
                Modelo.setNombre(Rs.getString("Nombre"));
                Modelo.setTipo(Rs.getString("Tipo"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // 3. MODIFICAR (ACTUALIZAR)
    // ==========================================
    public boolean modificar(ModeloUsuario Modelo) {
        PreparedStatement Ps = null;
        Connection Con = getConexion();
        // Nota: Asumimos que si modifica, pone una contraseña nueva, así que la encriptamos.
        String SQL = "UPDATE usuarios SET Password = ?, Nombre = ?, Tipo = ? WHERE Usuario = ?";

        try {
            Ps = Con.prepareStatement(SQL);
            String passEncriptado = encriptarSHA256(Modelo.getPassword());
            Ps.setString(1, passEncriptado);
            Ps.setString(2, Modelo.getNombre());
            Ps.setString(3, Modelo.getTipo());
            Ps.setString(4, Modelo.getUsuario());

            Ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // 4. ELIMINAR (BAJA)
    // ==========================================
    public boolean eliminar(ModeloUsuario Modelo) {
        PreparedStatement Ps = null;
        Connection Con = getConexion();
        String SQL = "DELETE FROM usuarios WHERE Usuario = ?";

        try {
            Ps = Con.prepareStatement(SQL);
            Ps.setString(1, Modelo.getUsuario());
            Ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}