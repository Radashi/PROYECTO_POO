package principal;

import controlador.ControladorVistaSplash;
import modelo.ModeloUsuario;
import vista.VistaSplash;

public class Main {
    public static void main(String[] args) {
        //ModeloUsuario ModeloUsuario = new ModeloUsuario();
        VistaSplash VistaSplash = new VistaSplash();
        ControladorVistaSplash ControladorVistaSplash = new ControladorVistaSplash(VistaSplash);
    }
}
