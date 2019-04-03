package com.sjsu.interfaces;

public interface AbstractBattleBoard {
	
	public void displayBoard();
	public void placeShip(int row, int col,String Letter);
	public boolean isValidLocation(int row, int col);
	public boolean areValidLocations(int alignment, int shipSize, int row, int col);
	public void setShip(int len, String id);
	public void attack(int row, int col);
}