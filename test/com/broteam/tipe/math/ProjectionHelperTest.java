package com.broteam.tipe.math;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class ProjectionHelperTest {

    @Test
    public void testProjectionOnEdge() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        
        Method getProjectionOnEdge = Geometry.class.getDeclaredMethod("getProjectionOnEdge", Point2D.class, Point2D.class, double.class, double.class);
        getProjectionOnEdge.setAccessible(true);

        double width = 20;
        double height = 15;

        Point2D source = new Point2D.Double();
        Point2D wayPoint = new Point2D.Double();
        Point2D projection = new Point2D.Double();

        source.setLocation(3, 8);
        wayPoint.setLocation(5, 8);
        projection.setLocation(width, 8);
        assertEquals(projection, getProjectionOnEdge.invoke(null, source, wayPoint, width, height));

        source.setLocation(5, 8);
        wayPoint.setLocation(3, 8);
        projection.setLocation(0, 8);
        assertEquals(projection, getProjectionOnEdge.invoke(null, source, wayPoint, width, height));

        source.setLocation(5, 5);
        wayPoint.setLocation(5, 8);
        projection.setLocation(5, height);
        assertEquals(projection, getProjectionOnEdge.invoke(null, source, wayPoint, width, height));

        source.setLocation(5, 8);
        wayPoint.setLocation(5, 3);
        projection.setLocation(5, 0);
        assertEquals(projection, getProjectionOnEdge.invoke(null, source, wayPoint, width, height));

        source.setLocation(5, 5);
        wayPoint.setLocation(7, 7);
        projection.setLocation(Math.min(width, height), Math.min(width, height));
        assertEquals(projection, getProjectionOnEdge.invoke(null, source, wayPoint, width, height));

        source.setLocation(7, 7);
        wayPoint.setLocation(5, 5);
        projection.setLocation(0, 0);
        assertEquals(projection, getProjectionOnEdge.invoke(null, source, wayPoint, width, height));
    }

}
