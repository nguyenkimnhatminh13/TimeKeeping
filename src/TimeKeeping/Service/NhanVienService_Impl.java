/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Service;

import TimeKeeping.DBC.NhanVienDB;
import TimeKeeping.DBC.NhanVienDB_Impl;
import TimeKeeping.Model.NhanVien;
import java.util.List;

/**
 *
 * @author Ad
 */
public class NhanVienService_Impl implements NhanVienService{
    
    private NhanVienDB nvDB = null;
    public NhanVienService_Impl(){
        nvDB = new NhanVienDB_Impl();
    }
    
    
    @Override
    public List<NhanVien> getList() {
        return nvDB.getList();
    }
    @Override
    public int createOrUpdate(NhanVien nv) {
        return nvDB.createOrUpdate(nv);
    }
}
