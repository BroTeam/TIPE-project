package com.broteam.tipe.shape;

public class Point {
	private int x, y;

	Point(int a, int b) {
		x = a;
		y = b;
	}

	Point() {
		this(0, 0);
	}

	Point(Point a) {
		this(a.x, a.y);
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

}