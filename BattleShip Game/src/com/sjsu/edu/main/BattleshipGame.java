package com.sjsu.edu.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sjsu.edu.util.Validator;
import com.sjsu.interfaces.AbstractGame;

import factory.GameFactory;

public class BattleshipGame {

	private static BufferedReader reader;
	private static String choice;
	
	
	public static void initializeGame() throws IOException {
		AbstractGame abstractGame = GameFactory.getGame(choice);
		abstractGame.begin();
	}
	
	public static void clearScreen() {  
		 
	}

	public static void selectMode() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {

			System.out.println("Select Game mode");
			System.out.println("1. Human vs Computer ");
			System.out.println("2. Human vs Human ");
			System.out.print("\nEnter your choice:  ");
			choice = reader.readLine();

			while (!Validator.validateChoice(choice) && !choice.equals("0")) {
				System.out.println("\nInvalid Input!! Press 0 to exit");
				System.out.println("1. Human vs Computer ");
				System.out.println("2. Human vs Human ");
				System.out.print("\nEnter your choice:  ");
				System.out.println("");
				choice = reader.readLine();
			}
			
			if (choice.equals("0")) {
				System.out.println("Thank you for playing!!");
				System.exit(0);
			} else {
				initializeGame();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		System.out.println("Welcome to BattleShip game");
		System.out.println("");
		selectMode();
	}
}
