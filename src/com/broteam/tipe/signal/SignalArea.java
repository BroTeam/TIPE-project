package com.broteam.tipe.signal;

import java.awt.Shape;

/**
 * Created by Titouan on 15/04/14.
 */
public class SignalArea extends java.awt.geom.Area {

    double attenuationFactor;

    public SignalArea(double attenuationFactor, Shape s) {
        super(s);
        this.attenuationFactor = attenuationFactor;
    }
}