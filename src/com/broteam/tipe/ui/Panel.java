package com.broteam.tipe.ui;

import java.awt.BasicStroke;
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

    private static final boolean DRAW_AREA_BOUNDARIES = false;

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
            paintBackground(g2d, Color.GRAY);
            return; // nothing to draw
        }
        paintBackground(g2d, Color.WHITE);

        // areas before elements, so that we can see the elements
        paintAreas(g2d);
        paintAreasBoundaries(g2d);

        // draw elements on top of areas
        paintElements(g2d, m);
    }

    private void paintAreas(Graphics2D g2d) {
        for (DrawableArea area : simulationAreas) {
            g2d.setClip(area.getClip());
            g2d.drawImage(area.getImage(), area.getX(), area.getY(), null);
        }
        g2d.setClip(null);
    }

    private void paintAreasBoundaries(Graphics2D g2d) {
        if (DRAW_AREA_BOUNDARIES) {
            g2d.setColor(Color.GRAY);
            final float dash1[] = { 4.0f };
            final BasicStroke dashed = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f,
                    dash1, 0.0f);
            g2d.setStroke(dashed);
            // draw the boundaries of the areas
            for (DrawableArea area : simulationAreas) {
                g2d.draw(area.getClip());
            }
            g2d.setStroke(new BasicStroke());
        }
    }

    private static void paintElements(Graphics2D g2d, Model m) {
        for (Element e : m.getElements()) {
            if (e != null) {
                e.drawSelf(g2d);
            }
        }
    }

    private void paintBackground(Graphics2D g2d, Color c) {
        g2d.setColor(c);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
