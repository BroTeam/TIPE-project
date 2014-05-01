package com.broteam.tipe.element;

import java.awt.Shape;

public abstract class Element {

	Shape sha;

	protected Element(Shape s) {
		sha = s;
	}
	
	public Shape getShape() {
		return sha;
	}
	
}