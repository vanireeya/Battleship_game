package com.sjsu.interfaces;

import java.io.IOException;

import com.sjsu.edu.components.BattleBoard;
import com.sjsu.edu.components.Player;

public interface AbstractGame {
	
	public void begin();
	public void initializePlayers();
	public void calculateNumberOfTurns();
	public void addDiffShips(BattleBoard battleBoard);
	public void play() throws IOException;
	void displayWinner(Player winner);
	void addShips(BattleBoard battleBoard, int lengthOfShip, int numberOfShips, String letter) throws IOException;
	
}
