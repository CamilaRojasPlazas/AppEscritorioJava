
package Modelo;

import Recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ClienteDao {
    
    //PERMITE CREAR CLIENTE
    public int crearCliente(Cliente cliente){
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO Clientes values (default,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cliente.getNombre());
            pstm.setInt(2, cliente.getCedula());
            pstm.setString(3, cliente.getTelefono());
            rtdo = pstm.executeUpdate();  
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }        
        return rtdo;       
    }
    
    
    //METODO QUE PERMITE ELIMINAR CLIENTE
    public int borrarCliente(int cedula){  
        
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con = Conexion.getConeccionBD();
            String sql = "DELETE FROM Clientes WHERE idCliente= ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, cedula);
            rtdo = pstm.executeUpdate(); 
            return rtdo;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        } 
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    
    //METODO QUE PERMITE MODIFICAR CLIENTE
    public int modificarCliente(Cliente cliente){ 
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con = Conexion.getConeccionBD();
            String sql = "UPDATE Clientes " +
                         "SET nombre=?,cedula=?,telefono= ?"
                    +    "WHERE idCliente=?";
            
            pstm = con.prepareStatement(sql);    
            pstm.setString(1, cliente.getNombre());
            pstm.setInt(2, cliente.getCedula());
            pstm.setString(3, cliente.getTelefono());
            pstm.setInt(4, cliente.getId());

            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    
    //PERMITE BUSCAR O LISTAR POR CEDULA
    public ArrayList<Cliente> listadoClientes(String cedula){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Cliente> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(cedula.equalsIgnoreCase("0")){
                sql = "SELECT * FROM Clientes ORDER BY idCliente";            
            }else{
                sql = "SELECT * FROM Clientes where cedula = ? "
                    + "ORDER BY idCliente";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(cedula != "0"){
                pstm.setInt(1, Integer.parseInt(cedula));
            }
            
            rs = pstm.executeQuery();
                        
            Cliente cliente= null;
            
            while(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCedula(rs.getInt("cedula"));
                cliente.setTelefono(rs.getString("telefono"));

                listado.add(cliente);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return listado;
    }
    
}
