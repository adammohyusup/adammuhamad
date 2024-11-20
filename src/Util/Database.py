/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author mac
 */
public class Database {
    
    Connection conn = null;
    
    public static Connection koneksiDB(){ 
     
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/solasta", "root", "");
            
            return conn;
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "Tidak Terkoneksi");
            System.exit(0);
            return null;
            
        }
        
    }
    
}
//OR 1=1