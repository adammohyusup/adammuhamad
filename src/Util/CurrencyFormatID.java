/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author mac
 */
public class CurrencyFormatID {
    
    public static void main(String[] args) {
        System.out.println(getCurrency(250000));
    }
    
    
    public static String getCurrency(double harga){

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        
        return ""+kursIndonesia.format(harga);
        
        
    }

    
}
