package JUnit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.sjsu.edu.components.HumanVsComputerGame;
import com.sjsu.edu.components.HumanVsHumanGame;
import com.sjsu.interfaces.AbstractGame;

import factory.GameFactory;
public class HumanVsComputerTest {

	@Test
    public void testcalculateNumberOfTurns() {
		HumanVsHumanGame hm = new HumanVsHumanGame();
		hm.setRows("5");
		hm.setCols("5");
		hm.calculateNumberOfTurns();
		assertEquals(5, hm.getNumberOfShips());
    }
	
	

}
