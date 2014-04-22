package com.broteam.tipe.testui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.broteam.tipe.shape.Element;
import com.broteam.tipe.shape.Pinceau;

public class Panel extends JPanel {

    private LinkedList<Element> elements;

    private Pinceau pinceau;

    /**
     * Default constructor.
     */
    Panel() {
    	elements = new LinkedList<>();
        pinceau = new Pinceau();
        addMouseListener(pinceau);
        addMouseMotionListener(pinceau);
    }

    /**
     * Adds the specified {@link Element} to this {@link Panel}.
     * 
     * @param e
     *            The {@link Element} to add.
     */
    public void add(Element e) {
    	elements.add(e);
        repaint();
    }

    /**
     * Replaces the last added {@link Element} by the specified {@link Element}.
     * 
     * @param e
     *            The {@link Element} to place instead of the last one.
     */
    public void replaceLast(Element e) {
    	elements.removeLast();
    	elements.add(e);
        repaint();
    }

    /**
     * Removes all {@link Element}s from this {@link Panel}.
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
                g2d.draw(e.getShape());
            }
        }
    }
    
}