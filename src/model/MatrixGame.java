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


		createMatrix();
		initializeMirror(mirror, rows, cols);

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
	
	public void createMatrix() {
		first = new Cell(0,'A');
		createRow(0, 0, first);
	}

	public void createCol(int r, int c, Cell prev) {
		if(c < cols) {
			Cell current = new Cell(r, (char)('A'+c));
			prev.setNext(current);
			current.setPrev(prev);
			createCol(r,c+1,current);
		}
	}

	public void createRow(int r, int c, Cell firstNode) {
		createCol(r,c+1,firstNode);

		if(r+1 < rows) {
			Cell currentDown = new Cell(r+1,'A');
			currentDown.setUp(firstNode);
			firstNode.setDown(currentDown);
			createRow(r+1,c,currentDown);
		}

	}

	public Cell recorrerMatrix(int i, char c, Cell temp) {    /// 3B
		try {
			if(i == temp.getRow()) { /// 1A

				if(c == temp.getCol()) {
					return temp;
				}else {
					temp = temp.getNext();
					return recorrerMatrix(i, c, temp);
				}
			}else {
				temp = temp.getDown();
				return recorrerMatrix(i,c,temp);
			}

		}catch(NullPointerException a) {
			return null;
		}
	}

		public Cell generatePosition(int r, int c) {     // r = 3; c = 5;
			double w = Math.random()*r+1;
			double q = Math.random()*c;
			int rows = (int) w;
			int b = (int) q;
			char cols = (char) ('A' + b);
			return recorrerMatrix(rows,cols,getFirst());
		}

		public void initializeMirror(int mirror, int a , int b) {
			setMirrorCon(mirror);
			setMirror(mirror);
			setContador(mirror);
		}
		
		public void calculateScore() {
			int resta = mirror - mirrorCon;
			
			int a = resta*100;
			int b = getDisparos()*4;
			
			int z = a-b;
			
			player.setScore(z);
		}

	}
