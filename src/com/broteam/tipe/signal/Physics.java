package com.broteam.tipe.signal;

public class Physics {
    // FIXME
    public static final double DEFAULT_FREQUENCY_MHZ = 5;

    private static final double LIGHTSPEED = 299792458;
    private static final double FSPL_CONSTANT = 20 * Math.log(4 * Math.PI / LIGHTSPEED);
    private static final double FSPL_CONSTANT_METERS_AND_MHZ = -27.55;

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

    public static double attenuate(double initPower, double dBattenuation) {
        return initPower * Math.pow(10, dBattenuation / 10);
    }
}
