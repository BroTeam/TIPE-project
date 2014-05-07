package com.broteam.tipe.element;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.SignalArea;

public class Door extends Obstacle {

    public Door(Point2D start, Point2D end, Material mat) {
    	super(new Line2D.Double(start, end), mat);
    }

	@Override
	public List<SignalArea> getAttenuatedAreas(AccessPoint ap, double panelWidth, double panelHeight) {
		// TODO Auto-generated method stub
		return null;
	}
}
