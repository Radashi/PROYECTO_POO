package vista;

import javax.swing.*;
import java.awt.event.*;

public class VistaLogin extends JFrame {

    public JLabel LblUsuario;
    public JLabel LblPassword;
    public JLabel LblOjito;
    public JTextField TxtUsuario;
    public JPasswordField TxtPassword;
    public JCheckBox ChkRecordar;
    public JButton BtnLogin;

    // 1. Declarar el componente BtnCerrar
    public JButton BtnCerrar;

    public VistaLogin() {
        this.setTitle("Login Sistema");
        this.setSize(400, 350);

        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        LblUsuario = new JLabel("Usuario:");
        TxtUsuario = new JTextField("user name");

        LblPassword = new JLabel("Password:");
        TxtPassword = new JPasswordField();

        LblOjito = new JLabel("O");

        ChkRecordar = new JCheckBox("Remember me");
        BtnLogin = new JButton("Login");

        // 2. Instanciar el botón Cerrar
        BtnCerrar = new JButton("Cerrar");

        LblUsuario.setBounds(40, 100, 100, 30);
        TxtUsuario.setBounds(140, 100, 150, 30);

        LblPassword.setBounds(40, 150, 100, 30);
        TxtPassword.setBounds(140, 150, 150, 30);

        LblOjito.setBounds(300, 150, 30, 30);

        ChkRecordar.setBounds(140, 190, 150, 30);

        BtnLogin.setBounds(140, 230, 100, 30);
        // 3. Configurar posición y tamaño (Lo colocamos en X=250, a un lado del BtnLogin)
        BtnCerrar.setBounds(250, 230, 100, 30);

        this.add(LblUsuario);
        this.add(TxtUsuario);
        this.add(LblPassword);
        this.add(TxtPassword);
        this.add(LblOjito);
        this.add(ChkRecordar);
        this.add(BtnLogin);

        // 4. Agregarlo al JFrame
        this.add(BtnCerrar);
    }
}