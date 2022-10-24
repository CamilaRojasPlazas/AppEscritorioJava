
package Controlador;


import Modelo.Factura;
import Modelo.FacturaDao;
import Modelo.MateriaPrima;
import Modelo.MateriaPrimaDao;
import Modelo.MateriaPrimaFacturada;
import Modelo.MateriaPrimaFacturadaDao;
import Modelo.Proveedor;

import Vista.VCompras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControladorComprar {
    
    private VCompras vista;
    private FacturaDao modeloFactura;
    private MateriaPrimaFacturadaDao modeloMateriaPrimaFac;
    private ArrayList<MateriaPrimaFacturada> articulos=new ArrayList<MateriaPrimaFacturada>();
    double neto;
    
    //CONSTRUCTORES
    public ControladorComprar() {        
    }
    
    public ControladorComprar(VCompras vista,FacturaDao modeloFactura,MateriaPrimaFacturadaDao modeloMateriaPrimaFac) {
        this.vista = vista;     
        this.modeloFactura=modeloFactura;
        this.modeloMateriaPrimaFac=modeloMateriaPrimaFac;
        
        this.vista.addListenerBtnAgregar(new listenerCompras());
        this.vista.addListenerBtnEliminar(new listenerCompras());
        this.vista.addListenerBtnCancelarCompra(new listenerCompras());
        this.vista.addListenerBtnRegistrarCompra(new listenerCompras());
        
        vista.setjTextFieldFecha();
        comboBoxProveedor();
        comboBoxMateriaPrima();
    }
    
    class listenerCompras implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("Agregar")){
                articulosCarrito();
            }else if(e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarArticuloCarrito();
            }else if(e.getActionCommand().equalsIgnoreCase("Cancelar Compra")){
                cancelarCompra();
            }else if(e.getActionCommand().equalsIgnoreCase("Comprar")){
                comprar();
            }
            
        }
    }    
    
    //AGREGAR ARTICULOS AL CARRITO
    private void articulosCarrito(){
        
        if(vista.getIdMateriaPrima().equals("")){
            vista.gestionMensajes("Ingrese un artículo", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else if (vista.getjTextFieldCant().equals("")){
            vista.gestionMensajes("Ingrese una cantidad", "Error de Entrada", JOptionPane.ERROR_MESSAGE);         
        }else if(vista.getjTextFieldPrecio().equals("")){
            vista.gestionMensajes("Ingrese un precio", "Error de Entrada", JOptionPane.ERROR_MESSAGE);      
        }else{
            
            MateriaPrimaFacturada articulo=new MateriaPrimaFacturada();
            
            try{
                
                int cantSolicitada=Integer.parseInt(vista.getjTextFieldCant());
                double valorUnitario=Double.parseDouble(vista.getjTextFieldPrecio());
                
                articulo.setIdMateriaPrima(Integer.parseInt(vista.getIdMateriaPrima()));
                articulo.setMateriaPrima(vista.getNombreMateriaPrima());
                articulo.setValorUnitario(valorUnitario);
                articulo.setCantSolicitada(cantSolicitada);                                
                articulo.setTotal(cantSolicitada*valorUnitario);
                articulos.add(articulo);
                
                neto=calcularTotal();
                vista.setjTextFieldNeto(Double.toString(neto));                
                vista.cargarMateriaPrimaFac(articulos);
                vista.limpiarTextFieldArticulo();
 
            }catch (NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }        
        }
    }
    
    
    private void eliminarArticuloCarrito(){
        
        int fila=vista.getFila();        
        try{
            if(fila==-1){
               vista.gestionMensajes("Seleccione una fila", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            
            }else{
                articulos.remove(fila);
                neto=calcularTotal();
                vista.setjTextFieldNeto(Double.toString(neto)); 
                vista.cargarMateriaPrimaFac(articulos);
                vista.setFila(-1);
            }        
        }catch(IndexOutOfBoundsException e){
            vista.gestionMensajes("No hay fila seleccionada", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void comprar(){
        
        if(vista.getIdProveedor().equals("")){
            vista.gestionMensajes("Ingrese un proveedor", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else if(articulos.size()==0){
            vista.gestionMensajes("Ingrese árticulos al carrito", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else{
            
            try{
                
                Factura factura=new Factura();
                factura.setFechaFactura(vista.getjTextFieldFecha());
                factura.setProveedor(Integer.parseInt(vista.getIdProveedor()));
                factura.setNetoFactura(neto);
            
                int resultado = 0;
                resultado =  modeloFactura.crearFactura(factura);
                if(resultado == 1){
                
                    ArrayList<Factura> listaFacturas=modeloFactura.listadoFactura(0,0);
                    int id=listaFacturas.get(listaFacturas.size()-1).getId();
                    int validador=0;
                    
                for(int i=0;i<articulos.size();i++){       
                    
                    //SE CREA EL REGISTRO DE MATERIA PRIMA FACTURADA
                    MateriaPrimaFacturada articulo=articulos.get(i);
                    articulo.setIdFactura(id);
                    validador=modeloMateriaPrimaFac.crearRegistroArticulo(articulo);                    
                }
                
                if(validador==1){
                    vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    
                    MateriaPrimaDao modeloMateriaPrima=new MateriaPrimaDao();
                    // SE MODIFICA EL STOCK
                    for(int j=0;j<articulos.size();j++){
                        
                        int idMateriaPrima=articulos.get(j).getIdMateriaPrima();
                        int cantidad=articulos.get(j).getCantSolicitada();
                        
                        MateriaPrima MateriaPrimaResult=modeloMateriaPrima.listadoMateriaID(idMateriaPrima).get(0);

                        Double cantidadMateriaPrimaAnterior=MateriaPrimaResult.getStock();
                        MateriaPrimaResult.setStock(cantidad+cantidadMateriaPrimaAnterior);
                        
                        modeloMateriaPrima.modificarMateriaPrima(MateriaPrimaResult);
                        
                    }
                    /*
                    if(validador1==1){
                        vista.gestionMensajes("Stock actualizado con exito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    }*/

        
                    cancelarCompra();
                }else{
                    vista.gestionMensajes("Hubo un error", 
                                "Confirmación",JOptionPane.ERROR_MESSAGE);
                    }            
                }            
            
            }catch(NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }        
        }    
    }
    
    private void cancelarCompra(){
        articulos=new ArrayList<MateriaPrimaFacturada>();
        vista.cargarMateriaPrimaFac(articulos);
        vista.setjTextFieldNeto(""); 
        vista.limpiarTextFieldArticulo();        
    }

    //------------------------------------------------------------------------
    
    
    //LLENAR COMBO BOX PROVEEDOR
    private void comboBoxProveedor(){        
       ControladorProveedor controladorProv=new ControladorProveedor();
       
       JComboBox<String> jComboBoxProv=vista.getjComboBoxProv();
       ArrayList<Proveedor> listadoProveedores;
       jComboBoxProv.removeAllItems();
       
       listadoProveedores=controladorProv.listadoPro();
       
       for (int i=0;i<listadoProveedores.size();i++){
           String id=Integer.toString(listadoProveedores.get(i).getId());
           jComboBoxProv.addItem(id+"-"+listadoProveedores.get(i).getNombre());
       }  
       
       vista.setjComboBoxProveedor(jComboBoxProv);        
    }
    
    
    //LLENAR COMBO BOX MATERIA PRIMA
    private void comboBoxMateriaPrima(){ 
       
       ControladorMateriaPrima controladorMP=new ControladorMateriaPrima();
        
       JComboBox<String> jComboBoxProd=vista.getjComboBoxCodPro();
       ArrayList<MateriaPrima> listadoMateriaPrima;       
       jComboBoxProd.removeAllItems();
       
       listadoMateriaPrima=controladorMP.listaMateriaP();
       
       for (int i=0;i<listadoMateriaPrima.size();i++){
           String id=Integer.toString(listadoMateriaPrima.get(i).getId());
           jComboBoxProd.addItem(id+"-"+listadoMateriaPrima.get(i).getNombre()+" ("+listadoMateriaPrima.get(i).getUnidadMedida()+")");
       }       
       vista.setjComboBoxProducto(jComboBoxProd);      
    
    }       
    
    private double calcularTotal(){
        
        double neto=0;
        for(int i=0; i<articulos.size();i++){
            neto=neto+articulos.get(i).getTotal();
        }        
        return neto;
    }

    
    
}
