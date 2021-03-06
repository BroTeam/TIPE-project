package com.broteam.tipe.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.ModelListener;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.Simulation;

public class Window extends JFrame implements ModelListener {

    public final Panel panel;
    private Model model;
    private final Simulation simulation = new Simulation();
    private boolean displayingSimulation = false;

    private final JComboBox<Material> comboBoxMateriau = new JComboBox<>();
    final JComboBox<AccessPoint> comboBoxAp = new JComboBox<>();
    private final JRadioButton rdbtn24ghz = new JRadioButton("2,4 GHz");
    private final JRadioButton rdbtn5ghz = new JRadioButton("5 GHz");
    private JSlider slider;
    private JPanel signalPanel;
    private JPanel obstaclePanel;
    private JPanel simulationPanel;

    private final Action fileNew;
    private final Action fileOpen;
    private final Action fileSave;
    private final Action fileSaveAs;

    private final Action toolAp;
    private final Action toolRepeater;
    private final Action toolWall;
    private final Action toolDoor;
    private final Action toolRoom;

    private final Action actionClear;
    private final Action actionLaunchSimulation;
    private final Action actionStopSimulation;
    private JButton btnSimulation;

    {
        panel = new Panel(this);

        String text;

        text = "Nouveau";
        fileNew = new AbstractAction(text, new ImageIcon("images/icn_file_new.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileNew");
                setModel(new Model());
                panel.repaint();
            }
        };
        fileNew.putValue(Action.SHORT_DESCRIPTION, text);
        fileNew.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));

        text = "Ouvrir...";
        fileOpen = new AbstractAction(text, new ImageIcon("images/icn_file_open.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileOpen");

                final JFileChooser fc = new JFileChooser();
                final int returnVal = fc.showOpenDialog(Window.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File file = fc.getSelectedFile();
                    System.out.println("Opening: " + file.getName() + ".");
                    try {
                        if (model != null) {
                            model.clear();
                        }
                        setSimulation(false);
                        setModel(Model.loadFrom(file.getAbsolutePath()));
                    } catch (final IOException ex) {
                        ex.printStackTrace(); // FIXME
                    }
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        };
        fileOpen.putValue(Action.SHORT_DESCRIPTION, text);
        fileOpen.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));

        text = "Enregistrer";
        fileSave = new AbstractAction(text, new ImageIcon("images/icn_file_save.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSave");
                if (model == null) {
                    throw new IllegalStateException("No model created, action fileSave should be disabled.");
                } else if (!model.hasBackingFile()) {
                    throw new IllegalStateException("No backing file, action fileSave should be disabled.");
                }
                try {
                    model.save();
                } catch (final IOException ex) {
                    ex.printStackTrace(); // FIXME
                }
            }
        };
        fileSave.putValue(Action.SHORT_DESCRIPTION, text);
        fileSave.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));

        text = "Enregistrer sous...";
        fileSaveAs = new AbstractAction(text, new ImageIcon("images/icn_file_save.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSaveAs");
                if (model == null) {
                    throw new IllegalStateException("No model created, action fileSave should be disabled.");
                }

                final JFileChooser fc = new JFileChooser();
                final int returnVal = fc.showSaveDialog(Window.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File file = fc.getSelectedFile();
                    System.out.println("Saving to file: " + file.getName() + ".");
                    try {
                        model.saveTo(file.getAbsolutePath());
                    } catch (final IOException ex) {
                        ex.printStackTrace(); // FIXME
                    }
                } else {
                    System.out.println("Save to command cancelled by user.");
                }
            }
        };
        fileSaveAs.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Tout effacer";
        actionClear = new AbstractAction(text, new ImageIcon("images/icn_action_clear.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("actionClear");
                model.clear();
                setSimulation(false);
            }
        };
        actionClear.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Point d'accès";
        toolAp = new AbstractAction(text, new ImageIcon("images/icn_tool_ap.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolAp");
                Brush.setAp();
            }
        };
        toolAp.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Répéteur";
        toolRepeater = new AbstractAction(text, new ImageIcon("images/icn_tool_repeater.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRepeater");
            }
        };
        toolRepeater.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Mur";
        toolWall = new AbstractAction(text, new ImageIcon("images/icn_tool_wall.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolWall");
                Brush.setWall();
            }
        };
        toolWall.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Porte";
        toolDoor = new AbstractAction(text, new ImageIcon("images/icn_tool_door.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolDoor");
                Brush.setDoor();
            }
        };
        toolDoor.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Pièce";
        toolRoom = new AbstractAction(text, new ImageIcon("images/icn_tool_room.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRoom");
                Brush.setRoom();
            }
        };
        toolRoom.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Start Simulation";
        actionLaunchSimulation = new AbstractAction(text, new ImageIcon("images/icn_action_launch_simu.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("actionLaunchSimulation");
                final AccessPoint ap = getSelectedAccessPoint();
                if (ap == null) {
                    throw new IllegalStateException(
                            "No access point selected, actionLaunchSimulation should be disabled.");
                }
                setSimulation(true);
            }
        };
        actionLaunchSimulation.putValue(Action.SHORT_DESCRIPTION, text);

        text = "Stop Simulation";
        actionStopSimulation = new AbstractAction(text, new ImageIcon("images/icn_action_stop_simu.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("actionStopSimulation");
                setSimulation(false);
            }
        };
        actionStopSimulation.putValue(Action.SHORT_DESCRIPTION, text);
    }

    public Window() {
        super();
        setTitle("Wi-Fi Access Point Broadcasting Simulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Brush brush = new Brush(this);
        panel.addMouseListener(brush);
        panel.addMouseMotionListener(brush);

        // add menu bar
        getContentPane().add(createMenuBar(), BorderLayout.NORTH);

        // create main panel
        final JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // add tool bar
        mainPanel.add(createToolBar(), BorderLayout.NORTH);

        // create split pane
        final JSplitPane splitPane = new JSplitPane();
        mainPanel.add(splitPane, BorderLayout.CENTER);

        // Populate left side
        final JTabbedPane tabBar = new JTabbedPane();
        splitPane.setLeftComponent(tabBar);

        final JPanel optionsTab = new JPanel();
        optionsTab.setLayout(new BorderLayout(0, 0));
        tabBar.addTab("Options", null, optionsTab, null);

        final JPanel optionsPanel = createOptionsPanel();
        optionsTab.add(optionsPanel, BorderLayout.NORTH);

        // Populate right side
        final JScrollPane scrollPane = new JScrollPane(panel);
        splitPane.setRightComponent(scrollPane);
        splitPane.setDividerLocation(0.20);

        setModel(null);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        setSimulation(false);
        if (this.model != null) {
            this.model.unregisterListener(this);
        }
        this.model = model;
        final boolean isModelPresent = model != null;
        fileSave.setEnabled(isModelPresent && model.hasBackingFile());
        fileSaveAs.setEnabled(isModelPresent);
        actionClear.setEnabled(isModelPresent);
        actionLaunchSimulation.setEnabled(isModelPresent && !model.getAccessPoints().isEmpty());
        actionStopSimulation.setEnabled(false);
        toolAp.setEnabled(isModelPresent);
        toolRepeater.setEnabled(false);
        toolWall.setEnabled(isModelPresent);
        toolRoom.setEnabled(isModelPresent);
        toolDoor.setEnabled(false);
        if (!isModelPresent) {
            return;
        }
        final List<AccessPoint> aps = model.getAccessPoints();
        comboBoxAp.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBoxAp.setModel(new DefaultComboBoxModel<>(aps.toArray(new AccessPoint[aps.size()])));
        model.registerListener(this);
    }

    public Material getSelectedMaterial() {
        return (Material) this.comboBoxMateriau.getSelectedItem();
    }

    public double getSelectedPower() {
        return this.slider.getValue();
    }

    public double getSelectedFrequency() {
        if (rdbtn24ghz.isSelected()) {
            return 2.4;
        } else {
            return 5;
        }
    }

    private AccessPoint getSelectedAccessPoint() {
        return (AccessPoint) comboBoxAp.getSelectedItem();
    }

    @Override
    public void onAccessPointAdded(AccessPoint ap) {
        System.out.println("access point added");
        comboBoxAp.addItem(ap);
        actionLaunchSimulation.setEnabled(!displayingSimulation);
    }

    @Override
    public void onAccessPointRemoved(AccessPoint ap) {
        System.out.println("access point removed");
        comboBoxAp.removeItem(ap);
        actionLaunchSimulation.setEnabled(!displayingSimulation && !model.getAccessPoints().isEmpty());
    }

    @Override
    public void onCleared() {
        comboBoxAp.removeAllItems();
        actionLaunchSimulation.setEnabled(false);
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu mnFichier = new JMenu("Fichier");
        mnFichier.add(fileNew);
        mnFichier.add(fileOpen);
        mnFichier.add(fileSave);
        mnFichier.add(fileSaveAs);
        menuBar.add(mnFichier);

        final JMenu mnEdition = new JMenu("Edition");
        mnEdition.add(actionClear);
        menuBar.add(mnEdition);

        final JMenu mnTools = new JMenu("Outils");
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
        final JToolBar toolBar = new JToolBar();
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
        toolBar.addSeparator();
        toolBar.add(actionLaunchSimulation);
        toolBar.add(actionStopSimulation);
        return toolBar;
    }

    private JPanel createOptionsPanel() {
        final JPanel optionsPanel = new JPanel();
        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        optionsPanel.add(Box.createVerticalStrut(5));

        final JLabel lblTools = new JLabel("Option des outils");
        lblTools.setFont(new Font("Tahoma", Font.BOLD, 11));
        optionsPanel.add(lblTools);

        optionsPanel.add(Box.createVerticalStrut(10));

        initSignalPanel();
        signalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.add(signalPanel);

        final Component verticalStrut_1 = Box.createVerticalStrut(5);
        signalPanel.add(verticalStrut_1);

        final JLabel lblFrquence = new JLabel("Fréquence :");
        signalPanel.add(lblFrquence);

        final ButtonGroup freqGroup = new ButtonGroup();

        rdbtn24ghz.setSelected(true);
        signalPanel.add(rdbtn24ghz);
        freqGroup.add(rdbtn24ghz);

        signalPanel.add(rdbtn5ghz);
        optionsPanel.add(Box.createVerticalStrut(10));
        freqGroup.add(rdbtn5ghz);

        initObstaclePanel();
        obstaclePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.add(obstaclePanel);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(new JSeparator());
        optionsPanel.add(Box.createVerticalStrut(10));

        initSimulationPanel();
        simulationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.add(simulationPanel);
        optionsPanel.add(Box.createVerticalStrut(5));

        final JCheckBox chckbxAreaBoundaries = new JCheckBox("Afficher les zones exclusives");
        optionsPanel.add(chckbxAreaBoundaries);
        chckbxAreaBoundaries.addItemListener(e -> {
            panel.setDrawAreaBoundaries(chckbxAreaBoundaries.isSelected());
            panel.repaint();
        });
        return optionsPanel;
    }

    private void initSignalPanel() {
        signalPanel = new JPanel();
        signalPanel.setLayout(new BoxLayout(signalPanel, BoxLayout.Y_AXIS));

        final JLabel lblPower = new JLabel("Puissance (en mW):");
        signalPanel.add(lblPower);

        signalPanel.add(Box.createVerticalStrut(5));

        // Pour les puissances voir ici http://en.wikipedia.org/wiki/DBm
        // http://assistance.orange.fr/le-wi-fi-et-la-sante-770.php#
        slider = new JSlider(SwingConstants.HORIZONTAL, 100, 1000, 100);
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);
        slider.setMinorTickSpacing(100);
        slider.setMajorTickSpacing(300);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        signalPanel.add(slider);
    }

    private void initObstaclePanel() {
        obstaclePanel = new JPanel();
        obstaclePanel.setLayout(new BoxLayout(obstaclePanel, BoxLayout.Y_AXIS));

        final JLabel lblMateriau = new JLabel("Matériau des obstacles:");
        obstaclePanel.add(lblMateriau);

        obstaclePanel.add(Box.createVerticalStrut(5));

        comboBoxMateriau.setModel(new DefaultComboBoxModel<>(Material.values()));
        comboBoxMateriau.setAlignmentX(Component.LEFT_ALIGNMENT);
        obstaclePanel.add(comboBoxMateriau);
    }

    private void initSimulationPanel() {
        simulationPanel = new JPanel();
        simulationPanel.setLayout(new BoxLayout(simulationPanel, BoxLayout.Y_AXIS));

        final JLabel lblSimulation = new JLabel("Simulation");
        lblSimulation.setFont(new Font("Tahoma", Font.BOLD, 11));
        simulationPanel.add(lblSimulation);

        simulationPanel.add(Box.createVerticalStrut(10));

        final JLabel lblSlectionnezUnPoint = new JLabel("Sélectionnez un Point d'Accès :");
        simulationPanel.add(lblSlectionnezUnPoint);

        simulationPanel.add(Box.createVerticalStrut(5));

        final Box hBox = Box.createHorizontalBox();
        hBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        simulationPanel.add(hBox);

        comboBoxAp.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBoxAp.addActionListener((e) -> {
            if (model != null) {
                final AccessPoint selected = getSelectedAccessPoint();
                for (final AccessPoint ap : model.getAccessPoints()) {
                    ap.setSelected(ap == selected);
                }
                if (selected != null && displayingSimulation) {
                    simulation.launchSimulation(selected, model, panel.getWidth(), panel.getHeight());
                    panel.updateSimulationDisplay(simulation);
                }
                panel.repaint();
            }
        });
        hBox.add(comboBoxAp);

        btnSimulation = new JButton(actionLaunchSimulation);
        btnSimulation.setHideActionText(true);
        hBox.add(btnSimulation);
    }

    private void setSimulation(boolean simulating) {
        if (simulating) {
            simulation.launchSimulation(getSelectedAccessPoint(), model, panel.getWidth(), panel.getHeight());
        } else {
            simulation.clear();
        }
        panel.updateSimulationDisplay(simulation);
        panel.repaint();
        displayingSimulation = simulating;
        actionLaunchSimulation.setEnabled(!simulating);
        actionStopSimulation.setEnabled(simulating);
        btnSimulation.setAction(simulating ? actionStopSimulation : actionLaunchSimulation);
        toolWall.setEnabled(!simulating);
        toolRoom.setEnabled(!simulating);
        Brush.setNoTool();
    }

}
