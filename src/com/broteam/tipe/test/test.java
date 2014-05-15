package com.broteam.tipe.test;

import java.awt.geom.Point2D;

import com.broteam.tipe.model.elements.AccessPoint;

public class test {
	
	private static final double LIGHTSPEED = 299792458;
	private static final double k = 20*Math.log(4*Math.PI/LIGHTSPEED);
	
	public static double FSPL(Point2D p, AccessPoint ap){
		double distance = ap.getLocation().distance(p);
		double freqWifi = ap.getPower();
		return 20*Math.log(distance) + 20*Math.log(freqWifi) + k;
	}
}
