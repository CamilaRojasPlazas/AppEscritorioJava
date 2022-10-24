
package Vista;

import Modelo.Receta;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class VRecetas extends javax.swing.JFrame {

    /**
     * Creates new form VRecetas
     */
    public VRecetas() {
        initComponents();
        setSize(800,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("PRODUCTOS SALUDABLES-RECETAS");
        jPanel1.setBackground(new Color(125,194,214));
    }
    
    //-------------------------------------------------------------------------


    public String getjTextFieldCant() {
        return jTextFieldCantidad.getText().trim();
    }
    
    public String getProductoId(){
        String[] partsProducto= {};
        String materiaPrima=jComboBoxProducto.getSelectedItem().toString();
        partsProducto = materiaPrima.split("-");          
        return partsProducto[0];    
    }
    
    public String getProductoNombre(){
        String[] partsProducto= {};
        String materiaPrima=jComboBoxProducto.getSelectedItem().toString();
        partsProducto = materiaPrima.split("-");        
        return partsProducto[1];    
    }
    

    public String getMateriaPrimaId(){
        String[] partsMateriaP= {};
        String materiaPrima=jComboBoxMateriaP.getSelectedItem().toString();
        partsMateriaP = materiaPrima.split("-");         
        return partsMateriaP[0];    
    }
    
    public String getMateriaPrimaNombre(){
        String[] partsMateriaP= {};
        String materiaPrima=jComboBoxMateriaP.getSelectedItem().toString();
        partsMateriaP = materiaPrima.split("-");         
        return partsMateriaP[1];    
    }
 
    
    public JComboBox<String> getjComboBoxMateriaP() {
        return jComboBoxMateriaP;
    }
    

    public JComboBox<String> getjComboBoxProducto() {
        return jComboBoxProducto;
    }
    
    public int getFila() {
        return fila;
    }
    
    public void setjComboBoxMateriaP(JComboBox<String> jComboBoxMateriaP) {
        this.jComboBoxMateriaP = jComboBoxMateriaP;
    }


    public void setjComboBoxProducto(JComboBox<String> jComboBoxProducto) {
        this.jComboBoxProducto = jComboBoxProducto;
    }
    
    public void setFila(int valor) {
        fila=valor;
    }

    public void setjTextFieldCantidad(String frase) {
        jTextFieldCantidad.setText(frase);
    }
    
    
    
    //-----------------------------------------------------------------------
    
// METODO PARA PONER LOS BOTONES A LA ESCUCHA    
    public void addListenerBtnAgregar(ActionListener listenMateriaP){
        jButtonAgregarArti.addActionListener(listenMateriaP);       
    }
    
    public void addListenerEliminar(ActionListener listenMateriaP){
        jButtonEliminarArt.addActionListener(listenMateriaP);       
    }
    
    public void addListenerAgregarReceta(ActionListener listenMateriaP){
        jButtonAgregarReceta.addActionListener(listenMateriaP);       
    }
    
    public void addListenerBuscarProducto(ActionListener listenMateriaP){
        jButtoBuscarXProduc.addActionListener(listenMateriaP);       
    }
    
    public void addListenerEliminarReceta(ActionListener listenMateriaP){
        jButtonEliminarReceta.addActionListener(listenMateriaP);       
    }
    
    //----------------------------------------------------------------------
    //METODO DE MENSAJES
    public void gestionMensajes(String mensaje, String titulo, int icono){
         JOptionPane.showMessageDialog(this,mensaje, titulo, icono);
    }
    
    //METODO PARA CARGAS PROVEEDORES EN LA TABLA
    public void cargarReceta(ArrayList<Receta> listadoReceta){
        
        DefaultTableModel modelo;
        centrarValoresTable();
        
        modelo = (DefaultTableModel) jTableMP.getModel();        
        limpiarListadoTabla();
        for(int i= 0; i < listadoReceta.size(); i++){
              modelo.addRow(new Object[]{
              listadoReceta.get(i).getProducto(),
              listadoReceta.get(i).getMateriaPrima(),
              listadoReceta.get(i).getCantidad()
              });
        }
    }
    
    //METODO QUE PERMITE CENTRAR LOS VALORES DE LA TABLA
    private void centrarValoresTable(){
        
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i=0; i<jTableMP.getColumnCount();i++){
            jTableMP.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }
    }
    
    //LIMPIA LA TABLA
    public void limpiarListadoTabla(){
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) jTableMP.getModel();
        for(int i=modelo.getRowCount()-1; i>=0 ; i--){
            modelo.removeRow(i);
        }
    }
    
    //METODO QUE PONE LOS JTEXTFIELD EN BLANCO
    public void limpiarTextFieldArticulo(){
        jTextFieldCantidad.setText("");
    }
    
    //METODO QUE DESABILITA BOTONES
    public void desabilitarBotones(){
        jButtonEliminarArt.setEnabled(false);
        jButtonAgregarArti.setEnabled(false);
        jButtonAgregarReceta.setEnabled(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanelProducto = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jButtonAgregarArti = new javax.swing.JButton();
        jButtonEliminarArt = new javax.swing.JButton();
        jComboBoxProducto = new javax.swing.JComboBox<>();
        jComboBoxMateriaP = new javax.swing.JComboBox<>();
        jButtoBuscarXProduc = new javax.swing.JButton();
        jButtonAgregarReceta = new javax.swing.JButton();
        jButtonEliminarReceta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RECETA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(125, 194, 214));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanelProducto.setBackground(new java.awt.Color(204, 204, 204));
        jPanelProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materia Prima Requerida", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 2, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanelProducto.setForeground(new java.awt.Color(255, 255, 255));
        jPanelProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelProducto.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setText("Producto");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setText("Materia Prima");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel4.setText("Cantidad");

        jButtonAgregarArti.setBackground(new java.awt.Color(153, 255, 153));
        jButtonAgregarArti.setText("Agregar");

        jButtonEliminarArt.setBackground(new java.awt.Color(255, 102, 102));
        jButtonEliminarArt.setText("Eliminar");

        jComboBoxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxMateriaP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtoBuscarXProduc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jButtoBuscarXProduc.setText("BuscarP");

        javax.swing.GroupLayout jPanelProductoLayout = new javax.swing.GroupLayout(jPanelProducto);
        jPanelProducto.setLayout(jPanelProductoLayout);
        jPanelProductoLayout.setHorizontalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxProducto, 0, 170, Short.MAX_VALUE)
                    .addComponent(jComboBoxMateriaP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtoBuscarXProduc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAgregarArti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminarArt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelProductoLayout.setVerticalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductoLayout.createSequentialGroup()
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButtonAgregarArti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtoBuscarXProduc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxMateriaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButtonEliminarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jButtonAgregarReceta.setBackground(new java.awt.Color(204, 255, 204));
        jButtonAgregarReceta.setText("AGREGAR RECETA");
        jButtonAgregarReceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarRecetaActionPerformed(evt);
            }
        });

        jButtonEliminarReceta.setBackground(new java.awt.Color(255, 153, 153));
        jButtonEliminarReceta.setText("ELIMINAR RECETA");
        jButtonEliminarReceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarRecetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAgregarReceta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarReceta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButtonAgregarReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEliminarReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(125, 194, 214));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 200));

        jTableMP.setBackground(new java.awt.Color(125, 194, 214));
        jTableMP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTableMP.setForeground(null);
        jTableMP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "PRODUCTO", "MATERIA PRIMA", "CANTIDAD"
            }
        ));
        jTableMP.setGridColor(new java.awt.Color(255, 255, 255));
        jTableMP.setMinimumSize(new java.awt.Dimension(60, 80));
        jTableMP.setPreferredSize(new java.awt.Dimension(300, 400));
        jTableMP.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTableMP.setShowGrid(true);
        jTableMP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 142, Short.MAX_VALUE))
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

    private void jTableMPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMPMouseClicked
        
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) jTableMP.getModel();
        
        fila=jTableMP.getSelectedRow();
       
                            
        if(fila==-1){
            if(jTableMP.getRowCount()==0){
                JOptionPane.showMessageDialog(this,"No hay registros");
            }
            else{
                JOptionPane.showMessageDialog(this,"Seleccione una fila");
            }
            
        }else{
            
            String producSelect=modelo.getValueAt(jTableMP.getSelectedRow(), 0).toString();
            
            for (int i=0;i<jComboBoxProducto.getComponentCount();i++){
                
                String[] split = jComboBoxProducto.getItemAt(i).split("-");
                String palabra=split[1].strip();

                if(producSelect.equals(palabra)){
                    jComboBoxProducto.setSelectedIndex(i);
                }        
            }
            
            
            String MateriaPrimaSelect=modelo.getValueAt(jTableMP.getSelectedRow(), 1).toString();
            
            for (int i=0;i<jComboBoxMateriaP.getComponentCount();i++){
                
                String[] split2 = jComboBoxMateriaP.getItemAt(i).split("-");
                String palabra2=split2[1].trim();

                if(MateriaPrimaSelect.equals(palabra2)){
                    jComboBoxMateriaP.setSelectedIndex(i);
                }        
            }     
        }        
    }//GEN-LAST:event_jTableMPMouseClicked

    private void jButtonAgregarRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarRecetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAgregarRecetaActionPerformed

    private void jButtonEliminarRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarRecetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEliminarRecetaActionPerformed

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
            java.util.logging.Logger.getLogger(VRecetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VRecetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VRecetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VRecetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VRecetas().setVisible(true);
            }
        });
    }
    
    private int fila=-1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtoBuscarXProduc;
    private javax.swing.JButton jButtonAgregarArti;
    private javax.swing.JButton jButtonAgregarReceta;
    private javax.swing.JButton jButtonEliminarArt;
    private javax.swing.JButton jButtonEliminarReceta;
    private javax.swing.JComboBox<String> jComboBoxMateriaP;
    private javax.swing.JComboBox<String> jComboBoxProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMP;
    private javax.swing.JTextField jTextFieldCantidad;
    // End of variables declaration//GEN-END:variables
}
