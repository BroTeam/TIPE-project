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

	Shape(Point a) {
		refPressed = a;
		refReleased = new Point(0, 0);
	}

	Shape(Point a1, Point a2) {
		refPressed = a1;
		refReleased = a2;
	}

    public void setColor(Color c) {
        col = c;
    }
	public abstract void draw(Graphics g);
}