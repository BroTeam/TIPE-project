package com.broteam.tipe.element;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Room extends Obstacle {

	Room(Point2D refPressed, Point2D refReleased, Material mat) {
        super(new Rectangle2D.Double(), mat);
        ((Rectangle2D.Double) getShape()).setFrameFromDiagonal(refPressed, refReleased);
	}

	@Override
	public List<SignalArea> getShadows(AccessPoint ap) {
		// TODO Auto-generated method stub
		return null;
	}

}