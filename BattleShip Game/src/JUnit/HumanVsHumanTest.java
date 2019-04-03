package JUnit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.sjsu.edu.components.HumanVsComputerGame;
import com.sjsu.edu.components.HumanVsHumanGame;
import com.sjsu.interfaces.AbstractGame;

import factory.GameFactory;
public class HumanVsHumanTest {

	@Test
    public void testcalculateNumberOfTurns() {
		HumanVsComputerGame hc = new HumanVsComputerGame();
		hc.setRows("7");
		hc.setCols("5");
		hc.calculateNumberOfTurns();
		assertEquals(10, hc.getNumberOfShips());
    }
	
	

}
