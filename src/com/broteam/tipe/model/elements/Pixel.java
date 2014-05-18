package com.broteam.tipe.model.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import com.broteam.tipe.signal.Simulation;

public class Pixel extends Point2D.Double {

	public void drawSelf(Graphics2D g2d, AccessPoint ap, LinkedList<Wall> walls) {
		double attenuation = Simulation.getAttenuation(this, ap, walls);
		g2d.setColor(new Color( (int) attenuation, (int) attenuation, (int) attenuation));
		g2d.draw((Shape) this);
	}
}
