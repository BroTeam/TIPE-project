package com.broteam.tipe.math;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class ProjectionHelper {

    /**
     * Returns the point that is the projection of the {@code source} to the edge of
     * the specified rectangle, when projected in the direction of {@code wayPoint}.
     * 
     * @param source
     *            The point to project to the edge.
     * @param wayPoint
     *            The direction to follow when projecting {@code source}.
     * @param panelWidth
     *            The width of the panel (maximum X).
     * @param panelHeight
     *            The height of the panel (maximum Y).
     * @return The resulting point, which is on an edge of the window.
     */
    public static Point2D getProjectionOnEdge(Point2D source, Point2D wayPoint, double panelWidth, double panelHeight) {
        LineEquation eq = new LineEquation(source, wayPoint);
        double interX; // X coord of the intersection
        double interY; // Y coord of the intersection

        if (eq.a > 0) {
            // Intersection avec la droite y = y_max
            interX = -(eq.c + eq.b * panelHeight) / eq.a;
            interY = panelHeight;
        } else {
            // Intersection avec la droite y = 0
            interX = -eq.c / eq.a;
            interY = 0;
        }
        if (interX >= 0 && interX <= panelWidth) {
            // this point is in the boundaries, no need to carry on
            return new Point2D.Double(interX, interY);
        }

        if (eq.b > 0) {
            // Intersection avec la droite x = 0
            interX = 0;
            interY = -eq.c / eq.b;
        } else {
            // Intersection avec la droite x = x_max
            interX = panelWidth;
            interY = -(eq.c + eq.a * panelWidth) / eq.b;
        }
        if (interY >= 0 && interY <= panelHeight) {
            // this point is in the boundaries
            return new Point2D.Double(interX, interY);
        }

        throw new RuntimeException("No correct intersection was found");
    }

    /**
     * Returns the shadow of the specified wall when lit from the specified source.
     * The returned polygon should extend to the edge of the window.
     * 
     * @param source
     *            The source lighting the wall.
     * @param wall
     *            The lit wall.
     * @param panelWidth
     *            The width of the panel (maximum X).
     * @param panelHeight
     *            The height of the panel (maximum Y).
     * @param panelWidth 
     * @param panelHeight 
     * @return A polygon delimited by the wall, the edges of the window, and the
     *         lines projected from the source and the wall's extremities.
     */
    public static Shape getWallShadow(Point2D source, Line2D wall, double panelWidth, double panelHeight) {
        Path2D shadow = new Path2D.Double();
        
        LineEquation eq = new LineEquation(wall);
        boolean clockwise = eq.getRelativePostion(source);
        Point2D P1 = clockwise ? wall.getP1() : wall.getP2();
        Point2D P2 = clockwise ? wall.getP2() : wall.getP1();
        
        Point2D projectP1 = getProjectionOnEdge(source, wall.getP1(), panelWidth, panelHeight);
        Point2D projectP2 = getProjectionOnEdge(source, wall.getP2(), panelWidth, panelHeight);
        // WARNING the points below are added in a specific order to avoid
        // hourglass-like polygons

        // add the wall as one side of the polygon
        shadow.moveTo((int) wall.getX2(), (int) wall.getY2());
        shadow.lineTo((int) wall.getX1(), (int) wall.getY1());
        // add the projection of the second point of the wall
        shadow.lineTo((int) projectP1.getX(), (int) projectP1.getY());
        // add possible corners between the 2 projections
        Side s = Side.get(projectP1, panelWidth, panelHeight);
        Side s2 = Side.get(projectP2, panelWidth, panelHeight);
        while (s != s2) {
            Point2D corner = s.getCorner(panelWidth, panelHeight);
            shadow.lineTo(corner.getX(), corner.getY());
            s = s.next();
        }
        // add the projection of the first point of the wall
        shadow.lineTo((int) projectP2.getX(), (int) projectP2.getY());
        shadow.closePath();
        return shadow;
    }

}
