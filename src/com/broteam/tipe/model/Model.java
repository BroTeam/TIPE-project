package com.broteam.tipe.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;

public class Model implements Serializable {

    /** Name of the file used for the last save */
    private transient String backingFile;

    private final LinkedList<Element> elements = new LinkedList<>();
    private final LinkedList<Obstacle> obstacles = new LinkedList<>();
    private final LinkedList<AccessPoint> aps = new LinkedList<>();

    private transient LinkedList<ModelListener> listeners = new LinkedList<>();

    /**
     * Adds the specified {@link Element} to this {@link Model}.
     *
     * @param element
     *            The {@link Element} to add.
     */
    public void add(Element element) {
        System.out.println("element added");
        elements.add(element);
        fireElementAdded(element);
        if (element instanceof Obstacle) {
            System.out.println("obstacle added");
            obstacles.add((Obstacle) element);
            fireObstacleAdded((Obstacle) element);
        }
        if (element instanceof AccessPoint) {
            System.out.println("ap added");
            aps.add((AccessPoint) element);
            fireAccessPointAdded((AccessPoint) element);
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
        fireElementRemoved(element);
        if (element instanceof Obstacle) {
            obstacles.remove(element);
            fireObstacleRemoved((Obstacle) element);
        }
        if (element instanceof AccessPoint) {
            aps.remove(element);
            fireAccessPointRemoved((AccessPoint) element);
        }
    }

    /**
     * Replaces the last added {@link Element} by the specified {@link Element}.
     *
     * @param element
     *            The {@link Element} to place instead of the last one.
     */
    public void replaceLast(Element element) {
        {
            final Element removed = elements.removeLast();
            fireElementRemoved(removed);
            elements.add(element);
            fireElementAdded(element);
        }
        if (element instanceof Obstacle) {
            final Obstacle removed = obstacles.removeLast();
            fireObstacleRemoved(removed);
            obstacles.add((Obstacle) element);
            fireObstacleAdded((Obstacle) element);
        }
        if (element instanceof AccessPoint) {
            final AccessPoint removed = aps.removeLast();
            fireAccessPointRemoved(removed);
            aps.add((AccessPoint) element);
            fireAccessPointAdded((AccessPoint) element);
        }
    }

    /**
     * Removes all {@link Element}s from this {@link Model}.
     */
    public void clear() {
        elements.clear();
        aps.clear();
        obstacles.clear();
        for (final ModelListener l : listeners) {
            l.onCleared();
        }
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

    public boolean hasBackingFile() {
        return backingFile != null;
    }

    public void setBackingFile(String backingFile) {
        this.backingFile = backingFile;
    }

    /**
     * Saves this {@link Model} to the last file used for a save.
     *
     * @throws IOException
     *             if an I/O error occurs
     */
    public void save() throws IOException {
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
     * @throws IOException
     *             if an I/O error occurs
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
     */
    public void saveTo(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            oos.flush();
        } catch (final IOException e) {
            throw new IOException("Unexpected error while saving to file '" + filename + "'.", e);
        }
        // everything went well, remember the saved file
        this.backingFile = filename;
    }

    /**
     * Loads a {@link Model} from the specified file.
     *
     * @param filename
     *            The file to load the model from.
     * @return a new {@code Model} representing the file's data.
     * @throws IOException
     *             if an I/O error occurs
     */
    public static Model loadFrom(String filename) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            final Model model = (Model) ois.readObject();
            // everything went well, remember the original file
            model.setBackingFile(filename);
            return model;
        } catch (final FileNotFoundException e) {
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("I/O error while loading model from file '" + filename + "'", e);
        }
    }

    public void registerListener(ModelListener listener) {
        System.out.println("Listener registered: " + listener.getClass().getSimpleName());
        if (listeners == null) {
            listeners = new LinkedList<>();
        }
        listeners.add(listener);
    }

    public void unregisterListener(ModelListener listener) {
        System.out.println("Listener unregistered: " + listener.getClass().getSimpleName());
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    private void fireElementAdded(Element e) {
        for (final ModelListener l : listeners) {
            System.out.println("fire element added on listener " + l.getClass().getSimpleName());
            l.onElementAdded(e);
        }
    }

    private void fireObstacleAdded(Obstacle o) {
        for (final ModelListener l : listeners) {
            l.onObstacleAdded(o);
        }
    }

    private void fireAccessPointAdded(AccessPoint ap) {
        for (final ModelListener l : listeners) {
            System.out.println("fire access point added on listener " + l.getClass().getSimpleName());
            l.onAccessPointAdded(ap);
        }
    }

    private void fireElementRemoved(Element e) {
        for (final ModelListener l : listeners) {
            l.onElementRemoved(e);
        }
    }

    private void fireObstacleRemoved(Obstacle o) {
        for (final ModelListener l : listeners) {
            l.onObstacleRemoved(o);
        }
    }

    private void fireAccessPointRemoved(AccessPoint ap) {
        for (final ModelListener l : listeners) {
            l.onAccessPointRemoved(ap);
        }
    }
}
