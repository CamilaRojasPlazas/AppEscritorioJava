
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
public class FacturaDao {
    
    //PERMITE CREAR UNA FACTURA
    public int crearFactura(Factura factura){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO FacturaCompra values (default,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setDate(1, (Date) factura.getFechaFactura());
            pstm.setInt(2, factura.getProveedor());
            pstm.setDouble(3, factura.getNetoFactura());
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
    public ArrayList<Factura> listadoFactura(int codigo,int metodoBusca){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Factura> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(metodoBusca==0){
                sql = "SELECT * FROM FacturaCompra ORDER BY idFactura";            
            }if(metodoBusca==1){
                sql = "SELECT * FROM FacturaCompra where idFactura = ? "
                    + "ORDER BY idFactura";      
            }if(metodoBusca==2){
                sql = "SELECT * FROM FacturaCompra where idProveedor = ? "
                    + "ORDER BY idFactura"; 
            }                        
            pstm = con.prepareStatement(sql);
            
            if(metodoBusca != 0){
                pstm.setInt(1, codigo);
            }
            
            rs = pstm.executeQuery();
                        
            Factura fac= null;
            
            while(rs.next()){
                fac = new Factura();
                fac.setId(rs.getInt("idFactura"));
                fac.setFechaFactura(rs.getDate("fecha"));
                fac.setProveedor(rs.getInt("idProveedor"));
                fac.setNetoFactura(rs.getDouble("netoFactura"));
                listado.add(fac);
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
