package model;

public class Player {
	
	private String nickname;
	private int score;
	
	private Player padre;
	private Player izq;
	private Player der;
	
	public Player (String nickname, int score) {
		this.nickname = nickname;
		this.score = score;
		
		this.padre = null;
		this.izq = null;
		this.der = null;
	}
	

}
