package com.sjsu.edu.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sjsu.edu.util.Validator;
import com.sjsu.interfaces.AbstractGame;

public class HumanVsComputerGame implements AbstractGame {

	private List<Player> players;
	private static BufferedReader reader;
	private String rows;
	private String cols;
	private int numberOfShips, numberOfMShips = 2, numberOfLships = 1;
	private int numberOfTurnsToPlay;

	@Override
	public void begin() {
		reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("----------------------------------");
		System.out.println("Set the grid:\n");
		System.out.print("Enter number of rows: ");
		try {
			rows = reader.readLine();
			rows=rows.trim();
			while (!Validator.isValidIntegerInput(rows.trim()) || !Validator.validateGridSize(rows.trim())) {
				System.out.println("Invalid input. Size should be an integer and atleast 5");
				System.out.print("Enter number of rows: ");
				rows = reader.readLine();
				rows=rows.trim();
			}
			System.out.print("Enter number of cols: ");
			cols = reader.readLine();
			cols=cols.trim();
			while (!Validator.isValidIntegerInput(cols.trim()) || !Validator.validateGridSize(cols.trim())) {
				System.out.println("Invalid input. Size should be an integer and atleast 5");
				System.out.print("Enter number of cols: ");
				cols = reader.readLine();
				cols=cols.trim();
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

			System.out.println("Enter Player name: ");
			String name = reader.readLine();
			System.out.println("----------------------------------------------------");
			System.out.println("\nHello " + name + " your board has been initialized");
			BattleBoard battleBoard = new BattleBoard(Integer.parseInt(rows.trim()), Integer.parseInt(cols.trim()));
			battleBoard.displayBoard();
			System.out.println("\nYou can place \n\t1 Large Ship, \n\t2 Medium ships  \n\t" + numberOfShips
					+ " small ships\non your board\n");
			addDiffShips(battleBoard);
			players.add(new Player(battleBoard, numberOfShips, name));
			System.out.println("Player setup completed!!");
			initializeComputer();
			play();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initializeComputer() {
		BattleBoard battleBoard = new BattleBoard(Integer.parseInt(rows.trim()), Integer.parseInt(cols.trim()));
		addCompShips(battleBoard, 3, numberOfLships, " B");
		addCompShips(battleBoard, 2, numberOfMShips, " M");
		addCompShips(battleBoard, 1, numberOfShips, " S");
		players.add(new Player(battleBoard, numberOfShips, "Computer"));
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
	public void play() throws IOException {
		for (int i = 0; i < players.size(); i++) {
			if (i == players.size() - 1) {
				players.get(i).setOpponent(players.get(0));
				break;
			} else {
				players.get(i).setOpponent(players.get(i + 1));
			}
		}

		playerAttack();

	}

	public void playerAttack() {
		System.out.println("------------------------------------");
		System.out.println(players.get(0).getName() + " your turn to attack!");

		System.out.println("Enter row for the co-ordinate to attack");
		String row, col;
		try {
			row = reader.readLine();

			System.out.println("Enter col for starting of the ship ");
			col = reader.readLine();
			System.out.println("After attack, game board of "+players.get(0).getOpponent().getName());
			
			if (Validator.isValidIntegerInput(row) && Validator.isValidIntegerInput(col) && players.get(0)
					.getBattleBoard().isInBoard(Integer.parseInt(row.trim()), Integer.parseInt(col.trim()))) {
				players.get(0).getOpponent().attack(Integer.parseInt(row.trim()), Integer.parseInt(col.trim()));

				if (players.get(0).getOpponent().allShipsSunk()) {
					displayWinner(players.get(0));
					players.get(0).getOpponent().getBattleBoard().displayGameBoard();
				} else {
					players.get(0).getOpponent().getBattleBoard().displayGameBoard();
					compAttack();
				}
			} else {
				System.out.println("Invalid input, you lose your turn!!");
				players.get(0).getOpponent().getBattleBoard().displayGameBoard();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void compAttack() {
		System.out.println("------------------------------------");
		System.out.println("Computer's attack");
		System.out.println("After attack, game board of "+players.get(1).getOpponent().getName());
		String row = Integer.toString(generateRandom(0, Integer.parseInt(rows)-1));
		String col = Integer.toString(generateRandom(0, Integer.parseInt(cols)-1));
		players.get(1).getOpponent().attack(Integer.parseInt(row.trim()), Integer.parseInt(col.trim()));
		if (players.get(1).getOpponent().allShipsSunk()) {
			displayWinner(players.get(1));
			players.get(1).getOpponent().getBattleBoard().displayGameBoard();
		} else {
			players.get(1).getOpponent().getBattleBoard().displayGameBoard();
			playerAttack();
		}

	}

	@Override
	public void displayWinner(Player winner) {
		System.out.println("\n Congratulations!! " + winner.getName() + " won the game");
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

	public void addCompShips(BattleBoard battleBoard, int lengthOfShip, int numberOfShips, String letter) {
		int alignment = 1;

		for (int i = 1; i <= numberOfShips; i++) {
			if (lengthOfShip > 1) {
				alignment = generateRandom(1, 2);

			}
			String row;

			row = Integer.toString(generateRandom(0, Integer.parseInt(rows)-1));
			String col = Integer.toString(generateRandom(0, Integer.parseInt(cols)-1));
			if (Validator.isValidIntegerInput(row) && Validator.isValidIntegerInput(col)
					&& battleBoard.areValidLocations(alignment, lengthOfShip, Integer.parseInt(row.trim()),
							Integer.parseInt(col.trim()))) {

				if (alignment == 1) {
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
			} else {
				i--;
			}

		}

	}

	public int generateRandom(int min, int max) {
		
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}

	public int getNumberOfShips() {
		return numberOfShips;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

}
