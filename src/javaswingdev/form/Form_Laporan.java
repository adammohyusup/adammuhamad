package javaswingdev.form;

import Util.CurrencyFormatID;
import Util.CurrentDate;
import Util.Database;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javaswingdev.card.ModelCard;
import javaswingdev.GoogleMaterialDesignIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Form_Laporan extends javax.swing.JPanel {
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rst;
    
    public Form_Laporan() {
        initComponents();
        conn = Database.koneksiDB();
        init();
    }

    private void init() {
        
        labelTanggal.setText("Transaksi sebelum tanggal "+CurrentDate.getDate());

        
        
        chart.addLegend("Penjualan", new Color(135, 189, 245));
        chart.addLegend("Pemasukan", new Color(245, 189, 135));
        

        updateChart();
        updateTable();
        
        
    }
    
    public void updateTable(){
        
        table_trx.fixTable(jScrollPane1);
        
        try{
            
            String sql="SELECT * FROM `transaksi` WHERE `Tanggal` >= '"+CurrentDate.getYear()+"-"+CurrentDate.getMonth()+"-01' ORDER BY Tanggal DESC;";
            
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            
            ResultSetMetaData stData = rst.getMetaData();
            
            int i = stData.getColumnCount();
            
            int no = 1;
            int total = 0;
            
            DefaultTableModel recordTable = (DefaultTableModel)table_trx.getModel();
            
            recordTable.setRowCount(0);
            
            while(rst.next()){
                
                Vector columnData = new Vector();
                
                for (int j = 0; j < i; j++) {
                    
                   columnData.add(no);
                   columnData.add(rst.getString("Kode_Transaksi"));
                   
                   columnData.add(rst.getString("Tanggal"));
                   columnData.add(CurrencyFormatID.getCurrency(Integer.parseInt( rst.getString("Total"))));
                   
                    
                }
                
                recordTable.addRow(columnData);
                no++;
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan data");
        } 
        
    }
    
    public void updateChart(){
        
        try{
            
            String sql = "SELECT count(Kode_Transaksi) as 'Total_p', SUM(Total) as 'Total', MONTH(Tanggal) as 'Bulan' FROM transaksi GROUP BY MONTH(Tanggal);";
            
            pst = conn.prepareStatement(sql);
            
            rst = pst.executeQuery();
            
            int bulan = 0;
            int[]total_penjualan = {0,0,0,0,0,0,0,0,0,0,0,0};
            int[]total_pemasukan = {0,0,0,0,0,0,0,0,0,0,0,0};
            
            
            
            while(rst.next()){
                
                total_penjualan[rst.getInt("Bulan") - 1] = rst.getInt("Total_p");
                total_pemasukan[rst.getInt("Bulan") - 1] = rst.getInt("Total");
                
                
                
            }
            
            chart.addData(new ModelChart("Jan", new double[]{total_penjualan[0] * 10, (total_pemasukan[0] / 100000) * 20}));
            chart.addData(new ModelChart("Feb", new double[]{total_penjualan[1] * 10, (total_pemasukan[1] / 100000) * 20}));
            chart.addData(new ModelChart("Mar", new double[]{total_penjualan[2] * 10, (total_pemasukan[2] / 100000) * 20}));
            chart.addData(new ModelChart("Apr", new double[]{total_penjualan[3] * 10, (total_pemasukan[3] / 100000) * 20}));
            chart.addData(new ModelChart("Mei", new double[]{total_penjualan[4] * 10, (total_pemasukan[4] / 100000) * 20}));
            chart.addData(new ModelChart("Jun", new double[]{total_penjualan[5] * 10, (total_pemasukan[5] / 100000) * 20}));
            chart.addData(new ModelChart("Jul", new double[]{total_penjualan[6] * 10, (total_pemasukan[6] / 100000) * 20}));
            chart.addData(new ModelChart("Aug", new double[]{total_penjualan[7] * 10, (total_pemasukan[7] / 100000) * 20}));
            chart.addData(new ModelChart("Sep", new double[]{total_penjualan[8] * 10, (total_pemasukan[8] / 100000) * 20}));
            chart.addData(new ModelChart("Okt", new double[]{total_penjualan[9] * 10, (total_pemasukan[9] / 100000) * 20}));
            chart.addData(new ModelChart("Nov", new double[]{total_penjualan[10] * 10, (total_pemasukan[10] / 100000) * 20}));
            chart.addData(new ModelChart("Des", new double[]{total_penjualan[11] * 10, (total_pemasukan[11] / 100000) * 20}));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa update data");
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_trx = new javaswingdev.swing.table.Table();
        roundPanel2 = new javaswingdev.swing.RoundPanel();
        chart = new com.raven.chart.Chart();
        roundPanel3 = new javaswingdev.swing.RoundPanel();
        labelTanggal = new javax.swing.JLabel();
        txt_hari_sebelum = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        txt_bulan_sebelum = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        txt_tahun_sebelum = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_tahun_antara1 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_hari_antara_1 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        txt_bulan_antara1 = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        btn_antara = new rojerusan.RSMaterialButtonRectangle();
        btn_sebelum = new rojerusan.RSMaterialButtonRectangle();
        txt_bulan_antara2 = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        txt_tahun_antara2 = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_hari_antara2 = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table_trx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode", "Tanggal", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_trx);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel2.setRound(10);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel3.setRound(10);

        labelTanggal.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        labelTanggal.setText("Transaksi sebelum tanggal ");

        txt_hari_sebelum.setForeground(new java.awt.Color(153, 153, 153));
        txt_hari_sebelum.setText("DD");
        txt_hari_sebelum.setBorder(null);
        txt_hari_sebelum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hari_sebelumFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_hari_sebelumFocusLost(evt);
            }
        });
        txt_hari_sebelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hari_sebelumActionPerformed(evt);
            }
        });

        txt_bulan_sebelum.setForeground(new java.awt.Color(153, 153, 153));
        txt_bulan_sebelum.setText("MM");
        txt_bulan_sebelum.setBorder(null);
        txt_bulan_sebelum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_bulan_sebelumFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bulan_sebelumFocusLost(evt);
            }
        });

        txt_tahun_sebelum.setForeground(new java.awt.Color(153, 153, 153));
        txt_tahun_sebelum.setText("YYYY");
        txt_tahun_sebelum.setBorder(null);
        txt_tahun_sebelum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tahun_sebelumFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_tahun_sebelumFocusLost(evt);
            }
        });

        jLabel1.setText("/");

        jLabel2.setText("/");

        txt_tahun_antara1.setForeground(new java.awt.Color(153, 153, 153));
        txt_tahun_antara1.setText("YYYY");
        txt_tahun_antara1.setBorder(null);
        txt_tahun_antara1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tahun_antara1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_tahun_antara1FocusLost(evt);
            }
        });
        txt_tahun_antara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tahun_antara1ActionPerformed(evt);
            }
        });

        jLabel3.setText("/");

        jLabel4.setText("/");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel15.setText("Transaksi Antara tanggal");

        txt_hari_antara_1.setForeground(new java.awt.Color(153, 153, 153));
        txt_hari_antara_1.setText("DD");
        txt_hari_antara_1.setBorder(null);
        txt_hari_antara_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hari_antara_1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_hari_antara_1FocusLost(evt);
            }
        });

        txt_bulan_antara1.setForeground(new java.awt.Color(153, 153, 153));
        txt_bulan_antara1.setText("MM");
        txt_bulan_antara1.setBorder(null);
        txt_bulan_antara1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_bulan_antara1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bulan_antara1FocusLost(evt);
            }
        });

        btn_antara.setBackground(new java.awt.Color(20, 123, 254));
        btn_antara.setText("Tampilkan");
        btn_antara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_antaraActionPerformed(evt);
            }
        });

        btn_sebelum.setBackground(new java.awt.Color(20, 123, 254));
        btn_sebelum.setText("Tampilkan");
        btn_sebelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sebelumActionPerformed(evt);
            }
        });

        txt_bulan_antara2.setForeground(new java.awt.Color(153, 153, 153));
        txt_bulan_antara2.setText("MM");
        txt_bulan_antara2.setBorder(null);
        txt_bulan_antara2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_bulan_antara2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bulan_antara2FocusLost(evt);
            }
        });

        txt_tahun_antara2.setForeground(new java.awt.Color(153, 153, 153));
        txt_tahun_antara2.setText("YYYY");
        txt_tahun_antara2.setBorder(null);
        txt_tahun_antara2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tahun_antara2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_tahun_antara2FocusLost(evt);
            }
        });

        jLabel7.setText("/");

        jLabel8.setText("/");

        txt_hari_antara2.setForeground(new java.awt.Color(153, 153, 153));
        txt_hari_antara2.setText("DD");
        txt_hari_antara2.setBorder(null);
        txt_hari_antara2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hari_antara2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_hari_antara2FocusLost(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sebelum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_antara, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel3Layout.createSequentialGroup()
                                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_tahun_sebelum, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_bulan_sebelum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_hari_sebelum, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(roundPanel3Layout.createSequentialGroup()
                                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_tahun_antara2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_bulan_antara2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(roundPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_tahun_antara1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_bulan_antara1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_hari_antara_1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(roundPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_hari_antara2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(272, 272, 272))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(labelTanggal)
                .addGap(18, 18, 18)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hari_sebelum, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bulan_sebelum, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tahun_sebelum, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(0, 0, 0)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator8)
                    .addComponent(jSeparator6)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_sebelum, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hari_antara_1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bulan_antara1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tahun_antara1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(0, 0, 0)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator9)
                    .addComponent(jSeparator10)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hari_antara2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bulan_antara2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tahun_antara2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(0, 0, 0)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator16)
                    .addComponent(jSeparator17)
                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_antara, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sebelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sebelumActionPerformed
        // TODO add your handling code here:
        table_trx.fixTable(jScrollPane1);
        String tahun=txt_tahun_sebelum.getText();
        String bulan=txt_bulan_sebelum.getText();
        String hari=txt_hari_sebelum.getText();
        try {
            String tanggal=tahun+"-"+bulan+"-"+hari;
            String sql="select * from transaksi where Tanggal > '"+tanggal+"'";
            pst=conn.prepareStatement(sql);
            rst=pst.executeQuery();
            ResultSetMetaData stData = rst.getMetaData();
            
            int i = stData.getColumnCount();
            
            int no = 1;
            int total = 0;
            
            DefaultTableModel recordTable = (DefaultTableModel)table_trx.getModel();
            
            recordTable.setRowCount(0);
            
            while(rst.next()){
                
                Vector columnData = new Vector();
                
                for (int j = 0; j < i; j++) {
                    
                   columnData.add(no);
                   columnData.add(rst.getString("Kode_Transaksi"));
                   
                   columnData.add(rst.getString("Tanggal"));
                   columnData.add(CurrencyFormatID.getCurrency(Integer.parseInt( rst.getString("Total"))));
                   
                    
                }
                
                recordTable.addRow(columnData);
                no++;
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan tanggal sebelum");
        }
    }//GEN-LAST:event_btn_sebelumActionPerformed

    private void btn_antaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_antaraActionPerformed
        // TODO add your handling code here:
        table_trx.fixTable(jScrollPane1);
        String tahun=txt_tahun_antara1.getText();
        String bulan=txt_hari_antara_1.getText();
        String hari=txt_hari_antara_1.getText();

        String tahun2=txt_tahun_antara2.getText();
        String bulan2=txt_bulan_antara2.getText();
        String hari2=txt_hari_antara2.getText();

        try {
            String tanggal=tahun+"-"+bulan+"-"+hari;
            String tanggal2=tahun2+"-"+bulan2+"-"+hari2;
            String sql="select * from transaksi where Tanggal between '"+tanggal+"' and '"+tanggal2+"'";
            pst=conn.prepareStatement(sql);
            rst=pst.executeQuery();
            ResultSetMetaData stData = rst.getMetaData();

            int i = stData.getColumnCount();

            int no = 1;
            int total = 0;

            DefaultTableModel recordTable = (DefaultTableModel)table_trx.getModel();

            recordTable.setRowCount(0);

            while(rst.next()){

                Vector columnData = new Vector();

                for (int j = 0; j < i; j++) {

                   columnData.add(no);
                   columnData.add(rst.getString("Kode_Transaksi"));
                   
                   columnData.add(rst.getString("Tanggal"));
                   columnData.add(CurrencyFormatID.getCurrency(Integer.parseInt( rst.getString("Total"))));


                }

                recordTable.addRow(columnData);
                no++;

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan tanggal antara");
        }
    }//GEN-LAST:event_btn_antaraActionPerformed

    private void txt_tahun_sebelumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tahun_sebelumFocusGained
        // TODO add your handling code here:
        
        if(txt_tahun_sebelum.getText().equals("YYYY")){
           txt_tahun_sebelum.setText("");
           txt_tahun_sebelum.setForeground(new Color(0, 0,0 ));
        }
        
    }//GEN-LAST:event_txt_tahun_sebelumFocusGained

    private void txt_tahun_sebelumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tahun_sebelumFocusLost
        // TODO add your handling code here:
        if(txt_tahun_sebelum.getText().equals("")){
           txt_tahun_sebelum.setText("YYYY");
           txt_tahun_sebelum.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_tahun_sebelumFocusLost

    private void txt_bulan_sebelumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bulan_sebelumFocusGained
        // TODO add your handling code here
        
        if(txt_bulan_sebelum.getText().equals("MM")){
           txt_bulan_sebelum.setText("");
           txt_bulan_sebelum.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_bulan_sebelumFocusGained

    private void txt_bulan_sebelumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bulan_sebelumFocusLost
        // TODO add your handling code here:
        
        if(txt_bulan_sebelum.getText().equals("")){
           txt_bulan_sebelum.setText("MM");
           txt_bulan_sebelum.setForeground(new Color(153, 153,153 ));
        }  
    }//GEN-LAST:event_txt_bulan_sebelumFocusLost

    private void txt_hari_sebelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hari_sebelumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hari_sebelumActionPerformed

    private void txt_hari_sebelumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hari_sebelumFocusGained
        // TODO add your handling code here:
        
        if(txt_hari_sebelum.getText().equals("DD")){
           txt_hari_sebelum.setText("");
           txt_hari_sebelum.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_hari_sebelumFocusGained

    private void txt_hari_sebelumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hari_sebelumFocusLost
        // TODO add your handling code here:
        
        if(txt_hari_sebelum.getText().equals("")){
           txt_hari_sebelum.setText("DD");
           txt_hari_sebelum.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_hari_sebelumFocusLost

    private void txt_tahun_antara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tahun_antara1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tahun_antara1ActionPerformed

    private void txt_tahun_antara1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tahun_antara1FocusGained
        // TODO add your handling code here:
        
        if(txt_tahun_antara1.getText().equals("YYYY")){
           txt_tahun_antara1.setText("");
           txt_tahun_antara1.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_tahun_antara1FocusGained

    private void txt_tahun_antara1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tahun_antara1FocusLost
        // TODO add your handling code here:
                
        if(txt_tahun_antara1.getText().equals("")){
           txt_tahun_antara1.setText("YYYY");
           txt_tahun_antara1.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_tahun_antara1FocusLost

    private void txt_bulan_antara1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bulan_antara1FocusGained
        // TODO add your handling code here:
        
        if(txt_bulan_antara1.getText().equals("MM")){
           txt_bulan_antara1.setText("");
           txt_bulan_antara1.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_bulan_antara1FocusGained

    private void txt_bulan_antara1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bulan_antara1FocusLost
        // TODO add your handling code here:
        
        if(txt_bulan_antara1.getText().equals("")){
           txt_bulan_antara1.setText("MM");
           txt_bulan_antara1.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_bulan_antara1FocusLost

    private void txt_hari_antara_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hari_antara_1FocusGained
        // TODO add your handling code here:
        
        if(txt_hari_antara_1.getText().equals("DD")){
           txt_hari_antara_1.setText("");
           txt_hari_antara_1.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_hari_antara_1FocusGained

    private void txt_hari_antara_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hari_antara_1FocusLost
        // TODO add your handling code here:
               
        if(txt_hari_antara_1.getText().equals("")){
           txt_hari_antara_1.setText("DD");
           txt_hari_antara_1.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_hari_antara_1FocusLost

    private void txt_tahun_antara2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tahun_antara2FocusGained
        // TODO add your handling code here:
        
        if(txt_tahun_antara2.getText().equals("YYYY")){
           txt_tahun_antara2.setText("");
           txt_tahun_antara2.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_tahun_antara2FocusGained

    private void txt_tahun_antara2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tahun_antara2FocusLost
        // TODO add your handling code here:
        
        if(txt_tahun_antara2.getText().equals("")){
           txt_tahun_antara2.setText("YYYY");
           txt_tahun_antara2.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_tahun_antara2FocusLost

    private void txt_bulan_antara2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bulan_antara2FocusGained
        // TODO add your handling code here:
        
        if(txt_bulan_antara2.getText().equals("MM")){
           txt_bulan_antara2.setText("");
           txt_bulan_antara2.setForeground(new Color(0, 0,0 ));
        }
    }//GEN-LAST:event_txt_bulan_antara2FocusGained

    private void txt_bulan_antara2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bulan_antara2FocusLost
        // TODO add your handling code here:
        
        if(txt_bulan_antara2.getText().equals("")){
           txt_bulan_antara2.setText("MM");
           txt_bulan_antara2.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_bulan_antara2FocusLost

    private void txt_hari_antara2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hari_antara2FocusGained
        // TODO add your handling code here:
        
        if(txt_hari_antara2.getText().equals("DD")){
           txt_hari_antara2.setText("");
           txt_hari_antara2.setForeground(new Color(0, 0,0 ));
        } 
    }//GEN-LAST:event_txt_hari_antara2FocusGained

    private void txt_hari_antara2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hari_antara2FocusLost
        // TODO add your handling code here:
        
        if(txt_hari_antara2.getText().equals("")){
           txt_hari_antara2.setText("DD");
           txt_hari_antara2.setForeground(new Color(153, 153,153 ));
        }
    }//GEN-LAST:event_txt_hari_antara2FocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btn_antara;
    private rojerusan.RSMaterialButtonRectangle btn_sebelum;
    private com.raven.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labelTanggal;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.RoundPanel roundPanel2;
    private javaswingdev.swing.RoundPanel roundPanel3;
    private javaswingdev.swing.table.Table table_trx;
    private javax.swing.JTextField txt_bulan_antara1;
    private javax.swing.JTextField txt_bulan_antara2;
    private javax.swing.JTextField txt_bulan_sebelum;
    private javax.swing.JTextField txt_hari_antara2;
    private javax.swing.JTextField txt_hari_antara_1;
    private javax.swing.JTextField txt_hari_sebelum;
    private javax.swing.JTextField txt_tahun_antara1;
    private javax.swing.JTextField txt_tahun_antara2;
    private javax.swing.JTextField txt_tahun_sebelum;
    // End of variables declaration//GEN-END:variables
}
