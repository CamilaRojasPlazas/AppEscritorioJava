
package Vista;


import Modelo.MateriaPrimaFacturada;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ASUS
 */
public class VCompras extends javax.swing.JFrame {


    public VCompras() {
        initComponents();
        setSize(800,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("PRODUCTOS SALUDABLES-COMPRAR");
        jPanel1.setBackground(new Color(125,194,214));
        
    }
    
    //-------------------------------------------------------------------------


    public String getjTextFieldCant() {
        return jTextFieldCant.getText().trim();
    }

    public String getjTextFieldPrecio() {
        return jTextFieldPrecio.getText().trim();
    }

    public JComboBox<String> getjComboBoxCodPro() {
        return jComboBoxCodPro;
    }

    public JComboBox<String> getjComboBoxProv() {
        return jComboBoxProv;
    }
     
    public int getFila() {
        return fila;
    }

    public void setjComboBoxProducto(JComboBox<String> jComboBoxProducto) {
        this.jComboBoxCodPro = jComboBoxProducto;
    }

    public void setjComboBoxProveedor(JComboBox<String> jComboBoxProveedor) {
        this.jComboBoxProv = jComboBoxProveedor;
    }
    
    public String getIdProveedor(){
        String[] parts= {};
        String opcion=jComboBoxProv.getSelectedItem().toString();
        parts = opcion.split("-");  
        
        return parts[0];    
    }
    
    public String getIdMateriaPrima(){
        String[] parts= {};
        String opcion=jComboBoxCodPro.getSelectedItem().toString();
        parts = opcion.split("-");  
        
        return parts[0];    
    }
    
    public String getNombreMateriaPrima(){
        String[] parts= {};
        String opcion=jComboBoxCodPro.getSelectedItem().toString();
        parts = opcion.split("-");  
        
        return parts[1];    
    }
    
    public Date getjTextFieldFecha() {
        DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.sql.Date date1 = java.sql.Date.valueOf(dtf5.format(LocalDateTime.now()));
        
        return date1;
        
    }
    
    
    
    
    
    //------------------------------SET---------------------------------------
    
    public void setFila(int fila) {
        this.fila = fila;//CONTROLA LAS FILAS SELECCIONADAS PARA SER ELIMINADAS
    }

    public void setjTextFieldFecha() {
        this.jTextFieldFecha.setText(horaActual());
    }

    public void setjTextFieldCant(String mensaje) {
        this.jTextFieldCant.setText(mensaje);
    }

    public void setjTextFieldNeto(String mensaje) {
        this.jTextFieldNeto.setText(mensaje);
    }

    public void setjTextFieldPrecio(String mensaje) {
        this.jTextFieldPrecio.setText(mensaje);
    }       
    
    //-----------------------------------------------------------------------
    // METODO PARA PONER LOS BOTONES A LA ESCUCHA    
    public void addListenerBtnAgregar(ActionListener listenMateriaP){
        jButtonAgregarArti.addActionListener(listenMateriaP);       
    }
    
    public void addListenerBtnEliminar(ActionListener listenMateriaP){
        jButtonEliminarArt.addActionListener(listenMateriaP);       
    }
    
    public void addListenerBtnCancelarCompra(ActionListener listenMateriaP){
        jButtonCancelarC.addActionListener(listenMateriaP);       
    }
    
    public void addListenerBtnRegistrarCompra(ActionListener listenMateriaP){
        jButtonComprar.addActionListener(listenMateriaP);       
    }
    
    
    //----------------------------------------------------------------------
    //METODO DE MENSAJES
    public void gestionMensajes(String mensaje, String titulo, int icono){
         JOptionPane.showMessageDialog(this,mensaje, titulo, icono);
    }
    
    //METODO PARA CARGAS PROVEEDORES EN LA TABLA
    public void cargarMateriaPrimaFac(ArrayList<MateriaPrimaFacturada> listadoMateriaPrima){
        DefaultTableModel modelo;
        centrarValoresTable();
        
        modelo = (DefaultTableModel) jTableMP.getModel();        
        limpiarListadoTabla();
        for(int i= 0; i < listadoMateriaPrima.size(); i++){
              modelo.addRow(new Object[]{
              listadoMateriaPrima.get(i).getIdMateriaPrima(),
              listadoMateriaPrima.get(i).getMateriaPrima(),
              listadoMateriaPrima.get(i).getCantSolicitada(),
              listadoMateriaPrima.get(i).getValorUnitario(), 
              listadoMateriaPrima.get(i).getTotal()});
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
        jTextFieldCant.setText("");
        jTextFieldPrecio.setText("");
        jComboBoxCodPro.setSelectedIndex(0);
    }
    
    private String horaActual(){
        DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf5.format(LocalDateTime.now());        
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
        jTableMP = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNeto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jComboBoxCodPro = new javax.swing.JComboBox<>();
        jLabelProd = new javax.swing.JLabel();
        jTextFieldCant = new javax.swing.JTextField();
        jLabelPrecio = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jButtonEliminarArt = new javax.swing.JButton();
        jButtonAgregarArti = new javax.swing.JButton();
        jLabelCantidad = new javax.swing.JLabel();
        jButtonCancelarC = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxProv = new javax.swing.JComboBox<>();
        jButtonComprar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COMPRAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(298, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(338, 338, 338))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(125, 194, 214));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 200));

