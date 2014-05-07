package com.broteam.tipe.signal;

public enum IntersectionCase {
	DISTINCT(),
	CONTAINS(), //(la première zone en parametre contient l'autre)
	CONTAINED_OR_EQUAL(), //(la première zone en parametre est contenue dans l'autre)
	INTERSECTS();
}


