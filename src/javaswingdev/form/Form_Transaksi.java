package javaswingdev.form;

import Util.CreatePdf;
import static Util.CreatePdf.cetakTransaksi;
import Util.CurrencyFormatID;
import Util.CurrentDate;
import Util.Database;
import static Util.Login.levels;
import static Util.Login.nama;
import static Util.Login.username;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javaswingdev.card.ModelCard;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.main.Main;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Form_Transaksi extends javax.swing.JPanel {

    Connection conn;
    PreparedStatement pst;
    ResultSet rst;

    String KodeTransaksi, kodeBarang;
    String[] namaBarang;
    String[] hargaTotBarang;
    String[] jumlahBeli;
    int stok, iHarga, subTotalBarang, stokBarang, subtotal, dJumlah;

    public Form_Transaksi() {

        initComponents();
        conn = Database.koneksiDB();
        init();

    }

    private void init() {

        updateTableBarang("");
        updateTableDetail();
        KodeTransaksi = generatorKodeTransaksi();

    }

    public void updateTableBarang(String search) {

        tableBarang.fixTable(jScrollPane1);

        try {

            String sql = "SELECT * FROM `barang` WHERE Nama_Barang LIKE '" + search + "%'";

            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();

            ResultSetMetaData stData = rst.getMetaData();

            int i = stData.getColumnCount();

            int no = 1;

            DefaultTableModel recordTable = (DefaultTableModel) tableBarang.getModel();

            recordTable.setRowCount(0);

            while (rst.next()) {

                Vector columnData = new Vector();

                for (int j = 0; j < i; j++) {

                    columnData.add("    "+no);
                    columnData.add(rst.getString("Kode_Barang"));
                    columnData.add(rst.getString("Nama_Barang"));
                    columnData.add(rst.getString("Stok"));
                    columnData.add("Rp " + rst.getString("Harga"));

                }

                recordTable.addRow(columnData);
                no++;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan data");
        }

    }

    public void updateTableDetail() {

        tableDetail.fixTable(jScrollPane2);

        try {

            String KD = "D" + KodeTransaksi;

            String sql = "select * from detail_barang where Kode_Detail='" + KD + "'";

            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();

            ResultSetMetaData stData = rst.getMetaData();

            int i = stData.getColumnCount();

            int no = 1;

            DefaultTableModel recordTable = (DefaultTableModel) tableDetail.getModel();

            recordTable.setRowCount(0);

            while (rst.next()) {

                Vector columnData = new Vector();

                for (int j = 0; j < i; j++) {

                    columnData.add("    "+no);
                    columnData.add(rst.getString("Kode_Detail"));
                    columnData.add(rst.getString("Kode_Barang"));
                    columnData.add("Rp " + rst.getString("Harga"));
                    columnData.add(rst.getString("Jumlah"));
                    columnData.add(rst.getString("Discount") + "%");
                    columnData.add("Rp " + rst.getString("Subtotal"));

                }

                recordTable.addRow(columnData);
                no++;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa menampilkan detail barang");
        }

    }

    public void hitungSubtotalBarang() {

        double diskon, jumlah;
        double diskonDouble;

        if (txt_diskon.getText().equals("") || txt_diskon.getText().equals("Diskon Barang")) {
            diskon = 0;
        } else {
            diskon = Double.parseDouble(txt_diskon.getText());
        }

        jumlah = Double.parseDouble(txt_jumlah.getText());

        diskonDouble = (jumlah * iHarga) * (diskon / 100);

        subTotalBarang = ((int) jumlah * (int) iHarga) - (int) diskonDouble;
    }

    public void kurangiStokBarang() {

        if (Integer.parseInt(txt_jumlah.getText()) > stokBarang) {

            JOptionPane.showMessageDialog(null, "Stok barang kurang");
            txt_jumlah.setText("1");

        } else {

            stokBarang = stokBarang - Integer.parseInt(txt_jumlah.getText());

        }

    }

    public void getStok() {
        try {

            String sql = "SELECT Stok FROM barang WHERE Kode_Barang = ?";

            pst = conn.prepareStatement(sql);

            pst.setString(1, kodeBarang);

            rst = pst.executeQuery();

            stokBarang = Integer.parseInt(rst.getString("Stok"));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Tidak bisa mengambil stok");

        }
    }

    public void tambahStok() {
        try {

            stokBarang = stokBarang + dJumlah;

            updateBarang();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa tambah stok");
        }
    }

    public String generatorKodeTransaksi() {

        try {

            String sql = "SELECT MAX(RIGHT(Kode_Transaksi,3)) AS NO FROM transaksi";

            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();

            if (!rst.next()) {
                return "TRX001";
            } else {
                int auto_id = rst.getInt(1) + 1;

                String no = String.valueOf(auto_id);

                int nomor_jual = no.length();

                for (int i = 0; i < 3 - nomor_jual; i++) {
                    no = "0" + no;
                }

                return "TRX" + no;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa Membuat kode transaksi");
            return null;
        }

    }

    public void updateBarang() {
        try {
            String sql = "update barang set Stok='" + stokBarang + "' where Kode_Barang='" + kodeBarang + "'";

            pst = conn.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa mengurangi stok barang");
        }

    }

    public void sum() {

        int subtotalSum;
        int total_bayar = 0;

        DefaultTableModel dataModel = (DefaultTableModel) tableDetail.getModel();

        int jumlah = tableDetail.getRowCount();

        for (int i = 0; i < jumlah; i++) {

            subtotalSum = Integer.parseInt(dataModel.getValueAt(i, 6).toString().substring(3));

            total_bayar += subtotalSum;

        }

        if (jumlah == 0) {
            txt_subtotal.setText("Masukan Kode Barang");
            txt_subtotal.setForeground(new Color(153, 153, 153));
        } else {
            subtotal = total_bayar;
            txt_subtotal.setText(CurrencyFormatID.getCurrency(total_bayar));
        }

    }

    public void Bayar() {

        try {

            if (Integer.parseInt(txt_bayar.getText()) - subtotal >= 0) {

                String sql = "INSERT INTO `transaksi`(`Kode_Transaksi`, `Kode_Detail`, `Tanggal`, `Total`) VALUES (?,?,?,?)";

                pst = conn.prepareStatement(sql);

                pst.setString(1, KodeTransaksi);
                pst.setString(2, "D" + KodeTransaksi);
                pst.setString(3, CurrentDate.getDate());
                pst.setString(4, String.valueOf(subtotal));

                pst.execute();

                txt_kembalian.setText(CurrencyFormatID.getCurrency(Integer.parseInt(txt_bayar.getText()) - subtotal));

                JOptionPane.showMessageDialog(null, "Transaksi Berhasil, Struk telah di download");

                cetakTransaksi();

            } else {

                JOptionPane.showMessageDialog(null, "Transaksi Gagal Uang pembayaran kurang");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Transaksi Gagal");
        }

    }

    public void hapusDetail() {

        try {

            String sql = "DELETE FROM `detail_barang` WHERE Kode_Barang = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, kodeBarang);

            pst.execute();
            updateTableDetail();
            updateTableBarang("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hapus Barang Detail Tidak Bisa");
        }

    }

    public void cetakTransaksi() {

        try {

            int total = getTotalData();

            if (total == 0) {

                JOptionPane.showMessageDialog(null, "Tidak ada barang");

            } else {

                String[] dataBarang = new String[getTotalData()];
                String[] jumlahB = new String[getTotalData()];
                String[] harga = new String[getTotalData()];
                String[] total_harga_barang = new String[getTotalData()];

                String kd = "D" + KodeTransaksi;

                String sql = "SELECT barang.Nama_Barang as 'Nama_Barang',detail_barang.Harga, detail_barang.Jumlah,detail_barang.Subtotal FROM barang, detail_barang WHERE detail_barang.Kode_Detail = '" + kd + "' and detail_barang.Kode_Barang = barang.Kode_Barang;";

                pst = conn.prepareStatement(sql);

                rst = pst.executeQuery();

                int jumlahH = 0;

                int i = 0;

                while (rst.next()) {

                    jumlahH = jumlahH + Integer.parseInt(rst.getString("Jumlah"));

                    dataBarang[i] = rst.getString("Nama_Barang");
                    harga[i] = rst.getString("Harga");
                    jumlahB[i] = rst.getString("Jumlah");
                    total_harga_barang[i] = rst.getString("Subtotal");

                    i++;

                }

                CreatePdf.cetakTransaksi(KodeTransaksi, CurrentDate.getDate(), txt_bayar.getText(), jumlahH, txt_subtotal.getText(), txt_kembalian.getText(), dataBarang, harga, jumlahB, total_harga_barang);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa cetak transaksi");
        }

    }

    public void clearText() {
        txt_barang.setText("Nama Barang");
        txt_barang.setForeground(new Color(153, 153, 153));
        txt_jumlah.setText("Jumlah Barang");
        txt_jumlah.setForeground(new Color(153, 153, 153));
        txt_diskon.setText("Diskon Barang");
        txt_diskon.setForeground(new Color(153, 153,153 ));
    }

    public int getTotalData() {
        try {

            String sql = "SELECT COUNT(*) AS total FROM detail_barang WHERE Kode_Detail= 'D" + KodeTransaksi + "'";

            pst = conn.prepareStatement(sql);

            rst = pst.executeQuery();

            int totalData = 0;

            if (rst.next()) {

                totalData = rst.getInt("total");

            }

            return totalData;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa mendapatkan total");
            return 0;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel2 = new javaswingdev.swing.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        roundPanel3 = new javaswingdev.swing.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_barang = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_diskon = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        btn_save = new rojerusan.RSMaterialButtonRectangle();
        roundPanel5 = new javaswingdev.swing.RoundPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_transaksi = new javax.swing.JTextField();
        txt_bayar = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javaswingdev.swing.table.Table();
        roundPanel4 = new javaswingdev.swing.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetail = new javaswingdev.swing.table.Table();
        btn_delete_detail = new rojerusan.RSMaterialButtonRectangle();
        roundPanel6 = new javaswingdev.swing.RoundPanel();
        jLabel22 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        btn_bayar1 = new rojerusan.RSMaterialButtonRectangle();

        setOpaque(false);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel2.setRound(10);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Magnifier.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel14.setText("Cari Barang");

        txt_cari.setForeground(new java.awt.Color(153, 153, 153));
        txt_cari.setText("Cari Barang");
        txt_cari.setBorder(null);
        txt_cari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariFocusLost(evt);
            }
        });
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel3.setRound(10);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Key.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel15.setText("Barang");

        txt_barang.setEditable(false);
        txt_barang.setBackground(new java.awt.Color(255, 255, 255));
        txt_barang.setForeground(new java.awt.Color(153, 153, 153));
        txt_barang.setText("Nama Barang");
        txt_barang.setBorder(null);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/transaksi.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel16.setText("Jumlah");

        txt_jumlah.setForeground(new java.awt.Color(153, 153, 153));
        txt_jumlah.setText("Jumlah Barang");
        txt_jumlah.setBorder(null);
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/perecent.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel17.setText("Diskon");

        txt_diskon.setForeground(new java.awt.Color(153, 153, 153));
        txt_diskon.setText("Diskon Barang");
        txt_diskon.setBorder(null);
        txt_diskon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_diskonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_diskonFocusLost(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(20, 123, 254));
        btn_save.setText("Tambah");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(roundPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(roundPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(roundPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(17, 17, 17))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txt_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(12, 12, 12)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(12, 12, 12)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txt_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel5.setRound(10);

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel18.setText("Kode Transaksi     ");

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel21.setText("Uang Bayar  ");

        txt_transaksi.setEditable(false);
        txt_transaksi.setBackground(new java.awt.Color(255, 255, 255));
        txt_transaksi.setForeground(new java.awt.Color(153, 153, 153));
        txt_transaksi.setText("Kode Transaksi");
        txt_transaksi.setBorder(null);

        txt_bayar.setForeground(new java.awt.Color(153, 153, 153));
        txt_bayar.setText("Masukan Uang Bayar");
        txt_bayar.setBorder(null);
        txt_bayar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_bayarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bayarFocusLost(evt);
            }
        });
        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel23.setText("Sub Total ");

        txt_subtotal.setEditable(false);
        txt_subtotal.setBackground(new java.awt.Color(255, 255, 255));
        txt_subtotal.setForeground(new java.awt.Color(153, 153, 153));
        txt_subtotal.setText("Sub Total");
        txt_subtotal.setBorder(null);
        txt_subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_subtotalActionPerformed(evt);
            }
        });

        jLabel1.setText("Rp");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "   No", "Kode Barang", "Nama Barang", "Stok", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBarang.setFocusable(false);
        tableBarang.setGridColor(new java.awt.Color(255, 255, 255));
        tableBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBarang);
        if (tableBarang.getColumnModel().getColumnCount() > 0) {
            tableBarang.getColumnModel().getColumn(0).setResizable(false);
            tableBarang.getColumnModel().getColumn(0).setPreferredWidth(4);
            tableBarang.getColumnModel().getColumn(1).setResizable(false);
            tableBarang.getColumnModel().getColumn(2).setResizable(false);
            tableBarang.getColumnModel().getColumn(3).setResizable(false);
            tableBarang.getColumnModel().getColumn(4).setResizable(false);
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel4.setRound(10);

        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "   No", "Kode Detail", "Kode Barang", "Harga", "Jumlah", "Discount", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDetail.setFocusable(false);
        tableDetail.setGridColor(new java.awt.Color(255, 255, 255));
        tableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableDetailMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableDetail);
        if (tableDetail.getColumnModel().getColumnCount() > 0) {
            tableDetail.getColumnModel().getColumn(0).setResizable(false);
            tableDetail.getColumnModel().getColumn(0).setPreferredWidth(3);
            tableDetail.getColumnModel().getColumn(1).setResizable(false);
            tableDetail.getColumnModel().getColumn(1).setPreferredWidth(20);
            tableDetail.getColumnModel().getColumn(2).setResizable(false);
            tableDetail.getColumnModel().getColumn(3).setResizable(false);
            tableDetail.getColumnModel().getColumn(3).setPreferredWidth(6);
            tableDetail.getColumnModel().getColumn(4).setResizable(false);
            tableDetail.getColumnModel().getColumn(4).setPreferredWidth(12);
            tableDetail.getColumnModel().getColumn(5).setResizable(false);
            tableDetail.getColumnModel().getColumn(5).setPreferredWidth(5);
            tableDetail.getColumnModel().getColumn(6).setResizable(false);
        }

        btn_delete_detail.setBackground(new java.awt.Color(252, 0, 58));
        btn_delete_detail.setText("Delete");
        btn_delete_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_detailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_delete_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_delete_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel6.setRound(10);

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel22.setText("Uang Kembalian");

        txt_kembalian.setEditable(false);
        txt_kembalian.setBackground(new java.awt.Color(255, 255, 255));
        txt_kembalian.setForeground(new java.awt.Color(153, 153, 153));
        txt_kembalian.setText("Uang Kembalian");
        txt_kembalian.setBorder(null);
        txt_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kembalianActionPerformed(evt);
            }
        });

        btn_bayar1.setBackground(new java.awt.Color(20, 123, 254));
        btn_bayar1.setText("Bayar");
        btn_bayar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bayar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(btn_bayar1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bayar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                        .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariFocusGained
        if (txt_cari.getText().equals("Cari Barang")) {
            txt_cari.setText("");
            txt_cari.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_cariFocusGained

    private void txt_cariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariFocusLost
        if (txt_cari.getText().equals("")) {
            txt_cari.setText("Cari Barang");
            txt_cari.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_cariFocusLost

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:

        if (txt_barang.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Pilih Barang !");

        } else if (Integer.parseInt(txt_jumlah.getText()) > stokBarang) {

            JOptionPane.showMessageDialog(null, "Stok kurang");

        } else {

            hitungSubtotalBarang();
            kurangiStokBarang();

            try {

                String diskon, jumlah_barang;

                if (txt_diskon.getText().equals("Diskon Barang")) {
                    diskon = "0";
                } else {
                    diskon = txt_diskon.getText();
                }

                String kode_detail = "D" + generatorKodeTransaksi();
                jumlah_barang = txt_jumlah.getText();

                String sql = "INSERT INTO `detail_barang`(`Kode_Detail`, `Kode_Barang`, `Harga`, `Jumlah`, `Discount`, `Subtotal`) VALUES (?,?,?,?,?,?)";

                pst = conn.prepareStatement(sql);

                pst.setString(1, kode_detail);
                pst.setString(2, kodeBarang);
                pst.setString(3, String.valueOf(iHarga));
                pst.setString(4, txt_jumlah.getText());
                pst.setString(5, diskon);
                pst.setString(6, String.valueOf(subTotalBarang));

                pst.execute();

                updateBarang();

                updateTableBarang("");

                updateTableDetail();
                
                

                txt_transaksi.setText(KodeTransaksi);

                sum();
                
                clearText();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak bisa menambah barang ");
            }

        }


    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_delete_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_detailActionPerformed
        // TODO add your handling code here:
        tambahStok();
        hapusDetail();
        sum();
        kodeBarang = "";


    }//GEN-LAST:event_btn_delete_detailActionPerformed

    private void txt_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kembalianActionPerformed

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        updateTableBarang(txt_cari.getText());
    }//GEN-LAST:event_txt_cariKeyReleased

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:

        DefaultTableModel recordTable = (DefaultTableModel) tableBarang.getModel();
        int selectedRow = tableBarang.getSelectedRow();

        if (Integer.valueOf(recordTable.getValueAt(selectedRow, 3).toString()) == 0) {

            JOptionPane.showMessageDialog(null, "Stok Habis");

        } else {
            txt_barang.setText(recordTable.getValueAt(selectedRow, 2).toString());
            txt_barang.setForeground(new Color(0, 0, 0));

            txt_jumlah.setText("1");
            txt_jumlah.setForeground(new Color(0, 0, 0));

            kodeBarang = recordTable.getValueAt(selectedRow, 1).toString();
            iHarga = Integer.parseInt(recordTable.getValueAt(selectedRow, 4).toString().substring(3));
            stokBarang = Integer.parseInt(recordTable.getValueAt(selectedRow, 3).toString());
        }


    }//GEN-LAST:event_tableBarangMouseClicked

    private void txt_diskonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_diskonFocusGained
        // TODO add your handling code here:
        if (txt_diskon.getText().equals("Diskon Barang")) {
            txt_diskon.setText("");
            txt_diskon.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_diskonFocusGained

    private void txt_diskonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_diskonFocusLost
        // TODO add your handling code here:
        if (txt_diskon.getText().equals("")) {
            txt_diskon.setText("Diskon Barang");
            txt_diskon.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_diskonFocusLost

    private void txt_subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_subtotalActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_bayarKeyReleased

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void txt_bayarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bayarFocusLost
        // TODO add your handling code here:
        if (txt_bayar.getText().equals("")) {
            txt_bayar.setText("Masukan Uang Bayar");
            txt_bayar.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_bayarFocusLost

    private void txt_bayarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bayarFocusGained
        // TODO add your handling code here:
        if (txt_bayar.getText().equals("Masukan Uang Bayar")) {
            txt_bayar.setText("");
            txt_bayar.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_bayarFocusGained

    private void btn_bayar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bayar1ActionPerformed
        // TODO add your handling code here:
        Bayar();
        KodeTransaksi = generatorKodeTransaksi();
        updateTableDetail();

        txt_diskon.setText("Diskon Barang");
        txt_diskon.setForeground(new Color(153, 153, 153));

        txt_bayar.setText("Masukan Uang Bayar");
        txt_bayar.setForeground(new Color(153, 153, 153));

        txt_subtotal.setText("Sub Total");
        txt_subtotal.setForeground(new Color(153, 153, 153));

        txt_kembalian.setText("Uang Kembalian");
        txt_kembalian.setForeground(new Color(153, 153, 153));

        txt_transaksi.setText("Kode Transaksi");
        txt_transaksi.setForeground(new Color(153, 153, 153));

        txt_barang.setText("Nama Barang");
        txt_barang.setForeground(new Color(153, 153, 153));

        txt_jumlah.setText("Jumlah Barang");
        txt_jumlah.setForeground(new Color(153, 153, 153));

    }//GEN-LAST:event_btn_bayar1ActionPerformed

    private void tableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailMouseClicked

    }//GEN-LAST:event_tableDetailMouseClicked

    private void tableDetailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailMousePressed
        // TODO add your handling code here:

        DefaultTableModel recordTable = (DefaultTableModel) tableDetail.getModel();
        int selectedRow = tableDetail.getSelectedRow();

        try {

            String sql = "SELECT * FROM `detail_barang` WHERE Kode_Barang=? and Kode_Detail=?";

            pst = conn.prepareStatement(sql);

            pst.setString(1, recordTable.getValueAt(selectedRow, 2).toString());
            pst.setString(2, "D" + KodeTransaksi);

            rst = pst.executeQuery();

            if (rst.next()) {
                kodeBarang = recordTable.getValueAt(selectedRow, 2).toString();
                dJumlah = Integer.valueOf(rst.getString("Jumlah"));

                System.out.println(kodeBarang);
                System.out.println(dJumlah);

            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada barang");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak bisa klik");
        }


    }//GEN-LAST:event_tableDetailMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btn_bayar1;
    private rojerusan.RSMaterialButtonRectangle btn_delete_detail;
    private rojerusan.RSMaterialButtonRectangle btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.RoundPanel roundPanel2;
    private javaswingdev.swing.RoundPanel roundPanel3;
    private javaswingdev.swing.RoundPanel roundPanel4;
    private javaswingdev.swing.RoundPanel roundPanel5;
    private javaswingdev.swing.RoundPanel roundPanel6;
    private javaswingdev.swing.table.Table tableBarang;
    private javaswingdev.swing.table.Table tableDetail;
    private javax.swing.JTextField txt_barang;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_diskon;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_transaksi;
    // End of variables declaration//GEN-END:variables
}
