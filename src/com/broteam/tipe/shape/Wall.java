package com.broteam.tipe.shape;

import java.awt.*;
import java.awt.geom.Line2D;

public class Wall extends Shape {

	private Point p1;
        private Point p2;

        Wall(Point ptPress, Point ptRel) {
            p1 = ptPress;
            p2 = ptRel;
            double coefDirect = (p2.y - p2.y)/(p1.x - p1.x);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(col);
		//g.drawLine(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
	}
}