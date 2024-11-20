    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author mac
 */
public class CurrentDate {
    
    public static void main(String[] args) {
        System.out.println(getMonth());
        System.out.println(getYear());
        System.out.println(getNameMonth());
    }
    
    
    
    public static String getDate(){
        
        Date date = new Date();
        
        String dayS;
        String monthS;
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Indonesian"));
        cal.setTime(date);
        
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        if(day < 10){
            dayS = "0"+day;
        }else{
            dayS = ""+day;
        }
            
        
        if(month < 10){
            monthS = "0"+month;
        }else{
            monthS = ""+month;
        }
            
        
        String tanggal = year + "-" + monthS + "-" +dayS;
        
        return tanggal;
    
    }
    
    public static String getYear(){
        Date date = new Date();
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Indonesian"));
        cal.setTime(date);
        
        int year = cal.get(Calendar.YEAR);
            
        
        String tahun = year+"";
        
        return tahun;
    }
    
    public static String getMonth(){
        Date date = new Date();
        
        String monthS;
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Indonesian"));
        cal.setTime(date);
        

        int month = cal.get(Calendar.MONTH) + 1;
        
        if(month < 10){
            monthS = "0"+month;
        }else{
            monthS = ""+month;
        }
            
        
        String bulan = monthS+"";
        
        return bulan;
    }
    
    public static String getNameMonth(){
        
        Date date = new Date();
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Indonesian"));
        cal.setTime(date);
        

        int month = cal.get(Calendar.MONTH);
        
        String[]namaBulan = {"Januari", "Febuari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "October", "November", "Desember"};
        
        
        return namaBulan[month];
        
    }
        
    
}
