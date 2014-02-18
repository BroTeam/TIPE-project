package com.broteam.tipe.shape;

/**
 * Created by Titouan on 18/02/14.
 */
public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("Fonctionnalité non implémentée [Pour l'instant...]");
    }

    public NotImplementedException(String message) {
        super(message);
    }

        /*
        public notImplementedException(String message, Throwable cause) {
            super(message, cause);
        }*/
}
