package com.broteam.tipe.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class SideTest {

    @Test
    public void testGet() {

        double height = 10;
        double width = 8;

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

        assertEquals("Side should be OUT (middle of rect)", Side.get(3, 3.5, width, height), Side.OUT);
        assertEquals("Side should be OUT (middle of rect)", Side.get(5, 8, width, height), Side.OUT);
        
        assertEquals("Side should be OUT (x < 0)", Side.get(-0.0001, 3, width, height), Side.OUT);
        assertEquals("Side should be OUT (x < 0)", Side.get(-2.5, 3, width, height), Side.OUT);
        assertEquals("Side should be OUT (x > width)", Side.get(width + 0.0001, 3, width, height), Side.OUT);
        assertEquals("Side should be OUT (x > width)", Side.get(width + 20, 3, width, height), Side.OUT);
        
        assertEquals("Side should be OUT (y < 0)", Side.get(3, -0.0001, width, height), Side.OUT);
        assertEquals("Side should be OUT (y < 0)", Side.get(3, -15, width, height), Side.OUT);
        assertEquals("Side should be OUT (y > height)", Side.get(3, height + 0.0001, width, height), Side.OUT);
        assertEquals("Side should be OUT (y > height)", Side.get(3, height + 25, width, height), Side.OUT);
        
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
