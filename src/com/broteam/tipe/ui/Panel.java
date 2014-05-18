package com.broteam.tipe.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.model.elements.Wall;
import com.broteam.tipe.signal.Simulation;

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
        Model m = window.getModel();
        if (m == null) {
        	return;
        }
        Graphics2D g2d = (Graphics2D) g;
        // areas before elements, so that we can see the elements
        System.out.println("It's gonna explode !");
        LinkedList<Wall> walls = (LinkedList<Wall>) Obstacle.obstaclesToWalls(m.getObstacles());
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                System.out.println(i + ":" + j);
                double attenuation = Simulation.getAttenuation(new Point2D.Double(i, j),
                        (AccessPoint) window.comboBoxAp.getSelectedItem(), walls);
                g2d.setColor(new Color((int) attenuation, (int) attenuation, (int) attenuation));
                g2d.draw((Shape) new Point2D.Double(i, j));
            }
        }
        /*
         * for (SignalArea sa : simulation.getSignalAreas()) { g2d.setColor(new
         * Color(randomGen.nextInt(MAX_INT_COLOR))); g2d.fill(sa); }
         */
        // draw elements on top of areas
        if (m != null) {
            for (Element e : m.getElements()) {
                if (e != null) {
                    e.drawSelf(g2d);
                }
            }
        }
    }

}
