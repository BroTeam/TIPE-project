package com.broteam.tipe.shape;

import java.awt.*;

public class Door extends Shape {

    private Point p1;
    private Point p2;

    Door(Point ptPress, Point ptRel) {
        p1 = ptPress;
        p2 = ptRel;
        int newP2XBD;
        int newP2YBD;
        int newP2XBG;
        int newP2YBG;
        int newP2XHD;
        int newP2YHD;
        int newP2XHG;
        int newP2YHG;
        //Math.abs pas nécessaire je pense
        double lengthDoor = Math.hypot(Math.abs(p1.x - p2.x),Math.abs(p1.y - p2.y));
        while (lengthDoor > 20) {
            // Tant que la longueur de la porte est plus grande que 20px
            //faire les conditions de définitions en HG HD BG BD
            if (p1.x <= p2.x & p1.y <= p2.y) {
                //CAS BD
                newP2XBD = (p2.x - 1);
                newP2YBD = (p2.y - 1);
                if (newP2XBD >= p1.x & newP2YBD >= p1.y) {
                    p2 = new Point(newP2XBD, newP2YBD);
                } else {
                    p2 = p1;
                }
            } else if (p1.x >= p2.x & p1.y <= p2.y) {
                //CAS BG
                newP2XBG = (p2.x + 1);
                newP2YBG = (p2.y - 1);
                if (newP2XBG <= p1.x & newP2YBG >= p1.y) {
                    p2 = new Point(newP2XBG, newP2YBG);
                } else {
                    p2 = p1;
                }
            } else if (p1.x <= p2.x & p1.y >= p2.y) {
                //CAS HD
                newP2XHD = (p2.x - 1);
                newP2YHD = (p2.y + 1);
                if (newP2XHD >= p1.x & newP2YHD <= p1.y) {
                    p2 = new Point(newP2XHD, newP2YHD);
                } else {
                    p2 = p1;
                }
            } else if (p1.x >= p2.x & p1.y >= p2.y) {
                //CAS HG
                newP2XHG = (p2.x + 1);
                newP2YHG = (p2.y + 1);
                if (newP2XHG <= p1.x & newP2YHG <= p1.y) {
                    p2 = new Point(newP2XHG, newP2YHG);
                } else {
                    p2 = p1;
                }
            }
            lengthDoor = Math.hypot(Math.abs(p1.x - p2.x),Math.abs(p1.y - p2.y));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(col);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

}
