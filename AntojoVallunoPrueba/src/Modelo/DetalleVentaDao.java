
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
public class DetalleVentaDao {
    
    //PERMITE CREAR REGISTRO DE PRODUCTO FACTURADA
    public int RegistroProductoFacturado(DetalleVenta productoFact){
        
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO DetalleVenta values (?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, productoFact.getIdFacturaVenta());
            pstm.setInt(2, productoFact.getIdProducto());
            pstm.setString(3,  productoFact.getTipoVenta());
            pstm.setDouble(4,  productoFact.getValorUnitario());
            pstm.setInt(5, productoFact.getCantSolicitada()); 
            pstm.setDouble(6, productoFact.getTotal()); 
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
    public ArrayList<DetalleVenta> listadoMateriaPrimaFacturada(int idFactura){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<DetalleVenta> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="SELECT * FROM DetalleVenta where idFacturaVenta = ?";
                         
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idFactura);

            
            rs = pstm.executeQuery();
                        
            DetalleVenta produFact= null;
            
            while(rs.next()){
                
                produFact = new DetalleVenta();
                
                produFact.setIdFacturaVenta(rs.getInt("idFacturaVenta"));
                produFact.setIdProducto(rs.getInt("idProducto"));
                produFact.setTipoVenta(rs.getString("tipoVenta"));
                produFact.setValorUnitario(rs.getDouble("valorUnitario"));
                produFact.setCantSolicitada(rs.getInt("cantSolicitada"));
                produFact.setTotal(rs.getDouble("total"));
                
                listado.add(produFact);
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
