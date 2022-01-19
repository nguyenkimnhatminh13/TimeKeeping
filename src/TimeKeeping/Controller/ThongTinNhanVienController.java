/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Controller;

import TimeKeeping.Model.NhanVien;
import TimeKeeping.Service.NhanVienService;
import TimeKeeping.Service.NhanVienService_Impl;
import TimeKeeping.Utility.ClassTableModel;
import TimeKeeping.View.QuanLyNhanVien;
import TimeKeeping.View.ThemNhanVien;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Ad
 */
public class ThongTinNhanVienController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    
    private NhanVienService nvService = null;
    
    private String[] listColmn = {"ID nhân Viên", "Họ Tên","Giới Tính","Ngày Sinh","Số Điện Thoại","Email","Địa Chỉ"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    public ThongTinNhanVienController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        
        this.nvService = new NhanVienService_Impl();
    }    
    public void setDateToTable(){
        List<NhanVien> listItem = nvService.getList();
        DefaultTableModel model = new ClassTableModel().setTableNhanVien(listItem, listColmn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length() == 0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.length() == 0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
             if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    NhanVien nv = new NhanVien();
                    nv.setIdNhanVien((int) model.getValueAt(selectedRowIndex, 0));
                    nv.setHoTen(model.getValueAt(selectedRowIndex, 1).toString());
                    nv.setGioiTinh(model.getValueAt(selectedRowIndex, 2).toString().equalsIgnoreCase("Nam"));
                    nv.setNgaySinh((Date)model.getValueAt(selectedRowIndex, 3));
                    
                    nv.setSoDienThoai(model.getValueAt(selectedRowIndex, 4) != null
                            ? model.getValueAt(selectedRowIndex, 4).toString() : null);
                    nv.setEmail(model.getValueAt(selectedRowIndex, 5) != null
                            ? model.getValueAt(selectedRowIndex, 5).toString() : null);
                    nv.setDiaChi(model.getValueAt(selectedRowIndex, 6) != null
                            ? model.getValueAt(selectedRowIndex, 6).toString() : null);
                    

                    ThemNhanVien frame = new ThemNhanVien(nv);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    
                    frame.setVisible(true);
             }
      }
        

});
        
        table.getTableHeader().setFont(new Font("Arrial",Font.BOLD,14));
        table.getTableHeader().setPreferredSize(new Dimension(100,50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300,400));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
        
    }
    
    public void setEvent(){
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                new ThemNhanVien(new NhanVien()).setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e){
                
            }
            @Override
            public void mouseExited(MouseEvent e){
                
            }
        });
    }
    
}
