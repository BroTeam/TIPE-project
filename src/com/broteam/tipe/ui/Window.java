package com.broteam.tipe.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.LinkedList;

import javax.swing.*;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.signal.Material;

public class Window extends JFrame {

    public final Panel panel;

    private final ButtonGroup btnGroup = new ButtonGroup();
    private final JComboBox<Material> comboBoxMateriau = new JComboBox<>();
    private final JSlider slider;
    private final JComboBox<AccessPoint> comboBoxAp = new JComboBox<>();

    private final Action fileNew;
    private final Action fileOpen;
    private final Action fileSave;
    private final Action fileSaveAs;
    private final Action actionClear;
    
    private final Action toolAp;
    private final Action toolRepeater;
    private final Action toolWall;
    private final Action toolDoor;
    private final Action toolRoom;

    {
        panel = new Panel(this);
        
        fileNew = new AbstractAction("Nouveau", new ImageIcon("images/icn_new_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileNew");
                panel.setModel(new Model());
            }
        };
        fileNew.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        fileOpen = new AbstractAction("Ouvrir...", new ImageIcon("images/icn_open_18x14.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileOpen");
            }
        };
        fileOpen.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        fileSave = new AbstractAction("Enregistrer", new ImageIcon("images/icn_save_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSave");
            }
        };
        fileSave.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        fileSaveAs = new AbstractAction("Enregistrer sous...", new ImageIcon(
                "images/icn_save_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSaveAs");
            }
        };
        actionClear = new AbstractAction("Tout effacer", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("actionClear");
                panel.getModel().clear();
            }
        };
        toolAp = new AbstractAction("Point d'accès", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolAp");
                Brush.setAp();
            }
        };
        toolRepeater = new AbstractAction("Répéteur", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRepeater");
            }
        };
        toolWall = new AbstractAction("Mur", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolWall");
                Brush.setWall();
            }
        };
        toolRoom = new AbstractAction("Pièce", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRoom");
                Brush.setRoom();
            }
        };
        toolDoor = new AbstractAction("Porte", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolDoor");
                Brush.setDoor();
            }
        };
    }
    
    public void onModelChanged(Model m) {
        boolean enable = m != null;
        fileSave.setEnabled(enable);
        fileSaveAs.setEnabled(enable);
        actionClear.setEnabled(enable);
        toolAp.setEnabled(enable);
        toolRepeater.setEnabled(false);
        toolWall.setEnabled(enable);
        toolRoom.setEnabled(enable);
        toolDoor.setEnabled(false);
    }

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

        mnFichier.add(fileNew);
        mnFichier.add(fileOpen);
        mnFichier.add(fileSave);
        mnFichier.add(fileSaveAs);

        JMenu mnEdition = new JMenu("Edition");
        menuBar.add(mnEdition);

        mnEdition.add(actionClear);
        
        JMenu mnTools = new JMenu("Outils");
        menuBar.add(mnTools);

        mnTools.add(toolAp);
        mnTools.add(toolRepeater);
        mnTools.add(toolWall);
        mnTools.add(toolRoom);
        mnTools.add(toolDoor);

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

        JToggleButton tglbtnAp = new JToggleButton(toolAp);
        JToggleButton tglbtnRepeater = new JToggleButton(toolRepeater);
        btnGroup.add(tglbtnAp);
        btnGroup.add(tglbtnRepeater);
        panel_signal_interieur.add(tglbtnAp);
        panel_signal_interieur.add(tglbtnRepeater);

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

        JToggleButton tglbtnWall = new JToggleButton(toolWall);
        JToggleButton tglbtnRoom = new JToggleButton(toolRoom);
        JToggleButton tglbtnDoor = new JToggleButton(toolDoor);
        btnGroupObstacles.add(tglbtnWall);
        btnGroupObstacles.add(tglbtnRoom);
        btnGroupObstacles.add(tglbtnDoor);
        panel_obstacle_interieur.add(tglbtnWall);
        panel_obstacle_interieur.add(tglbtnRoom);
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
                    panel.repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        splitPane.setRightComponent(scrollPane);
        splitPane.setDividerLocation(0.20);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setModel(null);
    }

    public Material getSelectedMaterial() {
        return (Material) this.comboBoxMateriau.getSelectedItem();
    }

    public int getPowerValue() {
        return this.slider.getValue();
    }

}
