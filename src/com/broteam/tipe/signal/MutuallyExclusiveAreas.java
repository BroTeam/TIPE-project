package com.broteam.tipe.signal;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a list of areas that do not intersect each other. More formally, any 2
 * areas of a {@link MutuallyExclusiveAreas} list have an empty intersection.
 */
public class MutuallyExclusiveAreas extends LinkedList<SignalArea> {

    /**
     * Adds the specified {@code SignalArea} to this list of mutually exclusive
     * areas. To do so, the specified area is intersected with all areas from this
     * list, thus creating new areas, so that the areas in this list stay mutually
     * exclusive.
     * <p>
     * <b>WARNING: this method is destructive.</b> The specified {@link SignalArea}
     * will be modified.</b>
     * </p>
     * 
     * @param areaToAdd
     *            The area to add.
     * @return {@code true}, as specified by L
     */
    public void addExclusive(SignalArea areaToAdd) {
        SignalArea current = areaToAdd;
        // list of already treated mutEx areas, which are mutually exclusive, and
        // exclusive with the 'current' area
        List<SignalArea> currentMutEx = new LinkedList<>();
        while (!isEmpty()) {
            SignalArea mutExArea = remove(0);
            SignalArea intersection = SignalArea.intersect(current, mutExArea);

            // the remaining part of 'mutExArea' is necessarily exclusive with
            // all other shapes of this list, because 'mutExArea' was in this list
            if (!mutExArea.isEmpty()) {
                currentMutEx.add(mutExArea);
            }

            // the intersection is necessarily exclusive with all other shapes of
            // this list, because it is part of 'mutExArea', which was in this list
            if (!intersection.isEmpty()) {
                currentMutEx.add(intersection);
            }

            if (current.isEmpty()) {
                // add the treated areas
                addAll(currentMutEx);
                // end here, because nothing's left of 'current'
                return;
            }

            // the remaining part of 'current' needs to be intersected with the other
            // shapes: carry on with what's left of 'current'
        }
        // no more areas to intersect: add what's left of 'current'
        add(current);
        // add the treated areas
        addAll(currentMutEx);
        return;
    }

    /**
     * Adds to this list all elements from the specified list of potentially
     * overlapping areas. The areas in the specified list are properly intersected,
     * so that areas of this list stay mutually exclusive.
     * <p>
     * <b>WARNING: this method is destructive.</b> The specified list of areas will
     * be empty after the execution of this method, and the areas inside it may
     * change too.
     * </p>
     * 
     * @param overlappingAreas
     *            A list of {@link SignalArea}s that may overlap.
     */
    public void addAllExclusive(List<SignalArea> overlappingAreas) {
        while (!overlappingAreas.isEmpty()) {
            SignalArea current = overlappingAreas.remove(0);
            addExclusive(current);
        }
    }
}
