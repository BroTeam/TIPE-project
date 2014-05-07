package com.broteam.tipe.math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Represents a straight line by its Cartesian equation {@code ax + by + c = 0}.
 */
public class LineEquation {

    /** Coefficient {@code a} in the Cartesian equation. */
    public double a;
    /** Coefficient {@code b} in the Cartesian equation. */
    public double b;
    /** Coefficient {@code c} in the Cartesian equation. */
    public double c;

    /**
     * Creates a new {@link LineEquation} representing the straight line going
     * through the specified 2 points.
     *
     * @param p1
     *            First point.
     * @param p2
     *            Second point.
     */
    public LineEquation(Point2D p1, Point2D p2) {
        a = p2.getY() - p1.getY();
        b = p1.getX() - p2.getX();
        c = -b * p1.getY() - a * p1.getX();
    }

    /**
     * Creates a new {@link LineEquation} for specified line.
     *
     * @param line
     *            The line.
     */
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