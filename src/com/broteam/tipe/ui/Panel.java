package com.broteam.tipe.ui;

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
        Model m = window.getModel();
        if (m == null) {
            return; // nothing to draw
        }
        Graphics2D g2d = (Graphics2D) g;
        // areas before elements, so that we can see the elements
        System.out.println("It's gonna explode !");
        for (DrawableArea area : simulationAreas) {
            g2d.setClip(area.getClip());
            g2d.drawImage(area.getImage(), area.getX(), area.getY(), null);
        }
        // draw elements on top of areas
        for (Element e : m.getElements()) {
            if (e != null) {
                e.drawSelf(g2d);
            }
        }
    }

}
