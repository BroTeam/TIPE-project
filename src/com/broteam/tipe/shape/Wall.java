package com.broteam.tipe.shape;

import java.awt.Graphics;

public class Wall extends Shape {

	private Point p1;
        private Point p2;

        Wall(Point ptPress, Point ptRel) {
            p1 = ptPress;
            p2 = ptRel;
		/*
		 * TODO: obliger les traits droits uniquement sans interdir l'affichage
		 * d'un trait non perpendiculaire mais en replaçant le trait de façon à
		 * ce qu'il soit correct.
		 */
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(col);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}
}