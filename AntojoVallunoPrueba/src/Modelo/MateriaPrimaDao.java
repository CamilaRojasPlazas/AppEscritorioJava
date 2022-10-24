
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
public class MateriaPrimaDao {
    
    //PERMITE CREAR MATERIA PRIMA
    public int crearMateriaPrima(MateriaPrima mp){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO MateriaPrima values (default,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, mp.getCodigo());
            pstm.setString(2, mp.getNombre());
            pstm.setString(3, mp.getUnidadMedida());
            pstm.setDouble(4, mp.getStock());
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
    
    //METODO QUE PERMITE ELIMINAR MATERIA PRIMA
    public int borrarMateriaPrima(String id){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Conexion.getConeccionBD();
            String sql = "DELETE FROM MateriaPrima WHERE idMateriaPrima= ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(id));
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
    public int modificarMateriaPrima(MateriaPrima mp){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Conexion.getConeccionBD();
            String sql = "UPDATE MateriaPrima " +
                         "SET codigo = ?, nombre = ?, unidadMedida=?, stock=? "
                    +    "WHERE idMateriaPrima=?";
            pstm = con.prepareStatement(sql);            
            pstm.setInt(1, mp.getCodigo());
            pstm.setString(2, mp.getNombre());
            pstm.setString(3, mp.getUnidadMedida());
            pstm.setDouble(4, mp.getStock());
            pstm.setInt(5, mp.getId());
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
    public ArrayList<MateriaPrima> listadoMateriaPrima(String codigo){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<MateriaPrima> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(codigo.equalsIgnoreCase("0")){
                sql = "SELECT * FROM MateriaPrima ORDER BY idMateriaPrima";            
            }else{
                sql = "SELECT * FROM MateriaPrima where codigo = ? "
                    + "ORDER BY idMateriaPrima";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(codigo != "0"){
                pstm.setInt(1, Integer.parseInt(codigo));
            }
            
            rs = pstm.executeQuery();
                        
            MateriaPrima mp= null;
            
            while(rs.next()){
                mp = new MateriaPrima();
                mp.setId(rs.getInt("idMateriaPrima"));
                mp.setCodigo(rs.getInt("codigo"));
                mp.setNombre(rs.getString("nombre"));
                mp.setUnidadMedida(rs.getString("unidadMedida"));
                mp.setStock(rs.getDouble("stock"));
                listado.add(mp);
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
    
    //PERMITE BUSCAR O LISTAR POR CODIGO
    public ArrayList<MateriaPrima> listadoMateriaID(int id){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<MateriaPrima> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="SELECT * FROM MateriaPrima where idMateriaPrima = ? ";
                        
            pstm = con.prepareStatement(sql);            
           
            pstm.setInt(1, id);
          
            
            rs = pstm.executeQuery();
                        
            MateriaPrima mp= null;
            
            while(rs.next()){
                mp = new MateriaPrima();
                mp.setId(rs.getInt("idMateriaPrima"));
                mp.setCodigo(rs.getInt("codigo"));
                mp.setNombre(rs.getString("nombre"));
                mp.setUnidadMedida(rs.getString("unidadMedida"));
                mp.setStock(rs.getDouble("stock"));
                listado.add(mp);
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
