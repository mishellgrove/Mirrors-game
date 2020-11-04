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
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public Cell getFirst() {
		return first;
	}

	public void setFirst(Cell first) {
		this.first = first;
	}
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public int getContadora() {
		return contadora;
	}

	public void setContadora(int contadora) {
		this.contadora = contadora;
	}
	
	public int getMirrorCon() {
		return mirrorCon;
	}

	public void setMirrorCon(int mirrorCon) {
		this.mirrorCon = mirrorCon;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getMirror() {
		return mirror;
	}

	public void setMirror(int mirror) {
		this.mirror = mirror;
	}

	public int getDisparos() {
		return disparos;
	}

	public void setDisparos(int disparos) {
		this.disparos = disparos;
	}

	

}
