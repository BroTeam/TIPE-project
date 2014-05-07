package com.broteam.tipe.element;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.SignalArea;

public class Room extends Obstacle {

	public Room(Point2D refPressed, Point2D refReleased, Material mat) {
        super(new Rectangle2D.Double(), mat);
        ((Rectangle2D.Double) getShape()).setFrameFromDiagonal(refPressed, refReleased);
	}

	@Override
	public List<SignalArea> getAttenuatedAreas(AccessPoint ap, double panelWidth, double panelHeight) {
		// TODO Auto-generated method stub
		return null;
	}

}