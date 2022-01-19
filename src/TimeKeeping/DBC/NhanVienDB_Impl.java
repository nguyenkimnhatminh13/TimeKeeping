/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.DBC;

import TimeKeeping.Model.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Ad
 */
public class NhanVienDB_Impl implements NhanVienDB{

    @Override
    public List<NhanVien> getList() {
        Connection cons = DBConnect.GetConnection();
        String sql = "SELECT * FROM nhanvien";
        List<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdNhanVien(rs.getInt("idnhanvien"));
                nv.setHoTen(rs.getString("hoten"));
                nv.setGioiTinh(rs.getBoolean("gioitinh"));
                nv.setNgaySinh(rs.getDate("ngaysinh"));
                nv.setSoDienThoai(rs.getString("sdt"));
                nv.setDiaChi(rs.getString("diachi"));
                nv.setEmail(rs.getString("email"));
                
                list.add(nv);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public int createOrUpdate(NhanVien nv) {
        try {
            Connection cons = DBConnect.GetConnection();
            String sql = "INSERT INTO nhanvien(idnhanvien, hoten, gioitinh, ngaysinh, sdt, email, diachi) VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE idnhanvien = VALUES(idnhanvien), hoten = VALUES(hoten), gioitinh = VALUES(gioitinh), ngaysinh = VALUES(ngaysinh), sdt = VALUES(sdt), email = VALUES(email), diachi = VALUES(diachi);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, nv.getIdNhanVien());
            ps.setString(2, nv.getHoTen());
            ps.setBoolean(3, nv.isGioiTinh());
            ps.setDate(4, (Date) nv.getNgaySinh());
            ps.setString(5, nv.getSoDienThoai());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getDiaChi());
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
    
    public static void main(String[] args) {
        NhanVienDB nhanvien = new NhanVienDB_Impl();
        System.out.println(nhanvien.getList());
    }
}
