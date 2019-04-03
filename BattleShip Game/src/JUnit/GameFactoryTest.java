package JUnit;


import org.junit.Test;
import static org.junit.Assert.assertTrue;
import com.sjsu.edu.components.HumanVsComputerGame;
import com.sjsu.edu.components.HumanVsHumanGame;
import com.sjsu.interfaces.AbstractGame;

import factory.GameFactory;
public class GameFactoryTest {

	@Test
    public void testInstanceOfHumanVsComputerGame() {
		AbstractGame abstractGame = GameFactory.getGame("1");
        boolean isTypeofHumanVsComputer = abstractGame instanceof HumanVsComputerGame;
        assertTrue(true==isTypeofHumanVsComputer);
    }
	
	@Test
    public void testInstanceOfHumanVsHumanGame() {
		AbstractGame abstractGame = GameFactory.getGame("2");
        boolean isTypeofHumanVsHumanGame = abstractGame instanceof HumanVsHumanGame;
        assertTrue(true==isTypeofHumanVsHumanGame);
    }

}
