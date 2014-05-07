package com.broteam.tipe.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.broteam.tipe.element.AccessPoint;
import com.broteam.tipe.element.Element;
import com.broteam.tipe.element.Obstacle;
import com.broteam.tipe.signal.Material;

public class Panel extends JPanel {

    private LinkedList<Element> elements;

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
        for (Element e : elements) {
            if (e != null) {
                if (e.getClass().getName() == "com.broteam.tipe.element.AccessPoint") {
                    AccessPoint ap = (AccessPoint) e;
                    g.drawImage(ap.getImage(), (int) ap.getLocation().getX(), (int) ap.getLocation().getY(), null);
                } else {
                    Obstacle o = (Obstacle) e;
                    Material m = o.getMaterial();
                    Color c = m.getColorMat();
                    g2d.setColor(c);
                    g2d.draw(o.getShape());
                }
            }
        }
    }

}