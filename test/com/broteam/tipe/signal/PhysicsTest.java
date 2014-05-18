package com.broteam.tipe.signal;

import org.junit.Test;

public class PhysicsTest {

    @Test
    public void testFSPL() {
        printFSPL(0);
        printFSPL(0.0000001);
        printFSPL(0.00001);
        printFSPL(0.2);
        printFSPL(0.5);
        printFSPL(1);
        printFSPL(10);
        printFSPL(50);
        printFSPL(100);
        printFSPL(200);
        printFSPL(1000);
    }
    
    private static void printFSPL(double distance) {
        //System.out.println("distance = " + distance + "   \tFSPL = " + Physics.FSPL(distance, 5));
        Physics.freeSpacePathLossDB(distance, 5);
    }

    @Test
    public void testAttenuate() {
        System.out.println(Physics.applyDBGain(100, -3));
    }
}
