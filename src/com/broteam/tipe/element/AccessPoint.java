package com.broteam.tipe.element;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AccessPoint extends Element {

    private BufferedImage img;
    private Point2D location;
    private int power;

    public AccessPoint(Point2D location, int power) {
        super(null);
        this.location = location;
        this.power = power;
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
    
    @Override
	public String toString() {
    	return ("AP : ("+location.getX()+","+location.getY()+") : "+power);
    }

	@Override
	public void drawSelf(Graphics2D g2d) {
        g2d.drawImage(img, (int) location.getX(), (int) location.getY(), null);
	}

}