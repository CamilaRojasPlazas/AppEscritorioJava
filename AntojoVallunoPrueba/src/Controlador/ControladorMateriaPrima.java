package Controlador;

import Modelo.MateriaPrima;
import Modelo.MateriaPrimaDao;

import Vista.VMateriaPrima;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ControladorMateriaPrima {
    
    private VMateriaPrima vista;
    private MateriaPrimaDao modelo;
    
    public ControladorMateriaPrima(){}
    
    public ControladorMateriaPrima(VMateriaPrima vista, MateriaPrimaDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        

        this.vista.addListenerBtnRegistrar(new listenerMateriaPrima());
        this.vista.addListenerBtnEliminar(new listenerMateriaPrima());
        this.vista.addListenerBtnModificar(new listenerMateriaPrima());
        this.vista.addListenerBtnBuscar(new listenerMateriaPrima());
        this.vista.addListenerBtnListar(new listenerMateriaPrima());
        
        ArrayList<MateriaPrima> listadoMateriaPrima;
        listadoMateriaPrima = this.modelo.listadoMateriaPrima("0");
        this.vista.cargarMateriaPrima(listadoMateriaPrima);

    }
    
    class listenerMateriaPrima implements ActionListener{

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
                
                ArrayList<MateriaPrima> listadoMateriaPrima;
                listadoMateriaPrima = modelo.listadoMateriaPrima("0");
                vista.cargarMateriaPrima(listadoMateriaPrima);
            }
            
        }
        
        //METODO REGISTRAR MATERIA PRIMA
        private void registrar(){
            if(vista.getCodigo().equals("")){
                vista.gestionMensajes("Ingrese el Codigo", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }else if (vista.getNombre().equals("")){
                    vista.gestionMensajes("Ingrese el nombre","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else if (vista.getUnidadMedida().equals("")){
                    vista.gestionMensajes("Ingrese la unidad de medida","Error de Entrada", JOptionPane.ERROR_MESSAGE );
            }else{
                
                MateriaPrima mp = new MateriaPrima();
                try{
                    mp.setCodigo(Integer.parseInt(vista.getCodigo()));
                    mp.setNombre(vista.getNombre());
                    mp.setUnidadMedida(vista.getUnidadMedida());
                    mp.setStock(0);

                    int tamaño;
                    tamaño = modelo.listadoMateriaPrima(vista.getCodigo()).size();

                    if(tamaño == 0){
                        int resultado = 0;
                        resultado = modelo.crearMateriaPrima(mp);
                        if(resultado == 1){
                            vista.gestionMensajes("Registro Grabado con éxito", 
                                "Confirmación",JOptionPane.INFORMATION_MESSAGE); 
                        
                        
                            ArrayList<MateriaPrima> listadoMateriaPrima;
                            listadoMateriaPrima = modelo.listadoMateriaPrima("0");
                            vista.cargarMateriaPrima(listadoMateriaPrima);

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
                    vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE);                
                }
            }                                               
        }
        
        //METODO ELIMINAR MATERIA PRIMA
        private void eliminar(){
            String codigo="";
                
            codigo  = vista.getID().trim();
          
            
            if(codigo.isEmpty()){
                 vista.gestionMensajes(
                         "Por favor seleccione la materia prima de la tabla",
                         "Mensaje de Advertencia ", 
                    JOptionPane.ERROR_MESSAGE);
            }else{
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "¿Desea Eliminar: " +
                        vista.getNombre() + " ?", 
                        "Confirmación de Acción", JOptionPane.YES_NO_OPTION);
                
                if(respuesta == JOptionPane.YES_OPTION){                    

                    if(modelo.borrarMateriaPrima(codigo) ==  1){
                        JOptionPane.showMessageDialog(null, 
                                "Registro Borrado con éxtio", 
                                "Confirmación de acción", 
                                JOptionPane.INFORMATION_MESSAGE);                    
                       
                        ArrayList<MateriaPrima> listadoMateriaPrima;
                        listadoMateriaPrima = modelo.listadoMateriaPrima("0");
                        vista.cargarMateriaPrima(listadoMateriaPrima);

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
        
        //METODO PARA ACTUALIZAR MATERIA PRIMA
        private void actualizar(){
            
            MateriaPrima mp = new MateriaPrima();
            
            try{
                mp.setId(Integer.parseInt(vista.getID()));
                mp.setCodigo(Integer.parseInt(vista.getCodigo()));                                          
                mp.setNombre(vista.getNombre()); 
                mp.setUnidadMedida(vista.getUnidadMedida());
                
               MateriaPrima materia=modelo.listadoMateriaID(mp.getId()).get(0);
               
               mp.setStock(materia.getStock());
           
                if(modelo.modificarMateriaPrima(mp) == 1){
                    vista.gestionMensajes(
                        "Actualización exitosa",
                        "Confirmación ", 
                        JOptionPane.INFORMATION_MESSAGE);
                                        
                    ArrayList<MateriaPrima> listadoMateriaPrima;
                    listadoMateriaPrima = modelo.listadoMateriaPrima("0");
                    vista.cargarMateriaPrima(listadoMateriaPrima);

                    vista.limpiarTextField();
                } else {
                    vista.gestionMensajes(
                        "Actualización Falida",
                        "Confirmación ", 
                        JOptionPane.ERROR_MESSAGE);                 
                }            
            }catch(NumberFormatException ext){
                vista.gestionMensajes("Ingrese valores validos", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
            }
        }
        
        //METODO PARA BUSCAR PROVEEDOR
        private void buscar(){
            String codigo;
            codigo  = vista.getCodigo().trim();
            
            if(codigo.isEmpty()){
                 vista.gestionMensajes(
                         "Por favor indique el codigo de la materia prima",
                         "Mensaje de Advertencia ", 
                    JOptionPane.ERROR_MESSAGE);
            }else{                
                ArrayList<MateriaPrima> listadoMateriaPrima;
                listadoMateriaPrima = modelo.listadoMateriaPrima(codigo);
                vista.cargarMateriaPrima(listadoMateriaPrima);

                vista.limpiarTextField();
            
            }            
        } 
        
        
    }
    public ArrayList<MateriaPrima> listaMateriaP(){
        MateriaPrimaDao modeloMP=new MateriaPrimaDao();
        ArrayList<MateriaPrima> listadoMateriaPrima;
        listadoMateriaPrima = modeloMP.listadoMateriaPrima("0");
        return listadoMateriaPrima;
    }
    
}
