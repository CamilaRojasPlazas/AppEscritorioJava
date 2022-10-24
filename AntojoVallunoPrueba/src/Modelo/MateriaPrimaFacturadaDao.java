
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
 * @author Egresados
 */
public class MateriaPrimaFacturadaDao {
    
    //PERMITE CREAR UNA FACTURA
    public int crearRegistroArticulo(MateriaPrimaFacturada MateriaFac){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        
        try{
            con=Conexion.getConeccionBD();
            String sql = "INSERT INTO MateriaPrimaFacturada values (?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, MateriaFac.getIdMateriaPrima());
            pstm.setInt(2, MateriaFac.getIdFactura());
            pstm.setDouble(3, MateriaFac.getValorUnitario());
            pstm.setInt(4, MateriaFac.getCantSolicitada()); 
            pstm.setDouble(5, MateriaFac.getTotal()); 
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
    public ArrayList<MateriaPrimaFacturada> listadoMateriaPrimaFacturada(int id){
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<MateriaPrimaFacturada> listado = new ArrayList<>();
        
        try{
            con=Conexion.getConeccionBD();
            String sql="SELECT * FROM MateriaPrimaFacturada where idFactura = ?";
                         
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, id);

            
            rs = pstm.executeQuery();
                        
            MateriaPrimaFacturada mpf= null;
            
            while(rs.next()){
                mpf = new MateriaPrimaFacturada();
                mpf.setIdMateriaPrima(rs.getInt("idMateriaPrima"));
                mpf.setIdFactura(rs.getInt("idFactura"));
                mpf.setValorUnitario(rs.getDouble("valorUnitario"));
                mpf.setCantSolicitada(rs.getInt("cantidad"));
                mpf.setTotal(rs.getDouble("total"));
                listado.add(mpf);
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
