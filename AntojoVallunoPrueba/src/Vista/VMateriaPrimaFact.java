package Vista;

import Modelo.MateriaPrimaFacturada;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Egresados
 */
public class VMateriaPrimaFact extends javax.swing.JFrame {

    /**
     * Creates new form VMateriaPrimaFact
     */
    public VMateriaPrimaFact() {
        initComponents();
        setTitle("PRODUCTOS SALUDABLES-DETALLES COMPRA MATERIA PRIMA");        
        setLocationRelativeTo(null);
    }
    
    //METODO PARA CARGAS PROVEEDORES EN LA TABLA
    public void cargarMateriaPrimaFac(ArrayList<MateriaPrimaFacturada> listadoMateriaPrima){
        DefaultTableModel modelo;
        centrarValoresTable();
        
        modelo = (DefaultTableModel) jTableMateriaPrimaFact.getModel();        
        limpiarListadoTabla();
        for(int i= 0; i < listadoMateriaPrima.size(); i++){
              modelo.addRow(new Object[]{
              listadoMateriaPrima.get(i).getIdMateriaPrima(),
              listadoMateriaPrima.get(i).getMateriaPrima(),
              listadoMateriaPrima.get(i).getUnidadMedida(),
              listadoMateriaPrima.get(i).getIdFactura(),
              listadoMateriaPrima.get(i).getValorUnitario(), 
              listadoMateriaPrima.get(i).getCantSolicitada(), 
              listadoMateriaPrima.get(i).getTotal()});
        }
    }
    
    //METODO QUE PERMITE CENTRAR LOS VALORES DE LA TABLA
    private void centrarValoresTable(){
        
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i=0; i<jTableMateriaPrimaFact.getColumnCount();i++){
            jTableMateriaPrimaFact.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }
    }
    
    //LIMPIA LA TABLA
    public void limpiarListadoTabla(){
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) jTableMateriaPrimaFact.getModel();
        for(int i=modelo.getRowCount()-1; i>=0 ; i--){
            modelo.removeRow(i);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMateriaPrimaFact = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DETALLES DE COMPRA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(250, 250, 250))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(125, 194, 214));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 200));

        jTableMateriaPrimaFact.setBackground(new java.awt.Color(125, 194, 214));
        jTableMateriaPrimaFact.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTableMateriaPrimaFact.setForeground(null);
        jTableMateriaPrimaFact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID MATERIA P", "MATERIA PRIMA", "UNIDAD", "ID FAC", "VALOR UNIT", "CANTIDAD", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMateriaPrimaFact.setGridColor(new java.awt.Color(255, 255, 255));
        jTableMateriaPrimaFact.setMinimumSize(new java.awt.Dimension(60, 80));
        jTableMateriaPrimaFact.setPreferredSize(new java.awt.Dimension(300, 500));
        jTableMateriaPrimaFact.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTableMateriaPrimaFact.setShowGrid(true);
        jTableMateriaPrimaFact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMateriaPrimaFactMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMateriaPrimaFact);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMateriaPrimaFactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMateriaPrimaFactMouseClicked

    }//GEN-LAST:event_jTableMateriaPrimaFactMouseClicked

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
            java.util.logging.Logger.getLogger(VMateriaPrimaFact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VMateriaPrimaFact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VMateriaPrimaFact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VMateriaPrimaFact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VMateriaPrimaFact().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMateriaPrimaFact;
    // End of variables declaration//GEN-END:variables
}
