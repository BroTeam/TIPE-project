package com.broteam.tipe.element;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;

import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.SignalArea;

public abstract class Obstacle extends Element {

    Shape sha;
    Material mat;

    protected Obstacle(Shape s, Material m) {
        super();
        sha = s;
        setMaterial(m);
    }

    public void setMaterial(Material m) {
        if (m != null) {
            mat = m;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Material getMaterial() {
        return mat;
    }

    public Shape getShape() {
        return sha;
    }
    
    @Override
	public void drawSelf(Graphics2D g2d) {
        g2d.setColor(mat.getColorMat());
        g2d.draw(this.getShape());
    }

    /**
     * Returns the areas attenuated by each part of this obstacle with respect to the
     * specified AP.
     * <p>
     * There should be one area per wall of this obstacle. These {@code Area}s may
     * overlap, intersections should not be dealt with by this method.
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
}