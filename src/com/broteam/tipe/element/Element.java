package com.broteam.tipe.element;

import java.awt.Shape;
import java.lang.IllegalArgumentException;

public class Element {
	
	Material mat;
	Shape sha;
	
	Element(Shape s, Material m) {
		sha = s;
		setMaterial(m);
	}
	
	public Shape getShape() {
		return sha;
	}
	
	public void setMaterial(Material m)  {
		if (m != null) {
			mat = m;	
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public Material getMaterial() {
		return mat;
	}
	
}