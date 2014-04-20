package com.broteam.tipe.shape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Room extends Rectangle2D.Double {

	Room(Point2D refPressed, Point2D refReleased) {
        super();
        setFrameFromDiagonal(refPressed, refReleased);
	}

}