package com.broteam.tipe.signal;

public class Physics {
    // FIXME
    public static final double DEFAULT_FREQUENCY = 0;
    
    private static final double LIGHTSPEED = 299792458;
    private static final double FSPL_CONSTANT = 20 * Math.log(4 * Math.PI / LIGHTSPEED);

    // FIXME
    public static double FSPL(double distance) {
        return FSPL(distance, DEFAULT_FREQUENCY);
    }

    public static double FSPL(double distance, double frequency) {
        return 20 * Math.log(distance) + 20 * Math.log(frequency) + FSPL_CONSTANT;
    }
}
