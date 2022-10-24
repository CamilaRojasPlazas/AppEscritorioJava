
package Controlador;

import Modelo.DetalleVenta;
import Modelo.DetalleVentaDao;
import Modelo.Producto;
import Modelo.ProductoDao;
import Vista.VProductosFact;
import java.util.ArrayList;

/**
 *
 * @author Egresados
 */
public class ControladorProductosFact {
    private VProductosFact vista;
    private DetalleVentaDao modelo;
    private int idFactura;
    
    public ControladorProductosFact(VProductosFact vista, DetalleVentaDao modelo, int idFactura) {
        this.vista = vista;
        this.modelo = modelo;
        this.idFactura=idFactura;
        
        DatosTabla(this.idFactura);
    }
    
    public void DatosTabla(int idFactura){
        
        ProductoDao modeloProducto=new ProductoDao();
        
        ArrayList<DetalleVenta> listadoProductos;
        listadoProductos = this.modelo.listadoMateriaPrimaFacturada(idFactura);


        for(int i=0;i<listadoProductos.size();i++){
           
           DetalleVenta mpf=listadoProductos.get(i);
           
           int idProducto=(mpf.getIdProducto());
           
            
           ArrayList<Producto> articulo= modeloProducto.listadoProducto(Integer.toString(idProducto), 1);
           
           mpf.setProducto(articulo.get(0).getNombre());
        }        
        vista.cargarProductosFact(listadoProductos);
        
    }
    
}
