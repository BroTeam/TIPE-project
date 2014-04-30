package com.broteam.tipe.element;

import java.awt.Color;

public enum Material {

    THICK_GLASS("Vitre épaisse", 1.5, Color.BLUE),
    WOOD("Bois", 2.5, Color.GREEN),
    PLASTER("Plâtre", 4.0, Color.RED),
    BOX("Box de bureau", 4.0, Color.YELLOW),
    GLASS_WALL("Cloison en verre", 7.0, Color.LIGHT_GRAY),
    WATER("Eau", 7.0, Color.CYAN),
    BRICK("Brique", 8.0, Color.ORANGE),
    CERAMIC("Céramique", 8.0, Color.GRAY),
    ARMORED_GLASS("Verre blindé", 12.5, Color.MAGENTA),
    CONCRETE("Béton", 12.5, Color.BLACK),
    MIRROR("Mirroir", Double.POSITIVE_INFINITY, Color.PINK),
    METAL("Metal", Double.POSITIVE_INFINITY, Color.DARK_GRAY);

    private String displayName;
    private Double attenuation;
    private Color colorMat;

    private Material(String displayName, Double attenuation, Color colorMat) {
        this.displayName = displayName;
        this.attenuation = attenuation;
        this.colorMat = colorMat;
    }

	public Double getAttenuation() {
		return attenuation;
	}
	public Color getColorMat() {
		return colorMat;
	}

    @Override
    public String toString() {
        return displayName;
    }
}