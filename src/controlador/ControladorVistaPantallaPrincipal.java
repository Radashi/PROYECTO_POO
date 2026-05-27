package controlador;

import vista.VistaPantallaPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class ControladorVistaPantallaPrincipal implements MouseListener {

    VistaPantallaPrincipal vistaPantallaPrincipal;

    // Constructor
    public ControladorVistaPantallaPrincipal(VistaPantallaPrincipal vistaPantallaPrincipal) {
        this.vistaPantallaPrincipal = vistaPantallaPrincipal;
        oyentes();
        this.vistaPantallaPrincipal.setVisible(true); // Hace visible la ventana al inicializar
    }

    // Método para agregar los listeners a los botones
    private void oyentes() {
        vistaPantallaPrincipal.btnMinimizar.addMouseListener(this);
        vistaPantallaPrincipal.btnSalir.addMouseListener(this);
        vistaPantallaPrincipal.btnUsuario.addMouseListener(this);
        vistaPantallaPrincipal.btnProductos.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Acción para el botón Salir
        if (e.getSource() == vistaPantallaPrincipal.btnSalir) {
            System.exit(0); // Cierra la aplicación completamente
        }
        // Acción para el botón Minimizar
        else if (e.getSource() == vistaPantallaPrincipal.btnMinimizar) {
            vistaPantallaPrincipal.setState(JFrame.ICONIFIED); // Minimiza la ventana
        }
        // Aquí puedes agregar los 'else if' para btnUsuario y btnProductos en el futuro
    }

    // Métodos obligatorios de MouseListener (pueden quedar vacíos si no se usan)
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}