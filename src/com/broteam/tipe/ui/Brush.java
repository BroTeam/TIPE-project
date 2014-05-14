package com.broteam.tipe.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Door;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Room;
import com.broteam.tipe.model.elements.Wall;

public class Brush extends MouseAdapter {

    private Point2D ptPress;
    private static int shapeSelector = 0;
    private Window win;

    public Brush(Window w) {
        win = w;
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

    public static void setAp() {
        shapeSelector = 4;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) {
            System.out.println(e.getButton());
            return;
        }
        ptPress = e.getPoint();
        Panel screen = (Panel) e.getSource();
        try {
            screen.getModel().add(getNewElement(ptPress, ptPress));
            screen.repaint();
        } catch (NoShapeChosenException e1) {
            ptPress = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (ptPress != null) {
            replaceElement(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (ptPress != null) {
            replaceElement(e);
            ptPress = null;
        }
    }

    private void replaceElement(MouseEvent e) {
        Panel screen = (Panel) e.getSource();
        double xMax = screen.getWidth() - 1;
        double yMax = screen.getHeight() - 1;
        double x = e.getX();
        double y = e.getY();
        x = x > xMax ? xMax : x < 0 ? 0 : x;
        y = y > yMax ? yMax : y < 0 ? 0 : y;
        screen.getModel().replaceLast(getNewElement(ptPress, new Point2D.Double(x, y)));
        screen.repaint();
    }

    public Element getNewElement(Point2D startPt, Point2D endPt) throws NoShapeChosenException {
        switch (shapeSelector) {
        case 1:
            return new Room(startPt, endPt, win.getSelectedMaterial());
        case 2:
            return new Wall(startPt, endPt, win.getSelectedMaterial());
        case 3:
            return new Door(startPt, endPt, win.getSelectedMaterial());
        case 4:
            int pow = win.getPowerValue();
            return new AccessPoint(endPt, pow);
        default:
            throw new NoShapeChosenException();
        }
    }

}