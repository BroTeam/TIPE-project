package com.broteam.tipe.testui;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import com.broteam.tipe.shape.Pinceau;
import com.broteam.tipe.shape.Shape;

public class Panel extends JPanel {

	private ArrayList<Shape> shapes;

	Pinceau pinceau;

	public void reloadPanel() {
		Graphics g = getGraphics();
		paintComponent(g);
	}

	public void add(Shape f) {
		shapes.add(f);
		reloadPanel();
	}

	void clear() {
		shapes.clear();
		reloadPanel();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape s : shapes)
			if (s != null)
				s.draw(g);
	}

	Panel() {
		shapes = new ArrayList<Shape>();
		pinceau = new Pinceau();
		addMouseListener(pinceau);
	}
}