package com.sjsu.edu.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import com.sjsu.edu.util.Validator;
import com.sjsu.interfaces.AbstractGame;

public class HumanVsHumanGame implements AbstractGame {

	private String currentWinner;
	private String rows;
	private String cols;
	private static BufferedReader reader;
	private int numberOfShips, numberOfMShips = 2, numberOfLships = 1;
	private List<Player> players;
	private int numberOfTurnsToPlay;
	private final static int NUMBER_OF_PLAYERS = 2;

	@Override
	public void displayWinner(Player winner) {
		System.out.println("\n Congratulations!! " + winner.getName() + " won the game");
	}

	@Override
	public void begin() {
		reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("----------------------------------");
		System.out.println("Set the grid:\n");
		System.out.print("Enter number of rows: ");
		try {
			rows = reader.readLine();
			rows = rows.trim();
			while (!Validator.isValidIntegerInput(rows.trim()) || !Validator.validateGridSize(rows.trim())) {
				System.out.println("Invalid input. Size should be an integer and atleast 5");
				System.out.print("Enter number of rows: ");
				rows = reader.readLine();
				rows = rows.trim();
			}
			System.out.print("Enter number of cols: ");
			cols = reader.readLine();
			cols = cols.trim();
			while (!Validator.isValidIntegerInput(cols.trim()) || !Validator.validateGridSize(cols.trim())) {
				System.out.println("Invalid input. Size should be an integer and atleast 5");
				System.out.print("Enter number of cols: ");
				cols = reader.readLine();
				cols = cols.trim();
			}

			System.out.println("");
			calculateNumberOfTurns();
			initializePlayers();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initializePlayers() {

		try {
			players = new ArrayList<>();
			for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
				System.out.println("Enter Player " + i + " name: ");
				String name = reader.readLine();
				System.out.println("----------------------------------------------------");
				System.out.println("\nHello " + name + " your board has been initialized");
				BattleBoard battleBoard = new BattleBoard(Integer.parseInt(rows.trim()), Integer.parseInt(cols.trim()));
				battleBoard.displayBoard();
				System.out.println("\nYou can place \n\t1 Large Ship, \n\t2 Medium ships  \n\t" + numberOfShips
						+ " small ships\non your board\n");
				addDiffShips(battleBoard);
				players.add(new Player(battleBoard, numberOfShips, name));
				System.out.println("Player  " + i + " setup completed!!");
			}
			play();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void calculateNumberOfTurns() {
		numberOfTurnsToPlay = ((Integer.parseInt(rows) * Integer.parseInt(cols)) / 2) - (2 * numberOfMShips)
				- (3 * numberOfLships);
		numberOfShips = numberOfTurnsToPlay;
	}

	@Override
	public void addDiffShips(BattleBoard battleBoard) {
		try {
			addShips(battleBoard, 3, numberOfLships, " B");
			addShips(battleBoard, 2, numberOfMShips, " M");
			addShips(battleBoard, 1, numberOfShips, " S");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void addShips(BattleBoard battleBoard, int lengthOfShip, int numberOfShips, String letter)
			throws IOException {
		String alignment = "1";

		for (int i = 1; i <= numberOfShips; i++) {
			if (lengthOfShip > 1) {
				System.out.println("How would you like to place ship with " + lengthOfShip
						+ " blocks length \n1. Horizontally \n2. Vertically");
				alignment = reader.readLine();
				while (!Validator.isValidIntegerInput(alignment.trim())
						|| !Validator.validateChoice(alignment.trim())) {
					System.out.println("Invalid input. Choice should be an integer and either 1 0r 2");
					System.out.print("How would you like to place ship with " + lengthOfShip
							+ " blocks) \n1. Horizontally \n2. Vertically ");
					alignment = reader.readLine();
				}
			}
			System.out.println("Enter row for starting of the ship ");
			String row;
			try {
				row = reader.readLine();
				System.out.println("Enter col for starting of the ship ");
				String col = reader.readLine();
				if (Validator.isValidIntegerInput(row) && Validator.isValidIntegerInput(col)
						&& battleBoard.areValidLocations(Integer.parseInt(alignment.trim()), lengthOfShip,
								Integer.parseInt(row.trim()), Integer.parseInt(col.trim()))) {

					if (alignment.equals("1")) {
						for (int k = 0; k < lengthOfShip; k++) {
							battleBoard.placeShip(Integer.parseInt(row.trim()), Integer.parseInt(col.trim()) + k,
									letter + i);
						}
					} else {
						for (int k = 0; k < lengthOfShip; k++) {
							battleBoard.placeShip(Integer.parseInt(row.trim()) + k, Integer.parseInt(col.trim()),
									letter + i);
						}
					}
					battleBoard.setShip(lengthOfShip, letter + i);
					battleBoard.displayBoard();

				} else {
					System.out.println("Invalid input !!");
					i--;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void play() throws IOException {

		for (int i = 0; i < players.size(); i++) {
			if (i == players.size() - 1) {
				players.get(i).setOpponent(players.get(0));
				break;
			} else {
				players.get(i).setOpponent(players.get(i + 1));
			}
		}

		int i = 0;
		while (true) {
			System.out.println("------------------------------------");
			System.out.println(players.get(i).getName() + " your turn to attack!");

			System.out.println("Enter row for the co-ordinate to attack");
			String row = reader.readLine();
			System.out.println("Enter col for starting of the ship ");
			String col = reader.readLine();

			if (Validator.isValidIntegerInput(row) && Validator.isValidIntegerInput(col) && players.get(i)
					.getBattleBoard().isInBoard(Integer.parseInt(row.trim()), Integer.parseInt(col.trim()))) {
				players.get(i).getOpponent().attack(Integer.parseInt(row.trim()), Integer.parseInt(col.trim()));

				if (players.get(i).getOpponent().allShipsSunk()) {
					displayWinner(players.get(i));
					break;
				}
			} else {
				System.out.println("Invalid input, you lose your turn!!");
			}
			System.out.println("Game board of player " + players.get(i).getOpponent().getName());
			players.get(i).getOpponent().getBattleBoard().displayGameBoard();

			if (i == players.size() - 1) {
				i = 0;
			} else {
				i++;
			}

		}

	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public int getNumberOfShips() {
		return numberOfShips;
	}

}
