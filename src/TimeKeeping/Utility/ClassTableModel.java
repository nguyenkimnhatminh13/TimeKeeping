/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Utility;

import TimeKeeping.Model.DiemDanh;
import TimeKeeping.Model.NhanVien;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ad
 */
public class ClassTableModel {
    public DefaultTableModel setTableNhanVien(List<NhanVien> listItem,String[] listColumn){
        DefaultTableModel dtm = new DefaultTableModel(){
            public boolean isTable(int row,int column){
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0){
            for(int i = 0;i < rows;i++){
                NhanVien nv = listItem.get(i);
                obj = new Object[columns];
                obj[0] = nv.getIdNhanVien();
                obj[1] = nv.getHoTen();
                obj[2] = nv.isGioiTinh() == true? "Nam":"Nữ";
                obj[3] = nv.getNgaySinh();
                obj[4] = nv.getSoDienThoai();
                obj[5] = nv.getEmail();
                obj[6] = nv.getDiaChi();
                
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
    
    public DefaultTableModel setTableDiemDanh(List<DiemDanh> listItem,String[] listColumn){
        DefaultTableModel dtm = new DefaultTableModel(){
            public boolean isTable(int row,int column){
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0){
            for(int i = 0;i < rows;i++){
                DiemDanh dd = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = dd.getNgay();
                obj[1] = dd.getId();
                obj[2] = dd.getGio();

                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
