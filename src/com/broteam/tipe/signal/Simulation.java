package com.broteam.tipe.signal;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import com.broteam.tipe.math.Geometry;
import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.model.elements.Wall;
import com.broteam.tipe.ui.Panel;

public class Simulation {

    private List<SignalArea> areas = new LinkedList<>();
    
    public List<SignalArea> getSignalAreas() {
    	return areas;
    }
    
    public void clear() {
        areas.clear();
    }

    public void launchSimulation(AccessPoint ap, LinkedList<Obstacle> obstacles, double rightLimit, double bottomLimit) {
        LinkedList<SignalArea> overlappingAreas = new LinkedList<>();
        for (Obstacle o : obstacles) {
            if (o != null) {
                overlappingAreas.addAll(o.getAttenuatedAreas(ap, rightLimit, bottomLimit));
            }
        }
        this.areas = createExclusiveAreas(overlappingAreas);
    }

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
    private static List<SignalArea> createExclusiveAreas(List<SignalArea> overlappingAreas) {
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
            SignalArea intersection = SignalArea.intersect(current, mutExArea);

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
     * Returns the total attenuation (in dB) at the specified {@link Point2D} by
     * calculating which {@link Wall}(s) the signal had intersected.
     * 
     * @param pixel
     *            The {@link Point2D} where we want the total attenuation.
     * @param ap
     *            The source of the signal.
     * @param walls
     *            The {@link LinkedList} of all the {@link Wall}s in the
     *            {@link Panel}.
     * @return The total attenuation (in dB) at the specified {@link Point2D}.
     */
    // TODO simplifier la fonction car l'algorithme ne mérite pas une fonction si
    // complexe ? (Ou on laisse pour l'évolutivité)
    public static double getAttenuation(Point2D pixel, AccessPoint ap, List<Wall> walls) {
        Line2D.Double line = new Line2D.Double(pixel, ap.getLocation());
        TreeMap<Point2D, Double> attenuationMap = new TreeMap<>(Comparator.comparingDouble(p -> p.distance(ap
                .getLocation())));
        for (Wall w : walls) {
            if (line.intersectsLine((Line2D) w.getShape())) {
                attenuationMap.put(Geometry.getIntersection(line, (Line2D.Double) w.getShape()), w.getMaterial()
                        .getAttenuationFactor());
            }
        }
        // Calcul de l'atténuation totale au pixel.
        double currentAttenuation = Physics.FSPL(pixel.distance(ap.getLocation()));
        for (Point2D intersectionPt : attenuationMap.navigableKeySet()) {
            currentAttenuation += attenuationMap.get(intersectionPt);
        }
        return currentAttenuation;
    }
}
