package com.broteam.tipe.signal;

import org.junit.Test;

public class PhysicsTest {
    
    @Test
    public void testFSPL() {
        System.out.println(Physics.FSPL(10, 5));
    }

    @Test
    public void testAttenuate() {
        System.out.println(Physics.attenuate(100, -3));
    }
}
