package JUnit;


import static org.junit.Assert.*;

import org.junit.Test;

import com.sjsu.edu.components.BattleBoard;
import com.sjsu.edu.components.HumanVsComputerGame;
import com.sjsu.edu.components.HumanVsHumanGame;
import com.sjsu.interfaces.AbstractGame;

import factory.GameFactory;
public class BattleBoardTest {

	@Test
    public void testPlacement() {
		BattleBoard bt= new BattleBoard(5,5);
		bt.placeShip(0, 0, " S1");
		assertEquals(" S1", bt.getBattleBoard()[0][0]);
    }
	
	@Test
    public void testMiss() {
		BattleBoard bt= new BattleBoard(5,5);
		bt.placeShip(0, 0, " S1");
		bt.setShip(1, " S1");
		bt.attack(1,1);
		assertEquals(" ~ ", bt.getBattleBoard()[1][1]);
    }
	
	
	@Test
    public void testHit() {
		BattleBoard bt= new BattleBoard(5,5);
		bt.placeShip(0, 0, " S1");
		bt.setShip(1, " S1");
		bt.attack(0,0);
		assertEquals(" X ", bt.getBattleBoard()[0][0]);
    }
	
	@Test
    public void testAlreadyTaken() {
		BattleBoard bt= new BattleBoard(5,5);
		bt.placeShip(0, 0, " S1");
		bt.setShip(1, " S1");
		bt.placeShip(1, 1, " S2");
		bt.setShip(1, " S2");
		bt.attack(0,0);
		bt.attack(0,0);
		assertEquals(" X ", bt.getBattleBoard()[0][0]);
    }
	
	@Test
    public void testWin() {
		BattleBoard bt= new BattleBoard(5,5);
		bt.placeShip(0, 0, " S1");
		bt.setShip(1, " S1");
		bt.placeShip(1, 1, " S2");
		bt.setShip(1, " S2");
		bt.attack(0,0);
		bt.attack(1,1);
		assertEquals(true, bt.checkState());
    }
	
	

}
