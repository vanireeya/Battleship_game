package com.sjsu.edu.components;



public class Player {
	
	
	private BattleBoard battleBoard = null;
	private Player opponent;
	private int numberofships = 0;
	private int score=0;
	private String name;
	
	
	public Player(BattleBoard battleBoard, int numberofships, String name) {
		super();
		this.battleBoard = battleBoard;
		this.numberofships = numberofships;
		this.name = name;
	}

	
	
	public Player getOpponent() {
		return opponent;
	}

	public boolean allShipsSunk(){
		return battleBoard.checkState();
	}


	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}



	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player() {
		battleBoard = new BattleBoard();
	}

	public BattleBoard getBattleBoard() {
		return battleBoard;
	}

	public void setBattleBoard(BattleBoard battleBoard) {
		this.battleBoard = battleBoard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumberofships(int numberofships) {
		this.numberofships = numberofships;
	}

	public int getNumberofships() {
		return numberofships;
	}
	
	 public void attack(int row, int col){
		 battleBoard.attack(row, col);
	 }
	
	
}
