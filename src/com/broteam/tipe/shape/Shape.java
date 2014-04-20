package com.broteam.tipe.shape;

import java.awt.*;

public abstract class Shape{
	
	protected Point refPressed;
	protected Point refReleased;
	protected Color col;

	Shape() {
		col = Color.BLACK;
	}

    //@Override
    public void setColor(Color c) {
        col = c;
    }
    
	public abstract void draw(Graphics2D g);
}