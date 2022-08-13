package Model;


public class Producto {
    private int id;
    private String Nombre;
    private String Categoria;
    private double Precio;
     private String Descripcion;

    public Producto(int id, String Nombre, String Categoria, double Precio, String Descripcion) {
        this.id = id;
        this.Nombre = Nombre;
        this.Categoria = Categoria;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
    }

    public Producto(String Nombre, String Categoria, double Precio, String Descripcion) {
        this.Nombre = Nombre;
        this.Categoria = Categoria;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
    }

    public Producto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

   

    
  
    
    
    
}
