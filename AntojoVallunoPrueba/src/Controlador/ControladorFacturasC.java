
package Controlador;

import Modelo.Factura;
import Modelo.FacturaDao;
import Modelo.MateriaPrimaFacturadaDao;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Vista.VHistorialCompras;
import Vista.VMateriaPrimaFact;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Egresados
 */
public class ControladorFacturasC {
    
    private VHistorialCompras vista;
    private FacturaDao modelo;

    public ControladorFacturasC() {
    }

    public ControladorFacturasC(VHistorialCompras vista, FacturaDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.addListenerBtnBuscar(new listenerFactura());
        this.vista.addListenerBtnListarTodo(new listenerFactura());
        this.vista.addListenerBtnDetalle(new listenerFactura());
        
        DatosTabla(0,0);

    }
    
    class listenerFactura implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("Buscar")){
                buscar();
            }else if(e.getActionCommand().equalsIgnoreCase("Listar todas las compras")){
                DatosTabla(0,0);
            }else if(e.getActionCommand().equalsIgnoreCase("VER DETALLE FACTURA")){
                abrirDetalles();

            }            
        }
        
        
        //METODO PARA BUSCAR PROVEEDOR
        private void buscar(){
            String idProveedor, idFactura;
            idProveedor  = vista.getCodigoProveedor().trim();
            idFactura=vista.getCodigoFactura().trim();
            
            try{
            
                if(idProveedor.isEmpty() && idFactura.isEmpty()){
                    vista.gestionMensajes("Por favor seleccione una fila", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
                }if(idFactura.length()!=0){
                    DatosTabla(Integer.parseInt(idFactura),1);                
                }if(idProveedor.length()!=0){
                    DatosTabla(Integer.parseInt(idProveedor),2); 
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
                    VMateriaPrimaFact view = new VMateriaPrimaFact();
                    MateriaPrimaFacturadaDao modelMateriaPrimaFac=new MateriaPrimaFacturadaDao();
                    int idFact=Integer.parseInt(idFactura);
                    
                    ControladorMateriaPrimaFact programaController = new ControladorMateriaPrimaFact(view,modelMateriaPrimaFac,idFact);
                    view.setVisible(true);
                    
                
                }                                  
            }catch(NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);               
            }
        }
    } 

    
    
    public void DatosTabla( int dato, int metodoBusca){
        
        ProveedorDao modeloProveedor=new ProveedorDao();
        
        ArrayList<Factura> listadoFactura;
        listadoFactura = this.modelo.listadoFactura(dato,metodoBusca);

        
        for(int i=0;i<listadoFactura.size();i++){
           
           Factura factura=listadoFactura.get(i);
           int idProveedor=(factura.getProveedor());
           Proveedor miPriveedor=modeloProveedor.listadoProveedoresID(idProveedor).get(0);
           
           listadoFactura.get(i).setNombreProveedor(miPriveedor.getNombre());
        }
        
        vista.cargarFacturas(listadoFactura);

    }
    
    
    
}
