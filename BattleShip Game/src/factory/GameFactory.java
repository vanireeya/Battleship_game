package factory;

import com.sjsu.edu.components.HumanVsComputerGame;
import com.sjsu.edu.components.HumanVsHumanGame;
import com.sjsu.interfaces.AbstractGame;

public class GameFactory {
	
	public static AbstractGame getGame(String choice) {
		return choice.equals("1") ? new HumanVsComputerGame() : new HumanVsHumanGame();
	}
}
