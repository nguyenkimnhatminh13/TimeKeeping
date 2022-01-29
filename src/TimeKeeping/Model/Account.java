/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Model;
//package TimeKeeping.DBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import TimeKeeping.Controller.MD5;
import TimeKeeping.DBC.DBConnect;

/**
 *
 * @author admin
 */
public class Account {
    private String username;
    private String password;
    public Account(String u, String p)
    {
        this.username=u;
        this.password=p;
    }
    public Account(){}
    public boolean check()
    {
        String sql="SELECT * FROM account WHERE username = '"+this.username+ "' AND password = '" +MD5.getMd5(this.password)+"'";
        System.out.println(sql);
        Connection cons= DBConnect.GetConnection();
        boolean c=false;
        try
        {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            if (rs.next())
            {
                c = true;
            }
            else
            {
                c=false;
            }
        }
        catch (Exception e){System.err.println(e);}
        return c;
    }
}
