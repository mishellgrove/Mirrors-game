package model;

public class MatrixGame {

	private int rows;
	private int cols;
	private int contador;
	private int contadora;
	private int mirrorCon;
	private int disparos;
	private int mirror;

	private Cell first;
	private Player player;

	
	public MatrixGame(String name, int rows, int cols, int mirror) {
		this.rows = rows;
		this.cols = cols;
		this.contador = 0;
		this.contadora = 0;
		this.mirrorCon = 0;
		this.disparos = 0;
		this.mirror = mirror;
		player = new Player(name, 0);

	}

}
