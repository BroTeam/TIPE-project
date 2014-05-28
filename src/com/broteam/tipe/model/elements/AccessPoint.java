package com.broteam.tipe.model.elements;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AccessPoint extends Element {

	private static final BufferedImage AP_IMG;
	private static final BufferedImage AP_IMG_SELECTED;
	static {
		BufferedImage img = null;
		BufferedImage imgSelected = null;
		try {
			img = ImageIO.read(new File("images/ap.png"));
			imgSelected = ImageIO.read(new File("images/ap_selected.png"));
		} catch (IOException e) {
		}
		AP_IMG = img;
		AP_IMG_SELECTED = imgSelected;
	}

	private Point2D location;
	private double power;
	private double frequency;
	private boolean selected;

	public AccessPoint(Point2D location, double power, double frequency) {
		super();
		this.location = location;
		this.power = power;
		this.frequency = frequency;
	}

	public BufferedImage getImage() {
		return AP_IMG;
	}

	public Point2D getLocation() {
		return location;
	}

	public double getPower() {
		return power;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return ("AP (" + location.getX() + "," + location.getY() + ") : " + frequency + " GHz");
	}

	@Override
	public void drawSelf(Graphics2D g2d) {
		int xPos = (int) location.getX();
		int yPos = (int) location.getY();
		BufferedImage img = selected ? AP_IMG_SELECTED : AP_IMG;
		int halfWidth = img.getWidth() / 2;
		int halfHeight = img.getHeight() / 2;
		g2d.drawImage(img, xPos - halfWidth, yPos - halfHeight, null);
	}

}