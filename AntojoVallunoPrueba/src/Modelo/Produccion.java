
package Modelo;

/**
 *
 * @author ASUS
 */
public class Produccion {
    
    private int id;
    private int idProducto;
    private String nombreProducto;
    private int codigo;
    private int cantidad;
    private int idLote; 
    
    public Produccion() {
    }

    public int getId() {
        return id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    
    
}
