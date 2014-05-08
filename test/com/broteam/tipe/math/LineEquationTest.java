package com.broteam.tipe.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

public class LineEquationTest {

    @Test
    public void testGetRelativePosition() {

        LineEquation le = new LineEquation(new Line2D.Double(2, 5, 1, 5));
        Point2D p = new Point2D.Double();

        p.setLocation(0, 5.1);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.5, 5.1);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(2.5, 5.1);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(5, 5.1);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));

        p.setLocation(0, 4.9);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.5, 4.9);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(2.5, 4.9);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(4, 4.9);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        
        le = new LineEquation(new Line2D.Double(1, 5, 2, 5));

        p.setLocation(0, 5.1);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.5, 5.1);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(3, 5.1);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));

        p.setLocation(0, 4.9);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.5, 4.9);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(3, 4.9);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));

        le = new LineEquation(new Line2D.Double(2, 4, 2, 5));

        p.setLocation(1.9, 1);
        assertFalse("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.9, 4.8);
        assertFalse("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.9, 7);
        assertFalse("Relative position should be true: clockwise triangle", le.getRelativePostion(p));

        p.setLocation(2.1, 1);
        assertTrue("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(2.1, 4.5);
        assertTrue("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(2.1, 7);
        assertTrue("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));

        le = new LineEquation(new Line2D.Double(2, 5, 2, 4));

        p.setLocation(1.9, 1);
        assertTrue("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.9, 4.8);
        assertTrue("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));
        p.setLocation(1.9, 7);
        assertTrue("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));

        p.setLocation(2.1, 1);
        assertFalse("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(2.1, 4.5);
        assertFalse("Relative position should be true: clockwise triangle", le.getRelativePostion(p));
        p.setLocation(2.1, 7);
        assertFalse("Relative position should be true: clockwise triangle", le.getRelativePostion(p));

    }

}
