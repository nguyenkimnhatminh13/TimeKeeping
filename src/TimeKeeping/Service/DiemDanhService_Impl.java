/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Service;

import TimeKeeping.DBC.DiemDanhDB;
import TimeKeeping.DBC.DiemDanhDB_Impl;
import TimeKeeping.Model.DiemDanh;
import java.util.List;

/**
 *
 * @author Ad
 */
public class DiemDanhService_Impl implements DiemDanhService{
    private DiemDanhDB ddDB = null;
    
    public DiemDanhService_Impl(){
        ddDB = new DiemDanhDB_Impl();
    }
       
    @Override
    public List<DiemDanh> getList() {
        return ddDB.getList();
    }
    @Override
    public int createOrUpdate(DiemDanh dd) {
        return ddDB.createOrUpdate(dd);
    }
}
