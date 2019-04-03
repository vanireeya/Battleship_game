package com.sjsu.edu.components;

import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import com.sjsu.edu.util.Validator;
import com.sjsu.interfaces.AbstractBattleBoard;

public class BattleBoard implements AbstractBattleBoard {

	private String[][] battleBoard;
	private String[][] displaybattleBoard;
	private int blocksOccupied;
	List<Ship> fleet = new ArrayList<>();

	public BattleBoard() {

	}

	public BattleBoard(int row, int col) {
		battleBoard = new String[row][col];
		displaybattleBoard = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				battleBoard[i][j] = " ~ ";
				displaybattleBoard[i][j] = " ~ ";
			}
		}
	}

	@Override
	public void displayBoard() {
		for (int i = 0; i < battleBoard.length; i++) {
			for (int j = 0; j < battleBoard[0].length; j++) {
				System.out.print(battleBoard[i][j] + " ");
			}
			System.out.println("");
		}

	}

	public void displayGameBoard() {
		for (int i = 0; i < displaybattleBoard.length; i++) {
			for (int j = 0; j < displaybattleBoard[0].length; j++) {
				System.out.print(displaybattleBoard[i][j] + " ");
			}
			System.out.println("");
		}
	}

	@Override
	public void placeShip(int row, int col, String letter) {
		battleBoard[row][col] = letter;
	}

	@Override
	public boolean isValidLocation(int row, int col) {

		if (row >= 0 && row < battleBoard.length && col >= 0 && col < battleBoard[0].length
				&& battleBoard[row][col].equals(" ~ ")) {
			return true;
		}
		return false;
	}

	public boolean isInBoard(int row, int col) {
		if (row >= 0 && row < battleBoard.length && col >= 0 && col < battleBoard[0].length) {
			return true;
		}
		return false;
	}

	@Override
	public boolean areValidLocations(int alignment, int shipSize, int row, int col) {
		// checking if alignment is horizontal
		if (alignment == 1) {
			for (int i = 0; i < shipSize; i++) {
				boolean isValidPosition = isValidLocation(row, col + i);
				if (!isValidPosition) {
					return false;
				}
			}
			return true;
		} else {
			for (int i = 0; i < shipSize; i++) {
				boolean isValidPosition = isValidLocation(row + i, col);
				if (!isValidPosition) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public void setShip(int len, String id) {
		fleet.add(new Ship(len, id));
	}

	@Override
	public void attack(int row, int col) {

		if (battleBoard[row][col].equals(" ~ ")) {
			System.out.println("Miss");
		} else if (battleBoard[row][col].equals(" X ")) {
			System.out.println("Already Taken");
		} else {
			hit(battleBoard[row][col]);
			battleBoard[row][col] = " X ";
			displaybattleBoard[row][col] = " X ";
		}
		checkState();
	}

	public boolean checkState() {
		return fleet.isEmpty();

	}

	public void hit(String id) {
		for (int i = 0; i < fleet.size(); i++) {
			if (fleet.get(i).id.equals(id)) {
				fleet.get(i).attacked();
				if (fleet.get(i).isSunk()) {
					System.out.println("Ship Sunk");
					fleet.remove(i);

					break;
				} else {
					System.out.println("Hit!");
				}
			}

		}

	}

	public String[][] getBattleBoard() {
		return battleBoard;
	}
	
	
	

}
