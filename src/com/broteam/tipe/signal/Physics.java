package com.broteam.tipe.signal;

public class Physics {

    private static final double LIGHTSPEED = 299792458;
    private static final double FSPL_CONSTANT_METERS_AND_MHZ = 20 * Math.log(4 * Math.PI * 1000000 / LIGHTSPEED);

    /**
     * Returns the free space loss in dB for a signal of the specified frequency when
     * it travels the specified distance.
     * 
     * @param distance
     *            The distance traveled in meters.
     * @param frequency
     *            The frequency of the signal in MHz.
     * @return The free space loss value.s
     */
    public static double FSPL(double distance, double frequency) {
        return 20 * Math.log(distance) + 20 * Math.log(frequency) + FSPL_CONSTANT_METERS_AND_MHZ;
    }

    /**
     * Calculates the attenuated value of the specified power.
     * 
     * @param power
     *            The initial power before attenuation
     * @param dBattenuation
     *            The attenuation in dB (negative)
     * @return the attenuated value of the specified power.
     */
    public static double attenuate(double power, double dBattenuation) {
        return power * Math.pow(10, dBattenuation / 10);
    }
}
