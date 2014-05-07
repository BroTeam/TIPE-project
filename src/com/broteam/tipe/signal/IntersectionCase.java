package com.broteam.tipe.signal;

public enum IntersectionCase {
	DISTINCT(),
	CONTAINS(), //(la première zone en parametre contient l'autre)
	CONTAINED(), //(la première zone en parametre est contenue dans l'autre)
	INTERSECTS();
}


