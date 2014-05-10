package com.broteam.tipe.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;

import com.broteam.tipe.element.AccessPoint;
import com.broteam.tipe.signal.Material;

public class Window extends JFrame {

    public Panel panel = new Panel();

    private final ButtonGroup btnGroupSignal = new ButtonGroup();
    private JComboBox<Material> comboBoxMateriau = new JComboBox<Material>();
    private JSlider slider;
	private JComboBox<AccessPoint> comboBoxAp = new JComboBox<AccessPoint>();

    public Window() {
        super();
        setTitle("Wi-Fi Access Point Broadcasting Simulator");

        Brush brush = new Brush(this);
        panel.addMouseListener(brush);
        panel.addMouseMotionListener(brush);

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
        mntmToutEffacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panel.clear();
            }
        });
        mnEdition.add(mntmToutEffacer);

        JSplitPane splitPane = new JSplitPane();
        getContentPane().add(splitPane, BorderLayout.CENTER);

        JTabbedPane tabBar = new JTabbedPane();
        splitPane.setLeftComponent(tabBar);

        JPanel panel_signal = new JPanel();
        tabBar.addTab("Signal", null, panel_signal, null);
        panel_signal.setLayout(new BorderLayout(0, 0));

        JPanel panel_signal_interieur = new JPanel();
        panel_signal.add(panel_signal_interieur, BorderLayout.NORTH);
        panel_signal_interieur.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_signal_interieur.setLayout(new GridLayout(4, 1, 0, 0));

        JToggleButton tglbtnAp = new JToggleButton("AP");
        panel_signal_interieur.add(tglbtnAp);
        btnGroupSignal.add(tglbtnAp);
        tglbtnAp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                com.broteam.tipe.ui.Brush.setAP();
            }
        });

        /*
         * JToggleButton tglbtnRepeater = new JToggleButton("Répéteur");
         * panel_1.add(tglbtnRepeater); btnGroupSignal.add(tglbtnRepeater);
         */

        JLabel lblPower = new JLabel("Puissance (en mW):");
        panel_signal_interieur.add(lblPower);

        // Pour les puissances voir ici http://en.wikipedia.org/wiki/DBm
        // http://assistance.orange.fr/le-wi-fi-et-la-sante-770.php#
        slider = new JSlider(SwingConstants.HORIZONTAL, 100, 1000, 100);
        slider.setMinorTickSpacing(100);
        slider.setMajorTickSpacing(300);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_signal_interieur.add(slider);

        ButtonGroup btnGroupObstacles = new ButtonGroup();

        JPanel panel_obstacle = new JPanel();
        tabBar.addTab("Obstacles", null, panel_obstacle, null);
        panel_obstacle.setLayout(new BorderLayout(0, 0));

        JPanel panel_obstacle_interieur = new JPanel();
        panel_obstacle.add(panel_obstacle_interieur, BorderLayout.NORTH);
        panel_obstacle_interieur.setLayout(new GridLayout(5, 1, 0, 0));

        JToggleButton tglbtnWall = new JToggleButton("Mur/Cloison");
        tglbtnWall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                com.broteam.tipe.ui.Brush.setWall();
            }
        });
        btnGroupObstacles.add(tglbtnWall);
        panel_obstacle_interieur.add(tglbtnWall);

        JToggleButton tglbtnRoom = new JToggleButton("Pièce");
        tglbtnRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                com.broteam.tipe.ui.Brush.setRoom();
            }
        });
        btnGroupObstacles.add(tglbtnRoom);
        panel_obstacle_interieur.add(tglbtnRoom);

        /*
         * JToggleButton tglbtnDoor = new JToggleButton("Porte");
         * tglbtnDoor.addActionListener(new ActionListener() {
         * @Override public void actionPerformed(ActionEvent arg0) {
         * com.broteam.tipe.shape.Pinceau.setDoor(); } });
         * btnGroupObstacles.add(tglbtnDoor); panel_2.add(tglbtnDoor);
         */

        JLabel lblMateriau = new JLabel("Matériau:");
        panel_obstacle_interieur.add(lblMateriau);

        panel_obstacle_interieur.add(comboBoxMateriau);
        comboBoxMateriau.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBoxMateriau.setModel(new DefaultComboBoxModel<Material>(Material.values()));

        JPanel panel_simulation = new JPanel();
        tabBar.addTab("Simulation", null, panel_simulation, null);

        JPanel panel_simulation_interieur = new JPanel();
        panel_simulation.add(panel_simulation_interieur);
        panel_simulation_interieur.setLayout(new BorderLayout(0, 0));

        JLabel lblSlectionnezUnPoint = new JLabel("Sélectionnez un Point d'Accès :");
        panel_simulation_interieur.add(lblSlectionnezUnPoint, BorderLayout.NORTH);

        panel_simulation_interieur.add(comboBoxAp, BorderLayout.CENTER);
        
        JButton btnRafrachir = new JButton("Rafraîchir");
        panel_simulation.add(btnRafrachir);
        btnRafrachir.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		comboBoxAp.removeAllItems();
        		LinkedList<AccessPoint> aps = panel.getApsList();
        		for (AccessPoint ap : aps) {
        			comboBoxAp.addItem(ap);
        		}
        	}
        });
        
        JButton btnSimulation = new JButton("Lancer la simulation !");
        panel_simulation_interieur.add(btnSimulation, BorderLayout.SOUTH);
        
        btnSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!panel.getApsList().isEmpty()) {
                    panel.launchSimulation((AccessPoint) comboBoxAp.getSelectedItem());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        splitPane.setRightComponent(scrollPane);
        splitPane.setDividerLocation(0.20);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public Material getSelectedMaterial() {
        return (Material) this.comboBoxMateriau.getSelectedItem();
    }

    public int getSliderValue() {
        return this.slider.getValue();
    }

}
