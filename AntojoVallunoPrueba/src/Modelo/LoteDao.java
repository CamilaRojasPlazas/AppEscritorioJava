
package Modelo;

import Recursos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class LoteDao {
    
    //PERMITE CREAR UNA FACTURA
    public int crearLote(Lote lote){
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO Lote values (default,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setDate(1, (Date) lote.getFechaLote());
            pstm.setString(2, lote.getSeriado());
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
    public ArrayList<Lote> listadoLote(String codigo){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Lote> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(codigo.equalsIgnoreCase("0")){
                sql = "SELECT * FROM Lote ORDER BY idLote";            
            }else{
                sql = "SELECT * FROM MateriaPrima where seriado = ? "
                    + "ORDER BY idLote";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(codigo != "0"){
                pstm.setInt(1, Integer.parseInt(codigo));
            }
            
            rs = pstm.executeQuery();
                        
            Lote lote= null;
            
            while(rs.next()){
                lote = new Lote();
                lote.setId(rs.getInt("idLote"));
                lote.setFechaLote(rs.getDate("fecha"));
                lote.setSeriado(rs.getString("seriado"));
                listado.add(lote);
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
