package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.broteam.tipe.testui.Panel;

public class Pinceau extends MouseAdapter {

    // TODO Checker les visibilités mieux
    private Color col = Color.BLACK;
    public Point ptPress;
    public Point ptDrag;
    public Point ptRel;
    public static int shapeSelector = 0;

    class NoShapeChose extends Exception {
		public NoShapeChose() {
			System.out.println("Aucune forme sélectionnée");
		}
	}

	class notImplemented extends Exception {
		public notImplemented() {
			System.out.println("Fonctionnalité non implémentée [Pour l'instant...]");
		}
	}

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
        Shape toDraw = null;
        try {
            toDraw = drawShape(ptPress, ptPress);
        } catch (Exception e1) {
        }
        Panel ecran = (Panel) e.getSource();
        ecran.add(toDraw);
	}

    @Override
    public void mouseDragged(MouseEvent e) {
        ptDrag = new Point(e.getX(), e.getY());
        Panel ecran = (Panel) e.getSource();
        ecran.removeLast();
        Shape toDraw = null;
        try {
            toDraw = drawShape(ptPress, ptDrag);
        } catch (Exception e1) {
        }
        ecran.add(toDraw);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ptRel = new Point(e.getX(), e.getY());
        Panel ecran = (Panel) e.getSource();
        ecran.removeLast();
        Shape toDraw = null;
        try {
            toDraw = drawShape(ptPress, ptRel);
        } catch (Exception e1) {
        }
        ecran.add(toDraw);
    }

	public Shape drawShape(Point ptPress, Point ptRel)
			throws Exception {
		switch (shapeSelector) {
		case 1:
			return new Room(ptPress, ptRel, col);
		case 2:
			return new Wall(ptPress, ptRel, col);
		case 3:
			throw new notImplemented();
			// TODO return toDraw = new Door(ptPress, ptRel, col);
		default:
			throw new NoShapeChose();
		}
	}
}