package modelo;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConsultasProducto extends Conexion {

    // 1. GUARDAR (ALTA)
    public boolean registrar(ModeloProducto modelo) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO productos (codigo, descripcion, precio) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, modelo.getCodigo());
            ps.setString(2, modelo.getDescripcion());
            ps.setFloat(3, modelo.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    // 2. BUSCAR
    public boolean buscar(ModeloProducto modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, modelo.getCodigo());
            rs = ps.executeQuery();
            if (rs.next()) {
                modelo.setDescripcion(rs.getString("descripcion"));
                modelo.setPrecio(rs.getFloat("precio"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
            return false;
        }
    }

    // 3. MODIFICAR
    public boolean modificar(ModeloProducto modelo) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE productos SET descripcion = ?, precio = ? WHERE codigo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modelo.getDescripcion());
            ps.setFloat(2, modelo.getPrecio());
            ps.setInt(3, modelo.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar producto: " + e.getMessage());
            return false;
        }
    }

    // 4. ELIMINAR
    public boolean eliminar(ModeloProducto modelo) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM productos WHERE codigo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, modelo.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}