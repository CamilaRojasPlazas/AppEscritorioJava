
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
public class VendedorDao {
    
    //PERMITE CREAR VENDEDOR
    public int crearVendedor(Vendedor vendedor){
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO vendedor values (default,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1, vendedor.getCedula());
            pstm.setString(2, vendedor.getNombre());
            pstm.setString(3, vendedor.getCorreo());
            pstm.setString(4, vendedor.getTelefono());
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
    
    //METODO QUE PERMITE ELIMINAR VENDEDOR
    public int borrarVendedor(int id){  
        
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con = Conexion.getConeccionBD();
            String sql = "DELETE FROM vendedor WHERE idVendedor= ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
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
    
    //METODO QUE PERMITE MODIFICAR VENDEDOR
    public int modificarVendedor(Vendedor vendedor){ 
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con = Conexion.getConeccionBD();
            String sql = "UPDATE Vendedor " +
                         "SET cedula=?,nombre=?,correo=?,telefono= ?"
                    +    "WHERE idVendedor=?";
            
            pstm = con.prepareStatement(sql);    
            pstm.setInt(1, vendedor.getCedula());
            pstm.setString(2, vendedor.getNombre());
            pstm.setString(3, vendedor.getCorreo());
            pstm.setString(4, vendedor.getTelefono());
            pstm.setInt(5, vendedor.getId());


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
    public ArrayList<Vendedor> listadoVendedores(String cedula){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Vendedor> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(cedula.equalsIgnoreCase("0")){
                sql = "SELECT * FROM vendedor ORDER BY idVendedor";            
            }else{
                sql = "SELECT * FROM vendedor where cedula = ? "
                    + "ORDER BY idVendedor";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(cedula != "0"){
                pstm.setInt(1, Integer.parseInt(cedula));
            }
            
            rs = pstm.executeQuery();
                        
            Vendedor vendedor= null;
            
            while(rs.next()){
                vendedor = new Vendedor();
                vendedor.setId(rs.getInt("idVendedor"));
                vendedor.setCedula(rs.getInt("cedula"));
                vendedor.setNombre(rs.getString("nombre"));
                vendedor.setCorreo(rs.getString("correo"));
                vendedor.setTelefono(rs.getString("telefono"));

                listado.add(vendedor);
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
