package com.broteam.tipe.element;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Titouan on 15/04/14.
 */
public class SignalArea extends java.awt.geom.Area {

    double attenuationFactor;

    SignalArea(double attenuationFactor, Shape s) {
        super(s);
        this.attenuationFactor = attenuationFactor;
    }

    // TODO move that to Wall
    SignalArea(Wall wall, Point2D pa) {

    }
    
}