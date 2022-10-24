
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
public class ProveedorDao {
    
    
    //PERMITE CREAR UN PROVEEDOR
    public int crearProveedor(Proveedor p){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO Proveedor values (default,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, p.getNit());
            pstm.setString(2, p.getNombre());
            pstm.setString(3,p.getTelefono());
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
    
    //METODO QUE PERMITE ELIMINAR PROVEEDOR
    public int borrarProveedor(String nit){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Conexion.getConeccionBD();
            String sql = "DELETE FROM Proveedor WHERE nit = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(nit));
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
    
    //METODO QUE PERMITE MODIFICAR POR NIT
    public int modificarProveedor(Proveedor prov){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Conexion.getConeccionBD();
            String sql = "UPDATE Proveedor " +
                         "SET nombre = ?, nit=? ,telefono = ? "
                    +    "WHERE idProveedor=?";
            pstm = con.prepareStatement(sql);            
            pstm.setString(1, prov.getNombre());
            pstm.setInt(2, prov.getNit());
            pstm.setString(3, prov.getTelefono());
            pstm.setInt(4, prov.getId());
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
    
    //PERMITE BUSCAR O LISTAR POR NIT
    public ArrayList<Proveedor> listadoProveedores(String nit){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Proveedor> listado = new ArrayList<>();
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(nit.equalsIgnoreCase("0")){
                sql = "SELECT * FROM Proveedor ORDER BY idProveedor";            
            }else{
                sql = "SELECT * FROM Proveedor where nit = ? "
                    + "ORDER BY idProveedor";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(nit != "0"){
                pstm.setInt(1, Integer.parseInt(nit));
            }
            
            rs = pstm.executeQuery();
                        
            Proveedor proveedor= null;
            
            while(rs.next()){
                proveedor = new Proveedor();
                proveedor.setId(rs.getInt("idProveedor"));
                proveedor.setNit(rs.getInt("nit"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));       
                listado.add(proveedor);
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
    
    //PERMITE BUSCAR O LISTAR POR NIT
    public ArrayList<Proveedor> listadoProveedoresID(int id){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Proveedor> listado = new ArrayList<>();
        try{
            con=Conexion.getConeccionBD();
            String sql="SELECT * FROM Proveedor where idProveedor = ? ";
                       
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, id);

            rs = pstm.executeQuery();
                        
            Proveedor proveedor= null;
            
            while(rs.next()){
                proveedor = new Proveedor();
                proveedor.setId(rs.getInt("idProveedor"));
                proveedor.setNit(rs.getInt("nit"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));       
                listado.add(proveedor);
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
