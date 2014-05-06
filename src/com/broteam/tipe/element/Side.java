package com.broteam.tipe.element;

import java.awt.geom.Point2D;

/**
 * Describes the possible sides of a rectangle.
 */
public enum Side {
    TOP {
        @Override
        public Point2D getCorner(double panelWidth, double panelHeight) {
            return new Point2D.Double(panelWidth, 0);
        }
    },
    RIGHT {
        @Override
        public Point2D getCorner(double panelWidth, double panelHeight) {
            return new Point2D.Double(panelWidth, panelHeight);
        }
    },
    BOTTOM {
        @Override
        public Point2D getCorner(double panelWidth, double panelHeight) {
            return new Point2D.Double(0, panelHeight);
        }
    },
    LEFT {
        @Override
        public Point2D getCorner(double panelWidth, double panelHeight) {
            return new Point2D.Double(0, 0);
        }
    },
    OUT {
        @Override
        public Point2D getCorner(double panelWidth, double panelHeight) {
            throw new RuntimeException("OUT is not a side, cannot call getCorner()");
        }
    };

    private static final Side[] VALUES = values();

    /**
     * Returns the next side, in clockwise order. This method must not be called on
     * {@link Side#OUT}, it throws an exception.
     * 
     * @return the next side, in clockwise order.
     */
    public Side next() {
        if (this == OUT) {
            throw new RuntimeException("OUT is not a side, cannot call next()");
        }
        return VALUES[ordinal() + 1];
    }

    /**
     * Returns the corner at the "end" of this {@link Side} when going clockwise.
     * This method returns the first corner (of the specified rectangle) that is
     * reached when going clockwise around the rectangle, starting from the middle of
     * this {@code Side}.
     * 
     * @param rectWidth
     *            The width of the rectangle.
     * @param rectHeight
     *            The height of the rectangle.
     * @return a new {@link Point2D} representing the corner of the specified
     *         rectangle.
     */
    public abstract Point2D getCorner(double rectWidth, double rectHeight);

    /**
     * Returns the {@link Side} of the rectangle where the specified point belongs.
     * 
     * @param point
     *            The point to find the side of.
     * @param rectWidth
     *            The width of the rectangle.
     * @param rectHeight
     *            The height of the rectangle.
     * @return the {@link Side} of the rectangle where the specified point belongs,
     *         or {@link Side#OUT} if the point is not on the rectangle boundaries.
     */
    public static Side get(Point2D point, double rectWidth, double rectHeight) {
        double x = point.getX();
        double y = point.getY();
        return getSide(x, y, rectWidth, rectHeight);
    }

    /**
     * Returns the {@link Side} of the rectangle where the specified point belongs.
     * 
     * @param x
     *            The x coordinate of the point.
     * @param y
     *            The y coordinate of the point.
     * @param rectWidth
     *            The width of the rectangle.
     * @param rectHeight
     *            The height of the rectangle.
     * @return the {@link Side} of the rectangle where the specified point belongs,
     *         or {@link Side#OUT} if the point is not on the rectangle boundaries.
     */
    public static Side getSide(double x, double y, double rectWidth, double rectHeight) {
        if (x >= 0 && x <= rectWidth) {
            if (y == 0) {
                return Side.TOP;
            } else if (y == rectHeight) {
                return Side.BOTTOM;
            } else {
                return Side.OUT;
            }
        } else if (y >= 0 && y <= rectHeight) {
            if (x == 0) {
                return Side.LEFT;
            } else if (x == rectWidth) {
                return Side.RIGHT;
            } else {
                return Side.OUT;
            }
        } else {
            return Side.OUT;
        }
    }
}