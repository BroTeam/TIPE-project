package com.broteam.tipe.signal;

public class Physics {

    public static final double PIXELS_PER_METER = 10;

    private static final double LOG_10 = Math.log(10);
    private static final double LIGHTSPEED = 299792458d;

    private static final double FSPL_CONSTANT = 4d * Math.PI / LIGHTSPEED;
    private static final double GHZ_TO_HZ = 1000000000d;

    private static final double FSPL_CONSTANT_SQ_DB = 20 * Math.log(FSPL_CONSTANT) / LOG_10;
    private static final double GHZ_TO_HZ_SQ_DB = 20 * Math.log(GHZ_TO_HZ) / LOG_10;

    /**
     * Returns the free space loss in dB for a signal of the specified frequency when
     * it travels the specified distance.
     * 
     * @param distance
     *            The distance traveled in meters.
     * @param frequency
     *            The frequency of the signal in GHz.
     * @return The free space path loss in dB.
     */
    public static double freeSpacePathLossDB(double distance, double frequency) {
        double distFreq = distance * frequency;
        double ln = 20 * Math.log(distance * frequency);
        double log = ln / LOG_10;
        double result = log + GHZ_TO_HZ_SQ_DB + FSPL_CONSTANT_SQ_DB;
//        System.out.println("dist=" + distance + "   \tdist*freq=" + distFreq + "   \tln=" + ln + "   \tlog=" + log
//                + "   \tresult=" + result);
        return -result;
    }

    /**
     * Returns the free space loss factor for a signal of the specified frequency
     * when it travels the specified distance.
     * 
     * @param distance
     *            The distance traveled in meters.
     * @param frequency
     *            The frequency of the signal in GHz.
     * @return The free space path loss factor.
     */
    public static double freeSpacePathLoss(double distance, double frequency) {
        double inner = distance * frequency * GHZ_TO_HZ * FSPL_CONSTANT;
        double result = inner * inner;
//        System.out.println("dist=" + distance + "   \t(dist*freq*4pi/c)=" + inner + "   \tresult=" + result);
        return -result;
    }

    /**
     * Calculates the value of the specified power after applying the specified gain.
     * 
     * @param power
     *            The initial power before attenuation
     * @param dBGain
     *            The gain to apply in dB
     * @return the resulting power value.
     */
    public static double applyDBGain(double power, double dBGain) {
        return power * Math.pow(10, dBGain / 10);
    }
}
