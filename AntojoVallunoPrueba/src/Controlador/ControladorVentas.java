
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.DetalleVenta;
import Modelo.DetalleVentaDao;
import Modelo.FacturaVenta;
import Modelo.FacturaVentaDao;
import Modelo.Producto;
import Modelo.ProductoDao;
import Modelo.Vendedor;
import Modelo.VendedorDao;
import Vista.VVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Egresados
 */
public class ControladorVentas {
    
    private VVentas vista;
    private DetalleVentaDao modeloDetalleVenta=new DetalleVentaDao();
    private VendedorDao modeloVendedor=new VendedorDao();
    private ClienteDao modeloCliente=new ClienteDao();
    private ProductoDao modeloProducto=new ProductoDao();
    private FacturaVentaDao modeloFacturaVenta=new FacturaVentaDao();
    
    private ArrayList<DetalleVenta> productos=new ArrayList<DetalleVenta>();
    double neto;
    
    public ControladorVentas(VVentas vista) {
        this.vista = vista;     
        
        
        this.vista.addListenerBtnAgregar(new listenerVentas());
        this.vista.addListenerBtnEliminar(new listenerVentas());
        this.vista.addListenerBtnCancelarCompra(new listenerVentas());
        this.vista.addListenerBtnRegistrarCompra(new listenerVentas());
 
        vista.setjTextFieldFecha();
        vista.agergarItemsLugarRegistro();  
        vista.setjTextFieldCoordX();
        vista.setjTextFieldCoordY();   
        comboBoxVendedor();
        comboBoxCliente();
        comboBoxProducto();
    }
    
