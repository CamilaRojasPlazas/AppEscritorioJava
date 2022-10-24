
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
public class ProduccionDao {
    
    //PERMITE CREAR PRODUCCIÓN
    public int RegistrarProduccion(Produccion produccion){
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO Produccion values (default,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, produccion.getIdProducto());
            pstm.setInt(2, produccion.getCodigo());
            pstm.setInt(3, produccion.getCantidad());
            pstm.setInt(4, produccion.getIdLote());
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
    
    //PERMITE BUSCAR O LISTAR POR CODIGO
    public ArrayList<Produccion> listadoProduccion(String codigo){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Produccion> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(codigo.equalsIgnoreCase("0")){
                sql = "SELECT * FROM Produccion ORDER BY idProduccion";            
            }else{
                sql = "SELECT * FROM Produccion where codigo = ? "
                    + "ORDER BY idProduccion";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(codigo != "0"){
                pstm.setInt(1, Integer.parseInt(codigo));
            }
            
            rs = pstm.executeQuery();
                        
            Produccion produc= null;
            
            while(rs.next()){
                produc = new Produccion();
                produc.setId(rs.getInt("idProduccion"));
                produc.setIdProducto(rs.getInt("idProducto"));
                produc.setCodigo(rs.getInt("codigo"));
                produc.setCantidad(rs.getInt("cantidadProducto"));
                produc.setIdLote(rs.getInt("idLote"));
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
