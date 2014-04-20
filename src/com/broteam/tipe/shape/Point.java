package com.broteam.tipe.shape;

import com.broteam.tipe.testui.Window;

import java.awt.geom.Point2D;

public class Point extends Point2D {
	
	public double x;
	public double y;

	public Point(double a, double b) {
        double maxWidth = Window.panel.getSize().width;
        double maxHeight = Window.panel.getSize().height;
        if ((a < 0) & (b < 0)){
            x = 0;
            y = 0;
        } else if (a < 0) {
            x = 0;
            y = b;
        } else if (b < 0) {
            x = a;
            y = 0;
        } else if ((a > maxWidth) & (b > maxHeight)){
            //-1 pour que ça soit visible et non pas sur le bord.
            x = maxWidth-1;
            y = maxHeight-1;
        } else if (a > maxWidth) {
            x = maxWidth-1;
            y = b;
        } else if  (b > maxHeight) {
            x = a;
            y = maxHeight-1; }
        else {
		    x = a;
		    y = b;
	    }
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}