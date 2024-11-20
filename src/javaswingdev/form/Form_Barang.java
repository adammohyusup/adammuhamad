package javaswingdev.form;

import Util.Database;
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

public class Form_Barang extends javax.swing.JPanel {

    Connection conn;
    PreparedStatement pst;
    ResultSet rst;

    public Form_Barang() {
        initComponents();
        conn = Database.koneksiDB();
        init();
    }

    private void init() {
        
        updateTable("");
        
    }
    
    public void updateTable(String search){
        
        table.fixTable(jScrollPane1);
        
        try{
            
            String sql="SELECT * FROM `barang` WHERE Nama_Barang LIKE '"+search+"%'";
            
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            
            ResultSetMetaData stData = rst.getMetaData();
            
            int i = stData.getColumnCount();
            
            int no = 1;
            
            DefaultTableModel recordTable = (DefaultTableModel)table.getModel();
            
            recordTable.setRowCount(0);
            
            while(rst.next()){
                
                Vector columnData = new Vector();
                
                for (int j = 0; j < i; j++) {
                    
                   columnData.add("    "+no);
                   columnData.add(rst.getString("Kode_Barang"));
                   columnData.add(rst.getString("Nama_Barang"));
                   columnData.add(rst.getString("Stok"));
                   columnData.add(rst.getString("Satuan"));
                   columnData.add("Rp "+rst.getString("Harga"));
                    
                }
                
                recordTable.addRow(columnData);
                no++;
                
            }
            
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan data");
        } 
        
    }
    
    public void clearText(){
        
        txt_kodebarang.setText("");
        txt_kodebarang.setText("Masukan Kode Barang");
        txt_kodebarang.setForeground(new Color(153, 153,153 ));
        
        txt_namabarang.setText("");
        txt_namabarang.setText("Masukan Nama Barang");
        txt_namabarang.setForeground(new Color(153, 153,153 ));
        
        txt_stokbarang.setText("");
        txt_stokbarang.setText("Masukan Stok Barang");
        txt_stokbarang.setForeground(new Color(153, 153,153 ));
        
        txt_hargabarang.setText("");
        txt_hargabarang.setText("Masukan Harga Barang");
        txt_hargabarang.setForeground(new Color(153, 153,153 ));
        
        txt_satuanbarang.setText("");
        txt_satuanbarang.setText("Masukan Satuan Barang");
        txt_satuanbarang.setForeground(new Color(153, 153,153 ));
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        roundPanel2 = new javaswingdev.swing.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_namabarang = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txt_stokbarang = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txt_hargabarang = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        txt_satuanbarang = new javax.swing.JTextField();
        btn_delete = new rojerusan.RSMaterialButtonRectangle();
        btn_save = new rojerusan.RSMaterialButtonRectangle();
        btn_update = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_kodebarang = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        roundPanel3 = new javaswingdev.swing.RoundPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        jScrollPane1.setBorder(null);
        jScrollPane1.setRequestFocusEnabled(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "   No", "Kode", "Nama", "Stok", "Satuan", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setGridColor(new java.awt.Color(255, 255, 255));
        table.setShowHorizontalLines(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(5);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(7);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(120);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(6);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel9.setText("Nama Barang");

        txt_namabarang.setForeground(new java.awt.Color(153, 153, 153));
        txt_namabarang.setText("Masukan Nama Barang");
        txt_namabarang.setBorder(null);
        txt_namabarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_namabarangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_namabarangFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel10.setText("Stok Barang");

        txt_stokbarang.setForeground(new java.awt.Color(153, 153, 153));
        txt_stokbarang.setText("Masukan Stok Barang");
        txt_stokbarang.setBorder(null);
        txt_stokbarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_stokbarangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_stokbarangFocusLost(evt);
            }
        });
        txt_stokbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stokbarangActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel11.setText("Harga Barang");

        txt_hargabarang.setForeground(new java.awt.Color(153, 153, 153));
        txt_hargabarang.setText("Masukan Harga Barang");
        txt_hargabarang.setBorder(null);
        txt_hargabarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hargabarangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_hargabarangFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel12.setText("Satuan Barang");

