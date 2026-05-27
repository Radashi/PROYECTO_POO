package modelo;

public class ModeloProducto {
    private int codigo;
    private String Descripcion;
    private float precio;

    // Constructor vacío
    public ModeloProducto() {
    }

    // Constructor con parámetros
    public ModeloProducto(int codigo, String Descripcion, float precio, int presentacion) {
        this.codigo = codigo;
        this.Descripcion = Descripcion;
        this.precio = precio;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}