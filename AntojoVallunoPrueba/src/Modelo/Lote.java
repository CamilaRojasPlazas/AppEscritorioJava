
package Modelo;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Lote {
    
    private int id;
    private Date fechaLote;
    private String seriado;


    public int getId() {
        return id;
    }

    public Date getFechaLote() {
        return fechaLote;
    }

    public String getSeriado() {
        return seriado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaLote(Date fechaLote) {
        this.fechaLote = fechaLote;
    }

    public void setSeriado(String seriado) {
        this.seriado = seriado;
    }
    
    
    
    
    
}
