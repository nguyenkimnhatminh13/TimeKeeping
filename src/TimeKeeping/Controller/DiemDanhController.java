/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Controller;

import TimeKeeping.Model.DiemDanh;
import TimeKeeping.Service.DiemDanhService;
import TimeKeeping.Service.DiemDanhService_Impl;
import TimeKeeping.Service.NhanVienService;
import TimeKeeping.Utility.ClassTableModel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JPanel;
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
public class DiemDanhController {
    private JTextField jtfSearch;
    private JPanel jpnView;
    
    private ClassTableModel classTableModel = null;

    private final String[] COLUMNS = {"Ngày", "ID Nhân Viên","Giờ"};
    
    private DiemDanhService diemdanhService = null;

    private TableRowSorter<TableModel> rowSorter = null;

    public DiemDanhController(JTextField jtfSearch, JPanel jpnView) {
        this.jtfSearch = jtfSearch;
        this.jpnView = jpnView;
        
        this.classTableModel = new ClassTableModel();
        this.diemdanhService = new DiemDanhService_Impl();
    }
    
    public void setDataToTable(){
        List<DiemDanh> listItem = diemdanhService.getList();
        DefaultTableModel model = classTableModel.setTableDiemDanh(listItem, COLUMNS);
        JTable table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }    
}
