
package Modelo;

/**
 *
 * @author ASUS
 */
public class Producto {
    
    private int id;
    private String codigo;
    private String nombre;
    private double valorDetal;
    private double valorMayor;
    private int stockProducto;

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public double getValorDetal() {
        return valorDetal;
    }

    public double getValorMayor() {
        return valorMayor;
    }

    public int getStockProducto() {
        return stockProducto;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorDetal(double valorDetal) {
        this.valorDetal = valorDetal;
    }

    public void setValorMayor(double valorMayor) {
        this.valorMayor = valorMayor;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

}
