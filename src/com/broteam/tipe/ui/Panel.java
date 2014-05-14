package com.broteam.tipe.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.Simulation;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.signal.SignalArea;

public class Panel extends JPanel {

    private static int MAX_INT_COLOR = 255 << 16 + 255 << 8 + 255;
    private final Random randomGen = new Random();

    private Window window;
    private Simulation simulation;

    /**
     * Default constructor.
     */
    Panel(Window window) {
        this.window = window;
        simulation = new Simulation();
    }

    public Simulation getSimulation() {
        return simulation;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // areas before elements, so that we can see the elements
        for (SignalArea sa : simulation.getSignalAreas()) {
            g2d.setColor(new Color(randomGen.nextInt(MAX_INT_COLOR)));
            g2d.fill(sa);
        }
        // draw elements on top of areas
        Model m = window.getModel();
        if (m != null) {
            for (Element e : m.getElements()) {
                if (e != null) {
                    e.drawSelf(g2d);
                }
            }
        }
    }
}