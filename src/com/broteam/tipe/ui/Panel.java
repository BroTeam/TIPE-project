package com.broteam.tipe.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.signal.SignalArea;
import com.broteam.tipe.signal.Simulation;

public class Panel extends JPanel {

    private Window window;
    private List<DrawableArea> simulationAreas = new LinkedList<>();

    /**
     * Default constructor.
     */
    Panel(Window window) {
        this.window = window;
    }

    public void updateSimulationDisplay(Simulation simulation) {
        simulationAreas.clear();
        for (SignalArea area : simulation.getAreas()) {
            simulationAreas.add(new DrawableArea(area, simulation));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Repainting!");
        Graphics2D g2d = (Graphics2D) g;
        Model m = window.getModel();
        if (m == null) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            return; // nothing to draw
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        // areas before elements, so that we can see the elements
        for (DrawableArea area : simulationAreas) {
            g2d.setClip(area.getClip());
            g2d.drawImage(area.getImage(), area.getX(), area.getY(), null);
        }
        // draw elements on top of areas
        g2d.setClip(null);
        for (Element e : m.getElements()) {
            if (e != null) {
                e.drawSelf(g2d);
            }
        }
    }

}
