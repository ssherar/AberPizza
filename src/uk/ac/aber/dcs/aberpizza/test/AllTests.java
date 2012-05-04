package uk.ac.aber.dcs.aberpizza.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPizza.class, TestOption.class, TestOrder.class })
public class AllTests {

}
