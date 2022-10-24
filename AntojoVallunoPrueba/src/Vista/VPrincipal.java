
package Vista;

import Controlador.ControladorClientes;
import Controlador.ControladorComprar;
import Controlador.ControladorFacturasC;
import Controlador.ControladorFacturasVentas;
import Controlador.ControladorMateriaPrima;
import Controlador.ControladorProduccion;
import Controlador.ControladorProducto;
import Controlador.ControladorProveedor;
import Controlador.ControladorVendedor;
import Controlador.ControladorVentas;
import Modelo.ClienteDao;
import Modelo.FacturaDao;
import Modelo.FacturaVentaDao;
import Modelo.MateriaPrimaDao;
import Modelo.MateriaPrimaFacturadaDao;
import Modelo.ProduccionDao;
import Modelo.ProductoDao;
import Modelo.ProveedorDao;
import Modelo.VendedorDao;
import java.awt.Color;

/**
 *
 * @author ASUS
 */
public class VPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VPrincipal
     */
    public VPrincipal() {
        initComponents();
        setSize(800,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("PRODUCTOS SALUDABLES");
        jPanel1.setBackground(new Color(125,194,214));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonMateriaP = new javax.swing.JButton();
        jButtonProveedor = new javax.swing.JButton();
        jButtonCompras = new javax.swing.JButton();
        jButtonHistorialCompras = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jButtonProducir = new javax.swing.JButton();
        jButtonProductos = new javax.swing.JButton();
        jButtonVendedores = new javax.swing.JButton();
        jButtonVender = new javax.swing.JButton();
        jButtonFacturasVentas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(0, 128, 131));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GESTIÓN EMPRESA PRODUCTOS SALUDABLES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(149, 149, 149))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
        );

        jButtonMateriaP.setBackground(new java.awt.Color(204, 255, 204));
        jButtonMateriaP.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonMateriaP.setText("MATERIA PRIMA");
        jButtonMateriaP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonMateriaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMateriaPActionPerformed(evt);
            }
        });

        jButtonProveedor.setBackground(new java.awt.Color(204, 255, 204));
        jButtonProveedor.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonProveedor.setText("PROVEEDORES");
        jButtonProveedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonProveedor.setFocusCycleRoot(true);
        jButtonProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProveedorActionPerformed(evt);
            }
        });

        jButtonCompras.setBackground(new java.awt.Color(204, 255, 204));
        jButtonCompras.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonCompras.setText("COMPRAR");
        jButtonCompras.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComprasActionPerformed(evt);
            }
        });

        jButtonHistorialCompras.setBackground(new java.awt.Color(204, 255, 204));
        jButtonHistorialCompras.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonHistorialCompras.setText("HISTORIAL C");
        jButtonHistorialCompras.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonHistorialCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorialComprasActionPerformed(evt);
            }
        });

        jButtonClientes.setBackground(new java.awt.Color(153, 153, 255));
        jButtonClientes.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonClientes.setText("CLIENTES");
        jButtonClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });

        jButtonProducir.setBackground(new java.awt.Color(255, 204, 153));
        jButtonProducir.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonProducir.setText("PRODUCIR");
        jButtonProducir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonProducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProducirActionPerformed(evt);
            }
        });

        jButtonProductos.setBackground(new java.awt.Color(255, 204, 153));
        jButtonProductos.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonProductos.setText("PRODUCTOS");
        jButtonProductos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductosActionPerformed(evt);
            }
        });

        jButtonVendedores.setBackground(new java.awt.Color(153, 153, 255));
        jButtonVendedores.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonVendedores.setText("VENDEDORES");
        jButtonVendedores.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVendedoresActionPerformed(evt);
            }
        });

        jButtonVender.setBackground(new java.awt.Color(153, 153, 255));
        jButtonVender.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButtonVender.setText("VENDER");
        jButtonVender.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVenderActionPerformed(evt);
            }
        });

        jButtonFacturasVentas.setBackground(new java.awt.Color(153, 153, 255));
        jButtonFacturasVentas.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButtonFacturasVentas.setText("HISTORIAL V");
        jButtonFacturasVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFacturasVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVender, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonMateriaP, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jButtonProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonProducir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonHistorialCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFacturasVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonHistorialCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonMateriaP, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonProducir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonVender, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonFacturasVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 140, Short.MAX_VALUE))
        );

        jButtonMateriaP.getAccessibleContext().setAccessibleName("Materia Prima");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMateriaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMateriaPActionPerformed
       VMateriaPrima view = new VMateriaPrima();
       MateriaPrimaDao model = new MateriaPrimaDao();
       
       ControladorMateriaPrima programaController = new ControladorMateriaPrima (view,model);
       
       view.setVisible(true);
    }//GEN-LAST:event_jButtonMateriaPActionPerformed

    private void jButtonProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProveedorActionPerformed
       
        VProveedores view = new VProveedores();
        ProveedorDao model = new ProveedorDao();
        
        ControladorProveedor programaController = new ControladorProveedor(view,model);

        view.setVisible(true);
    }//GEN-LAST:event_jButtonProveedorActionPerformed

    private void jButtonComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComprasActionPerformed

        VCompras view = new VCompras();
        FacturaDao modelFactura=new FacturaDao();
        MateriaPrimaFacturadaDao modelMateriaPrimaFac=new MateriaPrimaFacturadaDao();
        
        ControladorComprar programaController = new ControladorComprar(view,modelFactura,modelMateriaPrimaFac);
        
        view.setVisible(true);
    }//GEN-LAST:event_jButtonComprasActionPerformed

    private void jButtonHistorialComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorialComprasActionPerformed
         VHistorialCompras view = new  VHistorialCompras();
         FacturaDao modelFactura=new FacturaDao();
         
         ControladorFacturasC programaFacturas=new ControladorFacturasC(view,modelFactura);
         
         view.setVisible(true);
         
    }//GEN-LAST:event_jButtonHistorialComprasActionPerformed

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        
        VClientes view = new  VClientes();
        ClienteDao modelFactura=new ClienteDao();
        
        ControladorClientes programaFacturas=new ControladorClientes(view,modelFactura);
        
        view.setVisible(true);
        
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jButtonProducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProducirActionPerformed
        VProduccion view=new VProduccion();
        ProduccionDao  modeloProduccion=new ProduccionDao();
        
        ControladorProduccion programaProduccion=new ControladorProduccion(view,modeloProduccion);
        view.setVisible(true); 
        
    }//GEN-LAST:event_jButtonProducirActionPerformed

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed
        VProductos view=new VProductos();
        ProductoDao modeloProducto=new ProductoDao();
        
        ControladorProducto programaProductos=new ControladorProducto(view,modeloProducto);
        
        view.setVisible(true);  
    }//GEN-LAST:event_jButtonProductosActionPerformed

    private void jButtonVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVendedoresActionPerformed
        VVendedores view=new VVendedores();
        VendedorDao modeloProducto=new VendedorDao();
        
        ControladorVendedor programaProductos=new ControladorVendedor(view,modeloProducto);
        
        view.setVisible(true); 
    }//GEN-LAST:event_jButtonVendedoresActionPerformed

    private void jButtonVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVenderActionPerformed
        VVentas view=new VVentas();
        
        ControladorVentas programaVentas=new ControladorVentas(view);
        
        view.setVisible(true); 
        
    }//GEN-LAST:event_jButtonVenderActionPerformed

    private void jButtonFacturasVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFacturasVentasActionPerformed
        
        VHistorialVentas view = new  VHistorialVentas();
        FacturaVentaDao modelFactura=new FacturaVentaDao();
         
        ControladorFacturasVentas programaFacturas=new  ControladorFacturasVentas(view,modelFactura);
         
        view.setVisible(true);
    }//GEN-LAST:event_jButtonFacturasVentasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonCompras;
    private javax.swing.JButton jButtonFacturasVentas;
    private javax.swing.JButton jButtonHistorialCompras;
    private javax.swing.JButton jButtonMateriaP;
    private javax.swing.JButton jButtonProducir;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonProveedor;
    private javax.swing.JButton jButtonVendedores;
    private javax.swing.JButton jButtonVender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}