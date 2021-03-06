package com.broteam.tipe.signal;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Obstacle;

public class Simulation {

    private static final double ANTENNA_GAIN = 10; // dB

    private final MutuallyExclusiveAreas areas = new MutuallyExclusiveAreas();

    /* Buffer */
    private double apPower;
    private double apFreq;
    private Point2D apLoc;

    public MutuallyExclusiveAreas getAreas() {
        return areas;
    }

    public void clear() {
        areas.clear();
    }

    public void launchSimulation(AccessPoint ap, Model model, double rightLimit, double bottomLimit) {
        this.apLoc = ap.getLocation();
        this.apFreq = ap.getFrequency();
        this.apPower = ap.getPower();
        this.areas.clear();
        if (model == null) {
            return;
        }
        this.areas.addExclusive(new SignalArea(new Rectangle2D.Double(0, 0, rightLimit, bottomLimit)));
        for (final Obstacle o : model.getObstacles()) {
            this.areas.addAllExclusive(o.getAttenuatedAreas(ap, rightLimit, bottomLimit));
        }
    }

    public double getMaxPower() {
        return Physics.applyDBGain(apPower, ANTENNA_GAIN);
    }

    /**
     * Returns the power of the signal at the specified point.
     *
     * @param x
     *            The x coordinate of the point where we want the total attenuation.
     * @param y
     *            The y coordinate of the point where we want the total attenuation.
     * @param area
     *            The area the specified point belongs to.
     * @return The total attenuation (in dB) at the specified point.
     */
    public double getPower(int x, int y, SignalArea area) {
        final double distance = apLoc.distance(x, y) / Physics.PIXELS_PER_METER;
        final double fspl = Physics.freeSpacePathLossDB(distance, apFreq);
        final double attenuation = fspl / 20 + area.getObstaclesAttenuation();
        return Physics.applyDBGain(apPower, ANTENNA_GAIN + attenuation);
    }
}
