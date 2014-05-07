package com.broteam.tipe.signal;

import java.util.ArrayList;
import java.util.List;

public class AreaIntersector {

	public static IntersectionCase intersect(SignalArea area1, SignalArea area2, List<SignalArea> result) {
		// il m'oblige à la mettre static  normal ?
		
		return result;
	}

	public static List<SignalArea> createExclusiveAreas(List<SignalArea> overlappingAreas) {
		List<SignalArea> mutExAreas =  new ArrayList<>();
		while (!overlappingAreas.isEmpty()) {
			//a inline ?
			SignalArea current = overlappingAreas.remove(0);
			addAreaToExclusiveList(current, mutExAreas);
		}
		return mutExAreas;
	}

	public static void addAreaToExclusiveList(SignalArea areaToAdd, List<SignalArea> mutExAreas) {
		while (!mutExAreas.isEmpty()) {
			SignalArea exclusive = mutExAreas.remove(0);
			List<SignalArea> result =  new ArrayList<>();; // A CHANGER vide au lieu de null ! 
			switch (intersect(areaToAdd, exclusive, result)) {
			case DISTINCT:
				
				break;
			case CONTAINS:
				
				break;
			case CONTAINED:
				
				break;
			case INTERSECTS:
				
				break;
			}
		}
	}

}
