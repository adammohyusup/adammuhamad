/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author mac
 */
public class CreatePdf {
    
    public static void main(String[] args) {

    }
    
   
    
    public static void cetakTransaksi(String kode_transaksi, String tanggalTransaksi, String tunai,int jumlahBarang, String totalHarga, String totalKembalian, String[]dataBarang, String[]harga, String[]jumlah, String[]total_harga_barang){
        
        Document document = new Document();
        
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(kode_transaksi+".pdf"));

            document.open();

            Paragraph title = new Paragraph("SOLASTA\n\n");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            
            document.add(title);
            
            Paragraph tempat = new Paragraph("Jl. Mana aja RT 07 RW 03, Ciwaruga Wetan\nKec Apa, Kota Bandung");
            tempat.setAlignment(Paragraph.ALIGN_CENTER);
            
            document.add(tempat);
            
            Paragraph noTelp = new Paragraph("08123456789");
            noTelp.setAlignment(Paragraph.ALIGN_CENTER);
            
            document.add(noTelp);
            
            Paragraph info = new Paragraph("\n\n====================================\nKode Transaksi : \t\t"+kode_transaksi+
                    "\nTanggal Pembelian : \t\t"+tanggalTransaksi+
                    "\nJumlah barang : \t\t"+jumlahBarang+
                    "\n====================================\n\n\n"
                   );

            document.add(info);
            
            
            
            
            
            
            PdfPTable table = new PdfPTable(5);

            PdfPCell c1 = new PdfPCell(new Phrase("No"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Nama Barang"));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Jumlah"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Harga"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Total"));
            table.addCell(c1);
            
            table.setHeaderRows(1);
                
                
            for (int i = 0; i < dataBarang.length; i++) {
                
                table.addCell(""+(i+1)+".");
                table.addCell(dataBarang[i]);
                table.addCell(jumlah[i]);
                table.addCell(harga[i]);
                table.addCell(total_harga_barang[i]);
                
                
            }
            
            document.add(table);
            
            Paragraph total = new Paragraph("\n\nTotal harga : "+totalHarga);
            
            total.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(total);
            
            Paragraph uang = new Paragraph("Tunai : "+ CurrencyFormatID.getCurrency(Double.parseDouble(tunai))+"\n-------------------");
            uang.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(uang);
            
            Paragraph kembali = new Paragraph("Kembali : "+totalKembalian);
            kembali.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(kembali);
            
            
            
                
                document.close();
                writer.close();
            
        } catch (DocumentException e) {
            
               JOptionPane.showMessageDialog(null,"Tidak bisa Membuat data");
        
        } catch (FileNotFoundException e) {
        
            e.printStackTrace();
        
        }

        
    }
    
}
