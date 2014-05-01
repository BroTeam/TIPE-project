package com.broteam.tipe.element;

import java.awt.Shape;
import java.lang.IllegalArgumentException;

public class Obstacle extends Element{
	
	Material mat;
	
	Obstacle(Shape s, Material m) {
		super(s);
		setMaterial(m);
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