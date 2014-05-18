package com.broteam.tipe.math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Represents a straight line by its Cartesian equation {@code ax + by + c = 0}.
 */
class LineEquation {

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
     * <p>
     * The simplest way to compute the result is to determine in which half-plane the
     * point is located with respect to this straight line. To know which half-plane
     * corresponds to 'clockwise', taking a simple situation is the easiest way.
     * Let's assume the line is horizontal and P1 is to the right of P2.
     * </p>
     * <img src="http://s24.postimg.org/g0vylhadx/relative_position_clockwise.png" />
     * <p>
     * With these assumptions, as we can see on the figure, if P (the specified
     * point) is above this line, then the triangle (P, P1, P2) is <b>clockwise</b>,
     * otherwise it is anti-clockwise.
     * </p>
     * 
     * <p>
     * Since the line is assumed horizontal, we have {@code a=0} (see constructor).
     * The equation hence becomes {@code y = -c/b}. If {@code Py} denotes P's Y
     * coordinate, there are 3 possible cases:
     * </p>
     * <ul>
     * <li>{@code Py = -c/b} means P is <b>on</b> this line (a case we don't care to
     * handle)</li>
     * <li>{@code Py < -c/b} means P is <b>above</b> this line(*), thus (P, P1, P2)
     * is <b>clockwise</b></li>
     * <li>{@code Py > -c/b} means P is <b>below</b> this line(*), thus (P, P1, P2)
     * is <b>anti-clockwise</b></li>
     * </ul>
     * (*) a smaller Y means higher because the Y axis is pointing downwards.
     * <p>
     * Since P1 is to the right of P2, then {@code b > 0} (see constructor).
     * Therefore, we can multiply the inequalities back to the original inequation
     * without changing them:
     * </p>
     * <ul>
     * <li>{@code b * Py + c < 0} means <b>clockwise</b></li>
     * <li>{@code b * Py + c > 0} means <b>anti-clockwise</b></li>
     * </ul>
     * 
     * Back to the general case, this means:
     * <ul>
     * <li>{@code a * Px + b * Py  + c < 0} means <b>clockwise</b></li>
     * <li>{@code a * Px + b * Py  + c > 0} means <b>anti-clockwise</b></li>
     * </ul>
     * 
     * @param point
     *            The point to get the position for.
     * @return {@code true} if the triangle ({@code point}, {@code P1}, {@code P2})
     *         is drawn clockwise, {@code false} otherwise.
     */
    public boolean getRelativePostion(Point2D point) {
        return a * point.getX() + b * point.getY() + c < 0;
    }

    @Override
    public String toString() {
        return a + " * x + " + b + " * y + " + c + " = 0";
    }
}
