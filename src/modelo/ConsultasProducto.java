package modelo;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConsultasProducto extends Conexion {

    // Se conecta a la base de datos utilizando el método de la clase padre
    Connection Con = getConexion();

    public boolean insertar(ModeloProducto Modelo) {
        try {
            PreparedStatement Ps;
            String SQL = "insert into productos (codigo, descripcion, precio) values (?, ?, ?)";
            Ps = Con.prepareStatement(SQL);
            Ps.setInt(1, Modelo.getCodigo());
            Ps.setString(2, Modelo.getDescripcion());
            Ps.setFloat(3, Modelo.getPrecio());
            Ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
            return false;
        }
    }

    public boolean modificar(ModeloProducto Modelo) {
        try {
            PreparedStatement Ps;
            String SQL = "update productos set descripcion=?, precio=? where codigo=?";
            Ps = Con.prepareStatement(SQL);
            Ps.setString(1, Modelo.getDescripcion());
            Ps.setFloat(2, Modelo.getPrecio());
            Ps.setInt(3, Modelo.getCodigo());
            Ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
            return false;
        }
    }

    public boolean eliminar(ModeloProducto Modelo) {
        try {
            PreparedStatement Ps;
            String SQL = "delete from productos where codigo=?";
            Ps = Con.prepareStatement(SQL);
            Ps.setInt(1, Modelo.getCodigo());
            Ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
            return false;
        }
    }

    public boolean buscar(ModeloProducto Modelo) {
        try {
            PreparedStatement Ps;
            String SQL = "select * from productos where codigo = ?";
            Ps = Con.prepareStatement(SQL);
            Ps.setInt(1, Modelo.getCodigo());
            ResultSet Rs = Ps.executeQuery();

            if(Rs.next()) {
                Modelo.setDescripcion(Rs.getString("descripcion"));
                Modelo.setPrecio(Rs.getFloat("precio"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e);
            return false;
        }
    }
}