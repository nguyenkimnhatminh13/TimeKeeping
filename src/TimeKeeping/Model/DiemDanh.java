/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Model;

import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author Ad
 */
public class DiemDanh {
    private int id;
    private Date ngay;
    private Time gio;

    public int getId() {
        return id;
    }

    public Date getNgay() {
        return ngay;
    }
    
    public Time getGio() {
        return gio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
    
    public void setGio(Time gio) {
        this.gio = gio;
    }
}
