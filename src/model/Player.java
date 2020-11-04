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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player getPadre() {
		return padre;
	}

	public void setPadre(Player padre) {
		this.padre = padre;
	}

	public Player getIzq() {
		return izq;
	}

	public void setIzq(Player izq) {
		this.izq = izq;
	}

	public Player getDer() {
		return der;
	}

	public void setDer(Player der) {
		this.der = der;
	}

	public String toString() {
		return nickname;
	}

}
