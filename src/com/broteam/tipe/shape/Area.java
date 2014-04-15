package com.broteam.tipe.shape;

import java.awt.*;

/**
 * Created by Titouan on 15/04/14.
 */
public class Area extends Shape {

    private Point pointsTab[];


    Area(Point areaPtTab[]) {
        for (int k = 0 ; k < areaPtTab.length-1 ; k++) {
            pointsTab[k] =  areaPtTab[k];
        }
    }

    public boolean isInsideArea(Area areaShape, Point test) {
            int i;
            int j;
            boolean result = false;
            for (i = 0, j = areaShape.pointsTab.length - 1; i < areaShape.pointsTab.length; j = i++) {
                if ((areaShape.pointsTab[i].y > test.y) != (areaShape.pointsTab[j].y > test.y) &&
                        (test.x < (areaShape.pointsTab[j].x - areaShape.pointsTab[i].x) * (test.y - areaShape.pointsTab[i].y) / (areaShape.pointsTab[j].y-areaShape.pointsTab[i].y) + areaShape.pointsTab[i].x)) {
                    result = !result;
                }
            }
            return result;
        }


    @Override
    public void draw(Graphics g) {
        g.setColor(col);
    //    int px = topLeft.x;
    //    int py = topLeft.y;
    //    g.drawRect(px, py, width, height);
    }
}
