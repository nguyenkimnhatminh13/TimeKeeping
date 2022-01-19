/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ad
 */
public class DBConnect {
    public static Connection GetConnection(){
        Connection cons = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cons = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nhanvien","root","nhatminh");
            return cons;
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return null;
    }
    
//    public static void main(String[] args) throws SQLException{
//        Connection c = GetConnection();
//        System.err.println(c.toString());
//        c.close();
//    }
}
