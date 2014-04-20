package com.broteam.tipe.shape;

import java.awt.*;

public class Door extends Shape {

    private Point p1;
    private Point p2;

    Door(Point ptPress, Point ptRel) {
        p1 = ptPress;
        p2 = ptRel;
        //Math.abs pas nécessaire je pense
        //double lengthDoor = Math.hypot(Math.abs(p1.x - p2.x),Math.abs(p1.y - p2.y));
        double lengthDoor = calcLong(p1,p2);
        //Checkez le coef directeur puis cropper.
        /*double coefDirect = (p2.y - p2.y)/(p1.x - p1.x);
        Point ptProjection = new Point(p1.x,p2.y)
        if (coefDirect > 0) {
            double a = ;
            double b = ;
            double c = lengthDoor;
            double
        } else {

        }*/
    }

    protected double calcLong(Point p1, Point p2) {
        return Math.hypot(Math.abs(p1.x - p2.x),Math.abs(p1.y - p2.y));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(col);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}
