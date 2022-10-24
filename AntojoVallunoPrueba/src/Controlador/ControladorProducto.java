
package Controlador;

import Modelo.Producto;
import Modelo.ProductoDao;
import Modelo.RecetaDao;
import Vista.VProductos;
import Vista.VRecetas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControladorProducto {
    
    private VProductos vista;
    private ProductoDao modelo;

    public ControladorProducto(VProductos vista, ProductoDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.addListenerBtnRegistrar(new listenerProducto());
        this.vista.addListenerBtnEliminar(new listenerProducto());
        this.vista.addListenerBtnModificar(new listenerProducto());
        this.vista.addListenerBtnBuscar(new listenerProducto());
        this.vista.addListenerBtnListar(new listenerProducto());
        this.vista.addListenerBtnReceta(new listenerProducto());
        
        ArrayList<Producto> listadoProductos;
        listadoProductos = this.modelo.listadoProducto("0",0);
        this.vista.cargarProductos(listadoProductos);
        
    }
    
    class listenerProducto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("Registrar")){
                registrar();
            }else if(e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar();
            }else if(e.getActionCommand().equalsIgnoreCase("Modificar")){
                actualizar();
            }else if(e.getActionCommand().equalsIgnoreCase("Buscar")){
                buscar();
            }else if(e.getActionCommand().equalsIgnoreCase("Listar")){
                ArrayList<Producto> listadoProductos;
                listadoProductos = modelo.listadoProducto("0",0);
                vista.cargarProductos(listadoProductos);
            }else if(e.getActionCommand().equalsIgnoreCase("GESTIÓN RECETAS")){
                
                VRecetas view = new VRecetas();
                RecetaDao model = new RecetaDao();
        
                ControladorReceta programaController = new ControladorReceta(view,model);

                view.setVisible(true);
            
            }
            
        }
        
        //METODO REGISTRAR PRODUCTO
        private void registrar(){
            if(vista.getCodigo().equals("")){
                vista.gestionMensajes("Ingrese codigo del producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if(vista.getNombreProducto().equals("")){
                vista.gestionMensajes("Ingrese el nombre del producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getPrecioDetal().equals("")){
                    vista.gestionMensajes("Ingrese el precio al detal","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else if (vista.getPrecioMayor().equals("")){
                    vista.gestionMensajes("Ingrese el precio al por mayor","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{
                
                Producto produc = new Producto();
                try{
                    produc.setCodigo(vista.getCodigo());
                    produc.setNombre(vista.getNombreProducto());
                    produc.setValorDetal(Double.parseDouble(vista.getPrecioDetal()));
                    produc.setValorMayor(Double.parseDouble(vista.getPrecioMayor()));
                    produc.setStockProducto(0);      
                
                    int resultado = 0;
                    resultado = modelo.crearProducto(produc);
                
                    if(resultado == 1){
                        vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    
                        ArrayList<Producto> listadoProductos;
                        listadoProductos = modelo.listadoProducto("0",0);
                        vista.cargarProductos(listadoProductos);
                        vista.limpiarTextField();
                    
                    }else{
                        vista.gestionMensajes("Error al grabar",
                                "Confirmación",JOptionPane.ERROR_MESSAGE);
                    }
                }catch(NumberFormatException ext){
                    vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);                
                }               
            }                                               
        }
        
        //METODO ELIMINAR PRODUCTO
        private void eliminar(){
            
            String id="";            
     
            id  = vista.getID().trim();
    
            
            if(id.isEmpty()){
                 vista.gestionMensajes(
                         "Por favor seleccione un producto de la tabla",
                         "Mensaje de Advertencia ", 
                    JOptionPane.ERROR_MESSAGE);
            }else{
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "¿Desea Eliminar el producto : " +
                        vista.getNombreProducto() + " ?", 
                        "Confirmación de Acción", JOptionPane.YES_NO_OPTION);
                
                if(respuesta == JOptionPane.YES_OPTION){                    
                    if(modelo.borrarProducto(Integer.parseInt(id)) ==  1){
                        JOptionPane.showMessageDialog(null, 
                                "Registro Borrado con éxtio", 
                                "Confirmación de acción", 
                                JOptionPane.INFORMATION_MESSAGE);                    
                       
                        ArrayList<Producto> listadoProductos;
                        listadoProductos = modelo.listadoProducto("0",0);
                        vista.cargarProductos(listadoProductos);
                        vista.limpiarTextField();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, 
                                "Error al borrar",
                                "Confirmación de acción", 
                                JOptionPane.ERROR_MESSAGE);                    
                    }
                }
            }
        }
        
        
        //METODO PARA ACTUALIZAR PRODUCTO
        private void actualizar(){

            if(vista.getCodigo().equals("")){
                vista.gestionMensajes("Seleccione una fila", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if(vista.getNombreProducto().equals("")){
                vista.gestionMensajes("Seleccione una fila", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getPrecioDetal().equals("")){
                    vista.gestionMensajes("Seleccione una fila","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else if (vista.getPrecioMayor().equals("")){
                    vista.gestionMensajes("Seleccione una fila","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{
                
                Producto produc = new Producto();
                
                try{
                    produc.setId(Integer.parseInt(vista.getID()));
                    produc.setCodigo(vista.getCodigo());
                    produc.setNombre(vista.getNombreProducto());                                          
                    produc.setValorDetal(Double.parseDouble(vista.getPrecioDetal())); 
                    produc.setValorMayor(Double.parseDouble(vista.getPrecioMayor()));
                    
                    Producto modeloProducto=modelo.listadoProducto(vista.getID(),1).get(0);
                    produc.setStockProducto(modeloProducto.getStockProducto());
                    

                    if(modelo.modificarProducto(produc) == 1){
                        vista.gestionMensajes("Actualización exitosa", "Confirmación ", JOptionPane.INFORMATION_MESSAGE);  
                        
                        ArrayList<Producto> listadoProductos;
                        listadoProductos = modelo.listadoProducto("0",0);
                        vista.cargarProductos(listadoProductos);
                        vista.limpiarTextField();                        
                    }else{
                        vista.gestionMensajes("Actualización Falida","Confirmación ",JOptionPane.ERROR_MESSAGE); 
                    }                   
                    
                }catch(NumberFormatException ext){                    
                    vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
                }
            }            
        }
        
        //METODO PARA BUSCAR PRODUCTO
        private void buscar(){
            
            String codigo;
            codigo  = vista.getCodigo().trim();
            
            if(codigo.isEmpty()){
                 vista.gestionMensajes(
                         "Por favor indique el codigo de la materia prima",
                         "Mensaje de Advertencia ", 
                    JOptionPane.ERROR_MESSAGE);
            }else{                
                ArrayList<Producto> listadoProductos;
                listadoProductos = modelo.listadoProducto(codigo,2);
                vista.cargarProductos(listadoProductos);
                vista.limpiarTextField();    
            
            }            
        } 
    }   
}
