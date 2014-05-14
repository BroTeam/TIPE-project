package com.broteam.tipe.model;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.ui.Panel;

public class Model {

    /** Name of the file used for the last save */
    private String backingFile;

    private LinkedList<Element> elements = new LinkedList<>();
    private LinkedList<Obstacle> obstacles = new LinkedList<>();
    private LinkedList<AccessPoint> aps = new LinkedList<>();

    // TODO complete model

    /**
     * Adds the specified {@link Element} to this {@link Model}.
     *
     * @param element
     *            The {@link Element} to add.
     */
    public void add(Element element) {
        elements.add(element);
        if (element instanceof Obstacle) {
            obstacles.add((Obstacle) element);
        }
        if (element instanceof AccessPoint) {
            aps.add((AccessPoint) element);
        }
    }
    
    /**
     * Replaces the last added {@link Obstacle} by the specified {@link Obstacle}.
     *
     * @param obstacle
     *            The {@link Obstacle} to place instead of the last one.
     */
    public void replaceLast(Obstacle obstacle) {
    	obstacles.removeLast();
    	elements.removeLast();
    	obstacles.add(obstacle);
    	elements.add(obstacle);
        //repaint();
    }
    
    /**
     * Replaces the last added {@link AccessPoint} by the specified {@link AccessPoint}.
     *
     * @param ap
     *            The {@link AccessPoint} to place instead of the last one.
     */
    public void replaceLast(AccessPoint ap) {
    	aps.removeLast();
    	elements.removeLast();
    	aps.add(ap);
    	elements.add(ap);
        //repaint();
    }
    
    /**
     * Removes the specified {@link Obstacle} to this {@link Model}.
     *
     * @param obstacle
     *            The {@link Obstacle} to remove.
     */
    public void remove(Obstacle obstacle) {
    	obstacles.remove(obstacle);
        elements.remove(obstacle);
        //repaint();
    }
    
    /**
     * Removes the specified {@link AccessPoint} to this {@link Model}.
     *
     * @param ap
     *            The {@link AccessPoint} to remove.
     */
    public void remove(AccessPoint ap) {
    	aps.remove(ap);
        elements.remove(ap);
        //repaint();
    }

    /**
     * Removes all {@link Obstacle}s from this {@link Panel}.
     */
    public void clear() {
        elements.clear();
        aps.clear();
        obstacles.clear();
        //repaint();
    }
    
    public LinkedList<Element> getElements() {
        return elements;
    }

    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }

    public LinkedList<AccessPoint> getAccessPoints() {
        return aps;
    }

    /**
     * Saves this {@link Model} to the last file used for a save.
     * 
     * @throws FileNotFoundException
     *             if the file used to load or save this model cannot be accessed
     *             anymore
     */
    public void save() throws FileNotFoundException {
        if (backingFile == null) {
            throw new IllegalStateException("This model was never saved.");
        }
        saveTo(backingFile);
    }

    /**
     * Saves this {@link Model} to the specified file.
     * 
     * @param filename
     *            The file to save this model to.
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular file,
     *             does not exist but cannot be created, or cannot be opened for any
     *             other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
     */
    public void saveTo(String filename) throws FileNotFoundException {
        Serializer.serialize(this, new FileOutputStream(filename));
        // everything went well, remember the saved file
        this.backingFile = filename;
    }

    /**
     * Loads a {@link Model} from the specified file.
     * 
     * @param filename
     *            The file to load the model from.
     * @return a new {@code Model} representing the file's data.
     * @throws FileNotFoundException
     *             If the specified file was not found.
     */
    public static Model loadFrom(String filename) throws FileNotFoundException {
        Model model = Serializer.deserialize(new FileInputStream(filename));
        // everything went well, remember the original file
        model.backingFile = filename;
        return model;
    }

}
