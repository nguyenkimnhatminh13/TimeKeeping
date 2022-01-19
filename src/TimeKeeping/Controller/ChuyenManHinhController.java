/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Controller;

import TimeKeeping.Bean.DanhMucBean;
import TimeKeeping.View.ChamCongPanel;
import TimeKeeping.View.DangNhapPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ad
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    
    public void setView(JPanel jpnItem,JLabel jlbItem){
        kindSelected = "ChamCong";
        jpnItem.setBackground(new Color(139, 201, 253));
        //jlbItem.setBackground(Color.green);
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new ChamCongPanel());
        root.validate();
        root.repaint();
    }
  
    public void setEvent(List<DanhMucBean> listItemValue){
        this.listItem = listItemValue;
        for(DanhMucBean item : listItem){
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(),item.getJpn(),item.getJlb()));
            item.getJpn().addMouseListener(new LabelEvent(item.getKind(),item.getJpn(),item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener{
        private JPanel node;
        
        private String kind;        
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind){
                case "DangNhap":
                    node = new DangNhapPanel();
                    break;
                case "ChamCong":
                    node = new ChamCongPanel();
                    break;
                
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
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
    
    }
    private void setChangeBackground(String kind){
        for(DanhMucBean Item : listItem){
            if(Item.getKind().equalsIgnoreCase(kind)){
                Item.getJpn().setBackground(new Color(139, 201, 253));
            }
            else{
                Item.getJpn().setBackground(new Color(153,255,204));
            }
        }
    }
            
}
