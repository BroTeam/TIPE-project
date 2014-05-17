package com.broteam.tipe.model.elements;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import com.broteam.tipe.math.Geometry;
import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.SignalArea;

public class Room extends Obstacle {

    public Room(Point2D refPressed, Point2D refReleased, Material mat) {
        super(new Rectangle2D.Double(), mat);
        ((Rectangle2D.Double) getShape()).setFrameFromDiagonal(refPressed, refReleased);
    }

    @Override
	public LinkedList<Wall> getWalls() {
        Rectangle2D room = (Rectangle2D) getShape();
        Point2D cornerTL = new Point2D.Double(room.getMinX(), room.getMinY());
        Point2D cornerTR = new Point2D.Double(room.getMaxX(), room.getMinY());
        Point2D cornerBL = new Point2D.Double(room.getMinX(), room.getMaxY());
        Point2D cornerBR = new Point2D.Double(room.getMaxX(), room.getMaxY());
        Material material = this.getMaterial();
        Wall wallTop = new Wall(cornerTL, cornerTR, material);
        Wall wallRight = new Wall(cornerTR, cornerBR, material);
        Wall wallLeft = new Wall(cornerTL, cornerBL, material);
        Wall wallBottom = new Wall(cornerBL, cornerBR, material);
        LinkedList<Wall> walls = new LinkedList<>();
        walls.add(wallTop);
        walls.add(wallRight);
        walls.add(wallLeft);
        walls.add(wallBottom);
		return walls;
    }
        
    @Override
    public List<SignalArea> getAttenuatedAreas(AccessPoint ap, double panelWidth, double panelHeight) {
    	LinkedList<Wall> walls = new LinkedList<>(this.getWalls());
    	LinkedList<SignalArea> list = new LinkedList<>();
    	for (Wall w : walls) {
    		 list.add(new SignalArea(w, Geometry.getWallShadow(ap.getLocation(), (Line2D) w.getShape(), panelWidth,
    	                panelHeight)));
    	}
        return list;
    }
    
}