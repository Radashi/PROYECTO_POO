package vista;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class VistaPantallaPrincipal extends JFrame {

    // Elementos de la interfaz (puedes cambiar los textos por íconos más adelante)
    public JButton btnUsuario;
    public JButton btnProductos;
    public JButton btnMinimizar;
    public JButton btnSalir;

    public VistaPantallaPrincipal() {
        // Configuración básica de la ventana
        setTitle("Pantalla Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setUndecorated(true); // Quita la barra de título nativa de Windows/Mac para un diseño personalizado

        // Panel superior para los botones (simulando la Fig. 21)
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(255, 153, 102)); // Color anaranjado similar al PDF
        panelSuperior.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Inicializar botones
        btnUsuario = new JButton("Usuario");
        btnProductos = new JButton("Productos");
        btnMinimizar = new JButton("Minimizar (-)");
        btnSalir = new JButton("Salir (X)");

        // Agregar botones al panel superior
        panelSuperior.add(btnUsuario);
        panelSuperior.add(btnProductos);
        panelSuperior.add(btnMinimizar);
        panelSuperior.add(btnSalir);

        // Agregar el panel superior a la parte norte de la ventana
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central (fondo morado/azul oscuro)
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(60, 50, 90));
        add(panelCentral, BorderLayout.CENTER);
    }
}