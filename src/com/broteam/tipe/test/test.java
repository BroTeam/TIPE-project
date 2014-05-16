package com.broteam.tipe.test;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Obstacle;
import com.broteam.tipe.model.elements.Wall;

public class test {
	
	private static final double LIGHTSPEED = 299792458;
	private static final double k = 20*Math.log(4*Math.PI/LIGHTSPEED);
	
	
	public LinkedList<Wall> obstaclesToWalls(LinkedList<Obstacle> obstacles) {
		LinkedList<Wall> walls = new LinkedList<>();
		for (Obstacle o : obstacles) {
				walls.addAll(o.getWalls());
		}
		return walls;
	}
	
	
	//copié collé méthode SO.
    public static Point2D getIntersection(final Line2D.Double line1, final Line2D.Double line2) {
        final double x1,y1, x2,y2, x3,y3, x4,y4;
        x1 = line1.x1; y1 = line1.y1; x2 = line1.x2; y2 = line1.y2;
        x3 = line2.x1; y3 = line2.y1; x4 = line2.x2; y4 = line2.y2;
        final double x = (
                (x2 - x1)*(x3*y4 - x4*y3) - (x4 - x3)*(x1*y2 - x2*y1)
                ) /
                (
                (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4)
                );
        final double y = (
                (y3 - y4)*(x1*y2 - x2*y1) - (y1 - y2)*(x3*y4 - x4*y3)
                ) /
                (
                (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4)
                );
        return new Point2D.Double(x, y);
    }
    //fin copié collé
	
    /**
     * Returns the total attenuation at the specified {@link Point2D} by calculating
     * which {@link Wall}(s) the signal had intersected.
     * 
     * @param pixel
     *            The {@link Point2D} where we want the total attenuation.
     * @param ap
     * 			  The source of the signal.
     * @param walls
     * 			  The {@link LinkedList} of all the {@link Wall}s in the {@link Panel}.
     * @return The total attenuation at the specified {@link Point2D}.
     */
	public double getAttenuation(Point2D pixel, AccessPoint ap, LinkedList<Wall> walls) {
		Line2D.Double line = new Line2D.Double(pixel.getX(), pixel.getY(), ap.getLocation().getX(), ap.getLocation().getY());
		TreeMap<Point2D, Double> attenuationMap = new TreeMap<>(Comparator.comparingDouble(p -> p.distance(ap.getLocation())));
		for (Wall w : walls) {
			if (line.intersectsLine((Line2D) w.getShape())) {
				attenuationMap.put(getIntersection(line, (Line2D.Double) w.getShape()), w.getMaterial().getAttenuationFactor());
			}
		}
		//Calcul de l'atténuation totale au pixel.
		double currentAttenuation = 0;
		for (int i=0 ; i < attenuationMap.size() ; i++) {
			// ALORS JE NE SAIS PAS SI CA MARCHE CA
			Point2D intersectionPt = (Point2D) attenuationMap.navigableKeySet().toArray()[i];
			double fspl = FSPL(intersectionPt, ap);
			currentAttenuation += (fspl + attenuationMap.get(intersectionPt));
		}
		return currentAttenuation;
		/*LinkedList<Point2D> interPts = new LinkedList<>();
		HashMap<Point2D, Double> attenuationMap = new HashMap<>();
		for (Wall w : walls) {
			if (line.intersectsLine((Line2D) w.getShape())) {
				Point2D intersectionPt = getIntersection(line, (Line2D.Double) w.getShape());
				interPts.add(intersectionPt);
				attenuationMap.put(intersectionPt, w.getMaterial().getAttenuationFactor());
			}
		}
		// Le tri est-il dans le bon sens AP->pixel ?
		Collections.sort(interPts, Comparator.comparingDouble(p -> p.distance(ap.getLocation())));
		//Calcul de l'atténuation totale au pixel.
		double currentAttenuation = 0;
		while (!interPts.isEmpty()) {
			Point2D intersectionPt = interPts.remove();
			double fspl = FSPL(intersectionPt, ap);
			currentAttenuation += (fspl + attenuationMap.get(intersectionPt));
			// TODO a revoir c'est surement faux
		}
		return currentAttenuation;	*/
	}
	
	public static double FSPL(Point2D p, AccessPoint ap){
		double distance = ap.getLocation().distance(p);
		double freqWifi = ap.getPower();
		return 20*Math.log(distance) + 20*Math.log(freqWifi) + k;
	}
}
