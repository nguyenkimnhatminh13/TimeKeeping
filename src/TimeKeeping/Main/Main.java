/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TimeKeeping.Main;

import TimeKeeping.View.MainView;

/**
 *
 * @author Ad
 */
public class Main {
    public static void main(String[] args) {
        MainView mv = new MainView();
        mv.pack();
        mv.setLocationRelativeTo(null);
        mv.setVisible(true);
        mv.setResizable(false);
    }
}
