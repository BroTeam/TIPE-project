package com.broteam.tipe.signal;

import java.awt.Shape;
import java.awt.geom.Area;

/**
 * Created by Titouan on 15/04/14.
 */
public class SignalArea extends Area {

    private double attenuationFactor;

    public SignalArea(double attenuationFactor, Shape s) {
        super(s);
        this.attenuationFactor = attenuationFactor;
    }

    public SignalArea(SignalArea source) {
        super((Area) source.clone());
        this.attenuationFactor = source.attenuationFactor;
    }

    public double getAttenuationFactor() {
        return attenuationFactor;
    }

    public void setAttenuationFactor(double attenuationFactor) {
        this.attenuationFactor = attenuationFactor;
    }
    
    public void intersect(SignalArea other) {
        super.intersect(other);
        attenuationFactor *= other.attenuationFactor;
    }
}