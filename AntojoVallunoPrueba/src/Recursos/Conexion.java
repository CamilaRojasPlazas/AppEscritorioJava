
package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Conexion {
    
    private static Connection con = null;
    
    public static Connection getConeccionBD(){
        
        try{            
           
            if(con == null){
                
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
                
                ResourceBundle rb = ResourceBundle.getBundle("Recursos.jdbc");
                String driver = rb.getString("driver");
                String url = rb.getString("url");
                String pwd = rb.getString("pwd");
                String usr = rb.getString("usr");        
                
                Class.forName(driver);
                
                con = DriverManager.getConnection(url, usr, pwd);
            }
     
                    
        }catch(ClassNotFoundException | SQLException ex){  
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());  
        }
        
        return con;           
    }
    
    static class MiShDwnHook extends Thread{
        //Justo antes de finaliza el programa la JVM invocará
        //este método donde podemos cerra la conexión
        @Override
        public void run(){
            try{
                Connection con = Conexion.getConeccionBD();
                con.close();                     
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Error : " + 
                        ex.getMessage());
            }
        }
    }
    
    
}
