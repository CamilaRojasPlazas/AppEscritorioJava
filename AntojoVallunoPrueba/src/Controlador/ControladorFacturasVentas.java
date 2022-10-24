
package Controlador;


import Modelo.DetalleVentaDao;
import Modelo.FacturaVenta;
import Modelo.FacturaVentaDao;
import Vista.VHistorialVentas;
import Vista.VProductosFact;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Egresados
 */
public class ControladorFacturasVentas {
    
    private VHistorialVentas vista;
    private FacturaVentaDao modelo;
    
    public ControladorFacturasVentas(VHistorialVentas vista, FacturaVentaDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.addListenerBtnBuscar(new listenerFactura());
        this.vista.addListenerBtnListarTodo(new listenerFactura());
        this.vista.addListenerBtnDetalle(new listenerFactura());
        
        DatosTabla(0);

    }
    
    class listenerFactura implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("Buscar")){
                buscar();
            }else if(e.getActionCommand().equalsIgnoreCase("Listar todas las ventas")){
                DatosTabla(0);
            }else if(e.getActionCommand().equalsIgnoreCase("VER DETALLE FACTURA")){
                abrirDetalles();

            }            
        }
        //METODO PARA BUSCAR PROVEEDOR
        private void buscar(){
            
            String  idFactura;
            idFactura=vista.getCodigoFactura().trim();
            
            try{
            
                if(idFactura.isEmpty()){
                    vista.gestionMensajes("Por favor seleccione una fila", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);                    
                }else{
                    DatosTabla(Integer.parseInt(idFactura));
                }        
            }catch(NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);               
            }
        }
        
        private void abrirDetalles(){
            
            String  idFactura;
            idFactura=vista.getCodigoFactura().trim();
            
            try{
                if(idFactura.length()==0){
                    vista.gestionMensajes("Por favor seleccione una fila", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);                
                }else{
                    VProductosFact view = new VProductosFact();
                    DetalleVentaDao modelMateriaPrimaFac=new DetalleVentaDao();
                    int idFact=Integer.parseInt(idFactura);
                    
                    ControladorProductosFact programaController = new ControladorProductosFact(view,modelMateriaPrimaFac,idFact);
                    view.setVisible(true);
                    
                
                }                                  
            }catch(NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);               
            }
        }
    }
    
    
    public void DatosTabla( int dato){
        
        
        ArrayList<FacturaVenta> listadoFactura;
        listadoFactura = this.modelo.listadoFactura(dato);

        
        vista.cargarFacturas(listadoFactura);

    }
    
}
