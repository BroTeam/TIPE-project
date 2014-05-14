package com.broteam.tipe.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.List;

import javax.swing.*;
import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.ModelListener;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.signal.Material;

public class Window extends JFrame implements ModelListener {

    public final Panel panel;
    private Model model;

    private final JComboBox<Material> comboBoxMateriau = new JComboBox<>();
    private final JComboBox<AccessPoint> comboBoxAp = new JComboBox<>();
    private JSlider slider;

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

        String text;

        text = "Nouveau";
        fileNew = new AbstractAction(text, new ImageIcon("images/icn_new_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileNew");
                setModel(new Model());
            }
        };
        fileNew.putValue(AbstractAction.SHORT_DESCRIPTION, text);
        fileNew.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));

        text = "Ouvrir...";
        fileOpen = new AbstractAction(text, new ImageIcon("images/icn_open_18x14.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileOpen");
            }
        };
        fileOpen.putValue(AbstractAction.SHORT_DESCRIPTION, text);
        fileOpen.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));

        text = "Enregistrer";
        fileSave = new AbstractAction("Enregistrer", new ImageIcon("images/icn_save_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSave");
            }
        };
        fileSave.putValue(AbstractAction.SHORT_DESCRIPTION, text);
        fileSave.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));

        text = "Enregistrer sous...";
        fileSaveAs = new AbstractAction("Enregistrer sous...", new ImageIcon("images/icn_save_16.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSaveAs");
            }
        };
        fileSaveAs.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Tout effacer";
        actionClear = new AbstractAction("Tout effacer", new ImageIcon("images/icn_clear_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("actionClear");
                model.clear();
                panel.getSimulation().clear();
                panel.repaint();
            }
        };
        actionClear.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Point d'accès";
        toolAp = new AbstractAction("Point d'accès", new ImageIcon("images/icn_ap_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolAp");
                Brush.setAp();
            }
        };
        toolAp.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Répéteur";
        toolRepeater = new AbstractAction("Répéteur", new ImageIcon("images/icn_repeater_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRepeater");
            }
        };
        toolRepeater.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Mur";
        toolWall = new AbstractAction("Mur", new ImageIcon("images/icn_line1_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolWall");
                Brush.setWall();
            }
        };
        toolWall.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Porte";
        toolDoor = new AbstractAction("Porte", new ImageIcon("images/icn_door_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolDoor");
                Brush.setDoor();
            }
        };
        toolDoor.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Pièce";
        toolRoom = new AbstractAction("Pièce", new ImageIcon("images/icn_rectangle_16.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRoom");
                Brush.setRoom();
            }
        };
        toolRoom.putValue(AbstractAction.SHORT_DESCRIPTION, text);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        if (this.model != null) {
            this.model.unregisterListener(this);
        }
        this.model = model;
        boolean isModelPresent = model != null;
        fileSave.setEnabled(isModelPresent);
        fileSaveAs.setEnabled(isModelPresent);
        actionClear.setEnabled(isModelPresent);
        toolAp.setEnabled(isModelPresent);
        toolRepeater.setEnabled(false);
        toolWall.setEnabled(isModelPresent);
        toolRoom.setEnabled(isModelPresent);
        toolDoor.setEnabled(false);
        if (!isModelPresent) {
            return;
        }
        List<AccessPoint> aps = model.getAccessPoints();
        comboBoxAp.setModel(new DefaultComboBoxModel<>(aps.toArray(new AccessPoint[aps.size()])));
        model.registerListener(this);
    }

    @Override
    public void onAccessPointAdded(AccessPoint ap) {
        System.out.println("access point added");
        comboBoxAp.addItem(ap);
    }

    @Override
    public void onAccessPointRemoved(AccessPoint ap) {
        System.out.println("access point removed");
        comboBoxAp.removeItem(ap);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu mnFichier = new JMenu("Fichier");
        mnFichier.add(fileNew);
        mnFichier.add(fileOpen);
        mnFichier.add(fileSave);
        mnFichier.add(fileSaveAs);
        menuBar.add(mnFichier);

        JMenu mnEdition = new JMenu("Edition");
        mnEdition.add(actionClear);
        menuBar.add(mnEdition);

        JMenu mnTools = new JMenu("Outils");
        mnTools.add(toolAp);
        mnTools.add(toolRepeater);
        mnTools.addSeparator();
        mnTools.add(toolWall);
        mnTools.add(toolDoor);
        mnTools.add(toolRoom);
        menuBar.add(mnTools);

        return menuBar;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.add(fileNew);
        toolBar.add(fileOpen);
        toolBar.add(fileSave);
        toolBar.add(fileSaveAs);
        toolBar.addSeparator();
        toolBar.add(toolAp);
        toolBar.add(toolRepeater);
        toolBar.addSeparator();
        toolBar.add(toolWall);
        toolBar.add(toolDoor);
        toolBar.add(toolRoom);
        toolBar.addSeparator();
        toolBar.add(actionClear);
        return toolBar;
    }

    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.setLayout(new GridLayout(5, 1, 0, 0));

        JLabel lblPower = new JLabel("Puissance (en mW):");
        optionsPanel.add(lblPower);

        // Pour les puissances voir ici http://en.wikipedia.org/wiki/DBm
        // http://assistance.orange.fr/le-wi-fi-et-la-sante-770.php#
        slider = new JSlider(SwingConstants.HORIZONTAL, 100, 1000, 100);
        slider.setMinorTickSpacing(100);
        slider.setMajorTickSpacing(300);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.add(slider);

        JLabel lblMateriau = new JLabel("Matériau des obstacles:");
        optionsPanel.add(lblMateriau);

        optionsPanel.add(comboBoxMateriau);
        comboBoxMateriau.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBoxMateriau.setModel(new DefaultComboBoxModel<>(Material.values()));
        return optionsPanel;
    }

    private JPanel createSimulationPanel() {
        JPanel simulationPanel = new JPanel();
        simulationPanel.setLayout(new BorderLayout(0, 0));

        JLabel lblSlectionnezUnPoint = new JLabel("Sélectionnez un Point d'Accès :");
        simulationPanel.add(lblSlectionnezUnPoint, BorderLayout.NORTH);
        simulationPanel.add(comboBoxAp, BorderLayout.CENTER);

        JButton btnSimulation = new JButton("Lancer la simulation !");
        simulationPanel.add(btnSimulation, BorderLayout.SOUTH);

        btnSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!model.getAccessPoints().isEmpty()) {
                    panel.getSimulation().launchSimulation((AccessPoint) comboBoxAp.getSelectedItem(),
                            model.getObstacles(), panel.getWidth(), panel.getHeight());
                    panel.repaint();
                }
            }
        });
        return simulationPanel;
    }

    public Window() {
        super();
        setTitle("Wi-Fi Access Point Broadcasting Simulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Brush brush = new Brush(this);
        panel.addMouseListener(brush);
        panel.addMouseMotionListener(brush);

        // add menu bar
        getContentPane().add(createMenuBar(), BorderLayout.NORTH);

        // create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // add tool bar
        mainPanel.add(createToolBar(), BorderLayout.NORTH);

        // create split pane
        JSplitPane splitPane = new JSplitPane();
        mainPanel.add(splitPane, BorderLayout.CENTER);

        /*
         * Populate right side
         */

        JScrollPane scrollPane = new JScrollPane(panel);
        splitPane.setRightComponent(scrollPane);
        splitPane.setDividerLocation(0.20);

        /*
         * Populate left side
         */

        JTabbedPane tabBar = new JTabbedPane();
        splitPane.setLeftComponent(tabBar);

        JPanel optionsTab = new JPanel();
        optionsTab.setLayout(new BorderLayout(0, 0));
        optionsTab.add(createOptionsPanel(), BorderLayout.NORTH);
        tabBar.addTab("Options", null, optionsTab, null);

        JPanel simulationTab = new JPanel();
        simulationTab.add(createSimulationPanel());
        tabBar.addTab("Simulation", null, simulationTab, null);

        setModel(null);
    }

    public Material getSelectedMaterial() {
        return (Material) this.comboBoxMateriau.getSelectedItem();
    }

    public int getPowerValue() {
        return this.slider.getValue();
    }

}
