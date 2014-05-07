package com.broteam.tipe.signal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AreaIntersector {

	public static IntersectionCase intersect(SignalArea area1, SignalArea area2, List<SignalArea> result) {
		
		return ;
	}

	public static List<SignalArea> createExclusiveAreas(List<SignalArea> overlappingAreas) {
		List<SignalArea> mutExAreas =  new ArrayList<>();
		while (!overlappingAreas.isEmpty()) {
			SignalArea current = overlappingAreas.remove(0);
			addAreaToExclusiveList(current, mutExAreas);
		}
		return mutExAreas;
	}

	public static void addAreaToExclusiveList(SignalArea areaToAdd, List<SignalArea> mutExAreas) {
		SignalArea current = areaToAdd;
		List<SignalArea> currentMutEx =  new LinkedList<>();
		ArrayList<SignalArea> result =  new ArrayList<>();
		while (!mutExAreas.isEmpty()) {
			SignalArea exclusive = mutExAreas.remove(0);
			result.clear();
			switch (intersect(current, exclusive, result)) {
			case DISTINCT:
				currentMutEx.add(result.get(1)); // On ajoute exclusive à la CME
				break;
			case CONTAINS:
				currentMutEx.add(result.get(1)); // On ajoute l'intersection à la CME
				current = result.get(0);
				break;
			case CONTAINED:
				currentMutEx.add(result.get(0)); // On ajoute exclusive-intersection à la CME
				currentMutEx.add(result.get(1)); // On ajoute l'intersection à la CME
				mutExAreas.addAll(currentMutEx);
				currentMutEx.clear();
				return;
			case INTERSECTS:
				currentMutEx.add(result.get(0)); // On ajoute exclusive-intersection à la CME
				currentMutEx.add(result.get(2)); // On ajoute l'intersection à la CME
				current = result.get(1);
				break;
			}
		}
		mutExAreas.add(current);
		mutExAreas.addAll(currentMutEx);
	}

}
