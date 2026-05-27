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

        // Activamos el oyente para el ojito del password (según el documento 2)
        VistaLogin.LblOjito.addMouseListener(this);
    }

    // Funcion que retorna verdadero si los campos no estan vacios (Fig. 17)
    public boolean camposValidos() {
        // Checa si estan vacios o no los campos de texto usuario y password
        if (VistaLogin.TxtUsuario.getText().isEmpty() || VistaLogin.TxtPassword.getPassword().length == 0) {
            return false; // Los campos estan vacios
        } else {
            return true; // Los campos no estan vacios
        }
    }


    private void llenarModeloConCampos() {
        ModeloUsuario.setUsuario(VistaLogin.TxtUsuario.getText()); //
        ModeloUsuario.setPassword(new String(VistaLogin.TxtPassword.getPassword())); //
        ModeloUsuario.setNombre(""); // No se conoce
        ModeloUsuario.setTipo(""); // No se conoce
    }
    private void buscarUsuarioPassWord() {
        if (camposValidos() == true) { // Hay Texto en los campos
            llenarModeloConCampos(); // para que lleve la información que necesita la consulta SQL

            if (ConsultasUsuario.buscarLogin(ModeloUsuario) == true) { // si encontro al usuario
                // El ModeloUsuario ya lleno sus campos faltantes con la busqueda (nombre y tipo)
                // ya solo desplegarlos y dar la bienvenida
                JOptionPane.showMessageDialog(null, "Bienvenido: " + ModeloUsuario.getNombre(),
                        "Tipo: " + ModeloUsuario.getTipo(), 1); // 1 = tipo de icono

                // Liberar la ventana del login
                VistaLogin.dispose();

                // Crear el Modelo Vista de la pantalla principal
                VistaPantallaPrincipal VistaPantallaPrincipal = new VistaPantallaPrincipal();
                ControladorVistaPantallaPrincipal ControladorVistaPantallaPrincipal = new ControladorVistaPantallaPrincipal(VistaPantallaPrincipal);

            } else { // usuario no existe o contraseña incorrecta
                JOptionPane.showMessageDialog(null, "Usuario o Password Incorrectos");
            }
        } else { // Algun campo esta vacio o ambos (usuario/password)
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
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}