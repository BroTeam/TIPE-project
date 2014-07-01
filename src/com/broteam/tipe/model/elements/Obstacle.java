package com.broteam.tipe.model.elements;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.SignalArea;

public abstract class Obstacle extends Element {

    private final Shape shape;
    private final Material material;

    protected Obstacle(Shape s, Material m) {
        super();
        if (m == null) {
            throw new IllegalArgumentException();
        }
        shape = s;
        material = m;
    }

    public Material getMaterial() {
        return material;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public void drawSelf(Graphics2D g2d) {
        g2d.setColor(material.getColorMat());
        g2d.draw(getShape());
    }

    /**
     * Returns the {@link SignalArea}s attenuated by each part of this obstacle with
     * respect to the specified {@link AccessPoint}.
     * <p>
     * There should be one area per wall of this obstacle. These {@link SignalArea}s
     * may overlap, intersections should not be dealt with by this method.
     * </p>
     *
     * @param ap
     *            The {@link AccessPoint} creating the signal that projects the
     *            desired shadows.
     * @param panelWidth
     *            The current width of the panel delimiting the areas.
     * @param panelHeight
     *            The current height of the panel delimiting the areas.
     *
     * @return a non-null list of {@code Area}s.
     */
    public abstract List<SignalArea> getAttenuatedAreas(AccessPoint ap, double panelWidth, double panelHeight);

    public abstract List<Wall> getWalls();

    public static List<Wall> obstaclesToWalls(LinkedList<Obstacle> obstacles) {
        return obstacles.stream().flatMap(o -> o.getWalls().stream()).collect(Collectors.toList());
    }
}