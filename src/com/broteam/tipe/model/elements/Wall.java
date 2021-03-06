package com.broteam.tipe.model.elements;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import com.broteam.tipe.math.Geometry;
import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.SignalArea;

public class Wall extends Obstacle {

    public Wall(Point2D start, Point2D end, Material mat) {
        super(new Line2D.Double(start, end), mat);
    }

    @Override
    public List<SignalArea> getAttenuatedAreas(AccessPoint ap, double panelWidth, double panelHeight) {
        final LinkedList<SignalArea> list = new LinkedList<>();
        final Shape shadow = Geometry.getWallShadow(ap.getLocation(), (Line2D) getShape(), panelWidth, panelHeight);
        list.add(new SignalArea(this, shadow));
        return list;
    }

    @Override
    public List<Wall> getWalls() {
        final LinkedList<Wall> walls = new LinkedList<>();
        walls.add(this);
        return walls;
    }

}