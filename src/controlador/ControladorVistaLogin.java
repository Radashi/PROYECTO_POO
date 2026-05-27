package controlador; // Asegúrate de que el paquete sea el correcto según tu estructura

import vista.VistaLogin;
import vista.VistaPantallaPrincipal;
import modelo.ModeloUsuario;
import modelo.ConsultasUsuario;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ControladorVistaLogin implements MouseListener {

    VistaLogin VistaLogin; // Objeto de la vista
    ModeloUsuario ModeloUsuario; // Objeto del modelo
    ConsultasUsuario ConsultasUsuario = new ConsultasUsuario();

    public ControladorVistaLogin(VistaLogin vistaLogin, ModeloUsuario modeloUsuario) {
        this.VistaLogin = vistaLogin;
        this.ModeloUsuario = modeloUsuario;

        oyentes(); // Llamada al metodo para activar los eventos
        this.VistaLogin.setVisible(true); // Pone visible la ventana
    }

    private void oyentes() {
        // Activamos los oyentes para los botones de entrar y cerrar
        VistaLogin.BtnLogin.addMouseListener(this);
        VistaLogin.BtnCerrar.addMouseListener(this);

        // Activamos el oyente para el ojito del password
        VistaLogin.LblOjito.addMouseListener(this);

        VistaLogin.TxtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                // Cuando hacemos clic en la cajita, si dice "user name", lo borramos
                if (VistaLogin.TxtUsuario.getText().equals("user name")) {
                    VistaLogin.TxtUsuario.setText("");
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                // Cuando hacemos clic fuera de la cajita, si está vacía, le regresamos el texto
                if (VistaLogin.TxtUsuario.getText().isEmpty()) {
                    VistaLogin.TxtUsuario.setText("user name");
                }
            }
        });
    }

    // Funcion que retorna verdadero si los campos no estan vacios
    public boolean camposValidos() {
        String usuario = VistaLogin.TxtUsuario.getText();

        // Checa si estan vacios, si el usuario sigue siendo "user name" o si el password está vacío
        if (usuario.isEmpty() || usuario.equals("user name") || VistaLogin.TxtPassword.getPassword().length == 0) {
            return false; // Los campos estan vacios (o tienen el placeholder)
        } else {
            return true; // Los campos son válidos
        }
    }


    private void llenarModeloConCampos() {
        ModeloUsuario.setUsuario(VistaLogin.TxtUsuario.getText()); //
        ModeloUsuario.setPassword(new String(VistaLogin.TxtPassword.getPassword())); //
        ModeloUsuario.setNombre(""); // No se conoce
        ModeloUsuario.setTipo(""); // No se conoce
    }
    private void buscarUsuarioPassWord() {
            if (camposValidos() == true) {
                llenarModeloConCampos();

                if (ConsultasUsuario.buscarLogin(ModeloUsuario) == true) {

                    JOptionPane.showMessageDialog(null, "Bienvenido: " + ModeloUsuario.getNombre(),
                            "Tipo: " + ModeloUsuario.getTipo(), 1);

                    VistaLogin.dispose();

                    // Crear el Modelo Vista de la pantalla principal
                    VistaPantallaPrincipal VistaPantallaPrincipal = new VistaPantallaPrincipal();
                    ControladorVistaPantallaPrincipal ControladorVistaPantallaPrincipal = new ControladorVistaPantallaPrincipal(VistaPantallaPrincipal);

                    if (!ModeloUsuario.getTipo().equals("SuperAdministrador")) {
                        // Ocultamos el botón de Usuarios
                        VistaPantallaPrincipal.btnUsuario.setVisible(false);
                    }

                    // Aseguramos que la ventana principal se haga visible aquí
                    VistaPantallaPrincipal.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Password Incorrectos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes de colocar texto en los campos"
                        + " usuario y password");
            }
        }

    // =========================================================
    // EVENTOS DEL MOUSE
    // =========================================================

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == VistaLogin.BtnCerrar) {
            VistaLogin.dispose();
        } else if (e.getSource() == VistaLogin.BtnLogin) {
            buscarUsuarioPassWord();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Lógica para mostrar la contraseña al mantener presionado el ojito
        if (e.getSource() == VistaLogin.LblOjito) {
            VistaLogin.TxtPassword.setEchoChar((char) 0); // Muestra el texto
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Lógica para ocultar la contraseña al soltar el ojito     if (e.getSource() == VistaLogin.LblOjito) {
            VistaLogin.TxtPassword.setEchoChar('*'); // Oculta el texto con asteriscos
        }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}