        txt_satuanbarang.setForeground(new java.awt.Color(153, 153, 153));
        txt_satuanbarang.setText("Masukan Satuan Barang");
        txt_satuanbarang.setBorder(null);
        txt_satuanbarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_satuanbarangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_satuanbarangFocusLost(evt);
            }
        });
        txt_satuanbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_satuanbarangActionPerformed(evt);
            }
        });

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/web layout.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Basket_alt_3.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Key.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel14.setText("Kode Barang");

        txt_kodebarang.setForeground(new java.awt.Color(153, 153, 153));
        txt_kodebarang.setText("Masukan Kode Barang");
        txt_kodebarang.setBorder(null);
        txt_kodebarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_kodebarangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_kodebarangFocusLost(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_stokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_hargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_satuanbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_stokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_hargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_satuanbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel13.setText("Cari Barang");

        txt_search.setForeground(new java.awt.Color(153, 153, 153));
        txt_search.setText("Masukan Nama Barang");
        txt_search.setBorder(null);
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchFocusLost(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
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
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_stokbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stokbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokbarangActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        
        updateTable(txt_search.getText());
        
        
    }//GEN-LAST:event_txt_searchKeyReleased

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_jLabel5MousePressed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        
        try{
            String sql = "INSERT INTO barang(Kode_Barang, Nama_Barang, Stok, Satuan, Harga) VALUES (?,?,?,?,?)";
        
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, txt_kodebarang.getText());
            pst.setString(2, txt_namabarang.getText());
            pst.setString(3, txt_stokbarang.getText());
            pst.setString(4, txt_satuanbarang.getText());
            pst.setString(5, txt_hargabarang.getText());
            
            pst.execute();
            clearText();
            updateTable("");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menambah barang");
        }
        
    }//GEN-LAST:event_btn_saveActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel recordTable = (DefaultTableModel)table.getModel();
        int selectedRow = table.getSelectedRow();
        
        txt_kodebarang.setText(recordTable.getValueAt(selectedRow, 1).toString());
        txt_kodebarang.setForeground(new Color(0, 0,0 ));
        txt_namabarang.setText(recordTable.getValueAt(selectedRow, 2).toString());
        txt_namabarang.setForeground(new Color(0, 0,0 ));
        txt_stokbarang.setText(recordTable.getValueAt(selectedRow, 3).toString());
        txt_stokbarang.setForeground(new Color(0, 0,0 ));
        txt_satuanbarang.setText(recordTable.getValueAt(selectedRow,4).toString());
        txt_satuanbarang.setForeground(new Color(0, 0,0 ));
        txt_hargabarang.setText(recordTable.getValueAt(selectedRow, 5).toString().substring(3));
        txt_hargabarang.setForeground(new Color(0, 0,0 ));
        
        
    }//GEN-LAST:event_tableMouseClicked

    private void txt_kodebarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_kodebarangFocusGained
        // TODO add your handling code here:
        
        if(txt_kodebarang.getText().equals("Masukan Kode Barang")){
            txt_kodebarang.setText("");
            txt_kodebarang.setForeground(new Color(0, 0,0 ));
        }
        
    }//GEN-LAST:event_txt_kodebarangFocusGained

    private void txt_kodebarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_kodebarangFocusLost
        // TODO add your handling code here:
        if(txt_kodebarang.getText().equals("")){
            txt_kodebarang.setText("Masukan Kode Barang");
            txt_kodebarang.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_kodebarangFocusLost

    private void txt_namabarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namabarangFocusGained
        // TODO add your handling code here:
        if(txt_namabarang.getText().equals("Masukan Nama Barang")){
            txt_namabarang.setText("");
            txt_namabarang.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_namabarangFocusGained

    private void txt_namabarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namabarangFocusLost
        // TODO add your handling code here:
        if(txt_namabarang.getText().equals("")){
            txt_namabarang.setText("Masukan Nama Barang");
            txt_namabarang.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_namabarangFocusLost

    private void txt_stokbarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_stokbarangFocusGained
        // TODO add your handling code here:
        if(txt_stokbarang.getText().equals("Masukan Stok Barang")){
            txt_stokbarang.setText("");
            txt_stokbarang.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_stokbarangFocusGained

    private void txt_stokbarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_stokbarangFocusLost
        // TODO add your handling code here:
        if(txt_stokbarang.getText().equals("")){
            txt_stokbarang.setText("Masukan Stok Barang");
            txt_stokbarang.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_stokbarangFocusLost

    private void txt_hargabarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hargabarangFocusGained
        // TODO add your handling code here:
//        Masukan Harga Barang
        if(txt_hargabarang.getText().equals("Masukan Harga Barang")){
            txt_hargabarang.setText("");
            txt_hargabarang.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_hargabarangFocusGained

    private void txt_hargabarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hargabarangFocusLost
        // TODO add your handling code here:
        if(txt_hargabarang.getText().equals("")){
            txt_hargabarang.setText("Masukan Harga Barang");
            txt_hargabarang.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_hargabarangFocusLost

    private void txt_satuanbarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_satuanbarangFocusGained
        // TODO add your handling code here:
        if(txt_satuanbarang.getText().equals("Masukan Satuan Barang")){
            txt_satuanbarang.setText("");
            txt_satuanbarang.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_satuanbarangFocusGained

    private void txt_satuanbarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_satuanbarangFocusLost
        // TODO add your handling code here:
        if(txt_satuanbarang.getText().equals("")){
            txt_satuanbarang.setText("Masukan Satuan Barang");
            txt_satuanbarang.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_satuanbarangFocusLost

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
        // TODO add your handling code here:
        if(txt_search.getText().equals("Masukan Nama Barang")){
            txt_search.setText("");
            txt_search.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusLost
        // TODO add your handling code here:
        if(txt_search.getText().equals("")){
            txt_search.setText("Masukan Nama Barang");
            txt_search.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_searchFocusLost

    private void txt_satuanbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_satuanbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_satuanbarangActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
       
        try{
            
            String sql="delete from barang where Kode_Barang=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_kodebarang.getText());
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
            
            String value1=txt_namabarang.getText();
            String value2=txt_kodebarang.getText();
            String value3=txt_stokbarang.getText();
            String value4=txt_satuanbarang.getText();
            String value5=txt_hargabarang.getText();

            String sql="update barang set Nama_Barang='"+value1+"' ,Stok='"+value3+"' ,Satuan='"+value4+"' ,Harga='"+value5+"' where Kode_Barang='"+value2+"'";
            
            pst = conn.prepareStatement(sql);
            pst.execute();
            
            updateTable("");
            clearText();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Barang Tidak Bisa Di update");
        }
        
    }//GEN-LAST:event_btn_updateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btn_delete;
    private rojerusan.RSMaterialButtonRectangle btn_save;
    private rojerusan.RSMaterialButtonRectangle btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.RoundPanel roundPanel2;
    private javaswingdev.swing.RoundPanel roundPanel3;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JTextField txt_hargabarang;
    private javax.swing.JTextField txt_kodebarang;
    private javax.swing.JTextField txt_namabarang;
    private javax.swing.JTextField txt_satuanbarang;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_stokbarang;
    // End of variables declaration//GEN-END:variables
}
