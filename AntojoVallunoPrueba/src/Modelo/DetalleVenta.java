
package Modelo;

/**
 *
 * @author ASUS
 */
public class DetalleVenta {
    
    private int idFacturaVenta;
    private int idProducto;
    private String Producto;
    private String tipoVenta;
    private double valorUnitario;
    private int cantSolicitada;
    private double total;

    public int getIdFacturaVenta() {
        return idFacturaVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getProducto() {
        return Producto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public int getCantSolicitada() {
        return cantSolicitada;
    }

    public double getTotal() {
        return total;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }
    
    

    public void setIdFacturaVenta(int idFacturaVenta) {
        this.idFacturaVenta = idFacturaVenta;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setCantSolicitada(int cantSolicitada) {
        this.cantSolicitada = cantSolicitada;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
    
    
    
    
    
}
