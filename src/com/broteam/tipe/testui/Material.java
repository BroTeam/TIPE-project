package com.broteam.tipe.testui;

public enum Material {

    THICK_GLASS("Vitre épaisse", 1.5),
    WOOD("Bois", 2.5),
    PLASTER("Plâtre", 4.0),
    BOX("Box de bureau", 4.0),
    GLASS_WALL("Cloison en verre", 7.0),
    WATER("Eau", 7.0),
    BRICK("Brique", 8.0),
    CERAMIC("Céramique", 8.0),
    ARMORED_GLASS("Verre blindé", 12.5),
    CONCRETE("Béton", 12.5),
    MIRROR("Mirroir", Double.POSITIVE_INFINITY),
    METAL("Metal", Double.POSITIVE_INFINITY);

    private String displayName;
    private Double attenuation;

    private Material(String displayName, Double attenuation) {
        this.displayName = displayName;
        this.attenuation = attenuation;
    }

	public Double getAttenuation() {
		return attenuation;
	}

    @Override
    public String toString() {
        return displayName;
    }
}