    class listenerVentas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("Agregar")){
                articulosCarrito();
            }else if(e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarArticuloCarrito();
            }else if(e.getActionCommand().equalsIgnoreCase("Cancelar Compra")){
                cancelarVenta();
            }else if(e.getActionCommand().equalsIgnoreCase("Vender")){
                comprar();
            }
            
        }
    }
    
    //AGREGAR ARTICULOS AL CARRITO
    private void articulosCarrito(){
        
        if(vista.getProduct().equals("")){
            vista.gestionMensajes("Seleccione un producto", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else if (vista.getjTextFieldCant().equals("")){
            vista.gestionMensajes("Ingrese una cantidad", "Error de Entrada", JOptionPane.ERROR_MESSAGE);   
        }else{
            
            DetalleVenta produc=new DetalleVenta();
            
            try{                
    
                
                int cantSolicitada=Integer.parseInt(vista.getjTextFieldCant());    
                
                if(validarExistencia(vista.getProduct(),cantSolicitada)){
                    
                    produc.setIdProducto(Integer.parseInt(vista.getProduct()));
                    produc.setProducto(vista.getNombreProduct());
                    
                    if(cantSolicitada<6){
                        produc.setTipoVenta("Detal"); 
                        produc.setValorUnitario(precioProducto(vista.getProduct(),1));     

                    }else{
                        produc.setTipoVenta("Por mayor");
                        produc.setValorUnitario(precioProducto(vista.getProduct(),2));
                    }
                    
                    produc.setCantSolicitada(cantSolicitada);
                    produc.setTotal(cantSolicitada*precioProducto(vista.getProduct(),1));
                    
                    
                    productos.add(produc);
                    neto=calcularTotal();
                    vista.setjTextFieldNeto(Double.toString(neto));
                    vista.setCantidad();
                    vista.cargarProductosFacturados(productos);
                    
                    
                       
                }else{
                    vista.gestionMensajes("No hay suficiente producto en inventario", "Error de Entrada", JOptionPane.INFORMATION_MESSAGE);
                }
                
 
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
                productos.remove(fila);
                neto=calcularTotal();
                vista.setjTextFieldNeto(Double.toString(neto)); 
                vista.cargarProductosFacturados(productos);
                vista.setFila(-1);
            }        
        }catch(IndexOutOfBoundsException e){
            vista.gestionMensajes("No hay fila seleccionada", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void comprar(){
        
        if(vista.getVendedor().equals("")){
            vista.gestionMensajes("Seleccione un vendedor", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else if(vista.getIdCliente().equals("")){
            vista.gestionMensajes("Seleccione el cliente", "Error de Entrada", JOptionPane.ERROR_MESSAGE);        
        }else if(vista.getLugar().equals("")){
            vista.gestionMensajes("Seleccione el lugar", "Error de Entrada", JOptionPane.ERROR_MESSAGE);  
        }else if(productos.size()==0){
            vista.gestionMensajes("Ingrese árticulos al carrito", "Error de Entrada", JOptionPane.ERROR_MESSAGE); 
        }else{
            
            try{
                
                FacturaVenta factura=new FacturaVenta();
                
                factura.setFechaFactura(vista.getjTextFieldFecha());
                factura.setIdVendedor(Integer.parseInt(vista.getVendedor()));
                factura.setIdCliente(Integer.parseInt(vista.getIdCliente()));
                factura.setLugar(vista.getLugar());
                factura.setCoordX(vista.getjTextFieldX());
                factura.setCoordY(vista.getjTextFieldY());
                factura.setNetoFactura(neto);
            
                int resultado = 0;
                resultado =  modeloFacturaVenta.crearFactura(factura);
                
                
                if(resultado == 1){
                
                    ArrayList<FacturaVenta> listaFacturas=modeloFacturaVenta.listadoFactura(0);                    
                    int id=listaFacturas.get(listaFacturas.size()-1).getId();
                    int validador=0;
                    int validador2=0;
                    for(int i=0;i<productos.size();i++){       

                        //SE CREA EL REGISTRO DE PRODUCTO FACTURADO
                        DetalleVenta articulo=productos.get(i);
                        articulo.setIdFacturaVenta(id);
                        validador=modeloDetalleVenta.RegistroProductoFacturado(articulo);
                        validador2=validador2+validador;
                    }
                    
                    if(validador2==productos.size()){

                        // SE MODIFICA EL STOCK
                        for(int j=0;j<productos.size();j++){

                            int idProducto=productos.get(j).getIdProducto();
                            int cantidad=productos.get(j).getCantSolicitada();

                            Producto ProductoPrimaResult=modeloProducto.listadoProducto(Integer.toString(idProducto), 1).get(0);

                            int cantidadMateriaPrimaAnterior=ProductoPrimaResult.getStockProducto();
                            ProductoPrimaResult.setStockProducto(cantidadMateriaPrimaAnterior-cantidad);
                            
                            validador=0;
                            validador= modeloProducto.modificarProducto(ProductoPrimaResult);
                        }

                        if(validador==1){
                            vista.gestionMensajes("Stock actualizado con exito", 
                                    "Confirmación",JOptionPane.INFORMATION_MESSAGE);
                        }


                        cancelarVenta();
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
    
    
    //LLENAR COMBO BOX VENDEDOR
    private void comboBoxVendedor(){       
       
       JComboBox<String> jComboBoxVendedor=vista.getjComboBoxVendedor();
       ArrayList<Vendedor> listadoVendedor;
       jComboBoxVendedor.removeAllItems();
       
       listadoVendedor=modeloVendedor.listadoVendedores("0");

       for (int i=0;i<listadoVendedor.size();i++){
           int cedula=listadoVendedor.get(i).getCedula();
           jComboBoxVendedor.addItem(cedula+"-"+listadoVendedor.get(i).getNombre());
       }  
       
       vista.setjComboBoxVendedor(jComboBoxVendedor);
    }
    
    //LLENAR COMBO BOX CLIENTE
    private void comboBoxCliente(){       
       
       JComboBox<String> jComboBoxCliente=vista.getjComboBoxCliente();
       ArrayList<Cliente> listadoClientes;
       jComboBoxCliente.removeAllItems();
       
       listadoClientes=modeloCliente.listadoClientes("0");

       for (int i=0;i<listadoClientes.size();i++){
           int cedula=listadoClientes.get(i).getCedula();
           jComboBoxCliente.addItem(cedula+"-"+listadoClientes.get(i).getNombre());
       }  
       
       vista.setjComboBoxCliente(jComboBoxCliente);
    }
    
    //LLENAR COMBO BOX PRODUCTO
    private void comboBoxProducto(){       
       
       JComboBox<String> jComboBoxProducto=vista.getjComboBoxCodPro();
       ArrayList<Producto> listadoProductos;
       jComboBoxProducto.removeAllItems();
       
       listadoProductos=modeloProducto.listadoProducto("0", 0);

       for (int i=0;i<listadoProductos.size();i++){
           String id=Integer.toString(listadoProductos.get(i).getId());
           jComboBoxProducto.addItem(id+"-"+listadoProductos.get(i).getNombre());
       }  
       
       vista.setjComboBoxCodPro(jComboBoxProducto);
    }
    
    //VALIDA QUE LA CANTIDAD ESTE DISPONIBLE 
    private boolean validarExistencia(String id, int cantidad){
        
        ArrayList<Producto> listadoProductos;
        listadoProductos=modeloProducto.listadoProducto(id, 1);
        
        if(listadoProductos.get(0).getStockProducto()>=cantidad){
            return true;
        }else{
            return false;
        }   
    }
    
    //OBTENER EL PRECIO 
    private double precioProducto(String id, int opcion){
        
        ArrayList<Producto> listadoProductos;
        listadoProductos=modeloProducto.listadoProducto(id, 1);
        
        if(opcion==1){
            return listadoProductos.get(0).getValorDetal();
        }else{
            return listadoProductos.get(0).getValorMayor();
        }
        
    }
    
    private double calcularTotal(){
        
        double neto=0;
        for(int i=0; i<productos.size();i++){
            neto=neto+productos.get(i).getTotal();
        }        
        return neto;
    }
    
    private void cancelarVenta(){
        productos=new ArrayList<DetalleVenta>();
        vista.cargarProductosFacturados(productos);
        vista.setjTextFieldNeto("");      
    }

    
}
