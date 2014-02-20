package com.broteam.tipe.testui;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.broteam.tipe.shape.Pinceau;
import com.broteam.tipe.shape.Shape;

public class Panel extends JPanel {

	private LinkedList<Shape> shapes;

	private Pinceau pinceau;

	/**
	 * Default constructor.
	 */
	Panel() {
		shapes = new LinkedList<Shape>();
		pinceau = new Pinceau();
		addMouseListener(pinceau);
		addMouseMotionListener(pinceau);
	}

	/**
	 * Adds the specified {@link Shape} to this {@link Panel}.
	 * 
	 * @param s
	 *            The {@link Shape} to add.
	 */
	public void add(Shape s) {
		shapes.add(s);
		repaint();
	}

	/**
	 * Replaces the last added {@link Shape} by the specified {@link Shape}.
	 * 
	 * @param s
	 *            The {@link Shape} to place instead of the last one.
	 */
	public void replaceLast(Shape s) {
		shapes.removeLast();
		shapes.add(s);
		repaint();
	}

	/**
	 * Removes all {@link Shape}s from this {@link Panel}.
	 */
    void clear() {
		shapes.clear();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape s : shapes)
			if (s != null)
				s.draw(g);
	}
}