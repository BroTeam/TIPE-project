package com.broteam.tipe.math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class LineEquationTest {

    public static void main(String[] args) {
        
        LineEquation le = new LineEquation(new Line2D.Double(2,5,1,5));
        System.out.println(le);
        System.out.println();
        
        Point2D p = new Point2D.Double(3,6);
        System.out.println("Clockwise triangle:");
        System.out.println(p + ": " + le.getRelativePostion(p));
        System.out.println();
        
        p = new Point2D.Double(3,1);
        System.out.println("Anti-clockwise triangle:");
        System.out.println(p + ": " + le.getRelativePostion(p));
        
    }
    
}
