package com.broteam.tipe.testui;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.*;

import java.awt.Component;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window extends JFrame {
	private final ButtonGroup btnGroupSignal = new ButtonGroup();
    private final Action action = new SwingAction();
    private String test;
	public Integer x1;
	public Integer y1;
	public Integer x2;
	public Integer y2;
    
    public Window() {
        super();
        setTitle("Wi-Fi Access Point Broadcasting Simulator");
        
        JMenuBar menuBar = new JMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        
        JMenu mnFichier = new JMenu("Fichier");
        menuBar.add(mnFichier);
        
        JMenuItem mntmNouveau = new JMenuItem("Nouveau");
        mnFichier.add(mntmNouveau);
        
        JMenuItem mntmOuvrir = new JMenuItem("Ouvrir...");
        mnFichier.add(mntmOuvrir);
        
        JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
        mnFichier.add(mntmEnregistrer);
        
        JMenuItem mntmEnregistrerSous = new JMenuItem("Enregistrer sous...");
        mnFichier.add(mntmEnregistrerSous);
        
        JMenu mnEdition = new JMenu("Edition");
        menuBar.add(mnEdition);
        
        JMenuItem mntmToutEffacer = new JMenuItem("Tout effacer");
        mnEdition.add(mntmToutEffacer);
        
        JMenu mnSimulation = new JMenu("Simulation");
        menuBar.add(mnSimulation);
        
        JMenuItem mntmLancer = new JMenuItem("Activer un Point d'Accès");
        mnSimulation.add(mntmLancer);
        
        JSplitPane splitPane = new JSplitPane();
        getContentPane().add(splitPane, BorderLayout.CENTER);
        
        JTabbedPane toolBar = new JTabbedPane();
        splitPane.setLeftComponent(toolBar);
        
        JPanel panel_4 = new JPanel();
        toolBar.addTab("Signal", null, panel_4, null);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel_4.add(panel_1, BorderLayout.NORTH);
        panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_1.setLayout(new GridLayout(4, 1, 0, 0));
        
        JToggleButton tglbtnAp = new JToggleButton("AP");
        panel_1.add(tglbtnAp);
        btnGroupSignal.add(tglbtnAp);
        
        //JToggleButton tglbtnRepeater = new JToggleButton("Répéteur");
        //panel_1.add(tglbtnRepeater);
        //btnGroupSignal.add(tglbtnRepeater);
        
        JLabel lblNewLabel = new JLabel("Puissance:");
        panel_1.add(lblNewLabel);
        
        JSlider slider = new JSlider();
        panel_1.add(slider);
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        ButtonGroup btnGroupObstacles = new ButtonGroup();
        
        JPanel panel_3 = new JPanel();
        toolBar.addTab("Obstacles", null, panel_3, null);
        panel_3.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        panel_3.add(panel_2, BorderLayout.NORTH);
        panel_2.setLayout(new GridLayout(5, 1, 0, 0));
        
        JToggleButton tglbtnWall = new JToggleButton("Mur/Cloison");
        btnGroupObstacles.add(tglbtnWall);
        panel_2.add(tglbtnWall);
        
        JToggleButton tglbtnRoom = new JToggleButton("Pièce");
        tglbtnRoom.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent arg0) {
        		test = "room";
        	}
        });
        btnGroupObstacles.add(tglbtnRoom);
        panel_2.add(tglbtnRoom);
        
        JToggleButton tglbtnDoor = new JToggleButton("Porte");
        btnGroupObstacles.add(tglbtnDoor);
        panel_2.add(tglbtnDoor);
        
        JLabel lblNewLabel_1 = new JLabel("Matériau:");
        panel_2.add(lblNewLabel_1);
        
        JComboBox<Material> comboBox = new JComboBox<>();
        panel_2.add(comboBox);
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBox.setModel(new DefaultComboBoxModel<>(Material.values()));
        
        //JPanel panel = new JPanel();
        
        JPanel panel = new Panel();
        panel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		x1 = e.getX();
        		y1 = e.getY();
        	}
            @Override
			public void mouseReleased(MouseEvent e) {
            		x2 = e.getX();
            		y2 = e.getY();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(panel);
        splitPane.setRightComponent(scrollPane);

        splitPane.setDividerLocation(0.20);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
