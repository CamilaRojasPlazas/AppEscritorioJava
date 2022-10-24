
package Controlador;


import Modelo.Lote;
import Modelo.LoteDao;
import Modelo.MateriaPrima;
import Modelo.MateriaPrimaDao;
import Modelo.Produccion;

import Modelo.ProduccionDao;
import Modelo.Producto;
import Modelo.ProductoDao;
import Modelo.Receta;
import Modelo.RecetaDao;
import Vista.VProduccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;



/**
 *
 * @author ASUS
 */
public class ControladorProduccion {
    
    private VProduccion vista;
    private ProduccionDao modelo;
    private String codigo="";
    private LoteDao modeloLote=new LoteDao();
    private RecetaDao modeloReceta=new RecetaDao();
    private MateriaPrimaDao modeloMateriaPrima=new MateriaPrimaDao();
    private ProductoDao modeloProducto=new ProductoDao();
    
    public ControladorProduccion(VProduccion vista, ProduccionDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.addListenerBtnRegistrarLote(new listenerProduccion());
        this.vista.addListenerBtnProducir(new listenerProduccion());
        
        vista.setjTextFieldFecha();
        comboBoxProducto();
        cargarProduccion();
   
    }
    
    class listenerProduccion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("REGISTRAR LOTE")){
                RegistrarLote();
            }else if(e.getActionCommand().equalsIgnoreCase("PRODUCIR")){
                Producir();
            }else if(e.getActionCommand().equalsIgnoreCase("Modificar")){
                //actualizar();
            }else if(e.getActionCommand().equalsIgnoreCase("Buscar")){
                //buscar();
            }
            
        }
    }
    
    //Registrar lote
    private void RegistrarLote(){
        
        if(vista.horaActual().equals("")){
            vista.gestionMensajes("Error al cargar fecha", "Error de Entrada", JOptionPane.ERROR_MESSAGE);            
        }else{                
            codigo=JOptionPane.showInputDialog("Digite el seriado del lote");
        }
    }
    
    //AGREGAR ARTICULOS AL CARRITO
    private void Producir(){
        
        if(vista.horaActual().equals("")){
            vista.gestionMensajes("Error al cargar fecha", "Error de Entrada", JOptionPane.ERROR_MESSAGE);            
        }else if(vista.getIdProducto().equals("")){                
            vista.gestionMensajes("Ingrese el producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }else if(vista.getjTextFieldCodigo().equals("")){                
            vista.gestionMensajes("Digite el código de la producción", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }else if(vista.getjTextFieldCantidad().equals("")){                
            vista.gestionMensajes("Digite la cantidad a producir", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }else if(codigo.equals("")){                
            vista.gestionMensajes("Registre el Lote", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }else{
            
            try{
                int cantidadProducir=Integer.parseInt(vista.getjTextFieldCantidad());
                int idProducto=Integer.parseInt(vista.getIdProducto());
                
                if(verificarDisponibilidad(idProducto,cantidadProducir)){
                    
                    Lote miLote=new Lote();
                    miLote.setFechaLote(vista.getjTextFieldFecha());
                    miLote.setSeriado(codigo);
                    int resultado = 0;
                    resultado =  modeloLote.crearLote(miLote);
                    
                    if(resultado==1){  
                        
                        ArrayList<Lote> listaLotes=modeloLote.listadoLote("0");
                        int id=listaLotes.get(listaLotes.size()-1).getId();
                        
                        Produccion miProduccion=new Produccion();
                        miProduccion.setIdProducto(idProducto);
                        miProduccion.setCodigo(Integer.parseInt(vista.getjTextFieldCodigo()));
                        miProduccion.setCantidad(cantidadProducir);
                        miProduccion.setIdLote(id);
                        
                        resultado = 0;
                        resultado = modelo.RegistrarProduccion(miProduccion);
                        if(resultado == 1){                            
                            vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                            
                            actualizarInventarioMateriaPrima(idProducto,cantidadProducir); 
                            actualizarInventarioProducto(idProducto,cantidadProducir);
                            cargarProduccion();
                            vista.limpiarTextField();
                        }
                    }                
                }      
            
            }catch(NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }        
        }
    }
    
    //MIRAR SI HAY DISPONIBILIDAD DE MATERIA PRIMA PARA ACTUALIZAR
    private boolean verificarDisponibilidad(int idProducto,int cantidad){
        
        boolean verificador=false;
        ArrayList<Receta> listaMateriaPrima=modeloReceta.listadoReceta(idProducto);
        
        if(listaMateriaPrima.size()!=0){           
            
            for(int i=0;i<listaMateriaPrima.size();i++){
                
                int codigoMateriaPrima=listaMateriaPrima.get(i).getIdMateriaPrima();
                double cantidadRequerida=listaMateriaPrima.get(i).getCantidad();
                
                MateriaPrima ingrediente=modeloMateriaPrima.listadoMateriaID(codigoMateriaPrima).get(0);                
                Double stock=ingrediente.getStock();
                
                if((cantidad*cantidadRequerida)<=stock){
                    verificador=true;
                
                }else{
                    vista.gestionMensajes("Uno de los ingredientes de la receta no es suficiente", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                    break;
                } 
            }
            
        }else{
            vista.gestionMensajes("No hay receta asignada para ese producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
        return verificador;
    }
    
    
    //ACTUALIZAR INVENTARIO
    private void actualizarInventarioMateriaPrima(int idProducto,int cantidad){
        
        ArrayList<Receta> listaMateriaPrima=modeloReceta.listadoReceta(idProducto);
        
        if(listaMateriaPrima.size()!=0){           
            
            for(int i=0;i<listaMateriaPrima.size();i++){
 
                //RECETA
                int idMateriaPrima=listaMateriaPrima.get(i).getIdMateriaPrima();
                double cantidadRequeridaUnidad=listaMateriaPrima.get(i).getCantidad();
                
                //TOTAL REQUERIDO
                double TotalMateriaPrimaRequerida=cantidadRequeridaUnidad*cantidad;
                
                //BUSCAR LA MATERIA PRIMA PARA OBTENER EL STOCK
                MateriaPrima MateriaPrimaResult=modeloMateriaPrima.listadoMateriaID(idMateriaPrima).get(0);
                Double cantidadMateriaPrimaActual=MateriaPrimaResult.getStock();
                MateriaPrimaResult.setStock(cantidadMateriaPrimaActual-TotalMateriaPrimaRequerida);
                
                modeloMateriaPrima.modificarMateriaPrima(MateriaPrimaResult);
                
            }
            
        }else{
            vista.gestionMensajes("No hay receta asignada para ese producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //ACTUALIZAR INVENTARIO
    private void actualizarInventarioProducto(int idProducto,int cantidad){
        
        //BUSCAR LA MATERIA PRIMA PARA OBTENER EL STOCK
        Producto miProducto=modeloProducto.listadoProducto(Integer.toString(idProducto),1).get(0);
        
        int cantidadProductoActual=miProducto.getStockProducto();
        miProducto.setStockProducto(cantidadProductoActual+cantidad);
                
        modeloProducto.modificarProducto(miProducto);
    }
    
    //CARGAR TABLA
    private void cargarProduccion(){
        
        ArrayList<Produccion> listadoProduccion;
        listadoProduccion = this.modelo.listadoProduccion("0");

        if(listadoProduccion.size()!= 0){
            
            for(int i=0;i<listadoProduccion.size();i++){
                
                int idProducto=listadoProduccion.get(i).getIdProducto();
                String nombreProducto=modeloProducto.listadoProducto(Integer.toString(idProducto),1).get(0).getNombre();
                listadoProduccion.get(i).setNombreProducto(nombreProducto);
            }
        }        
        vista.cargarProduccion(listadoProduccion);        
    }

    
    //LLENAR COMBO BOX PRODUCTO
    private void comboBoxProducto(){       
        
       ProductoDao productoDao=new ProductoDao();
       
       JComboBox<String> jComboBoxProd=vista.getjComboBoxProducto();
       ArrayList<Producto> listadoProductos;
       jComboBoxProd.removeAllItems();
       
       listadoProductos=productoDao.listadoProducto("0",0);
       
       for (int i=0;i<listadoProductos.size();i++){
           String id=Integer.toString(listadoProductos.get(i).getId());
           jComboBoxProd.addItem(id+"-"+listadoProductos.get(i).getNombre());
       }  
       
       vista.setjComboBoxProducto(jComboBoxProd);
    }
    
}
