package com.broteam.tipe.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;

public class Model {

    /** Name of the file used for the last save */
    private String backingFile;

    private List<Element> elements = new LinkedList<>();
    private List<Obstacle> obstacles = new LinkedList<>();
    private List<AccessPoint> aps = new LinkedList<>();

    // TODO complete model

    public void add(Obstacle obstacle) {
        elements.add(obstacle);
        obstacles.add(obstacle);
    }

    public void add(AccessPoint ap) {
        elements.add(ap);
        aps.add(ap);
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<AccessPoint> getAccessPoints() {
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
