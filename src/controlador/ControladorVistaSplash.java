package controlador;

import modelo.ModeloUsuario;
import vista.*;

import java.awt.Cursor;
import java.awt.event.*;

public class ControladorVistaSplash implements MouseListener, MouseMotionListener, MouseWheelListener {
    VistaSplash VistaSplash;
    int x, y;

    public ControladorVistaSplash(VistaSplash VistaSplash) {
        this.VistaSplash = VistaSplash;
        oyentes();
        VistaSplash.setVisible(true);

        dormirMatarSplash(5);
    }

    private void oyentes() {
        VistaSplash.LblSplash.addMouseListener(this);
        VistaSplash.LblSplash.addMouseMotionListener(this);
    }

    private void dormirMatarSplash(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex);
        } finally {
            VistaSplash.dispose();
            ModeloUsuario modeloUsuario = new ModeloUsuario();
            VistaLogin vistaLogin = new VistaLogin();
            ControladorVistaLogin controlador = new ControladorVistaLogin(vistaLogin, modeloUsuario); // [cite: 549]
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == VistaSplash.LblSplash) {
            VistaSplash.LblSplash.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == VistaSplash.LblSplash) {
            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == VistaSplash.LblSplash) {
            VistaSplash.setLocation(VistaSplash.getLocation().x + e.getX()-x,
                                    VistaSplash.getLocation().y + e.getY()-y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getSource() == VistaSplash.LblSplash) {
            VistaSplash.dispose();
        }
    }
}