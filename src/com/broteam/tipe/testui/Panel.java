package com.broteam.tipe.testui;

import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panel extends JPanel {
	
	public void paintTest(Graphics g, int x1, int y1){
		g.drawRect(x1, y1, 10, 10);
	}
	
	//public void paintLine(Graphics g , int x1, int y1, int x2, int y2){
	//	g.drawLine(x1, y1, x2, y2);
	//}
//  @Override
//public void paintComponent(Graphics g){
    //x1, y1, width, height
  //  g.drawRect(50, 50, 100, 100);
   // g.drawRect(50, 50, 100, 50);
   // g.drawRect(50, 50, 50, 50);
 // }
}