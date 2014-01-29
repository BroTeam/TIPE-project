package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Shape {

	Wall(Point ptPress, Point ptRel, Color c) {
		refPressed = ptPress;
		refReleased = ptRel;
		col = c;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(col);
		// TODO obliger les traits droits uniquement sans interdir l'affichage
		// d'un trait non perpendiculaire mais en replaçant le trait de façon à
		// ce qu'il soit correct.
		g.drawLine(refPressed.getX(), refPressed.getY(), refReleased.getX(),
				refReleased.getY());
	}
}