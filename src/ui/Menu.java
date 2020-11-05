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
		System.out.println("---------------------------------------");
		System.out.println("Welcome to the MatrixGame ");
		System.out.println("---------------------------------------");
		System.out.println("¿What operation would you like to do?");
		System.out.println("[1]. Play game");
		System.out.println("[2]. Score´s top");
		System.out.println("[3]. Exit the game");

		int option = entrie2.nextInt();
		option(option);

	}
	
	public void option(int option) {
		if(option == 1) {
			option1();
		}else if(option == 2) {
			option2();
		}else if(option == 3) {
			option3();
		}else {
			System.out.println("Incorrect Entry");
			startMenu();
		}
	}
	
	public void option1() {
		System.out.println("- Please write his nickname, the dimension mxn of the matrix and k mirrors. Please: All information is separate with [,]");
		String answer1 = entrie1.nextLine();
		String[] answer2 = answer1.split(",");
		control.initialize(answer2[0], Integer.parseInt(answer2[1]), Integer.parseInt(answer2[2]), Integer.parseInt(answer2[3]));
		System.out.println(control.getGame());
		lectura();
		
	}
	
	public void lectura() {
		if(control.getGame().getMirrorCon() == 0) {
			System.out.println(control.getGame().getPlayer().getNickname() + " " + control.getGame().getPlayer().getScore());
			System.out.println("Ganaste");
			control.getGame().calculateScore();
			control.recibirdatos();
			startMenu();
		}else {
			System.out.println(control.getGame().toString(true));
			System.out.println("CODE: ");
			System.out.println("Espejos restantes: " + control.getGame().getMirrorCon());
			control.getGame().calculateScore();
			System.out.println(control.getGame().getPlayer().getNickname() + " " + control.getGame().getPlayer().getScore());
			String comms = entrie1.nextLine();
			if(comms.equals("menu")) {
				startMenu();
			}else {
				control.getGame().identificarComms(comms);
				System.out.println(control.getGame());
				lectura();
			}
			
		}
	}
	
	public void option2() {
		control.inOrden(control.getRoot());
		System.out.println(control.getParticipants());
		startMenu();
		
	}
	
	/*
	 * If you call a method option3, it allow to exit of the program
	 * @pre: m != null;
	 * @param : empty
	 * @return : void
	 * @post : The exit of the program
	 */
	
	public void option3() {
		System.exit(0);
		
	}


}
