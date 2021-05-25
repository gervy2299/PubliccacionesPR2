/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyectprogra2.Conexion;

/**
 *
 * @author HP
 */
public class LISTA_TIPIFICACION extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Conexion co = new Conexion();
    Connection con = co.conexiondb();
    public static String usuTip;

    /**
     * Creates new form JF8
     */
    public LISTA_TIPIFICACION() {
        initComponents();
        cabecera();
        llenarCategria();
        datosGen();
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    public void datosGen() {
        String[] registros = new String[4];
        String docente = "";
        String sql = "CALL p_usuario('" + usuTip + "');";
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
            jLabel7.setText(" " + docente.toUpperCase());
            jLabel4.setText(" " + registros[3]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos" + e.getMessage(), "Mensaje", 0);
        }
    }

    public void cabecera() {
        String[] tit = {"DNI", "Autor", "Titulo", "Paginas", "Capitulos", "Fecha", "Escuela", "tipo publicacion", "Estado"};
        model.setColumnIdentifiers(tit);
        TablaTipi.setModel(model);
    }

    public void limpiarTabla(JTable tb, DefaultTableModel md) {
        while (tb.getRowCount() > 0) {
            md.removeRow(0);
        }
    }

    public void llenarCategria() {
        try {
            limpiarTabla(TablaTipi, model);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM tipi_estado";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            int[] anchos = {100, 100, 100, 100, 100, 100, 100, 100, 100};
            for (int i = 0; i < TablaTipi.getColumnCount(); i++) {
                TablaTipi.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                model.addRow(filas);
            }
            //int i = TablaRati.getRowCount()+1;
            //   txtdni.setText(Integer.toString(i));
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void buscarCat(String campo) {
        try {
            TablaTipi.setModel(model);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion objC = new Conexion();
            Connection conn = objC.conexiondb();
            String sql = "select re.DNI, re.NOMBRE, re.TITULO, re.PAGINAS, re.CAPITULO, re.FECHA, re.ESCUELA, re.TIPO, re.ESTADO from tipi_estado re WHERE DNI LIKE'%" + campo + "%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            limpiarTabla(TablaTipi, model);
            while (rs.next()) {
                Object[] filas= new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i]=rs.getObject(i+1);
                }
                model.addRow(filas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al buscar Articulo: \n" + e);
        }
    }

    public void pasarDato() {

        int Fila = TablaTipi.getSelectedRow();
        String tituloT = TablaTipi.getValueAt(Fila, 2).toString();
        String paginaT = TablaTipi.getValueAt(Fila, 3).toString();
        String capituloT = TablaTipi.getValueAt(Fila, 4).toString();
        String fechaT = TablaTipi.getValueAt(Fila, 5).toString();
        String escuelaT = TablaTipi.getValueAt(Fila, 6).toString();
        String tipoT = TablaTipi.getValueAt(Fila, 7).toString();
        VALIDAR_TIPIFICACION.titulo = tituloT;
        VALIDAR_TIPIFICACION.tipo = tipoT;
        VALIDAR_TIPIFICACION.escuela = escuelaT;
        VALIDAR_TIPIFICACION.fecha = fechaT;
        VALIDAR_TIPIFICACION.pagina = paginaT;
        VALIDAR_TIPIFICACION.capitulo = capituloT;
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
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTipi = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(55, 221, 193));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 221, 193));
        jLabel1.setText("NOMBRE:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(55, 221, 193));
        jLabel2.setText("TIPO DE USUARIO:");

        jButton1.setBackground(new java.awt.Color(255, 51, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LOGOUT");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("jLabel4");

        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addGap(114, 114, 114)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 990, 80));

        jPanel2.setBackground(new java.awt.Color(244, 252, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 252, 250)));
        jPanel2.setToolTipText("");
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaTipi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaTipi);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 68, 950, 304));

        jButton2.setBackground(new java.awt.Color(55, 221, 193));
        jButton2.setText("ATRAS");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 157, 50));

        jButton3.setBackground(new java.awt.Color(55, 221, 193));
        jButton3.setText("VALIDAR");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 158, 50));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("DNI:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });
        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdniKeyReleased(evt);
            }
        });
        jPanel2.add(txtdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 220, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 99, 980, 480));

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 252, 250), 200));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyReleased
        buscarCat(txtdni.getText());
    }//GEN-LAST:event_txtdniKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        pasarDato();
        VALIDAR_TIPIFICACION.usuTip1 = usuTip;
        new VALIDAR_TIPIFICACION().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       new Login.LOGIN().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MENU.usu=usuTip;
        new MENU().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdniActionPerformed

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
            java.util.logging.Logger.getLogger(LISTA_TIPIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LISTA_TIPIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LISTA_TIPIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LISTA_TIPIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LISTA_TIPIFICACION().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaTipi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtdni;
    // End of variables declaration//GEN-END:variables
}
