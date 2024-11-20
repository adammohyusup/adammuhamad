package javaswingdev.form;

import Util.CurrencyFormatID;
import Util.CurrentDate;
import Util.Database;
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

public class Form_Dashboard extends javax.swing.JPanel {
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rst;

    public Form_Dashboard() {
        initComponents();
        conn = Database.koneksiDB();
        init();
    }

    private void init() {
        
        updateTable();
        setCard3Info();
        
    
    }
    
    public void updateTable(){
        
        table.fixTable(jScrollPane1);
        
        try{
            
            String sql="SELECT * FROM `transaksi` ORDER BY Kode_Transaksi DESC;;";
            
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            
            ResultSetMetaData stData = rst.getMetaData();
            
            int i = stData.getColumnCount();
            
            int no = 1;
            int total = 0;
            
            DefaultTableModel recordTable = (DefaultTableModel)table.getModel();
            
            recordTable.setRowCount(0);
            
            while(rst.next()){
                
                Vector columnData = new Vector();
                
                for (int j = 0; j < i; j++) {
                    
                   columnData.add("    "+no);
                   columnData.add(rst.getString("Kode_Transaksi"));
                   columnData.add(rst.getString("Kode_Detail"));
                   columnData.add(rst.getString("Tanggal"));
                   columnData.add(rst.getString("Total"));
                   
                    
                }
                total = total + Integer.parseInt(rst.getString("Total"));
                recordTable.addRow(columnData);
                no++;
                
            }
            
            no--;
            
            card1.setData(new ModelCard(null, null, null,CurrencyFormatID.getCurrency(total),"Pemasukan Bulan "+CurrentDate.getNameMonth()));
            card2.setData(new ModelCard(null, null, null,""+no,"Total transaksi bulan "+CurrentDate.getNameMonth()));
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan data");
        } 
        
    }
    
    public void setCard3Info(){
//        SELECT detail_barang.Jumlah from transaksi, detail_barang where transaksi.Kode_Detail = detail_barang.Kode_Detail and transaksi.Tanggal >= '2023-07-01'
        try{
            String sql="SELECT detail_barang.Jumlah from transaksi, detail_barang where transaksi.Kode_Detail = detail_barang.Kode_Detail and transaksi.Tanggal >= '"+CurrentDate.getYear()+"-"+CurrentDate.getMonth()+"-01'";
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            
            int stok = 0;
            
            while(rst.next()){
                stok = stok + Integer.parseInt(rst.getString("Jumlah"));
            }
            
            card3.setData(new ModelCard(null, null, null,""+stok,"Barang tejual bulan "+CurrentDate.getNameMonth()));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan Card2");
        }
            
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new javaswingdev.card.Card();
        card2 = new javaswingdev.card.Card();
        card3 = new javaswingdev.card.Card();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();

        setOpaque(false);

        card2.setColor1(new java.awt.Color(254, 163, 2));
        card2.setColor2(new java.awt.Color(254, 163, 2));
        card2.setIcon(javaswingdev.GoogleMaterialDesignIcon.PIE_CHART);

        card3.setColor1(new java.awt.Color(40, 167, 69));
        card3.setColor2(new java.awt.Color(40, 167, 69));
        card3.setIcon(javaswingdev.GoogleMaterialDesignIcon.RING_VOLUME);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "   No", "Kode Transaksi", "Kode Detail Barang", "Tanggal", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(5);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(card2, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(card3, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.card.Card card1;
    private javaswingdev.card.Card card2;
    private javaswingdev.card.Card card3;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    public javaswingdev.swing.table.Table table;
    // End of variables declaration//GEN-END:variables
}
