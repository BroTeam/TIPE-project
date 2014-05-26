package com.broteam.tipe.model.elements;

import java.awt.Graphics2D;

import java.io.Serializable;

/**
 * Represents any element that can be drawn.
 */
public abstract class Element implements Serializable {

    /**
     * Draws this element using the specified {@link Graphics2D}.
     * 
     * @param g2d
     *            The {@link Graphics2D} to use to draw.
     */
    public abstract void drawSelf(Graphics2D g2d);
    
}