package com.broteam.tipe.math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LineEquation {
    public double a;
    public double b;
    public double c;

    public LineEquation(Point2D p1, Point2D p2) {
        a = p2.getY() - p1.getY();
        b = p1.getX() - p2.getX();
        c = -b * p1.getY() - a * p1.getX();
    }

    public LineEquation(Line2D line) {
        this(line.getP1(), line.getP2());
    }

    /**
     * Returns the relative position of the specified point with respect to this
     * line.
     * <p>
     * This line was drawn based on two {@link Point2D} {@code P1} and {@code P2}.
     * With the specified {@code point}, they form a triangle ({@code point},
     * {@code P1}, {@code P2}). The returned value describes whether that triangle is
     * drawn clockwise or anti-clockwise.
     * </p>
     * 
     * @param point
     *            The point to get the position for.
     * @return {@code true} if the triangle is drawn clockwise, {@code false}
     *         otherwise.
     */
    public boolean getRelativePostion(Point2D point) {
        return a * point.getX() + b * point.getY() + c > 0;
    }
    
    @Override
    public String toString() {
        return a + " * x + " + b + " * y + " + c + " = 0";
    }
}