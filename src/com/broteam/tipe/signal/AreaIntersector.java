package com.broteam.tipe.signal;

import java.util.LinkedList;
import java.util.List;

public class AreaIntersector {

    public static class MutExIntersected {
        IntersectionCase type;
        SignalArea remaining1;
        SignalArea remaining2;
        SignalArea intersection;
    }

    public static MutExIntersected intersect(SignalArea area1, SignalArea area2) {
        MutExIntersected mei = new MutExIntersected();
        mei.intersection = new SignalArea(area1);
        mei.remaining1 = new SignalArea(area1);
        mei.remaining2 = new SignalArea(area2);
        mei.intersection.intersect(area2);
        mei.remaining1.subtract(mei.intersection);
        mei.remaining2.subtract(mei.intersection);
        if (mei.intersection.isEmpty()) {
            mei.type = IntersectionCase.DISTINCT;
        } else if (mei.remaining1.isEmpty()) {
            mei.type = IntersectionCase.CONTAINED_OR_EQUAL;
        } else if (mei.remaining2.isEmpty()) {
            mei.type = IntersectionCase.CONTAINS;
        } else {
            mei.type = IntersectionCase.INTERSECTS;
        }
        return mei;
    }

    public static List<SignalArea> createExclusiveAreas(List<SignalArea> overlappingAreas) {
        List<SignalArea> mutExAreas = new LinkedList<>();
        while (!overlappingAreas.isEmpty()) {
            SignalArea current = overlappingAreas.remove(0);
            addAreaToExclusiveList(current, mutExAreas);
        }
        return mutExAreas;
    }

    public static void addAreaToExclusiveList(SignalArea areaToAdd, List<SignalArea> mutExAreas) {
        SignalArea current = areaToAdd;
        // list of already treated mutEx areas, which are exclusive with current
        List<SignalArea> currentMutEx = new LinkedList<>(); // CME
        while (!mutExAreas.isEmpty()) {
            SignalArea ex = mutExAreas.remove(0);
            MutExIntersected mei = intersect(current, ex);
            // the intersection is always mutually exclusive with all other shapes, because it is part of E
            switch (mei.type) {
            case DISTINCT: // 'ex' not overlapping
                // 'ex' already exclusive to 'current'
                currentMutEx.add(ex);
                break;
            case CONTAINS: // 'current' contains 'ex'
                // the intersection (same shape as 'ex') is necessarily exclusive with all other shapes
                currentMutEx.add(mei.intersection);
                // carry on with what's left of 'current'
                current = mei.remaining1;
                break;
            case CONTAINED_OR_EQUAL: // 'ex' contains 'current'
                // add what's left of 'ex' to CME
                currentMutEx.add(mei.remaining2); 
                // add the intersection (same shape as 'current')
                currentMutEx.add(mei.intersection);
                // end there, because nothing's left to intersect
                mutExAreas.addAll(currentMutEx);
                currentMutEx.clear();
                return;
            case INTERSECTS: // normal case
                // add what's remaining of 'ex' to CME
                currentMutEx.add(mei.remaining2); 
                currentMutEx.add(mei.intersection); 
                current = mei.remaining1;
                break;
            }
        }
        mutExAreas.add(current);
        mutExAreas.addAll(currentMutEx);
    }

}
