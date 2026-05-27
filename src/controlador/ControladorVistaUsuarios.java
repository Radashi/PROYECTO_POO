package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import modelo.ConsultasUsuario;
import modelo.ModeloUsuario;
import vista.VistaUsuarios; // Suponiendo que así se llama su ventana

public class ControladorVistaUsuarios implements ActionListener {

    private VistaUsuarios vista;
    private ModeloUsuario modelo;
    private ConsultasUsuario consultas;

    public ControladorVistaUsuarios(VistaUsuarios vista, ModeloUsuario modelo, ConsultasUsuario consultas) {
        this.vista = vista;
        this.modelo = modelo;
        this.consultas = consultas;

        // 1. Escuchar los clicks de los 4 botones
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);

        // =========================================================
        // VALIDACIÓN DE RUBRICA (15 PTOS): No dejar poner números en el Nombre
        // =========================================================
        this.vista.txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Si el usuario teclea un número, consumimos (bloqueamos) el evento
                if (Character.isDigit(c)) {
                    e.consume();
                    // Opcional: Mostrar advertencia
                    // JOptionPane.showMessageDialog(null, "Solo se permiten letras en el nombre");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- BOTÓN GUARDAR (ALTA) ---
        if (e.getSource() == vista.btnGuardar) {
            modelo.setUsuario(vista.txtUsuario.getText());
            modelo.setPassword(vista.txtPassword.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setTipo(vista.cbxTipo.getSelectedItem().toString());

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Usuario Guardado (Contraseña encriptada exitosamente)");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el usuario");
            }
        }

        // --- BOTÓN BUSCAR ---
        if (e.getSource() == vista.btnBuscar) {
            modelo.setUsuario(vista.txtUsuario.getText());

            if (consultas.buscar(modelo)) {
                vista.txtPassword.setText(modelo.getPassword());
                vista.txtNombre.setText(modelo.getNombre());
                vista.cbxTipo.setSelectedItem(modelo.getTipo());
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                limpiarCajas();
            }
        }

        // --- BOTÓN MODIFICAR ---
        if (e.getSource() == vista.btnModificar) {
            modelo.setUsuario(vista.txtUsuario.getText());
            modelo.setPassword(vista.txtPassword.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setTipo(vista.cbxTipo.getSelectedItem().toString());

            if (consultas.modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Usuario Modificado");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }

        // --- BOTÓN ELIMINAR (BAJA) ---
        if (e.getSource() == vista.btnEliminar) {
            modelo.setUsuario(vista.txtUsuario.getText());

            if (consultas.eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        }
    }

    public void limpiarCajas() {
        vista.txtUsuario.setText(null);
        vista.txtPassword.setText(null);
        vista.txtNombre.setText(null);
        vista.cbxTipo.setSelectedIndex(0);
    }
}