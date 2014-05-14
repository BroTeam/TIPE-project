package com.broteam.tipe.model;

import java.util.LinkedList;
import java.util.List;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.signal.AreaIntersector;
import com.broteam.tipe.signal.SignalArea;

public class Simulation {

    private List<SignalArea> areas = new LinkedList<>();
    
    public List<SignalArea> getSignalAreas() {
    	return areas;
    }

    public void launchSimulation(AccessPoint ap, LinkedList<Obstacle> obstacles, double panelWidth, double panelHeight) {
        LinkedList<SignalArea> overlappingAreas = new LinkedList<>();
        for (Obstacle o : obstacles) {
            if (o != null) {
                overlappingAreas.addAll(o.getAttenuatedAreas(ap, panelWidth, panelHeight));
            }
        }
        this.areas = AreaIntersector.createExclusiveAreas(overlappingAreas);
    }
    
    public void clear() {
        areas.clear();
    }
}
