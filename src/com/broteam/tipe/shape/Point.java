package com.broteam.tipe.shape;

import com.broteam.tipe.testui.Window;

public class Point extends java.awt.Point {
	
	public int x;
	public int y;
	
	public Point() {
		this(0, 0);
	}

	public Point(Point a) {
		this(a.x, a.y);
	}
	
	public Point(int a, int b) {
        int maxWidth = Window.panel.getSize().width;
        int maxHeight = Window.panel.getSize().height;
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
}