package com.broteam.tipe.shape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.broteam.tipe.testui.Panel;

public class Pinceau extends MouseAdapter {

    private Point ptPress;
    private static int shapeSelector = 0;

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
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ptPress = new Point(e.getX(), e.getY());
        Shape toDraw = getNewShape(ptPress, ptPress);
        Panel screen = (Panel) e.getSource();
        screen.add(toDraw);
	}

    @Override
    public void mouseDragged(MouseEvent e) {
        Point ptDrag = new Point(e.getX(), e.getY());
        System.out.println("Pt drag =("+e.getX()+","+e.getY()+")");
        Panel screen = (Panel) e.getSource();
        screen.replaceLast(getNewShape(ptPress, ptDrag));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point ptRel = new Point(e.getX(), e.getY());
        //System.out.println(e.getX()+","+e.getY());
        Panel screen = (Panel) e.getSource();
        screen.replaceLast(getNewShape(ptPress, ptRel));
    }

	public Shape getNewShape(Point ptPress, Point ptRel) {
        switch (shapeSelector) {
        case 1:
            return new Room(ptPress, ptRel);
        case 2:
            return new Wall(ptPress, ptRel);
        case 3:
            return new Door(ptPress, ptRel);
        default:
            throw new NoShapeChosenException();
        }
	}
}