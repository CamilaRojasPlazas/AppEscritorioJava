
package Modelo;

/**
 *
 * @author ASUS
 */
public class MateriaPrimaFacturada {
    
    private int idMateriaPrima;
    private String materiaPrima;
    private String unidadMedida;
    private int idFactura;
    private double valorUnitario;
    private int cantSolicitada;
    private double total;

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public int getIdFactura() {
        return idFactura;
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

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }
    

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
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

    public void setMateriaPrima(String materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    
    
    
    
    
    
}
