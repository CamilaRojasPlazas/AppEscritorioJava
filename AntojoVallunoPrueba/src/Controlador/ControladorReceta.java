
package Controlador;


import Modelo.MateriaPrima;
import Modelo.MateriaPrimaDao;
import Modelo.Producto;
import Modelo.ProductoDao;
import Modelo.Receta;
import Modelo.RecetaDao;
import Vista.VRecetas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControladorReceta {
    
    private VRecetas vista;
    private RecetaDao modeloReceta;
    private ArrayList<Receta> articulos=new ArrayList<Receta>();

    public ControladorReceta(VRecetas vista, RecetaDao modeloReceta) {
        this.vista = vista;
        this.modeloReceta=modeloReceta;
        
        this.vista.addListenerBtnAgregar(new listenerProductosRequeridos());
        this.vista.addListenerEliminar(new listenerProductosRequeridos());
        this.vista.addListenerAgregarReceta(new listenerProductosRequeridos());
        this.vista.addListenerBuscarProducto(new listenerProductosRequeridos());
        this.vista.addListenerEliminarReceta(new listenerProductosRequeridos());
      
        comboBoxProducto();
        comboBoxMateriaPrima();
    }
    
    class listenerProductosRequeridos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("Agregar")){
                articulosRequeridos();
            }else if(e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarMateriaPrima();
            }else if(e.getActionCommand().equalsIgnoreCase("AGREGAR RECETA")){
                AgregarReceta();
            }else if(e.getActionCommand().equalsIgnoreCase("BuscarP")){
                BuscarXProducto();
            }else if(e.getActionCommand().equalsIgnoreCase("ELIMINAR RECETA")){
                EliminarReceta();
            }            
        }
    }
    
    //AGREGAR ARTICULOS AL CARRITO
    private void articulosRequeridos(){
        
        if(vista.getMateriaPrimaId().equals("")){
            vista.gestionMensajes("Ingrese la materia prima", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }else if(vista.getMateriaPrimaNombre().equals("")){
            vista.gestionMensajes("Ingrese la materia prima", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }else if (vista.getjTextFieldCant().equals("")){
            vista.gestionMensajes("Ingrese la cantidad requerida", "Error de Entrada", JOptionPane.ERROR_MESSAGE);         
        }else if(vista.getProductoId().equals("")){
            vista.gestionMensajes("Ingrese  el producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
        }else if(vista.getProductoNombre().equals("")){
            vista.gestionMensajes("Ingrese  el producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
            
        }else{
            
            Receta materiaPrimaReceta=new Receta();
            
            try{

                materiaPrimaReceta.setIdProducto(Integer.parseInt(vista.getProductoId()));
                materiaPrimaReceta.setProducto(vista.getProductoNombre());
                materiaPrimaReceta.setIdMateriaPrima(Integer.parseInt(vista.getMateriaPrimaId()));
                materiaPrimaReceta.setMateriaPrima(vista.getMateriaPrimaNombre());                                
                materiaPrimaReceta.setCantidad(Double.parseDouble(vista.getjTextFieldCant()));
                articulos.add(materiaPrimaReceta);
                
                vista.cargarReceta(articulos);
                vista.limpiarTextFieldArticulo();
 
            }catch (NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }        
        }        
        
    }
    
    private void eliminarMateriaPrima(){
        
        int fila=vista.getFila();        
        try{
            if(fila==-1){
               vista.gestionMensajes("Seleccione una fila", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            
            }else{
                articulos.remove(fila);
                vista.cargarReceta(articulos);
                vista.setFila(-1);
            }        
        }catch(IndexOutOfBoundsException e){
            vista.gestionMensajes("No hay fila seleccionada", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void AgregarReceta(){
        
        int validadorCant=0;
        
        if(articulos.size()==0){
            vista.gestionMensajes("Ingrese al menos un ingrediente", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else{
            
            try{
                            
                for(int i=0;i<articulos.size();i++){       
                    
                    //SE CREA EL REGISTRO DE LA RECETA
                    Receta ingrediente=articulos.get(i);  
                    int validador=modeloReceta.crearRegistroReceta(ingrediente);
                    validadorCant=validadorCant+validador;
                    
                }
                
                if(validadorCant==articulos.size()){
                    vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    
                    articulos=new ArrayList<Receta>();
                    vista.cargarReceta(articulos);
                    vista.setjTextFieldCantidad("");
                    
                }

            }catch(Exception ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }        
        }    
    }
    
    private void BuscarXProducto(){        
        
        if(vista.getProductoId().equals("")){
            vista.gestionMensajes("Seleccione el producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else{            
            try{                
                int idProducto=Integer.parseInt(vista.getProductoId());
                
                ArrayList<Receta> lista;                
                lista=modeloReceta.listadoReceta(idProducto);
                
                for(int i=0;i<lista.size();i++){
                    
                    ProductoDao productoDao=new ProductoDao();
                    MateriaPrimaDao materiaDao=new MateriaPrimaDao();
                    
                    String nombreProducto=productoDao.listadoProducto(vista.getProductoId(), 1).get(0).getNombre();
                    lista.get(i).setProducto(nombreProducto);
                    String nombreMateriaPrima=materiaDao.listadoMateriaID(lista.get(i).getIdMateriaPrima()).get(0).getNombre();
                    lista.get(i).setMateriaPrima(nombreMateriaPrima);
                }
                
                if(lista.size()!=0){
                    
                    vista.desabilitarBotones();
                    vista.cargarReceta(lista);
                }
                if(lista.size()==0){
                    vista.gestionMensajes("No hay receta registrada para dicho producto", "Error de Entrada", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }        
        }    
    }
    
    private void EliminarReceta(){        
        
        if(vista.getProductoId().equals("")){
            vista.gestionMensajes("Seleccione el producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else{            
            try{                
                int idProducto=Integer.parseInt(vista.getProductoId());
  
                if(this.modeloReceta.borrarReceta(idProducto)!=0){
                    vista.gestionMensajes("Se ha eliminado la receta", "Error de Entrada", JOptionPane.INFORMATION_MESSAGE);
                    articulos=new ArrayList<Receta>();
                    vista.cargarReceta(articulos);
                }else{
                    vista.gestionMensajes("Ha ocurrido un error con la eliminación", "Error al borrar", JOptionPane.ERROR_MESSAGE);
                }              
            }catch(Exception ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }        
        }    
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
    
    //LLENAR COMBO BOX MATERIA PRIMA
    private void comboBoxMateriaPrima(){       
        
       MateriaPrimaDao materiaPrimaDao=new MateriaPrimaDao();
       
       JComboBox<String> jComboBoxMateriaPrima=vista.getjComboBoxMateriaP();
       
       ArrayList<MateriaPrima> listadoMateriaPrima;
       jComboBoxMateriaPrima.removeAllItems();
       
       listadoMateriaPrima=materiaPrimaDao.listadoMateriaPrima("0");
       
       for (int i=0;i<listadoMateriaPrima.size();i++){
           String id=Integer.toString(listadoMateriaPrima.get(i).getId());
           jComboBoxMateriaPrima.addItem(id+"-"+listadoMateriaPrima.get(i).getNombre()+"-"+listadoMateriaPrima.get(i).getUnidadMedida());
       }  
       
       vista.setjComboBoxMateriaP(jComboBoxMateriaPrima);
    }

    
    
}
