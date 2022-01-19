/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.DBC;

import TimeKeeping.Model.DiemDanh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ad
 */
public class DiemDanhDB_Impl implements DiemDanhDB{

    @Override
    public List<DiemDanh> getList() {
        Connection cons = DBConnect.GetConnection();
        String sql = "SELECT * FROM diemdanh";
        List<DiemDanh> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiemDanh dd = new DiemDanh();
                dd.setNgay(rs.getDate("ngay"));
                
                dd.setId(rs.getInt("idnhanvien"));
                dd.setGio(rs.getTime("gio"));
                list.add(dd);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }  
    
    @Override
    public int createOrUpdate(DiemDanh dd) {
        try {
            Connection cons = DBConnect.GetConnection();
            String sql = "INSERT INTO diemdanh(ngay, idnhanvien, gio) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE ngay = VALUES(ngay), idnhanvien = VALUES(idnhanvien), gio = VALUES(gio);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setDate(2, dd.getNgay());
            ps.setInt(1, dd.getId());
            ps.setTime(3, dd.getGio());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
