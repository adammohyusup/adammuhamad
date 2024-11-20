package javaswingdev.form;

import Util.Database;
import Util.Login;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.util.Vector;
import javaswingdev.card.ModelCard;
import javaswingdev.GoogleMaterialDesignIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Form_Pegawai extends javax.swing.JPanel {

    Connection conn;
    PreparedStatement pst;
    ResultSet rst;
    
    public String username;

    public Form_Pegawai() {
        initComponents();
        conn = Database.koneksiDB();
        init();
    }

    private void init() {
        
        updateTable("");
        
    }
    
    public void updateTable(String search){
        
        table_pegawai.fixTable(jScrollPane1);
        
        try{
            
            String sql="SELECT * FROM users WHERE nama LIKE '"+search+"%' or username LIKE '"+search+"%' or levels LIKE '"+search+"%';";
            
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            
            ResultSetMetaData stData = rst.getMetaData();
            
            int i = stData.getColumnCount();
            
            int no = 1;
            
            DefaultTableModel recordTable = (DefaultTableModel)table_pegawai.getModel();
            
            recordTable.setRowCount(0);
            
            while(rst.next()){
                
                if(!rst.getString("username").equals(Login.username)){
                    Vector columnData = new Vector();
                
                
                for (int j = 0; j < i; j++) {
                   
                   
                   columnData.add(no);
                   columnData.add(rst.getString("nama"));
                   columnData.add(rst.getString("username"));
                   columnData.add(rst.getString("levels"));
                    
                    
                   
                    
                }
                
                recordTable.addRow(columnData);
                no++;
                }
                
                
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan data");
        } 
        
    }
    
    public void clearText(){
          
        txt_nama_lengkap.setText("");
        txt_nama_lengkap.setText("Masukan Nama Lengkap");
        txt_nama_lengkap.setForeground(new Color(153, 153,153 ));
        
        txt_username.setText("");
        txt_username.setText("Masukan Username");
        txt_username.setForeground(new Color(153, 153,153 ));
        
        txt_password.setText("");
        txt_password.setText("Masukan Password");
        txt_password.setForeground(new Color(153, 153,153 ));
           
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_pegawai = new javaswingdev.swing.table.Table();
        roundPanel2 = new javaswingdev.swing.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txt_password = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        btn_delete = new rojerusan.RSMaterialButtonRectangle();
        btn_save = new rojerusan.RSMaterialButtonRectangle();
        btn_update = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_nama_lengkap = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        adm = new javax.swing.JRadioButton();
        ksr = new javax.swing.JRadioButton();
        roundPanel3 = new javaswingdev.swing.RoundPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        jScrollPane1.setBorder(null);
        jScrollPane1.setRequestFocusEnabled(false);

        table_pegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama", "Username", "Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_pegawai.setGridColor(new java.awt.Color(255, 255, 255));
        table_pegawai.setShowHorizontalLines(false);
        table_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pegawaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_pegawai);
        if (table_pegawai.getColumnModel().getColumnCount() > 0) {
            table_pegawai.getColumnModel().getColumn(0).setResizable(false);
            table_pegawai.getColumnModel().getColumn(0).setPreferredWidth(5);
            table_pegawai.getColumnModel().getColumn(1).setResizable(false);
            table_pegawai.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_pegawai.getColumnModel().getColumn(2).setResizable(false);
            table_pegawai.getColumnModel().getColumn(2).setPreferredWidth(30);
            table_pegawai.getColumnModel().getColumn(3).setResizable(false);
            table_pegawai.getColumnModel().getColumn(3).setPreferredWidth(6);
        }

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel9.setText("Username");

        txt_username.setForeground(new java.awt.Color(153, 153, 153));
        txt_username.setText("Masukan Username");
        txt_username.setBorder(null);
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel10.setText("Password");

        txt_password.setForeground(new java.awt.Color(153, 153, 153));
        txt_password.setText("Masukan Password");
        txt_password.setBorder(null);
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_passwordFocusLost(evt);
            }
        });
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel11.setText("Levels");

        btn_delete.setBackground(new java.awt.Color(252, 0, 58));
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(20, 123, 254));
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(254, 163, 2));
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bar graph.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/dollar.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Basket_alt_3.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Key.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel14.setText("Nama");

        txt_nama_lengkap.setForeground(new java.awt.Color(153, 153, 153));
        txt_nama_lengkap.setText("Masukan Nama Lengkap");
        txt_nama_lengkap.setBorder(null);
        txt_nama_lengkap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nama_lengkapFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nama_lengkapFocusLost(evt);
            }
        });

        adm.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(adm);
        adm.setText("admin");
        adm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admActionPerformed(evt);
            }
        });

        ksr.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(ksr);
        ksr.setText("kasir");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nama_lengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ksr)
                            .addComponent(adm))))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_nama_lengkap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(22, 22, 22)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(adm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ksr)
                .addGap(104, 104, 104)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel13.setText("Cari Pegawai");

        txt_cari.setForeground(new java.awt.Color(153, 153, 153));
        txt_cari.setText("Masukan Nama Pegawai");
        txt_cari.setBorder(null);
        txt_cari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariFocusLost(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Magnifier 2.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        updateTable(txt_cari.getText());
    }//GEN-LAST:event_txt_cariKeyReleased

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        // TODO add your handling code here:
                
    }//GEN-LAST:event_jLabel5MousePressed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        
        try{
            String level="";        
            if (adm.isSelected()) {
                level = "admin";
            }else if (ksr.isSelected()) {
                level = "kasir";
            }
        
            String sql = "INSERT INTO users(nama, username, password, levels) VALUES (?,?,MD5(?),?)";
        
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, txt_nama_lengkap.getText());
            pst.setString(2, txt_username.getText());
            pst.setString(3, txt_password.getText());
            pst.setString(4, level);
            
            pst.execute();
            clearText();
            updateTable("");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menambah pegawai");
        }
        
    }//GEN-LAST:event_btn_saveActionPerformed

    private void table_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pegawaiMouseClicked
        // TODO add your handling code here:
           
        DefaultTableModel recordTable = (DefaultTableModel)table_pegawai.getModel();
        int selectedRow = table_pegawai.getSelectedRow();
        
        txt_nama_lengkap.setText(recordTable.getValueAt(selectedRow, 1).toString());
        txt_nama_lengkap.setForeground(new Color(0, 0,0 ));
        txt_username.setText(recordTable.getValueAt(selectedRow, 2).toString());
        txt_username.setForeground(new Color(0, 0,0 ));
       
        if (recordTable.getValueAt(selectedRow, 3).toString().equals("admin")){
            adm.setSelected(true);
        } else if (recordTable.getValueAt(selectedRow, 3).toString().equals("kasir")){
            ksr.setSelected(true);
        }
        
        username = txt_username.getText();
    
    }//GEN-LAST:event_table_pegawaiMouseClicked

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
       
       try{
            
            String sql="delete from users where nama=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_nama_lengkap.getText());
            pst.execute();
            clearText();
            updateTable("");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menghapus data");
        }
            
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        
        try{
            
            String level="";        
            if (adm.isSelected()) {
                level = "admin";
            }else if (ksr.isSelected()) {
                level = "kasir";
            }
            
            String value1=txt_nama_lengkap.getText();
            String value2=txt_username.getText();
           
            String sql="update users set nama='"+value1+"' ,username='"+value2+"' ,levels='"+level+"' where username='"+username+"'";
            
            pst = conn.prepareStatement(sql);
            pst.execute();
            
            updateTable("");
            clearText();
            
            username = "";
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Pegawai Tidak Bisa Di update");
        }
      
    }//GEN-LAST:event_btn_updateActionPerformed

    private void admActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admActionPerformed

    private void txt_cariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariFocusGained
        // TODO add your handling code here:      
        
        if(txt_cari.getText().equals("Masukan Nama Pegawai")){
            txt_cari.setText("");
            txt_cari.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_cariFocusGained

    private void txt_cariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariFocusLost
        // TODO add your handling code here:
        if(txt_cari.getText().equals("")){
            txt_cari.setText("Masukan Nama Pegawai");
            txt_cari.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_cariFocusLost

    private void txt_nama_lengkapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nama_lengkapFocusGained
        // TODO add your handling code here:
        if(txt_nama_lengkap.getText().equals("Masukan Nama Lengkap")){
            txt_nama_lengkap.setText("");
            txt_nama_lengkap.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_nama_lengkapFocusGained

    private void txt_nama_lengkapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nama_lengkapFocusLost
        // TODO add your handling code here:
        if(txt_nama_lengkap.getText().equals("")){
            txt_nama_lengkap.setText("Masukan Nama Lengkap");
            txt_nama_lengkap.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_nama_lengkapFocusLost

    private void txt_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusGained
        // TODO add your handling code here:
        if(txt_username.getText().equals("Masukan Username")){
            txt_username.setText("");
            txt_username.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_usernameFocusGained

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        // TODO add your handling code here:
        if(txt_username.getText().equals("")){
            txt_username.setText("Masukan Username");
            txt_username.setForeground(new Color(153, 153,153 ));
        }
        
    }//GEN-LAST:event_txt_usernameFocusLost

    private void txt_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusGained
        // TODO add your handling code here:
        if(txt_password.getText().equals("Masukan Password")){
            txt_password.setText("");
            txt_password.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_passwordFocusGained

    private void txt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusLost
        // TODO add your handling code here:
        if(txt_password.getText().equals("")){
            txt_password.setText("Masukan Password");
            txt_password.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_passwordFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton adm;
    private rojerusan.RSMaterialButtonRectangle btn_delete;
    private rojerusan.RSMaterialButtonRectangle btn_save;
    private rojerusan.RSMaterialButtonRectangle btn_update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JRadioButton ksr;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.RoundPanel roundPanel2;
    private javaswingdev.swing.RoundPanel roundPanel3;
    private javaswingdev.swing.table.Table table_pegawai;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_nama_lengkap;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
