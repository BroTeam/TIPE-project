package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Graphics;

public class Room extends Shape {

	Room(Point ptPress, Point ptRel) {
		super(ptPress,ptRel);
		int px = refPressed.getX();
		int py = refPressed.getY();
		int rx = refReleased.getX();
		int ry = refReleased.getY();
		if ((px > rx) && (py > ry)) {
				refPressed = new Point(rx, ry);
				refReleased = new Point(px, py);
			} else if (py > ry) {
                refPressed = new Point(px, ry);
                refReleased = new Point(rx, py);
            } else if (px > rx) {
				refPressed = new Point(rx, py);
				refReleased = new Point(px, ry);
	    }
    }

	@Override
	public void draw(Graphics g) {
		g.setColor(col);
		int px = refPressed.getX();
		int py = refPressed.getY();
		int rx = refReleased.getX();
		int ry = refReleased.getY();
		g.drawRect(px, py, Math.abs(rx - px), Math.abs(ry - py));
	}
}