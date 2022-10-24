
package Modelo;

/**
 *
 * @author ASUS
 */
public class MateriaPrima {
    
    private int id;
    private int codigo;
    private String nombre;
    private String unidadMedida;
    private double stock; 
    
    public MateriaPrima() {
    }



    public int getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public double getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
    
    
    
    
    
    
    
    
}
