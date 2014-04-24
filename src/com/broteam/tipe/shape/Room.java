package com.broteam.tipe.shape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Room extends Element {

	Room(Point2D refPressed, Point2D refReleased, Material mat) {
        super(new Rectangle2D.Double(), mat);
        ((Rectangle2D.Double) getShape()).setFrameFromDiagonal(refPressed, refReleased);
	}

}