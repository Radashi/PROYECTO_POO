package vista;

import javax.swing.*;

public class VistaProductos extends JFrame {
    public JLabel lblCodigo, lblDescripcion, lblPrecio;
    public JTextField txtCodigo, txtDescripcion, txtPrecio;
    public JButton btnGuardar, btnBuscar, btnModificar, btnEliminar;

    public VistaProductos() {
        this.setTitle("Gestión de Productos");
        this.setSize(550, 350);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(50, 40, 100, 30);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(150, 40, 250, 30);

        lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 90, 100, 30);
        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 90, 250, 30);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(50, 140, 100, 30);
        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 140, 250, 30);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 220, 115, 35);
        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/guardar.png")));

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(145, 220, 115, 35);
        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(270, 220, 115, 35);
        btnModificar.setIcon(new ImageIcon(getClass().getResource("/imagenes/modificar.png")));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(395, 220, 115, 35);
        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));

        this.add(lblCodigo); this.add(txtCodigo);
        this.add(lblDescripcion); this.add(txtDescripcion);
        this.add(lblPrecio); this.add(txtPrecio);
        this.add(btnGuardar); this.add(btnBuscar);
        this.add(btnModificar); this.add(btnEliminar);
    }
}