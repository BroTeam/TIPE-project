package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	protected Point refPressed;
	protected Point refReleased;
	protected Color col = Color.BLACK;

	Shape() {
		refPressed = new Point(0, 0);
		refReleased = new Point(0, 0);
	}

	Shape(int x1, int y1, int x2, int y2) {
		refPressed = new Point(x1, y1);
		refReleased = new Point(x2, y2);
	}

	Shape(Point a) {
		refPressed = new Point(a);
		refReleased = new Point(0, 0);
	}

	Shape(Point a, int x2, int y2) {
		refPressed = new Point(a);
		refReleased = new Point(x2, y2);
	}

	Shape(Point a1, Point a2) {
		refPressed = new Point(a1);
		refReleased = new Point(a2);
	}

	public abstract void draw(Graphics g);
}