package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import modelo.ConsultasProducto;
import modelo.ModeloProducto;
import vista.VistaProductos;

public class ControladorVistaProductos implements ActionListener {

    private VistaProductos vista;
    private ModeloProducto modelo;
    private ConsultasProducto consultas;

    public ControladorVistaProductos(VistaProductos vista, ModeloProducto modelo, ConsultasProducto consultas) {
        this.vista = vista;
        this.modelo = modelo;
        this.consultas = consultas;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);

        // =========================================================
        // VALIDACIÓN (15 PTOS): Código solo acepta números enteros
        // =========================================================
        this.vista.txtCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Bloquea si no es un número
                }
            }
        });

        // =========================================================
        // VALIDACIÓN: Precio acepta números y punto decimal
        // =========================================================
        this.vista.txtPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Si no es dígito y tampoco es un punto, se bloquea
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- GUARDAR ---
        if (e.getSource() == vista.btnGuardar) {
            modelo.setCodigo(Integer.parseInt(vista.txtCodigo.getText()));
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setPrecio(Float.parseFloat(vista.txtPrecio.getText()));

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Producto Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        }

        // --- BUSCAR ---
        if (e.getSource() == vista.btnBuscar) {
            modelo.setCodigo(Integer.parseInt(vista.txtCodigo.getText()));

            if (consultas.buscar(modelo)) {
                vista.txtDescripcion.setText(modelo.getDescripcion());
                vista.txtPrecio.setText(String.valueOf(modelo.getPrecio()));
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
                limpiar();
            }
        }

        // --- MODIFICAR ---
        if (e.getSource() == vista.btnModificar) {
            modelo.setCodigo(Integer.parseInt(vista.txtCodigo.getText()));
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setPrecio(Float.parseFloat(vista.txtPrecio.getText()));

            if (consultas.modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Producto Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }

        // --- ELIMINAR ---
        if (e.getSource() == vista.btnEliminar) {
            modelo.setCodigo(Integer.parseInt(vista.txtCodigo.getText()));

            if (consultas.eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Producto Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        }
    }

    private void limpiar() {
        vista.txtCodigo.setText(null);
        vista.txtDescripcion.setText(null);
        vista.txtPrecio.setText(null);
    }
}