/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author mac
 */

import java.util.Scanner;


public class DateValidation {
    
    public static boolean getDateValidation(){
        
        String[] namaBulan = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        };

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan Tanggal: ");
        int tanggal = scanner.nextInt();
        System.out.print("Masukkan Bulan: ");
        int bulan = scanner.nextInt();
        System.out.print("Masukkan Tahun: ");
        int tahun = scanner.nextInt();
        int kabisat = tahun % 4;

        int hari;
        String bln;

        if (bulan % 2 == 0) {
            hari = 30;
            bln = namaBulan[bulan - 1];

            // Tahun Kabisat
            if (kabisat == 0) {
                hari = 29;
                bln = namaBulan[bulan - 1];
            } else {
                hari = 28;
                bln = namaBulan[bulan - 1];
            }
        } else {
            hari = 31;
            bln = namaBulan[bulan - 1];
        }

        boolean bTanggal = tanggal >= 1 && tanggal <= hari;
        boolean bBulan = bulan >= 1 && bulan <= 12;
        
        boolean bValid = bTanggal && bBulan;

        return bValid;

       
        
    }
    
    
}

