package com.broteam.tipe.signal;

public class Physics {

    private static final double LOG_10 = Math.log(10);
    private static final double LIGHTSPEED = 299792458d;
    private static final double FSPL_CONSTANT_METERS_AND_GHZ = 20 * Math.log(4d * Math.PI * 1000000000d / LIGHTSPEED);

    /**
     * Returns the free space loss in dB for a signal of the specified frequency when
     * it travels the specified distance.
     * 
     * @param distance
     *            The distance traveled in meters.
     * @param frequency
     *            The frequency of the signal in GHz.
     * @return The free space loss value.s
     */
    public static double FSPL(double distance, double frequency) {
        return -(20 * Math.log(distance * frequency) / LOG_10 + FSPL_CONSTANT_METERS_AND_GHZ);
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
