package com.broteam.tipe.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;

class Serializer {

    /**
     * Saves a Model object to the specified output stream.
     * 
     * @param model
     *            The Model object to save
     * @param os
     *            The output stream
     */
    public static void serialize(Model model, FileOutputStream os) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(model);
            oos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (oos != null)
                    oos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads a Model object from the specified input stream.
     * 
     * @param is
     *            The input stream
     */
    public static Model deserialize(FileInputStream is) {
        Model model = null;
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(is);
            model = (Model)ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (ois != null)
                    ois.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return model;
    }
    
}
