/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.DBC;

import TimeKeeping.Model.NhanVien;
import java.util.List;

/**
 *
 * @author Ad
 */
public interface NhanVienDB {
    public List<NhanVien> getList();
    
     public int createOrUpdate(NhanVien nv);
}
