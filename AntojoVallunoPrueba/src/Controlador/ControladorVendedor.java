
package Controlador;


import Modelo.Cliente;
import Modelo.Vendedor;
import Modelo.VendedorDao;
import Vista.VVendedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControladorVendedor {
    
    private VVendedores vista;
    private VendedorDao modelo;
    
    public ControladorVendedor(VVendedores vista, VendedorDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.addListenerBtnRegistrar(new listenerVendedor());
        this.vista.addListenerBtnEliminar(new listenerVendedor());
        this.vista.addListenerBtnModificar(new listenerVendedor());
        this.vista.addListenerBtnBuscar(new listenerVendedor());
        this.vista.addListenerBtnListar(new listenerVendedor());

        ArrayList<Vendedor> listadoVendedores;
        listadoVendedores = this.modelo.listadoVendedores("0");
        this.vista.cargarVendedores(listadoVendedores);
        
    }
    
    class listenerVendedor implements ActionListener{

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
                
                ArrayList<Vendedor> listadoVendedores;
                listadoVendedores = modelo.listadoVendedores("0");
                vista.cargarVendedores(listadoVendedores);
            }
            
        }
        
        //METODO REGISTRAR VENDEDOR
        private void registrar(){
            
            if(vista.getNombre().equals("")){
                vista.gestionMensajes("Ingrese el nombre del vendedor", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if(vista.getCedula().equals("")){
                vista.gestionMensajes("Ingrese la cedula del vendedor", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getTelefono().equals("")){
                    vista.gestionMensajes("Ingrese el telefono","Error de Entrada", JOptionPane.ERROR_MESSAGE );  
            }else if (vista.getCorreo().equals("")){
                    vista.gestionMensajes("Ingrese el correo","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{
                
                Vendedor vendedor= new Vendedor();
                
                try{
                    vendedor.setNombre(vista.getNombre());
                    vendedor.setCedula(Integer.parseInt(vista.getCedula()));
                    vendedor.setTelefono(vista.getTelefono()); 
                    vendedor.setCorreo(vista.getCorreo()); 
                
                    int resultado = 0;
                    resultado = modelo.crearVendedor(vendedor);
                
                    if(resultado == 1){
                        vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                    
                        ArrayList<Vendedor> listadoVendedores;
                        listadoVendedores = modelo.listadoVendedores("0");
                        vista.cargarVendedores(listadoVendedores);
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
        
        //METODO ELIMINAR VENDEDOR
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
                            "¿Desea Eliminar el vendedor : " +
                            vista.getNombre() + " ?", 
                            "Confirmación de Acción", JOptionPane.YES_NO_OPTION);

                    if(respuesta == JOptionPane.YES_OPTION){                    
                        if(modelo.borrarVendedor(Integer.parseInt(id)) ==  1){
                            JOptionPane.showMessageDialog(null, 
                                    "Registro Borrado con éxtio", 
                                    "Confirmación de acción", 
                                    JOptionPane.INFORMATION_MESSAGE);                    

                            ArrayList<Vendedor> listadoVendedores;
                            listadoVendedores = modelo.listadoVendedores("0");
                            vista.cargarVendedores(listadoVendedores);
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
        
        //METODO PARA ACTUALIZAR VENDEDOR
        private void actualizar(){

            if(vista.getID().equals("")){
                vista.gestionMensajes("Seleccione una fila", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if(vista.getNombre().equals("")){
                vista.gestionMensajes("Ingrese el nombre", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getCedula().equals("")){
                    vista.gestionMensajes("Ingrese la cedula","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else if (vista.getTelefono().equals("")){
                    vista.gestionMensajes("Ingrese el telefono","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else if (vista.getCorreo().equals("")){
                    vista.gestionMensajes("Ingrese el correo","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{
                
                Vendedor vendedor= new  Vendedor();
                
                try{
                    vendedor.setId(Integer.parseInt(vista.getID()));
                    vendedor.setCedula(Integer.parseInt(vista.getCedula())); 
                    vendedor.setNombre(vista.getNombre());
                    vendedor.setCorreo(vista.getCorreo());
                    vendedor.setTelefono(vista.getTelefono());
                    
                    if(modelo.modificarVendedor(vendedor) == 1){
                        vista.gestionMensajes("Actualización exitosa", "Confirmación ", JOptionPane.INFORMATION_MESSAGE);  
                        
                        ArrayList<Vendedor> listadoVendedores;
                        listadoVendedores = modelo.listadoVendedores("0");
                        vista.cargarVendedores(listadoVendedores);
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

                    ArrayList<Vendedor> listadoVendedores;
                    listadoVendedores = modelo.listadoVendedores(cedula);
                    vista.cargarVendedores(listadoVendedores);
                    vista.limpiarTextField();   
                    
                }catch(NumberFormatException ext){                    
                    vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
                }
            
            }            
        }
        
        
        
        
    }
    
    
}
