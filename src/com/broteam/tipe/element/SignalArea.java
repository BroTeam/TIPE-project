package com.broteam.tipe.element;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by Titouan on 15/04/14.
 */
public class SignalArea extends java.awt.geom.Area {

	double attenuationFactor;

	SignalArea(double attenuationFactor, Shape s) {
		super(s);
		this.attenuationFactor = attenuationFactor;
	}

	// TODO move that to Wall
	SignalArea(Wall wall, Point2D pa) {

	}

	/**
	 * Returns the point that is the projection of the {@code source} to the edge of
	 * the windows, when projected in the direction of {@code wayPoint}.
	 * 
	 * @param source
	 *            The point to project to the edge.
	 * @param wayPoint
	 *            The direction to follow when projecting {@code source}
	 * @return The resulting point, which is on an edge of the window.
	 */
	private static Point2D getProjectionOnEdge(Point2D source, Point2D wayPoint, double panelWidth, double panelHeight) {
		double x1 = source.getX();
		double y1 = source.getY();
		double x2 = source.getX();
		double y2 = source.getY();
		double a = y2 - y1;
		double b = x1 - x2;
		double c = -b*y1 - a*x1;
		Point2D inter1;
		Point2D inter2;
		if (a > 0) {
			//Intersection avec la droite y = y_max
			double ymax = panelHeight;		
			inter1 = new Point2D.Double(-(c+b*ymax)/a, ymax);
		} else {
			//Intersection avec la droite y = 0
			inter1 = new Point2D.Double(-c/a, 0);
		}
		if (b > 0) {
			//Intersection avec la droite x = 0
			inter2 = new Point2D.Double(0, -c/b);
		} else {
			//Intersection avec la droite x = x_max
			double xmax = panelWidth;
			inter2 = new Point2D.Double(xmax, -(c+a*xmax)/b);
		}
		// TODO
		return null;
	}

	/**
	 * Returns the shadow of the specified wall when lit from the specified source.
	 * The returned polygon should extend to the edge of the window.
	 * 
	 * @param source
	 *            The source lighting the wall.
	 * @param wall
	 *            The lit wall.
	 * @return A polygon delimited by the wall, the edges of the window, and the
	 *         lines projected from the source and the wall's extremities.
	 */
	private static Shape getWallShadow(Point2D source, Line2D wall) {
		Polygon p = new Polygon();
		// add the wall as one side of the polygon
		p.addPoint((int) wall.getX1(), (int) wall.getY1());
		p.addPoint((int) wall.getX2(), (int) wall.getY2());
		// On calcule ensuite les points de la base du trapèze formé du mur avec le
		// bord de la fenêtre
		Point2D p1 = wall.getP1();
		Point2D p2 = wall.getP2();
		// TODO complete this
		// WARNING the points should be added in a specific order (avoid hourglass-like polygons!)
		
		//Double coef1 = getCoefDirect(line);
		// Equ droit1 : coef1*x +
		
		return null;
	}

}