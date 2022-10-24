
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Vista.VClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControladorClientes {
    
    private VClientes vista;
    private ClienteDao modelo;
    
    public ControladorClientes(VClientes vista, ClienteDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.addListenerBtnRegistrar(new listenerCliente());
        this.vista.addListenerBtnEliminar(new listenerCliente());
        this.vista.addListenerBtnModificar(new listenerCliente());
        this.vista.addListenerBtnBuscar(new listenerCliente());
        this.vista.addListenerBtnListar(new listenerCliente());

        ArrayList<Cliente> listadoClientes;
        listadoClientes = this.modelo.listadoClientes("0");
        this.vista.cargarClientes(listadoClientes);
        
    }
    
    class listenerCliente implements ActionListener{

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
                
                ArrayList<Cliente> listadoProductos;
                listadoProductos = modelo.listadoClientes("0");
                vista.cargarClientes(listadoProductos);
            }
            
        }
        
        //METODO REGISTRAR CLIENTE
        private void registrar(){
            
            if(vista.getNombre().equals("")){
                vista.gestionMensajes("Ingrese el nombre del cliente", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if(vista.getCedula().equals("")){
                vista.gestionMensajes("Ingrese el nombre del producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getTelefono().equals("")){
                    vista.gestionMensajes("Ingrese el telefono","Error de Entrada", JOptionPane.ERROR_MESSAGE );          
            }else{
                
                Cliente cliente= new Cliente();
                
                try{
                    cliente.setNombre(vista.getNombre());
                    cliente.setCedula(Integer.parseInt(vista.getCedula()));
                    cliente.setTelefono(vista.getTelefono());    
                
                    int resultado = 0;
                    resultado = modelo.crearCliente(cliente);
                
                    if(resultado == 1){
                        vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    
                        ArrayList<Cliente> listadoClientes;
                        listadoClientes = modelo.listadoClientes("0");
                        vista.cargarClientes(listadoClientes);
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
        
        //METODO ELIMINAR CLIENTE
        private void eliminar(){
            
            String id="";            
     
            id  = vista.getID().trim();
    
            try{
                if(id.isEmpty()){
                     vista.gestionMensajes(
                             "Por favor seleccione un cliente de la tabla",
                             "Mensaje de Advertencia ", 
                        JOptionPane.ERROR_MESSAGE);
                }else{
                    int respuesta = JOptionPane.showConfirmDialog(null,
                            "¿Desea Eliminar el cliente : " +
                            vista.getNombre() + " ?", 
                            "Confirmación de Acción", JOptionPane.YES_NO_OPTION);

                    if(respuesta == JOptionPane.YES_OPTION){                    
                        if(modelo.borrarCliente(Integer.parseInt(id)) ==  1){
                            JOptionPane.showMessageDialog(null, 
                                    "Registro Borrado con éxtio", 
                                    "Confirmación de acción", 
                                    JOptionPane.INFORMATION_MESSAGE);                    

                            ArrayList<Cliente> listadoClientes;
                            listadoClientes = modelo.listadoClientes("0");
                            vista.cargarClientes(listadoClientes);
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
                
            }catch(NumberFormatException ext){
                    vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);                
            }        
        }
        
        //METODO PARA ACTUALIZAR CLIENTE
        private void actualizar(){

            if(vista.getID().equals("")){
                vista.gestionMensajes("Seleccione una fila", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if(vista.getNombre().equals("")){
                vista.gestionMensajes("Ingrese el nombre", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getCedula().equals("")){
                    vista.gestionMensajes("Ingrese la cedula","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else if (vista.getTelefono().equals("")){
                    vista.gestionMensajes("Ingrese el telefono","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{
                
                Cliente cliente = new Cliente();
                
                try{
                    cliente.setId(Integer.parseInt(vista.getID()));
                    cliente.setNombre(vista.getNombre());                                          
                    cliente.setCedula(Integer.parseInt(vista.getCedula())); 
                    cliente.setTelefono(vista.getTelefono());
                    
                    if(modelo.modificarCliente(cliente) == 1){
                        vista.gestionMensajes("Actualización exitosa", "Confirmación ", JOptionPane.INFORMATION_MESSAGE);  
                        
                        ArrayList<Cliente> listadoClientes;
                        listadoClientes = modelo.listadoClientes("0");
                        vista.cargarClientes(listadoClientes);
                        vista.limpiarTextField();                        
                    }else{
                        vista.gestionMensajes("Actualización Falida","Confirmación ",JOptionPane.ERROR_MESSAGE); 
                    }    
                }catch(NumberFormatException ext){                    
                    vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
                }
            }            
        }
        
        //METODO PARA BUSCAR CLIENTE
        private void buscar(){
            
            String cedula;
            cedula  = vista.getCedula().trim();
            
            if(cedula.isEmpty()){
                 vista.gestionMensajes(
                         "Por favor seleccione una fila",
                         "Mensaje de Advertencia ", 
                    JOptionPane.ERROR_MESSAGE);
            }else{

                try{

                    ArrayList<Cliente> listadoClientes;
                    listadoClientes = modelo.listadoClientes(cedula);
                    vista.cargarClientes(listadoClientes);
                    vista.limpiarTextField();   
                    
                }catch(NumberFormatException ext){                    
                    vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
                }
            
            }            
        } 
        
    }
    
}
