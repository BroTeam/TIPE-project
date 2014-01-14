package com.broteam.tipe.testui;

import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panel extends JPanel {
  @Override
public void paintComponent(Graphics g){
    //x1, y1, width, height
    g.drawRect(20, 20, 100, 200);
  }
}