package com.broteam.tipe.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.File;

import javax.swing.*;

import com.broteam.tipe.model.Model;
import com.broteam.tipe.model.ModelListener;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.signal.Material;
import com.broteam.tipe.signal.Simulation;

public class Window extends JFrame implements ModelListener {

    public final Panel panel;
    private Model model;
    private Simulation simulation = new Simulation();

    private final JComboBox<Material> comboBoxMateriau = new JComboBox<>();
    final JComboBox<AccessPoint> comboBoxAp = new JComboBox<>();
    private JRadioButton rdbtn24ghz = new JRadioButton("2,4 GHz");
    private JRadioButton rdbtn5ghz = new JRadioButton("5 GHz");
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
        fileNew.putValue(AbstractAction.SHORT_DESCRIPTION, text);
        fileNew.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));

        text = "Ouvrir...";
        fileOpen = new AbstractAction(text, new ImageIcon("images/icn_file_open.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileOpen");

                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(Window.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println("Opening: " + file.getName() + ".");
                    try {
                    	setModel(Model.loadFrom(file.getAbsolutePath()));
                    }
                    catch (FileNotFoundException ex) {
                    	ex.printStackTrace();
                    }
                } else {
                	System.out.println("Open command cancelled by user.");
                }
            }
        };
        fileOpen.putValue(AbstractAction.SHORT_DESCRIPTION, text);
        fileOpen.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));

        text = "Enregistrer";
        fileSave = new AbstractAction(text, new ImageIcon("images/icn_file_save.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSave");
                if (model == null) {
                    throw new IllegalStateException("No model created, action fileSave should be disabled.");
                }
                else if (!model.hasBackingFile()) {
                	throw new IllegalStateException("No backing file, action fileSave should be disabled.");
                }
                try {
                    model.save();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        };
        fileSave.putValue(AbstractAction.SHORT_DESCRIPTION, text);
        fileSave.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));

        text = "Enregistrer sous...";
        fileSaveAs = new AbstractAction(text, new ImageIcon("images/icn_file_save.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fileSaveAs");
                if (model == null) {
                    throw new IllegalStateException("No model created, action fileSave should be disabled.");
                }

                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(Window.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println("Saving to file: " + file.getName() + ".");
                    try {
                    	model.saveTo(file.getAbsolutePath());
                    }
                    catch (FileNotFoundException ex) {
                    	ex.printStackTrace();
                    }
                } else {
                	System.out.println("Save to command cancelled by user.");
                }
            }
        };
        fileSaveAs.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Tout effacer";
        actionClear = new AbstractAction(text, new ImageIcon("images/icn_action_clear.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("actionClear");
                model.clear();
                simulation.clear();
                panel.updateSimulationDisplay(simulation);
                panel.repaint();
            }
        };
        actionClear.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Point d'accès";
        toolAp = new AbstractAction(text, new ImageIcon("images/icn_tool_ap.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolAp");
                Brush.setAp();
            }
        };
        toolAp.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Répéteur";
        toolRepeater = new AbstractAction(text, new ImageIcon("images/icn_tool_repeater.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRepeater");
            }
        };
        toolRepeater.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Mur";
        toolWall = new AbstractAction(text, new ImageIcon("images/icn_tool_wall.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolWall");
                Brush.setWall();
            }
        };
        toolWall.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Porte";
        toolDoor = new AbstractAction(text, new ImageIcon("images/icn_tool_door.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolDoor");
                Brush.setDoor();
            }
        };
        toolDoor.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Pièce";
        toolRoom = new AbstractAction(text, new ImageIcon("images/icn_tool_room.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("toolRoom");
                Brush.setRoom();
            }
        };
        toolRoom.putValue(AbstractAction.SHORT_DESCRIPTION, text);

        text = "Simulation";
        actionLaunchSimulation = new AbstractAction(text, new ImageIcon("images/icn_action_launch_simu.png")) {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("actionLaunchSimulation");
                AccessPoint ap = getSelectedAccessPoint();
                if (ap == null) {
                    throw new IllegalStateException(
                            "No access point selected, actionLaunchSimulation should be disabled.");
                }
                simulation.launchSimulation(ap, model, panel.getWidth(), panel.getHeight());
                panel.updateSimulationDisplay(simulation);
                panel.repaint();
            }
        };
        actionLaunchSimulation.putValue(AbstractAction.SHORT_DESCRIPTION, text);
    }

    public Window() {
        super();
        setTitle("Wi-Fi Access Point Broadcasting Simulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Brush brush = new Brush(this);
        panel.addMouseListener(brush);
        panel.addMouseMotionListener(brush);
        setModel(null);

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

        // Populate left side
        JTabbedPane tabBar = new JTabbedPane();
        splitPane.setLeftComponent(tabBar);

        JPanel optionsTab = new JPanel();
        optionsTab.setLayout(new BorderLayout(0, 0));
        tabBar.addTab("Options", null, optionsTab, null);

        JPanel optionsPanel = createOptionsPanel();
        optionsTab.add(optionsPanel, BorderLayout.NORTH);

        // Populate right side
        JScrollPane scrollPane = new JScrollPane(panel);
        splitPane.setRightComponent(scrollPane);
        splitPane.setDividerLocation(0.20);
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
        fileSave.setEnabled(isModelPresent && model.hasBackingFile());
        fileSaveAs.setEnabled(isModelPresent);
        actionClear.setEnabled(isModelPresent);
        actionLaunchSimulation.setEnabled(isModelPresent && !model.getAccessPoints().isEmpty());
        toolAp.setEnabled(isModelPresent);
        toolRepeater.setEnabled(false);
        toolWall.setEnabled(isModelPresent);
        toolRoom.setEnabled(isModelPresent);
        toolDoor.setEnabled(false);
        if (!isModelPresent) {
            return;
        }
        List<AccessPoint> aps = model.getAccessPoints();
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
        actionLaunchSimulation.setEnabled(true);
    }

    @Override
    public void onAccessPointRemoved(AccessPoint ap) {
        System.out.println("access point removed");
        comboBoxAp.removeItem(ap);
        actionLaunchSimulation.setEnabled(!model.getAccessPoints().isEmpty());
    }

    @Override
    public void onCleared() {
        comboBoxAp.removeAllItems();
        actionLaunchSimulation.setEnabled(false);
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
        toolBar.addSeparator();
        toolBar.add(actionLaunchSimulation);
        return toolBar;
    }

    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        optionsPanel.add(Box.createVerticalStrut(5));

        JLabel lblTools = new JLabel("Option des outils");
        lblTools.setFont(new Font("Tahoma", Font.BOLD, 11));
        optionsPanel.add(lblTools);

        optionsPanel.add(Box.createVerticalStrut(10));

        initSignalPanel();
        signalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.add(signalPanel);

        Component verticalStrut_1 = Box.createVerticalStrut(5);
        signalPanel.add(verticalStrut_1);

        JLabel lblFrquence = new JLabel("Fréquence :");
        signalPanel.add(lblFrquence);

        ButtonGroup freqGroup = new ButtonGroup();

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
        
        JCheckBox chckbxAreaBoundaries = new JCheckBox("Afficher les zones exclusives");
        optionsPanel.add(chckbxAreaBoundaries);
        chckbxAreaBoundaries.addItemListener(new ItemListener() {
            @Override
			public void itemStateChanged(ItemEvent e) {
                panel.setDrawAreaBoundaries(chckbxAreaBoundaries.isSelected());
                panel.repaint();
            }
        });
        return optionsPanel;
    }

    private void initSignalPanel() {
        signalPanel = new JPanel();
        signalPanel.setLayout(new BoxLayout(signalPanel, BoxLayout.Y_AXIS));

        JLabel lblPower = new JLabel("Puissance (en mW):");
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

        JLabel lblMateriau = new JLabel("Matériau des obstacles:");
        obstaclePanel.add(lblMateriau);

        obstaclePanel.add(Box.createVerticalStrut(5));

        comboBoxMateriau.setModel(new DefaultComboBoxModel<>(Material.values()));
        comboBoxMateriau.setAlignmentX(Component.LEFT_ALIGNMENT);
        obstaclePanel.add(comboBoxMateriau);
    }

    private void initSimulationPanel() {
        simulationPanel = new JPanel();
        simulationPanel.setLayout(new BoxLayout(simulationPanel, BoxLayout.Y_AXIS));

        JLabel lblSimulation = new JLabel("Simulation");
        lblSimulation.setFont(new Font("Tahoma", Font.BOLD, 11));
        simulationPanel.add(lblSimulation);

        simulationPanel.add(Box.createVerticalStrut(10));

        JLabel lblSlectionnezUnPoint = new JLabel("Sélectionnez un Point d'Accès :");
        simulationPanel.add(lblSlectionnezUnPoint);

        simulationPanel.add(Box.createVerticalStrut(5));

        Box hBox = Box.createHorizontalBox();
        hBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        simulationPanel.add(hBox);

        comboBoxAp.setAlignmentX(Component.LEFT_ALIGNMENT);
        hBox.add(comboBoxAp);

        JButton btnLaunch = new JButton(actionLaunchSimulation);
        btnLaunch.setHideActionText(true);
        hBox.add(btnLaunch);
    }

}
