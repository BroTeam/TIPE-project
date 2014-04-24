package com.broteam.tipe.testui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.broteam.tipe.shape.Element;
import com.broteam.tipe.shape.Material;

public class Panel extends JPanel {

    private LinkedList<Element> elements;

    /**
     * Default constructor.
     */
    Panel() {
    	elements = new LinkedList<>();
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
                Material m = e.getMaterial();
                Color c = m.getColorMat();
                g2d.setColor(c);
                g2d.draw(e.getShape());
            }
        }
    }
    
}