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
	private Double attenuationDb;
	private Color colorMat;

	private Material(String displayName, Double attenuationDb, Color colorMat) {
		this.displayName = displayName;
		this.attenuationDb = attenuationDb;
		this.colorMat = colorMat;
	}

	/**
	 * Returns the multiplicative attenuation factor of this material.
	 * <p>
	 * When a signal passes through this material, the following formula applies:
	 * </p>
	 * p<sub>2</sub> = p<sub>1</sub> * {@code getAttenuationFactor()}
	 * <ul>
	 * <li>p<sub>1</sub> is the signal power before passing through this material</li>
	 * <li>p<sub>2</sub> is the signal power after passing through this material</li>
	 * </ul>
	 * 
	 * @return the multiplicative attenuation factor of this material.
	 */
	public Double getAttenuationFactor() {
		return Math.pow(10, attenuationDb / 10);
	}

	public Color getColorMat() {
		return colorMat;
	}

	@Override
	public String toString() {
		return displayName;
	}
}