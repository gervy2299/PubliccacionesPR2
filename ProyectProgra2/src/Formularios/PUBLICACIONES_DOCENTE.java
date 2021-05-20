/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Login.LOGIN;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyectprogra2.Conexion;

/**
 *
 * @author HP
 */
public class PUBLICACIONES_DOCENTE extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Conexion co = new Conexion();
    Connection con = co.conexiondb();
    public static String usu5;

    /**
     * Creates new form JF5
     */
    
    public PUBLICACIONES_DOCENTE() {
        initComponents();
        datosGen();
        cabecera();
        llenarPubs();
        comboEstado();
        comboTipPub();
        this.setLocationRelativeTo(null);
    }

    public void datosGen() {
        String[] registros = new String[4];
        String docente = "";
        String sql = "CALL p_usuario('" + usu5 + "');";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("d.nombres");
                registros[1] = rs.getString("d.apellido_pat");
                registros[2] = rs.getString("d.apellido_mat");
                registros[3] = rs.getString("r.denominacion");
            }
            docente = registros[1] + " " + registros[2] + " " + registros[0];
            lblNom.setText(" " + docente.toUpperCase());
            lblTipUsu.setText(" " + registros[3]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos" + e.getMessage(), "Mensaje", 0);
        }
    }

    public void cabecera() {
        String[] tit = {"Título", "Fecha", "Tipo Publicacion", "Escuela", "Estado", "reporte"};
        model.setColumnIdentifiers(tit);
        tbPubs.setModel(model);
    }

    public void limpiarTabla(JTable tb, DefaultTableModel md) {
        while (tb.getRowCount() > 0) {
            md.removeRow(0);
        }
    }

    public void llenarPubs() {
        try {
            limpiarTabla(tbPubs, model);
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "CALL p_pubs('" + usu5 + "');";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();
            int[] anchos = {100, 50, 80, 80, 80, 80};
            for (int i = 0; i < tbPubs.getColumnCount(); i++) {
                tbPubs.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas + 1];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                filas[cantidadColumnas] = "FALTA";
                model.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error Al llenar: \n" + e);
        }
    }

    public void comboEstado() {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM estado_publicacion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cmbEst.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar datos: \n" + e);
        }
    }

    public void comboTipPub() {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM tipo_publicacion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cmbTipBus.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar datos: \n" + e);
        }
    }

    public void buscarAutor(String campo) {
        String where = "";
        if (!txtNom.equals(campo)) {
            where = "WHERE p.titulo LIKE'%" + campo + "%'";
        }
        if (!txtFec.getText().equals("")) {
            where = "WHERE p.fecha_publicacion LIKE'%" + campo + "%'";
        }
        //busqueda por combo
        if (cmbEst.getSelectedIndex() != 0) {
            where = "WHERE p.fkestado_publicacion LIKE'%" + campo + "%'";
        } else {
            llenarPubs();
        }
        if (cmbTipBus.getSelectedIndex() != 0) {
            where = "WHERE p.fkidtipo_publicacion LIKE'%" + campo + "%'";
        } else {
            llenarPubs();
        }
        //fin
        try {
            tbPubs.setModel(model);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion objC = new Conexion();
            Connection conn = objC.conexiondb();
            String sql = "Select p.titulo,p.fecha_publicacion,p.fkidtipo_publicacion,p.fk_idescuela,p.fkestado_publicacion\n"
                    + "from autores a\n"
                    + "INNER JOIN docentes d ON a.fk_DNI=d.DNI\n"
                    + "INNER JOIN publicaciones p ON p.fk_idautores=a.idautores " + where + " AND d.usuario='" + usu5 + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            java.sql.ResultSetMetaData rsMd = (java.sql.ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            limpiarTabla(tbPubs, model);
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                model.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al buscar Articulo: \n" + e);
        }
    }
    
    public String idPub() {
        String id = "";
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            int Fila = tbPubs.getSelectedRow();
            String sql = "CALL p_idPub('" + usu5 + "','"+tbPubs.getValueAt(Fila, 0).toString()+"');";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("idpublicacion");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar idpublicacion: \n" + e);
        }
        return id;
    }
    
    public void EliminarPub() {
        PreparedStatement ps = null;
        try {
            Conexion objC = new Conexion();
            Connection conn = objC.conexiondb();
            int Fila = tbPubs.getSelectedRow();
            
            ps = conn.prepareStatement("DELETE FROM publicaciones WHERE idPublicacion=?");
            ps.setString(1, idPub());
            System.err.println("ee : "+ps);
            ps.execute();
            model.removeRow(Fila);
            JOptionPane.showMessageDialog(rootPane, "Publicacion Eliminada");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al Borrar Publicacion: \n" + e);
        }
        llenarPubs();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNom = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        lblTipUsu = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        txtFec = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPubs = new javax.swing.JTable();
        btnMenu = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cmbTipBus = new javax.swing.JComboBox<>();
        cmbEst = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("NOMBRE:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("TIPO DE USUARIO:");

        lblNom.setText("jLabel3");

        btnExit.setBackground(new java.awt.Color(255, 117, 109));
        btnExit.setText("LOGOUT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTipUsu.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblTipUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 470, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipUsu)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 870, 70));

        jPanel2.setBackground(new java.awt.Color(184, 236, 166));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PUBLICACIONES REALIZADAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N

        jLabel6.setText("TITULO:");

        jLabel7.setText("FECHA:");

        jLabel8.setText("ESTADO:");

        jLabel9.setText("TIPO DE PUBLICACION:");

        txtNom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomMouseClicked(evt);
            }
        });
        txtNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomKeyReleased(evt);
            }
        });

        txtFec.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        txtFec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFecMouseClicked(evt);
            }
        });
        txtFec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFecKeyReleased(evt);
            }
        });

        tbPubs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbPubs);

        btnMenu.setText("MENU PRINCIPAL");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        cmbTipBus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cmbTipBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipBusActionPerformed(evt);
            }
        });

        cmbEst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cmbEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFec, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbEst, 0, 148, Short.MAX_VALUE)
                    .addComponent(cmbTipBus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(141, 141, 141))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(294, 294, 294))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtNom)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(cmbEst, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtFec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(cmbTipBus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 96, 870, 450));

        jLabel12.setBackground(new java.awt.Color(184, 236, 166));
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(184, 236, 166), 300));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        LOGIN lg=new LOGIN();
        lg.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtNomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyReleased
        buscarAutor(txtNom.getText());
    }//GEN-LAST:event_txtNomKeyReleased

    private void txtFecKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFecKeyReleased
        buscarAutor(txtFec.getText());
    }//GEN-LAST:event_txtFecKeyReleased

    private void cmbEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstActionPerformed
        buscarAutor("" + cmbEst.getSelectedIndex());
    }//GEN-LAST:event_cmbEstActionPerformed

    private void cmbTipBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipBusActionPerformed
        buscarAutor("" + cmbTipBus.getSelectedIndex());
    }//GEN-LAST:event_cmbTipBusActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarPub();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomMouseClicked
        cmbEst.setSelectedIndex(0);
        cmbTipBus.setSelectedIndex(0);
        llenarPubs();
    }//GEN-LAST:event_txtNomMouseClicked

    private void txtFecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFecMouseClicked
        cmbEst.setSelectedIndex(0);
        cmbTipBus.setSelectedIndex(0);
        llenarPubs();
    }//GEN-LAST:event_txtFecMouseClicked

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
            java.util.logging.Logger.getLogger(PUBLICACIONES_DOCENTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PUBLICACIONES_DOCENTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PUBLICACIONES_DOCENTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PUBLICACIONES_DOCENTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PUBLICACIONES_DOCENTE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMenu;
    private javax.swing.JComboBox<String> cmbEst;
    private javax.swing.JComboBox<String> cmbTipBus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblTipUsu;
    private javax.swing.JTable tbPubs;
    private javax.swing.JFormattedTextField txtFec;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
