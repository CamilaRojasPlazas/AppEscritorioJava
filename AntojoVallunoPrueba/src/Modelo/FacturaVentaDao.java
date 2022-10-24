
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
 * @author Egresados
 */
public class FacturaVentaDao {
    
    //PERMITE CREAR UNA FACTURA
    public int crearFactura(FacturaVenta factura){
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO FacturaVenta values (default,?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setDate(1, (Date) factura.getFechaFactura());
            pstm.setInt(2, factura.getIdVendedor());
            pstm.setDouble(3, factura.getIdCliente());
            pstm.setString(4, factura.getLugar());
            pstm.setString(5, factura.getCoordX());
            pstm.setString(6, factura.getCoordY());
            pstm.setDouble(7, factura.getNetoFactura());
            
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
    
    
   public ArrayList<FacturaVenta> listadoFactura(int id){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<FacturaVenta> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="";
            if(id==0){
                sql = "SELECT * FROM FacturaVenta ORDER BY idFactura";            
            }if(id!=0){
                sql = "SELECT * FROM FacturaVenta where idFactura = ? "
                    + "ORDER BY idFactura";      
            }                       
            pstm = con.prepareStatement(sql);
            
            if(id!=0){
                pstm.setInt(1, id);
            }
            
            rs = pstm.executeQuery();
                        
            FacturaVenta fac= null;
            
            while(rs.next()){
                fac = new FacturaVenta();
                
                fac.setId(rs.getInt("idFactura"));
                fac.setFechaFactura(rs.getDate("fecha"));
                fac.setIdVendedor(rs.getInt("idVendedor"));
                fac.setIdCliente(rs.getInt("idCliente"));
                fac.setLugar(rs.getString("lugar"));
                fac.setCoordX(rs.getString("coordenadaX"));
                fac.setCoordY(rs.getString("coordenadaY"));
                fac.setNetoFactura(rs.getDouble("neto"));
                
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
