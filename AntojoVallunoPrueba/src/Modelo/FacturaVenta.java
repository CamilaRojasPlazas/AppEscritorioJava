
package Modelo;

import java.util.Date;

/**
 *
 * @author Egresados
 */
public class FacturaVenta {
    
    private int id;
    private Date fechaFactura;
    private int idVendedor;
    private int idCliente;
    private String lugar;
    private String coordX;
    private String coordY;
    private double netoFactura;

    public int getId() {
        return id;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getLugar() {
        return lugar;
    }

    public String getCoordX() {
        return coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public double getNetoFactura() {
        return netoFactura;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public void setNetoFactura(double netoFactura) {
        this.netoFactura = netoFactura;
    }
    
    
    
}
