package com.broteam.tipe.ui;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.LinkedList;

import javax.swing.*;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.signal.Material;

public class Window extends JFrame {

    public Panel panel = new Panel();

    private final ButtonGroup btnGroupSignal = new ButtonGroup();
    private JComboBox<Material> comboBoxMateriau = new JComboBox<>();
    private JSlider slider;
    private JComboBox<AccessPoint> comboBoxAp = new JComboBox<>();

    private Action newFile;
    private Action openFile;
    private Action saveFile;
    private Action saveFileAs;
    private Action clear;

    private void createActions() {
        newFile = new AbstractAction("Nouveau", new ImageIcon("images/icn_new_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New file");
            }
        };
        newFile.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        openFile = new AbstractAction("Ouvrir...", new ImageIcon("images/icn_open_18x14.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open file");
            }
        };
        openFile.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        saveFile = new AbstractAction("Enregistrer", new ImageIcon("images/icn_save_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save file");
            }
        };
        saveFile.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        saveFileAs = new AbstractAction("Enregistrer sous...", new ImageIcon(
                "images/icn_save_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save file as");
            }
        };
        clear = new AbstractAction("Tout effacer", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panel.getModel().clear();
            }
        };
    }

    public Window() {
        super();
        setTitle("Wi-Fi Access Point Broadcasting Simulator");
        createActions();

        Brush brush = new Brush(this);
        panel.addMouseListener(brush);
        panel.addMouseMotionListener(brush);

        JMenuBar menuBar = new JMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu mnFichier = new JMenu("Fichier");
        menuBar.add(mnFichier);

        mnFichier.add(newFile);
        mnFichier.add(openFile);
        mnFichier.add(saveFile);
        mnFichier.add(saveFileAs);

        JMenu mnEdition = new JMenu("Edition");
        menuBar.add(mnEdition);

        mnEdition.add(clear);

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

        JToggleButton tglbtnRepeater = new JToggleButton("Répéteur");
        tglbtnRepeater.setEnabled(false);
        panel_signal_interieur.add(tglbtnRepeater);
        btnGroupSignal.add(tglbtnRepeater);

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

        JToggleButton tglbtnDoor = new JToggleButton("Porte");
        tglbtnDoor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Brush.setDoor();
            }
        });
        tglbtnDoor.setEnabled(false);
        btnGroupObstacles.add(tglbtnDoor);
        panel_obstacle_interieur.add(tglbtnDoor);

        JLabel lblMateriau = new JLabel("Matériau:");
        panel_obstacle_interieur.add(lblMateriau);

        panel_obstacle_interieur.add(comboBoxMateriau);
        comboBoxMateriau.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBoxMateriau.setModel(new DefaultComboBoxModel<>(Material.values()));

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
                LinkedList<AccessPoint> aps =panel.getModel().getAccessPoints();
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
                if (!panel.getModel().getAccessPoints().isEmpty()) {
                    panel.getSimulation().launchSimulation((AccessPoint) comboBoxAp.getSelectedItem(), panel.getModel().getObstacles(), panel.getWidth(), panel.getHeight());
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

    public int getPowerValue() {
        return this.slider.getValue();
    }

}
