
package Controlador;

import Modelo.MateriaPrima;
import Modelo.MateriaPrimaDao;
import Modelo.MateriaPrimaFacturada;
import Modelo.MateriaPrimaFacturadaDao;
import Vista.VMateriaPrimaFact;
import java.util.ArrayList;


/**
 *
 * @author Egresados
 */
public class ControladorMateriaPrimaFact {
    
    private VMateriaPrimaFact vista;
    private MateriaPrimaFacturadaDao modelo;
    private int idProveedor;

    public ControladorMateriaPrimaFact() {
    }

    public ControladorMateriaPrimaFact(VMateriaPrimaFact vista, MateriaPrimaFacturadaDao modelo, int idProveedor) {
        this.vista = vista;
        this.modelo = modelo;
        this.idProveedor=idProveedor;
        
        DatosTabla(this.idProveedor);
    }
    
    
    
    public void DatosTabla(int idFactura){
        
        MateriaPrimaDao modeloMateriaPrima=new MateriaPrimaDao();
        
        ArrayList<MateriaPrimaFacturada> listadoMateriaPrimaFac;
        listadoMateriaPrimaFac = this.modelo.listadoMateriaPrimaFacturada(idFactura);


        for(int i=0;i<listadoMateriaPrimaFac.size();i++){
           
           MateriaPrimaFacturada mpf=listadoMateriaPrimaFac.get(i);
           
           int idMateriaPrima=(mpf.getIdMateriaPrima());
           
            
           ArrayList<MateriaPrima> articulo= modeloMateriaPrima.listadoMateriaID(idMateriaPrima);            
           
           mpf.setMateriaPrima(articulo.get(0).getNombre());
           mpf.setUnidadMedida(articulo.get(0).getUnidadMedida());
        }        
        vista.cargarMateriaPrimaFac(listadoMateriaPrimaFac);
        
    }
    
}


