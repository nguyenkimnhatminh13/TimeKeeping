/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Controller;

import TimeKeeping.Model.NhanVien;
import TimeKeeping.Service.NhanVienService;
import TimeKeeping.Service.NhanVienService_Impl;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Ad
 */
public class ThemSuaNhanVienController {
    private JButton jbtSave;
    private JTextField jtfID;
    private JTextField jtfHoTen;
    private JRadioButton jrbNam;
    private JRadioButton jrbNu;
    private JDateChooser jdcNgaySinh;
    private JTextField jtfSoDienThoai; 
    private JTextField jtfEmail;
    private JTextArea jtaDiaChi;
    private JLabel jlbMsg;
    
    private NhanVien nv = null;
    private NhanVienService nvService = null;

    public ThemSuaNhanVienController(JButton jbtSave, JTextField jtfID, JTextField jtfHoTen, JRadioButton jrbNam, JRadioButton jrbNu, JDateChooser jdcNgaySinh, JTextField jtfSoDienThoai,JTextField jtfEmail ,JTextArea jtaDiaChi, JLabel jlbMsg) {
        this.jbtSave = jbtSave;
        this.jtfID = jtfID;
        this.jtfHoTen = jtfHoTen;
        this.jrbNam = jrbNam;
        this.jrbNu = jrbNu;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jtfSoDienThoai = jtfSoDienThoai;
        this.jtfEmail = jtfEmail;
        this.jtaDiaChi = jtaDiaChi;
        this.jlbMsg = jlbMsg;
        
        this.nvService = new NhanVienService_Impl();
    }
    
    public void setView(NhanVien nv) {
        this.nv = nv;
        // set data
        jtfID.setText("" + nv.getIdNhanVien());
        jtfHoTen.setText(nv.getHoTen());
        jdcNgaySinh.setDate(nv.getNgaySinh());
        if (nv.isGioiTinh()) {
            jrbNam.setSelected(true);
            jrbNu.setSelected(false);
        } else {
            jrbNam.setSelected(false);
            jrbNu.setSelected(true);
        }
        jtfSoDienThoai.setText(nv.getSoDienThoai());
        jtfEmail.setText(nv.getEmail());
        jtaDiaChi.setText(nv.getDiaChi());
        // set event
        setEvent();
    }
    
    public void setEvent() {
        jbtSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!checkNotNull()) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        String s = jtfID.getText();
                        int n = Integer.parseInt(s);
                        nv.setIdNhanVien(n);
                        
                        nv.setHoTen(jtfHoTen.getText().trim());
                        nv.setNgaySinh(covertDateToDateSql(jdcNgaySinh.getDate()));
                        nv.setSoDienThoai(jtfSoDienThoai.getText());
                        nv.setEmail(jtfEmail.getText());
                        nv.setDiaChi(jtaDiaChi.getText());
                        nv.setGioiTinh(jrbNam.isSelected());
                        
                        int lastId = nvService.createOrUpdate(nv);
                        System.out.println(lastId);
                            if (n > 0) {
                                //System.out.println(lastId);
                                nv.setIdNhanVien(n);
                                jtfID.setText("" + nv.getIdNhanVien());
                                jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");
                            } else {
                                jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                        }
                } catch (Exception ex) {
                    jlbMsg.setText(ex.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
    }
    
    private boolean checkNotNull() {
        return jtfHoTen.getText() != null && !jtfHoTen.getText().equalsIgnoreCase("");
    }
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
}
