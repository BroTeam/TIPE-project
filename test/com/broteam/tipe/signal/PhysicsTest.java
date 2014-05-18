package com.broteam.tipe.signal;

import org.junit.Test;

public class PhysicsTest {

    @Test
    public void testFSPL() {
        System.out.println(Math.log(5000000) * 20);
        for (int distance = 1; distance < 1000; distance++) {
            System.out.println("distance = " + distance + " FSPL = " + Physics.FSPL(distance, 5));
        }
    }

    @Test
    public void testAttenuate() {
        System.out.println(Physics.attenuate(100, -3));
    }
}
