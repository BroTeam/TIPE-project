package com.broteam.tipe.shape;

/**
 * Created by Titouan on 18/02/14.
 */
public class NoShapeChosenException extends RuntimeException {
    public NoShapeChosenException() {
        super("Aucune forme sélectionnée");
    }

    public NoShapeChosenException(String message) {
        super(message);
    }

    public NoShapeChosenException(String message, Throwable cause) {
        super(message, cause);
    }
}