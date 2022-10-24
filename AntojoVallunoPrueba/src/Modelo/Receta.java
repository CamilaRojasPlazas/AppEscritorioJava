
package Modelo;

/**
 *
 * @author ASUS
 */
public class Receta {
    
    private int idProducto;
    private String producto;
    private int idMateriaPrima;
    private String materiaPrima;
    private double cantidad;

    public Receta() {
    }

    public Receta(int idProducto, String producto, int idMateriaPrima, String materiaPrima, double cantidad) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.idMateriaPrima = idMateriaPrima;
        this.materiaPrima = materiaPrima;
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public void setMateriaPrima(String materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
