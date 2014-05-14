package com.broteam.tipe.model;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;

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
     * Replaces the last added {@link Element} by the specified {@link Element}.
     *
     * @param element
     *            The {@link Element} to place instead of the last one.
     */
    public void replaceLast(Element element) {
    	elements.removeLast();
        elements.add(element);
        if (element instanceof Obstacle) {
        	obstacles.removeLast();
            obstacles.add((Obstacle) element);
        }
        if (element instanceof AccessPoint) {
        	aps.removeLast();
            aps.add((AccessPoint) element);
        }
    }
       
    /**
     * Removes the specified {@link Element} to this {@link Model}.
     *
     * @param element
     *            The {@link Element} to remove.
     */
    public void remove(Element element) {
        elements.remove(element);
        if (element instanceof Obstacle) {
            obstacles.remove(element);
        }
        if (element instanceof AccessPoint) {
            aps.remove(element);
        }
    }
    
    /**
     * Removes all {@link Element}s from this {@link Model}.
     */
    public void clear() {
        elements.clear();
        aps.clear();
        obstacles.clear();
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
