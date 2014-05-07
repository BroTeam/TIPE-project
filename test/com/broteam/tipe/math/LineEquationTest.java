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

        p.setLocation(3, 5.1);
        assertTrue("Relative position should be true: clockwise triangle", le.getRelativePostion(p));

        p.setLocation(3, 4.9);
        assertFalse("Relative position should be false: anti-clockwise triangle", le.getRelativePostion(p));

    }

}
