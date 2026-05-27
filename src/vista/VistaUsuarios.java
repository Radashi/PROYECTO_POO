package vista;

import javax.swing.*;

public class VistaUsuarios extends JFrame {
    public JLabel lblUsuario, lblPassword, lblNombre, lblTipo;
    public JTextField txtUsuario, txtPassword, txtNombre;
    public JComboBox<String> cbxTipo;
    public JButton btnGuardar, btnBuscar, btnModificar, btnEliminar;

    public VistaUsuarios() {
        this.setTitle("Gestión de Usuarios");
        this.setSize(550, 380);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 40, 100, 30);
        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 40, 250, 30);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 90, 100, 30);
        txtPassword = new JTextField();
        txtPassword.setBounds(150, 90, 250, 30);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 140, 100, 30);
        txtNombre = new JTextField();
        txtNombre.setBounds(150, 140, 250, 30);

        lblTipo = new JLabel("Tipo (Rol):");
        lblTipo.setBounds(50, 190, 100, 30);
        cbxTipo = new JComboBox<>(new String[]{"Registrado", "SuperAdministrador"});
        cbxTipo.setBounds(150, 190, 250, 30);

        // Agregando Iconos a los Botones
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 260, 115, 35);
        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/guardar.png")));

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(145, 260, 115, 35);
        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(270, 260, 115, 35);
        btnModificar.setIcon(new ImageIcon(getClass().getResource("/imagenes/modificar.png")));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(395, 260, 115, 35);
        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));

        this.add(lblUsuario); this.add(txtUsuario);
        this.add(lblPassword); this.add(txtPassword);
        this.add(lblNombre); this.add(txtNombre);
        this.add(lblTipo); this.add(cbxTipo);
        this.add(btnGuardar); this.add(btnBuscar);
        this.add(btnModificar); this.add(btnEliminar);
    }
}