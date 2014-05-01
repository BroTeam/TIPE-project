package com.broteam.tipe.element;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by Titouan on 15/04/14.
 */
public class Area extends java.awt.geom.Area {
	
	Area(Room room) {
		
	}
	
	Area(Wall wall, Point2D pa) {
		Polygon p = new Polygon(); 
		p.addPoint((int) ((Line2D.Double) wall.getShape()).getP1().getX(), (int) ((Line2D.Double) wall.getShape()).getP1().getY());
		p.addPoint((int) ((Line2D.Double) wall.getShape()).getP2().getX(), (int) ((Line2D.Double) wall.getShape()).getP2().getY());
		//On calcule ensuite les points de la base du trapèze formé du mur avec le bord de la fenêtre
		Point2D p1 = ((Line2D) wall.getShape()).getP1();
		Line2D.Double line = new Line2D.Double(pa, p1);
		Double coef1 = getCoefDirect(line);
		// Equ droit1 : coef1*x + 
		
	}
	
	private static double getCoefDirect(Line2D l) {
		// TODO test pour coef direct infini !
		return ((l.getP2().getY() - l.getP1().getY())/(l.getP2().getX() - l.getP1().getX()));
	}
	
}