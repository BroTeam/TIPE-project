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
		p.addPoint((int) ((Line2D) wall.getShape()).getP1().getX(), (int) ((Line2D) wall.getShape()).getP1().getY());
		p.addPoint((int) ((Line2D) wall.getShape()).getP2().getX(), (int) ((Line2D) wall.getShape()).getP2().getY());
		//On calcule ensuite le trapèze formé du mur avec le bord de la fenêtre
		
	}
	
}
