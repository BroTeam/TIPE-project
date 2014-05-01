package com.broteam.tipe.element;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AccessPoint extends Element {
	
	private BufferedImage img;
	private Point2D ptAP;
	private int power;
	
	public AccessPoint(Point2D start, Point2D end, int pow) {
        super(new Line2D.Double(start, end));
		ptAP = start;
		power = pow;
		try {
		    img = ImageIO.read(new File("images/ap.png"));
		} catch (IOException e) {
		}
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	public Point2D getPtAP() {
		return ptAP;
	}
	
	public int getPowerAP() {
		return power;
	}
	
}