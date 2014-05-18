package com.broteam.tipe.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.Simulation;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.model.elements.Pixel;
import com.broteam.tipe.model.elements.Wall;
import com.broteam.tipe.signal.SignalArea;

public class Panel extends JPanel {

	private static int MAX_INT_COLOR = 255 << 16 + 255 << 8 + 255;
	private final Random randomGen = new Random();

	private Window window;
	private Simulation simulation;
	private List<Pixel> pixels;

	/**
	 * Default constructor.
	 */
	Panel(Window window) {
		this.window = window;
		simulation = new Simulation();
	}

	public Simulation getSimulation() {
		return simulation;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// areas before elements, so that we can see the elements
		System.out.println("It's gonna explode !");
		this.setPixels();
		// Transformation des Obstacle en Wall
		LinkedList<Wall> walls = new LinkedList<>();
		for (Obstacle o : window.getModel().getObstacles()) {
			walls.addAll(o.getWalls());
		}
		for (Pixel p : pixels) {
			p.drawSelf(g2d, (AccessPoint) window.comboBoxAp.getSelectedItem(), walls);
		}
		/*
		 * for (SignalArea sa : simulation.getSignalAreas()) { g2d.setColor(new
		 * Color(randomGen.nextInt(MAX_INT_COLOR))); g2d.fill(sa); }
		 */
		// draw elements on top of areas
		Model m = window.getModel();
		if (m != null) {
			for (Element e : m.getElements()) {
				if (e != null) {
					e.drawSelf(g2d);
				}
			}
		}
	}

	public void setPixels() {
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				pixels.add((Pixel) new Point2D.Double(i, j));
			}
		}
	}
}