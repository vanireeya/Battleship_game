package JUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ BattleBoardTest.class, GameFactoryTest.class, HumanVsComputerTest.class, HumanVsHumanTest.class })
public class AllTests {

	
}
