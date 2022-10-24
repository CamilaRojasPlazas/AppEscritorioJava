
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
public class RecetaDao {
    
    //PERMITE CREAR UN REGISTRO EN LA RECETA
    public int crearRegistroReceta(Receta receta){
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO Receta values (?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, receta.getIdProducto());
            pstm.setInt(2, receta.getIdMateriaPrima());
            pstm.setDouble(3, receta.getCantidad());
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
    
    //PERMITE BUSCAR O LISTAR POR ID
    public ArrayList<Receta> listadoReceta(int codigo){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Receta> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(codigo==0){
                sql = "SELECT * FROM Receta ORDER BY idProducto";            
            }else{
                sql = "SELECT * FROM Receta where idProducto = ? "
                    + "ORDER BY idProducto";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(codigo != 0){
                pstm.setInt(1, codigo);
            }
            
            rs = pstm.executeQuery();
                        
            Receta receta= null;
            
            while(rs.next()){
                receta = new Receta();
                receta.setIdProducto(rs.getInt("idProducto"));
                receta.setIdMateriaPrima(rs.getInt("idMateriaPrima"));
                receta.setCantidad(rs.getDouble("cantidadMateriaPrima"));
                listado.add(receta);
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
    
    //PERMITE BORRAR UNA RECETA COMPLETA 
    public int borrarReceta(int idProducto){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con = Conexion.getConeccionBD();
            String sql = "DELETE FROM Receta WHERE idProducto= ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idProducto);
            rtdo = pstm.executeUpdate(); 
            System.out.println(rtdo);
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
    
}