        jTableMP.setBackground(new java.awt.Color(125, 194, 214));
        jTableMP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTableMP.setForeground(null);
        jTableMP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID MATERIA P", "MATERIA PRIMA", "CANTIDAD", "PRECIO UNI", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel2.setText("TOTAL A PAGAR");

        jTextFieldNeto.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jTextFieldNeto.setEnabled(false);
        jTextFieldNeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNetoActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(125, 194, 214));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanel5.setBackground(new java.awt.Color(125, 194, 214));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ARTÍCULO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 12))); // NOI18N
        jPanel5.setOpaque(false);

        jComboBoxCodPro.setBackground(new java.awt.Color(204, 204, 255));

        jLabelProd.setBackground(null);
        jLabelProd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelProd.setText("Materia P");

        jLabelPrecio.setBackground(null);
        jLabelPrecio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPrecio.setText("Precio");

        jButtonEliminarArt.setBackground(new java.awt.Color(255, 102, 102));
        jButtonEliminarArt.setText("Eliminar");

        jButtonAgregarArti.setBackground(new java.awt.Color(153, 255, 153));
        jButtonAgregarArti.setText("Agregar");

        jLabelCantidad.setBackground(null);
        jLabelCantidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCantidad.setText("Cantidad");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelPrecio)
                            .addComponent(jLabelProd)
                            .addComponent(jLabelCantidad))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxCodPro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldCant)
                            .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButtonAgregarArti, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEliminarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProd)
                    .addComponent(jComboBoxCodPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCantidad))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrecio)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEliminarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAgregarArti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButtonCancelarC.setBackground(new java.awt.Color(255, 255, 204));
        jButtonCancelarC.setText("Cancelar Compra");

        jPanel4.setBackground(new java.awt.Color(125, 194, 214));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel3.setText("FECHA");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel5.setText("PROVEEDOR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldFecha)
                    .addComponent(jComboBoxProv, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBoxProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jButtonComprar.setBackground(new java.awt.Color(204, 255, 204));
        jButtonComprar.setText("Comprar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCancelarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelarC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(52, 52, 52)
                                .addComponent(jTextFieldNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMPMouseClicked
        
        fila=jTableMP.getSelectedRow();
                            
        if(fila==-1){
            if(jTableMP.getRowCount()==0){
                JOptionPane.showMessageDialog(this,"No hay registros");
            }
            else{
                JOptionPane.showMessageDialog(this,"Seleccione una fila");
            }
        }      
    }//GEN-LAST:event_jTableMPMouseClicked

    private void jTextFieldNetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNetoActionPerformed

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
            java.util.logging.Logger.getLogger(VCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VCompras().setVisible(true);
            }
        });
    }
    
    private int fila=-1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarArti;
    private javax.swing.JButton jButtonCancelarC;
    private javax.swing.JButton jButtonComprar;
    private javax.swing.JButton jButtonEliminarArt;
    private javax.swing.JComboBox<String> jComboBoxCodPro;
    private javax.swing.JComboBox<String> jComboBoxProv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelProd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMP;
    private javax.swing.JTextField jTextFieldCant;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldNeto;
    private javax.swing.JTextField jTextFieldPrecio;
    // End of variables declaration//GEN-END:variables
}
