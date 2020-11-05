package ui;

import java.util.Scanner;

import model.Control;

public class Menu {
	private Control control;
	private Scanner entrie1;
	private Scanner entrie2;

	public Menu() {
		entrie1 = new Scanner(System.in);
		entrie2 = new Scanner(System.in);
		this.control = new Control();
	}

	public void startMenu() {
		System.out.println("Welcome to the MatrixGame ");
		System.out.println("¿What operation would you like to do?");
		System.out.println("[1]. Play game");
		System.out.println("[2]. Score´s top");
		System.out.println("[3]. Exit the game");

		int option = entrie2.nextInt();

	}

}
