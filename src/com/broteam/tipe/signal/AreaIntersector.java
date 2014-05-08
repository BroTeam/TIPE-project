package com.broteam.tipe.signal;

import java.util.LinkedList;
import java.util.List;

/**
 * Provides the method {@link #createExclusiveAreas(List)} to prepare the
 * {@link SignalArea}s for display.
 */
public class AreaIntersector {

    /**
     * Intersects all areas in the {@code overlappingAres} list, and returns a list
     * of corresponding mutually exclusive areas.
     * <p>
     * For instance, if {@code overlappingAres} contains only 2 areas A and B, the
     * final list will contain 3 lists instead: the intersection of A and B, A minus
     * the intersection, and B minus the intersection.
     * </p>
     * 
     * @param overlappingAreas
     *            A list of {@link SignalArea}s that may overlap.
     * @return The list of the corresponding {@link SignalArea}s with no overlap.
     */
    public static List<SignalArea> createExclusiveAreas(List<SignalArea> overlappingAreas) {
        List<SignalArea> mutExAreas = new LinkedList<>();
        while (!overlappingAreas.isEmpty()) {
            SignalArea current = overlappingAreas.remove(0);
            addAreaToExclusiveList(current, mutExAreas);
        }
        return mutExAreas;
    }

    /**
     * Adds the specified {@code SignalArea} to the specified list of mutually
     * exclusive areas. To do so, the specified area is intersected with all areas
     * from the list, thus creating new areas, so that the areas in the final list
     * still are mutually exclusive.
     * 
     * @param areaToAdd
     *            The area to add.
     * @param mutExAreas
     *            A list of mutually exclusive areas.
     */
    private static void addAreaToExclusiveList(SignalArea areaToAdd, List<SignalArea> mutExAreas) {
        SignalArea current = areaToAdd;
        // list of already treated mutEx areas, which are mutually exclusive, and
        // exclusive with current
        List<SignalArea> currentMutEx = new LinkedList<>();
        while (!mutExAreas.isEmpty()) {
            SignalArea mutExArea = mutExAreas.remove(0);
            SignalArea intersection = intersect(current, mutExArea);

            // the remaining part of 'mutExArea' is necessarily exclusive with
            // all other shapes, because 'mutExArea' was.
            if (!mutExArea.isEmpty()) {
                currentMutEx.add(mutExArea);
            }

            // the intersection is necessarily exclusive with all other shapes,
            // because it is part of 'mutExArea', which was.
            if (!intersection.isEmpty()) {
                currentMutEx.add(intersection);
            }

            if (current.isEmpty()) {
                // add the treated areas
                mutExAreas.addAll(currentMutEx);
                // end here, because nothing's left (of 'current') to intersect
                return;
            }

            // the remaining part of 'current' needs to be intersected with the other
            // shapes: carry on with what's left of 'current'
        }
        // no more areas to intersect: add what's left of 'current'
        mutExAreas.add(current);
        // add the treated areas
        mutExAreas.addAll(currentMutEx);
    }

    /**
     * Returns the intersection of {@code area1} and {@code area2}, and subtract it
     * to the original areas.
     * 
     * @param area1
     *            The first area to intersect.
     * @param area2
     *            The second area to intersect.
     * @return THe intersection of the 2 specified areas.
     */
    private static SignalArea intersect(SignalArea area1, SignalArea area2) {
        SignalArea intersection = new SignalArea(area1);
        intersection.intersect(area2);
        area1.subtract(intersection);
        area2.subtract(intersection);
        return intersection;
    }
}
