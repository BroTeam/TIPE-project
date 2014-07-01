package com.broteam.tipe.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Test;

public class SideTest {

    @Test
    public void testNext() {
        assertEquals("TOP.next() should be RIGHT", Side.TOP.next(), Side.RIGHT);
        assertEquals("RIGHT.next() should be BOTTOM", Side.RIGHT.next(), Side.BOTTOM);
        assertEquals("BOTTOM.next() should be LEFT", Side.BOTTOM.next(), Side.LEFT);
        assertEquals("LEFT.next() should be TOP", Side.LEFT.next(), Side.TOP);
    }

    @Test
    public void testGetCorner() {
        final double height = 10;
        final double width = 8;

        final Point2D corner = new Point2D.Double(width, 0);
        assertEquals(Side.TOP.getCorner(width, height), corner);

        corner.setLocation(0, 0);
        assertEquals(Side.LEFT.getCorner(width, height), corner);

        corner.setLocation(0, height);
        assertEquals(Side.BOTTOM.getCorner(width, height), corner);

        corner.setLocation(width, height);
        assertEquals(Side.RIGHT.getCorner(width, height), corner);
    }

    @Test
    public void testGet() {
        final double height = 10;
        final double width = 8;

        assertEquals("Side should be LEFT", Side.get(0, 2, width, height), Side.LEFT);
        assertEquals("Side should be LEFT", Side.get(0, 2.5, width, height), Side.LEFT);
        assertEquals("Side should be LEFT", Side.get(0, 8.5, width, height), Side.LEFT);

        assertEquals("Side should be TOP", Side.get(1, 0, width, height), Side.TOP);
        assertEquals("Side should be TOP", Side.get(2.5, 0, width, height), Side.TOP);
        assertEquals("Side should be TOP", Side.get(4, 0, width, height), Side.TOP);
        assertEquals("Side should be TOP", Side.get(5, 0, width, height), Side.TOP);

        assertEquals("Side should be RIGHT", Side.get(width, 0.0001, width, height), Side.RIGHT);
        assertEquals("Side should be RIGHT", Side.get(width, 3.5, width, height), Side.RIGHT);
        assertEquals("Side should be RIGHT", Side.get(width, 4.8, width, height), Side.RIGHT);
        assertEquals("Side should be RIGHT", Side.get(width, height - 0.0001, width, height), Side.RIGHT);

        assertEquals("Side should be BOTTOM", Side.get(0.5, height, width, height), Side.BOTTOM);
        assertEquals("Side should be BOTTOM", Side.get(2.5, height, width, height), Side.BOTTOM);
        assertEquals("Side should be BOTTOM", Side.get(4.7, height, width, height), Side.BOTTOM);
        assertEquals("Side should be BOTTOM", Side.get(7.99, height, width, height), Side.BOTTOM);

        assertEquals("Side should be null (middle of rect)", Side.get(3, 3.5, width, height), null);
        assertEquals("Side should be null (middle of rect)", Side.get(5, 8, width, height), null);

        assertEquals("Side should be null (x < 0)", Side.get(-0.0001, 3, width, height), null);
        assertEquals("Side should be null (x < 0)", Side.get(-2.5, 3, width, height), null);
        assertEquals("Side should be null (x > width)", Side.get(width + 0.0001, 3, width, height), null);
        assertEquals("Side should be null (x > width)", Side.get(width + 20, 3, width, height), null);

        assertEquals("Side should be null (y < 0)", Side.get(3, -0.0001, width, height), null);
        assertEquals("Side should be null (y < 0)", Side.get(3, -15, width, height), null);
        assertEquals("Side should be null (y > height)", Side.get(3, height + 0.0001, width, height), null);
        assertEquals("Side should be null (y > height)", Side.get(3, height + 25, width, height), null);

        Side s;
        s = Side.get(0, 0, width, height);
        assertTrue("Side should be LEFT or TOP (top-left corner)", s == Side.LEFT || s == Side.TOP);
        s = Side.get(width, 0, width, height);
        assertTrue("Side should be TOP or RIGHT (top-right corner)", s == Side.TOP || s == Side.RIGHT);
        s = Side.get(0, height, width, height);
        assertTrue("Side should be LEFT or BOTTOM (bottom-left corner)", s == Side.LEFT || s == Side.BOTTOM);
        s = Side.get(width, height, width, height);
        assertTrue("Side should be RIGHT or BOTTOM (bottom-right corner)", s == Side.RIGHT || s == Side.BOTTOM);
    }
}
