package com.broteam.tipe.model;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

class Serializer {

    /**
     * Saves a {@link Model} object to the specified output stream.
     * 
     * @param model
     *            The Model object to save
     * @param os
     *            The output stream
     */
    public static void serialize(Model model, FileOutputStream os) {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(model);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a {@link Model} object from the specified {@link InputStream}.
     * 
     * @param is
     *            The input stream
     * @return the {@link Model} created from the input stream.
     */
    public static Model deserialize(FileInputStream is) {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Model) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
