package com.broteam.tipe.signal;

import java.awt.Shape;

import java.awt.geom.Area;
import java.util.LinkedList;

import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.model.elements.Wall;

/**
 * Created by Titouan on 15/04/14.
 */
public class SignalArea extends Area {

	private LinkedList<Obstacle> obstacles;

    public SignalArea(Wall w, Shape s) {
        super(s);
        this.obstacles = new LinkedList<>();
        this.obstacles.add(w); 
    }

    public SignalArea(SignalArea source) {
        super((Area) source.clone());
        this.obstacles = new LinkedList<>(source.obstacles);
    }

    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }
    
    public void intersect(SignalArea other) {
        super.intersect(other);
        this.obstacles.addAll(other.getObstacles());
    }
}