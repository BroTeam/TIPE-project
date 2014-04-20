package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import com.broteam.tipe.testui.Panel;

public class Pinceau extends MouseAdapter {

    private Point2D ptPress;
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
        if (e.getButton() != MouseEvent.BUTTON1) {
            System.out.println(e.getButton());
            return;
        }
        ptPress = e.getPoint();
        Panel screen = (Panel) e.getSource();
        try {
            screen.add(getNewShape(ptPress, ptPress));
        } catch (NoShapeChosenException e1) {
            ptPress = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (ptPress != null) {
            replaceShape(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (ptPress != null) {
            replaceShape(e);
            ptPress = null;
        }
    }

    private void replaceShape(MouseEvent e) {
        Panel screen = (Panel) e.getSource();
        double xMax = screen.getWidth() - 1;
        double yMax = screen.getHeight() - 1;
        double x = e.getX();
        double y = e.getY();
        x = x > xMax ? xMax : x < 0 ? 0 : x;
        y = y > yMax ? yMax : y < 0 ? 0 : y;
        screen.replaceLast(getNewShape(ptPress, new Point2D.Double(x, y)));
    }

    public Shape getNewShape(Point2D ptPress, Point2D ptRel) throws NoShapeChosenException {
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