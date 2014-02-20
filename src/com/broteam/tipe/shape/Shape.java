package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	
	protected Point refPressed;
	protected Point refReleased;
	protected Color col;

	Shape() {
		col = Color.BLACK;
	}

    public void setColor(Color c) {
        col = c;
    }
    
	public abstract void draw(Graphics g);
}