
package Modelo;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Factura{ 
        
    private int id;
    private Date fechaFactura;
    private int proveedor;
    private String nombreProveedor;
    private double netoFactura;

    public Factura() {
    }

    public Factura(Date fechaFactura, int proveedor, double netoFactura) {
        this.fechaFactura = fechaFactura;
        this.proveedor = proveedor;
        this.netoFactura = netoFactura;
    }
    

    public int getId() {
        return id;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }
    

    public int getProveedor() {
        return proveedor;
    }

    public double getNetoFactura() {
        return netoFactura;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
    
    

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
    
    


    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public void setNetoFactura(double netoFactura) {
        this.netoFactura = netoFactura;
    }
    
    
    
}
