package com.broteam.tipe.element;

import java.awt.Shape;

public class Element {

	Shape sha;

	Element(Shape s) {
		sha = s;
	}
	
	public Shape getShape() {
		return sha;
	}
	
}
