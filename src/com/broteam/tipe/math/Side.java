package com.broteam.tipe.math;

import java.awt.geom.Point2D;

/**
 * Describes the possible sides of a rectangle.
 */
enum Side {
    TOP {
        @Override
        public Point2D getCorner(double rectWidth, double rectHeight) {
            return new Point2D.Double(rectWidth, 0);
        }
    },
    RIGHT {
        @Override
        public Point2D getCorner(double rectWidth, double rectHeight) {
            return new Point2D.Double(rectWidth, rectHeight);
        }
    },
    BOTTOM {
        @Override
        public Point2D getCorner(double rectWidth, double rectHeight) {
            return new Point2D.Double(0, rectHeight);
        }
    },
    LEFT {
        @Override
        public Point2D getCorner(double rectWidth, double rectHeight) {
            return new Point2D.Double(0, 0);
        }
    };

    private static final Side[] VALUES = values();

    /**
     * Returns the next side, in clockwise order.
     *
     * @return the next side, in clockwise order.
     */
    public Side next() {
        return VALUES[(ordinal() + 1) % VALUES.length];
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
     *         or {@code null} if the point is not on the rectangle boundaries.
     */
    public static Side get(Point2D point, double rectWidth, double rectHeight) {
        final double x = point.getX();
        final double y = point.getY();
        return get(x, y, rectWidth, rectHeight);
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
     *         or {@code null} if the point is not on the rectangle boundaries.
     */
    public static Side get(double x, double y, double rectWidth, double rectHeight) {
        if (x >= 0 && x <= rectWidth && y >= 0 && y <= rectHeight) {
            if (x == 0) {
                return Side.LEFT;
            } else if (x == rectWidth) {
                return Side.RIGHT;
            } else if (y == 0) {
                return Side.TOP;
            } else if (y == rectHeight) {
                return Side.BOTTOM;
            }
        }
        return null;
    }
}