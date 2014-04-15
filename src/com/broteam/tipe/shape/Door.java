package com.broteam.tipe.shape;

import java.awt.*;

public class Door extends Shape {

    private Point p1;
    private Point p2;

    Door(Point ptPress, Point ptRel) {
        p1 = ptPress;
        p2 = ptRel;
        //Math.abs pas nécessaire je pense
        double lengthDoor = Math.hypot(Math.abs(p1.x - p2.x),Math.abs(p1.y - p2.y));
        while (lengthDoor > 20) {
            // Tant que la longueur de la porte est plus grande que 20px
            p1 = new Point((p1.x)-1, (p1.y)-1);
            p2 = new Point((p2.x)-1, (p2.y)-1);
            lengthDoor = Math.hypot(Math.abs(p1.x - p2.x),Math.abs(p1.y - p2.y));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(col);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

}
