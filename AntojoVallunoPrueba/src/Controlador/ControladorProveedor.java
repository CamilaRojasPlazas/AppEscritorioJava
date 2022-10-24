
package Controlador;


import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Vista.VProveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControladorProveedor {
    
    private VProveedores vista;
    private ProveedorDao modelo;

    
    public ControladorProveedor() {
    }
    
    public ControladorProveedor(VProveedores vista, ProveedorDao modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.addListenerBtnRegistrar(new listenerProveedor());
        this.vista.addListenerBtnEliminar(new listenerProveedor());
        this.vista.addListenerBtnModificar(new listenerProveedor());
        this.vista.addListenerBtnBuscar(new listenerProveedor());
        this.vista.addListenerBtnListar(new listenerProveedor());     
        
        ArrayList<Proveedor> listadoProveedores;
        listadoProveedores = this.modelo.listadoProveedores("0");
        this.vista.cargarProveedores(listadoProveedores);
        
    }
    
    
               
    class listenerProveedor implements ActionListener{

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
                ArrayList<Proveedor> listadoProveedores;
                listadoProveedores = modelo.listadoProveedores("0");
                vista.cargarProveedores(listadoProveedores);
            }
            
        }        
        
        //METODO REGISTRAR PROVEEDOR
        private void registrar(){
            if(vista.getNit().equals("")){
                vista.gestionMensajes("Ingrese el NIT", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getTelefono().equals("")){
                    vista.gestionMensajes("Ingrese el telefono","Error de Entrada", JOptionPane.ERROR_MESSAGE ); 
            }else if (vista.getNombre().equals("")){
                    vista.gestionMensajes("Ingrese el nombre","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{
                
                Proveedor prov=new Proveedor();
                
                try{
                    prov.setNombre(vista.getNombre());
                    prov.setNit(Integer.parseInt(vista.getNit()));
                    prov.setTelefono(vista.getTelefono());                    
               
                    
                    int tamaño;
                    
                    tamaño = modelo.listadoProveedores(vista.getNit()).size();

                    
                    if(tamaño == 0){
                        
                        int resultado = 0;
                        resultado = modelo.crearProveedor(prov);
                    
                        if(resultado == 1){
                            vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE); 
                        
                        
                            ArrayList<Proveedor> listadoProveedores;
                            listadoProveedores = modelo.listadoProveedores("0");
                            vista.cargarProveedores(listadoProveedores);

                            vista.limpiarTextField();
                        
                        }
                        else{
                            vista.gestionMensajes("Error al grabar",
                                "Confirmación",JOptionPane.ERROR_MESSAGE);                                                 
                        }
                    }else{
                        vista.gestionMensajes("Codigo ya está registrado",
                            "Confirmación",
                            JOptionPane.ERROR_MESSAGE);                                      
                    }
                }catch(NumberFormatException ext){
                    vista.gestionMensajes("Ingrese datos correctos","Error de Entrada", JOptionPane.ERROR_MESSAGE );
                } 
                               
            }                                               
        }
        
        //METODO ELIMINAR PROVEEDOR
        private void eliminar(){
            String nit;
            nit  = vista.getNit().trim();
            
            if(nit.isEmpty()){
                 vista.gestionMensajes(
                         "Por favor seleccione un proveedor de la tabla",
                         "Mensaje de Advertencia ", 
                    JOptionPane.ERROR_MESSAGE);
            }else{
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "¿Desea Eliminar el proveedor : " +
                        vista.getNombre() + " ?", 
                        "Confirmación de Acción", JOptionPane.YES_NO_OPTION);
                
                if(respuesta == JOptionPane.YES_OPTION){                    

                    if(modelo.borrarProveedor(nit) ==  1){
                        JOptionPane.showMessageDialog(null, 
                                "Registro Borrado con éxtio", 
                                "Confirmación de acción", 
                                JOptionPane.INFORMATION_MESSAGE);                    
                       
                        ArrayList<Proveedor> listadoProveedores; 
                        listadoProveedores = modelo.listadoProveedores("0");
                        vista.cargarProveedores(listadoProveedores);
                        
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
        
        
        //METODO PARA ACTUALIZAR PROVEEDOR
        private void actualizar(){
            
            if(vista.getNit().equals("")){
                vista.gestionMensajes("Ingrese el NIT", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getTelefono().equals("")){
                    vista.gestionMensajes("Ingrese el telefono","Error de Entrada", JOptionPane.ERROR_MESSAGE ); 
            }else if (vista.getNombre().equals("")){
                    vista.gestionMensajes("Ingrese el nombre","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{

                Proveedor prov = new Proveedor();
                
                try{
                    prov.setId(Integer.parseInt(vista.getID()));
                    prov.setNit(Integer.parseInt(vista.getNit()));
                    prov.setNombre(vista.getNombre()); 
                    prov.setTelefono(vista.getTelefono()); 

                    if(modelo.modificarProveedor(prov) == 1){
                        vista.gestionMensajes(
                            "Actualización exitosa",
                            "Confirmación ", 
                            JOptionPane.INFORMATION_MESSAGE);
                                        
                        ArrayList<Proveedor> listadoProveedores; 
                        listadoProveedores = modelo.listadoProveedores("0");
                        vista.cargarProveedores(listadoProveedores);  
                        vista.limpiarTextField();
                    }else {
                        vista.gestionMensajes(
                            "Actualización Falida",
                            "Confirmación ", 
                            JOptionPane.ERROR_MESSAGE);  
                    }
                }catch(NumberFormatException ext){
                    vista.gestionMensajes("Ingrese datos correctos","Error de Entrada", JOptionPane.ERROR_MESSAGE );
                }              
            }
        }
        
        
        //METODO PARA BUSCAR PROVEEDOR
        private void buscar(){
            String nit;
            nit  = vista.getNit().trim();
            
            if(nit.isEmpty()){
                 vista.gestionMensajes(
                         "Por favor indique el NIT del proveedor",
                         "Mensaje de Advertencia ", 
                    JOptionPane.ERROR_MESSAGE);
            }else{                
                ArrayList<Proveedor> listadoProveedores; 
                listadoProveedores = modelo.listadoProveedores(nit);
                vista.cargarProveedores(listadoProveedores);
                        
                vista.limpiarTextField();
            
            }            
        } 
       
       
    }
    public ArrayList<Proveedor> listadoPro(){
        ProveedorDao modeloProv=new ProveedorDao();
        ArrayList<Proveedor> listadoProveedor;
        listadoProveedor = modeloProv.listadoProveedores("0");
        
        return listadoProveedor;
    }
    
}

