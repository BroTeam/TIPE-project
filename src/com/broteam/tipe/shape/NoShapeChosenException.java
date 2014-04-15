package com.broteam.tipe.shape;

import javax.swing.JOptionPane;

/**
 * Created by Titouan on 18/02/14.
 */
public class NoShapeChosenException extends RuntimeException {
    public NoShapeChosenException() {
        super("Aucune forme sélectionnée");
        JOptionPane.showMessageDialog(null, "Aucune forme sélectionnée", "Attention !", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    public NoShapeChosenException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message, "Attention !", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    public NoShapeChosenException(String message, Throwable cause) {
        super(message, cause);
        JOptionPane.showMessageDialog(null, message+" Cause : "+cause, "Attention !", javax.swing.JOptionPane.WARNING_MESSAGE);
    }
}