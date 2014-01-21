package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.broteam.tipe.testui.Panel;

public class Pinceau extends MouseAdapter {

	private static final Exception NoShapeChoosed = null;
	private int x1, y1, x2, y2;
	private Point ptPress = null; 
	private Point ptRel = null;
	public static int shapeSelector = 0;
	private int drawingScale;
	private int lR, hR, rC; // dimensions du pinceau

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
		ptPress = new Point(e.getY(), e.getX());		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ptRel = new Point(e.getY(), e.getX());
		Shape toDraw = null;
		try {
			drawShape(toDraw, ptPress, ptRel);
		} catch (Exception e1) {
			System.out.println("Pas de forme sélectionnée.");
		}
		Panel ecran = (Panel) e.getSource();
		ecran.add(toDraw);
	}

	public void drawShape(Shape toDraw, Point ptPress, Point ptRel) throws Exception {
		System.out.println(shapeSelector);
		switch (shapeSelector)
		{    
		    case 1:
		    	toDraw = new Room(ptPress, ptRel, col);
		    	break; 
		    //case 2:
			//	toDraw = new Wall(a, b);
		    //case 3:
			//	toDraw = new Door(a, b);
			default:
				throw NoShapeChoosed;
		}
	}
	
	void setDrawingScale(int size) {
		drawingScale = size;
		lR = drawingScale;
		hR = (3 * drawingScale) / 2;
		rC = drawingScale;
	}

	public Pinceau(int drawingScale) {
		this.setDrawingScale(drawingScale);
	}
}