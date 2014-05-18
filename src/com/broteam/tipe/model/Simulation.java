package com.broteam.tipe.model;

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
import com.broteam.tipe.signal.AreaIntersector;
import com.broteam.tipe.signal.Physics;
import com.broteam.tipe.signal.SignalArea;
import com.broteam.tipe.ui.Panel;

public class Simulation {

	private List<SignalArea> areas = new LinkedList<>();

	public List<SignalArea> getSignalAreas() {
		return areas;
	}

	public void launchSimulation(AccessPoint ap, LinkedList<Obstacle> obstacles, Panel panel) {
		LinkedList<SignalArea> overlappingAreas = new LinkedList<>();
		for (Obstacle o : obstacles) {
			if (o != null) {
				overlappingAreas.addAll(o.getAttenuatedAreas(ap, panel.getWidth(), panel.getHeight()));
			}
		}
		this.areas = AreaIntersector.createExclusiveAreas(overlappingAreas);
	}

	public void clear() {
		areas.clear();
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
	public static double getAttenuation(Point2D pixel, AccessPoint ap, LinkedList<Wall> walls) {
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
