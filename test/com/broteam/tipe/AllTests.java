package com.broteam.tipe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.broteam.tipe.math.LineEquationTest;
import com.broteam.tipe.math.ProjectionHelperTest;
import com.broteam.tipe.math.SideTest;
import com.broteam.tipe.signal.PhysicsTest;

@RunWith(Suite.class)
@SuiteClasses({ LineEquationTest.class, SideTest.class, ProjectionHelperTest.class, PhysicsTest.class })
public class AllTests {

}
