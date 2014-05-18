package com.broteam.tipe.ui;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import com.broteam.tipe.signal.SignalArea;
import com.broteam.tipe.signal.Simulation;

public class DrawableArea {

    /** Opacity of the {@link DrawableArea}s. */
    private static final int OPACITY = 180;

    private Simulation simulation;
    private SignalArea area;
    private BufferedImage image;

    private int xPosition;
    private int yPosition;

    private final double MAX_POWER;

    public DrawableArea(SignalArea area, Simulation simulation) {
        this.area = area;
        this.simulation = simulation;
        this.MAX_POWER = simulation.getMaxPower();
        Rectangle bounds = area.getBounds();
        xPosition = bounds.x;
        yPosition = bounds.y;
        this.image = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB);
        paintIntoImage();
    }

    public Shape getClip() {
        return area;
    }

    public BufferedImage getImage() {
        return image;
    }

    private void paintIntoImage() {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                image.setRGB(x, y, getColor(x, y));
            }
        }
    }

    private int getColor(int x, int y) {
        double power = simulation.getPower(x + xPosition, y + yPosition, area);
        int scaledPower = (int) (power * 255d / MAX_POWER);
        int green = scaledPower % 256;
        int red = 255 - green;
        int argb = (OPACITY << 24) + (red << 16) + (green << 8);
        //System.out.println("power = " + power + "  R = " + red + "  G = " + green + "  argb = 0x" + Integer.toHexString(argb));
        return argb;
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }
}
