package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.broteam.tipe.testui.Panel;

public class Pinceau extends MouseAdapter {

	private static final Exception NoShapeChoosed = null;
	private int x1, y1, x2, y2;
	public Point ptPress; 
	public Point ptRel;
	public static int shapeSelector = 0;

	private Color col = Color.BLACK;

	public static void setRoom() {
		shapeSelector = 1;
	}
	
	public static void setWall() {
		shapeSelector = 2;
	}

	public static void setDoor() {
		shapeSelector = 3;
	}

	void setColor(Color c) {
		col = c;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ptPress = new Point(e.getX(), e.getY());		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ptRel = new Point(e.getX(), e.getY());
		Shape toDraw = null;
		try {
			toDraw = drawShape(toDraw, ptPress, ptRel);
		} catch (Exception e1) {
			System.out.println("Pas de forme sélectionnée.");
		}
		Panel ecran = (Panel) e.getSource();
		ecran.add(toDraw);
	}

	public Shape drawShape(Shape toDraw, Point ptPress, Point ptRel) throws Exception {
		switch (shapeSelector)
		{    
		    case 1:
		    	return toDraw = new Room(ptPress, ptRel, col);
		    //case 2:
		    //	return toDraw = new Wall(ptPress, ptRel, col);
		    //case 3:
		    //	return toDraw = new Door(ptPress, ptRel, col);
			default:
				throw NoShapeChoosed;
		}
	}
}