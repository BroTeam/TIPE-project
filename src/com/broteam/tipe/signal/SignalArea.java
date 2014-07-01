package com.broteam.tipe.signal;

import java.awt.Shape;
import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.List;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.model.elements.Wall;

/**
 * Represents an area of points that are separated from the underlying
 * {@link AccessPoint} by the same obstacles.
 *
 * @author Titouan on 15/04/14.
 */
public class SignalArea extends Area {

    /**
     * The obstacles separating each point of this area from the underlying
     * {@link AccessPoint}.
     */
    private final List<Obstacle> obstacles;
    /**
     * The total dB attenuation of the obstacles creating this area.
     */
    private double attenuation;

    /**
     * Creates a signal area with 0 attenuation.
     *
     * @param s
     *            The shape of this area.
     */
    public SignalArea(Shape s) {
        super(s);
        this.obstacles = new LinkedList<>();
        this.attenuation = 0;
    }

    /**
     * Creates a signal area representing the shadow of the specified wall.
     *
     * @param w
     *            The wall creating this area.
     * @param s
     *            The shape of this area.
     */
    public SignalArea(Wall w, Shape s) {
        super(s);
        this.obstacles = new LinkedList<>();
        this.obstacles.add(w);
        this.attenuation = w.getMaterial().getDbAttenuation();
    }

    /**
     * Creates a deep copy of the specified {@link SignalArea}.
     *
     * @param source
     *            The area to copy.
     */
    public SignalArea(SignalArea source) {
        super((Area) source.clone());
        this.obstacles = new LinkedList<>(source.obstacles);
        this.attenuation = source.attenuation;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public double getObstaclesAttenuation() {
        return attenuation;
    }

    /**
     * Reduces this {@link SignalArea} to its intersection with the specified area.
     *
     * @param other
     *            The {@link SignalArea} to intersect with this area.
     */
    public void intersect(SignalArea other) {
        super.intersect(other);
        this.obstacles.addAll(other.getObstacles());
        this.attenuation += other.attenuation;
    }

    /**
     * Returns the intersection of {@code area1} and {@code area2}, and subtract it
     * to the original areas.
     *
     * @param area1
     *            The first area to intersect.
     * @param area2
     *            The second area to intersect.
     * @return The intersection of the 2 specified areas.
     */
    public static SignalArea intersect(SignalArea area1, SignalArea area2) {
        final SignalArea intersection = new SignalArea(area1);
        intersection.intersect(area2);
        area1.subtract(intersection);
        area2.subtract(intersection);
        return intersection;
    }
}
