package com.broteam.tipe.element;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AccessPoint extends Element {

    private static final BufferedImage AP_IMG;
    static {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images/ap.png"));
        } catch (IOException e) {
        }
        AP_IMG = img;
    }
    
    private Point2D location;
    private int power;

    public AccessPoint(Point2D location, int power) {
        super();
        this.location = location;
        this.power = power;
    }

    public BufferedImage getImage() {
        return AP_IMG;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getPower() {
        return power;
    }
    
    @Override
	public String toString() {
    	return ("AP : ("+location.getX()+","+location.getY()+") : "+power);
    }

	@Override
	public void drawSelf(Graphics2D g2d) {
        g2d.drawImage(AP_IMG, (int) location.getX(), (int) location.getY(), null);
	}

}