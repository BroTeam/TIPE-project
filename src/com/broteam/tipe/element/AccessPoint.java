package com.broteam.tipe.element;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AccessPoint extends Element {
	
	private BufferedImage img;
	private Point2D location;
	private int power;
	
	public AccessPoint(Point2D start, Point2D end, int pow) {
        super(null);
		location = start;
		power = pow;
		try {
		    img = ImageIO.read(new File("images/ap.png"));
		} catch (IOException e) {
		}
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	public Point2D getLocation() {
		return location;
	}
	
	public int getPower() {
		return power;
	}
	
}