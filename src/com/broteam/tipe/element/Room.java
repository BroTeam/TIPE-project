package com.broteam.tipe.element;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import com.broteam.tipe.math.ProjectionHelper;
import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.SignalArea;

public class Room extends Obstacle {

    public Room(Point2D refPressed, Point2D refReleased, Material mat) {
        super(new Rectangle2D.Double(), mat);
        ((Rectangle2D.Double) getShape()).setFrameFromDiagonal(refPressed, refReleased);
    }

    @Override
    public List<SignalArea> getAttenuatedAreas(AccessPoint ap, double panelWidth, double panelHeight) {
        LinkedList<SignalArea> list = new LinkedList<>();
        Rectangle2D room = (Rectangle2D) getShape();
        Point2D cornerTL = new Point2D.Double(room.getMinX(), room.getMinY());
        Point2D cornerTR = new Point2D.Double(room.getMaxX(), room.getMinY());
        Point2D cornerBL = new Point2D.Double(room.getMinX(), room.getMaxY());
        Point2D cornerBR = new Point2D.Double(room.getMaxX(), room.getMaxY());
        Line2D wallTop = new Line2D.Double(cornerTL, cornerTR);
        Line2D wallRight = new Line2D.Double(cornerTR, cornerBR);
        Line2D wallLeft = new Line2D.Double(cornerTL, cornerBL);
        Line2D wallBottom = new Line2D.Double(cornerBL, cornerBR);
        Double attenuation = getMaterial().getAttenuationFactor();
        list.add(new SignalArea(attenuation, ProjectionHelper.getWallShadow(ap.getLocation(), wallTop, panelWidth,
                panelHeight)));
        list.add(new SignalArea(attenuation, ProjectionHelper.getWallShadow(ap.getLocation(), wallRight, panelWidth,
                panelHeight)));
        list.add(new SignalArea(attenuation, ProjectionHelper.getWallShadow(ap.getLocation(), wallLeft, panelWidth,
                panelHeight)));
        list.add(new SignalArea(attenuation, ProjectionHelper.getWallShadow(ap.getLocation(), wallBottom, panelWidth,
                panelHeight)));
        return list;
    }
}