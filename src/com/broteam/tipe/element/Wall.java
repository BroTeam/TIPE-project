package com.broteam.tipe.element;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Wall extends Obstacle {

    public Wall(Point2D start, Point2D end, Material mat) {
        super(new Line2D.Double(start, end), mat);
    }

	@Override
	public List<SignalArea> getShadows(AccessPoint ap) {
		LinkedList<SignalArea> list = new LinkedList<>();
		// TODO calculate the trapezoid area
		return list;
	}

}