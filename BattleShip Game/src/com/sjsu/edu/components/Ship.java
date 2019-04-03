package com.sjsu.edu.components;

public class Ship {

	private int lenOfShip;
	String id;

	public int getLenOfShip() {
		return lenOfShip;
	}

	public void setLenOfShip(int lenOfShip) {
		this.lenOfShip = lenOfShip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ship(int len, String _id) {
		lenOfShip = len;
		id = _id;
	}

	public boolean isSunk() {
		return lenOfShip == 0;
	}

	public void attacked() {
		lenOfShip--;
	}

}
