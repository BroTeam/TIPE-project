package com.broteam.tipe.element;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

public class Door extends Obstacle {

    Door(Point2D start, Point2D end, Material mat) {
    	super(new Line2D.Double(start, end), mat);
    }

	@Override
	public List<SignalArea> getShadows(AccessPoint ap) {
		// TODO Auto-generated method stub
		return null;
	}
}
