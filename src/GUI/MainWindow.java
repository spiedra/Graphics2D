/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;

/**
 *
 * @author spiedra
 */
public class MainWindow extends JFrame{

    public MainWindow() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.init();
    }
    
    private void init(){
        this.add(new DrawingArea2D());
    }
}
