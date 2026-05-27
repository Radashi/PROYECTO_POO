package vista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

public class VistaSplash extends JFrame {
    ImageIcon ImSplash = new ImageIcon(getClass().getResource("/imagenes/splash.gif"));
    public JLabel LblSplash = new JLabel(ImSplash);

    public VistaSplash() {
        configuracion();
        agregarComponentes();
    }

    private void configuracion() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(ImSplash.getIconWidth(), ImSplash.getIconHeight());
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setAlwaysOnTop(true);
    }

    private void agregarComponentes() {
        this.add(LblSplash);
    }
}
