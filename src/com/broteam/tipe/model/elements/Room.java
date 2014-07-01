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
        final Rectangle2D room = (Rectangle2D) getShape();
        final Point2D cornerTL = new Point2D.Double(room.getMinX(), room.getMinY());
        final Point2D cornerTR = new Point2D.Double(room.getMaxX(), room.getMinY());
        final Point2D cornerBL = new Point2D.Double(room.getMinX(), room.getMaxY());
        final Point2D cornerBR = new Point2D.Double(room.getMaxX(), room.getMaxY());
        final Material material = getMaterial();
        final Wall wallTop = new Wall(cornerTL, cornerTR, material);
        final Wall wallRight = new Wall(cornerTR, cornerBR, material);
        final Wall wallLeft = new Wall(cornerTL, cornerBL, material);
        final Wall wallBottom = new Wall(cornerBL, cornerBR, material);
        final LinkedList<Wall> walls = new LinkedList<>();
        walls.add(wallTop);
        walls.add(wallRight);
        walls.add(wallLeft);
        walls.add(wallBottom);
        return walls;
    }

    @Override
    public List<SignalArea> getAttenuatedAreas(AccessPoint ap, double panelWidth, double panelHeight) {
        final LinkedList<Wall> walls = new LinkedList<>(getWalls());
        final LinkedList<SignalArea> list = new LinkedList<>();
        for (final Wall w : walls) {
            list.add(new SignalArea(w, Geometry.getWallShadow(ap.getLocation(), (Line2D) w.getShape(), panelWidth,
                    panelHeight)));
        }
        return list;
    }

}