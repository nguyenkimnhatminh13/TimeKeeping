/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.DBC;

import TimeKeeping.Model.DiemDanh;
import java.util.List;

/**
 *
 * @author Ad
 */
public interface DiemDanhDB {
    public List<DiemDanh> getList();
    public int createOrUpdate(DiemDanh dd);
}
