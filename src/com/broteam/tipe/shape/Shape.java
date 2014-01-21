package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Graphics;


abstract class Shape {
	protected Point ref;
	protected Color col = Color.BLACK;

	Shape() {
		ref = new Point(0, 0);
	}

	Shape(int a, int b) {
		ref = new Point(a, b);
	}

	Shape(Point a) {
		ref = new Point(a);
	}

	abstract void draw(Graphics g);
}