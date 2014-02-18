package com.broteam.tipe.shape;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Shape {

	Wall(Point ptPress, Point ptRel) {
        super(ptPress,ptRel);
      	/*
		TODO: obliger les traits droits uniquement
		sans interdir l'affichage d'un trait non perpendiculaire mais en replaçant le trait de façon à ce qu'il soit
		 correct.
		*/
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(col);
        g.drawLine(refPressed.getX(), refPressed.getY(), refReleased.getX(),refReleased.getY());
	}
}