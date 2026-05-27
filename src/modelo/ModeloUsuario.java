package modelo;

public class ModeloUsuario {
    // Atributos privados según el diagrama UML (Fig. 3)
    private String Usuario;
    private String Password;
    private String Nombre;
    private String Tipo;

    // Constructor que recibe todos los campos (Fig. 4)
    public ModeloUsuario(String Usuario, String Password, String Nombre, String Tipo) {
        this.Usuario = Usuario;
        this.Password = Password;
        this.Nombre = Nombre;
        this.Tipo = Tipo;
    }

    // Constructor vacío (Fig. 4)
    public ModeloUsuario() {
    }

    // Métodos Getters y Setters para acceder a los datos (Fig. 4)
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
}