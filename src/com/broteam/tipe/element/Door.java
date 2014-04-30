package com.broteam.tipe.element;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Door extends Element {

    Door(Point2D start, Point2D end, Material mat) {
    	super(new Line2D.Double(start, end), mat);
    }
}