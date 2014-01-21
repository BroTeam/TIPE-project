package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Graphics;

public class Room extends Shape{

	Room(Point ptPress, Point ptRel, Color c) {
		System.out.println("Test Room");
		System.out.println(ptPress.getX());
		System.out.println(ptPress.getY());
		refPressed = ptPress;
		refReleased = ptRel;
		col = c;
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(col);
		System.out.println(refPressed.getX());
		System.out.println(refPressed.getY());
		g.fillRect(refPressed.getX(), refPressed.getY(), 10, 10);
		//g.fillRect(refPressed.getX(), refPressed.getY(), refReleased.getX(), refReleased.getY());
	}
}