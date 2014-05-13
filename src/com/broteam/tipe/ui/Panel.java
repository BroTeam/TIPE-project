package com.broteam.tipe.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.signal.AreaIntersector;
import com.broteam.tipe.signal.SignalArea;

public class Panel extends JPanel {

    private LinkedList<Element> elements;
    private LinkedList<Obstacle> obstacles = new LinkedList<>();
    private LinkedList<AccessPoint> aps = new LinkedList<>();

    private List<SignalArea> areas = new LinkedList<>();

    private static int MAX_INT_COLOR = 255 << 16 + 255 << 8 + 255;
    private final Random randomGen = new Random();

    /**
     * Default constructor.
     */
    Panel() {
        elements = new LinkedList<>();
    }

    /**
     * Adds the specified {@link Obstacle} to this {@link Panel}.
     *
     * @param e
     *            The {@link Obstacle} to add.
     */
    public void add(Element e) {
        elements.add(e);
        repaint();
    }

    /**
     * Replaces the last added {@link Obstacle} by the specified {@link Obstacle}.
     *
     * @param e
     *            The {@link Obstacle} to place instead of the last one.
     */
    public void replaceLast(Element e) {
        elements.removeLast();
        elements.add(e);
        repaint();
    }
      
    /**
     * Removes the specified {@link Element} to this {@link Panel}.
     *
     * @param e
     *            The {@link Element} to remove.
     */
    public void remove(Element e) {
        elements.remove(e);
        repaint();
    }

    /**
     * Removes all {@link Obstacle}s from this {@link Panel}.
     */
    void clear() {
        elements.clear();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // areas before elements, so that we can see the elements
        for (SignalArea sa : areas) {
            g2d.setColor(new Color(randomGen.nextInt(MAX_INT_COLOR)));
            g2d.fill(sa);
        }
        // draw elements on top of areas
        for (Element e : elements) {
            if (e != null) {
                e.drawSelf(g2d);
            }
        }
    }

    private void splitElementList() {
        aps.clear();
        obstacles.clear();
        for (Element e : elements) {
            if (e != null) {
                if (e instanceof AccessPoint) {
                    aps.add((AccessPoint) e);
                } else {
                    obstacles.add((Obstacle) e);
                }
            }
        }
    }

    public LinkedList<AccessPoint> getApsList() {
        splitElementList();
        return aps;
    }

    public LinkedList<Obstacle> getObstaclesList() {
        splitElementList();
        return obstacles;
    }

    public void launchSimulation(AccessPoint ap) {
        LinkedList<SignalArea> overlappingAreas = new LinkedList<>();
        for (Obstacle o : getObstaclesList()) {
            if (o != null) {
                overlappingAreas.addAll(o.getAttenuatedAreas(ap, this.getWidth(), this.getHeight()));
            }
        }
        areas = AreaIntersector.createExclusiveAreas(overlappingAreas);
        repaint();
    }
}