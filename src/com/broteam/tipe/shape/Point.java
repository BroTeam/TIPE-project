package com.broteam.tipe.shape;

public class Point {
	
	public int x;
	public int y;
	
	public Point() {
		this(0, 0);
	}

	public Point(Point a) {
		this(a.x, a.y);
	}
	
	public Point(int a, int b) {
		x = a;
		y = b;
	}
}