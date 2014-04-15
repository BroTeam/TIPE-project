package com.broteam.tipe.shape;

import java.awt.*;

public class Door extends Shape {

    private Point p1;
    private Point p2;

    Door(Point ptPress, Point ptRel) {
        p1 = ptPress;
        p2 = ptRel;
        //TODO: obliger la porte à faire une longueur précise (20px ?).
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(col);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

}
