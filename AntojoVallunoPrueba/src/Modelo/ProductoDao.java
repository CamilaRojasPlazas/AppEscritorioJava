
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
public class ProductoDao {
    
    //PERMITE CREAR PRODUCTO
    public int crearProducto(Producto producto){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO Producto values (default,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, producto.getCodigo());
            pstm.setString(2, producto.getNombre());
            pstm.setDouble(3, producto.getValorDetal());
            pstm.setDouble(4, producto.getValorMayor());
            pstm.setInt(5, producto.getStockProducto());
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
    
    //METODO QUE PERMITE ELIMINAR PRODUCTO
    public int borrarProducto(int idProducto){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Conexion.getConeccionBD();
            String sql = "DELETE FROM Producto WHERE idProducto= ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idProducto);
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
    
    //METODO QUE PERMITE MODIFICAR PRODUCTO POR ID
    public int modificarProducto(Producto producto){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Conexion.getConeccionBD();
            String sql = "UPDATE Producto " +
                         "SET codigo=?,nombre = ?, valorDetal = ?, valorMayor=?, stock=? "
                    +    "WHERE idProducto=?";
            pstm = con.prepareStatement(sql);    
            pstm.setString(1, producto.getCodigo());
            pstm.setString(2, producto.getNombre());
            pstm.setDouble(3, producto.getValorDetal());
            pstm.setDouble(4, producto.getValorMayor());
            pstm.setInt(5, producto.getStockProducto());
            pstm.setInt(6, producto.getId());
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
    
    //PERMITE BUSCAR O LISTAR POR CODIGO
    public ArrayList<Producto> listadoProducto(String id,int opcion){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Producto> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(id.equalsIgnoreCase("0") && opcion==0){
                sql = "SELECT * FROM Producto ORDER BY idProducto";            
            }else if(opcion==1 && id != "0"){
                sql = "SELECT * FROM Producto where idProducto = ? "
                    + "ORDER BY idProducto";      
            }else if(opcion==2 && id != "0"){
                sql = "SELECT * FROM Producto where codigo = ? "
                    + "ORDER BY idProducto";  
            } 
            
            
            pstm = con.prepareStatement(sql);
            
            if(id != "0" && opcion==1){
                pstm.setInt(1, Integer.parseInt(id));
            }
            if(id != "0" && opcion==2){
                pstm.setString(1, id);
            }
            
            rs = pstm.executeQuery();
                        
            Producto produc= null;
            
            while(rs.next()){
                produc = new Producto();
                produc.setId(rs.getInt("idProducto"));
                produc.setCodigo(rs.getString("codigo"));
                produc.setNombre(rs.getString("nombre"));
                produc.setValorDetal(rs.getDouble("valorDetal"));
                produc.setValorMayor(rs.getDouble("valorMayor"));
                produc.setStockProducto(rs.getInt("stock"));
                listado.add(produc);
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
