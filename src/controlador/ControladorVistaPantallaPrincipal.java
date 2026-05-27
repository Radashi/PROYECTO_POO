package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaPantallaPrincipal;
import vista.VistaUsuarios;
import modelo.ModeloUsuario;
import modelo.ConsultasUsuario;

// Implementamos ActionListener para escuchar los clics de los botones
public class ControladorVistaPantallaPrincipal implements ActionListener {

    private VistaPantallaPrincipal vistaPrincipal;

    public ControladorVistaPantallaPrincipal(VistaPantallaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;

        this.vistaPrincipal.btnUsuario.addActionListener(this);
        this.vistaPrincipal.btnProductos.addActionListener(this);
        this.vistaPrincipal.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // SI HACEN CLIC EN EL BOTÓN "USUARIO"
        if (e.getSource() == vistaPrincipal.btnUsuario) {

            // 1. Instanciamos la Vista, el Modelo y las Consultas
            VistaUsuarios vistaUsr = new VistaUsuarios();
            ModeloUsuario modeloUsr = new ModeloUsuario();
            ConsultasUsuario consultasUsr = new ConsultasUsuario();

            // 2. Conectamos todo pasándoselo al Controlador de Usuarios
            ControladorVistaUsuarios ctrlUsr = new ControladorVistaUsuarios(vistaUsr, modeloUsr, consultasUsr);

            // 3. Hacemos visible la nueva ventana
            vistaUsr.setVisible(true);
        }

        // SI HACEN CLIC EN EL BOTÓN "PRODUCTOS"
        if (e.getSource() == vistaPrincipal.btnProductos) {
            vista.VistaProductos vistaProd = new vista.VistaProductos();
            modelo.ModeloProducto modeloProd = new modelo.ModeloProducto();
            modelo.ConsultasProducto consultasProd = new modelo.ConsultasProducto();

            controlador.ControladorVistaProductos ctrlProd = new controlador.ControladorVistaProductos(vistaProd, modeloProd, consultasProd);

            vistaProd.setVisible(true);
        }

        if (e.getSource() == vistaPrincipal.btnSalir) {
            System.exit(0); // Cierra todo el programa
        }
    }
}