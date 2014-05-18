package com.broteam.tipe.model;

import java.io.InputStream;
import java.io.OutputStream;

class Serializer {

    public static void serialize(Model model, OutputStream out) {
        // TODO Joffrey-generated method stub
        // TODO write the model to the output stream
    }
    
    public static Model deserialize(InputStream is) {
        Model model = new Model();
        // TODO Joffrey-generated method stub
        // TODO populate the model with data from the input stream
        return model;
    }
    
}
