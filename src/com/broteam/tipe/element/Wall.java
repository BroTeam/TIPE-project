package com.broteam.tipe.shape;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Wall extends Element {

    public Wall(Point2D start, Point2D end, Material mat) {
        super(new Line2D.Double(start, end), mat);
    }

}