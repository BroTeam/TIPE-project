package com.broteam.tipe.shape;

import java.awt.Shape;

import com.broteam.tipe.testui.Material;

public class Element {
	
	Material mat;
	Shape sha;
	
	Element(Shape s) {
		sha = s;
	}
	public Shape getShape() {
		return sha;
	}
	
	public void setMaterial(Material m) {
		mat = m;
	}
	
	public Material getMaterial() {
		return mat;
	}
	
}