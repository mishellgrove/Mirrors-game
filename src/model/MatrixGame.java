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
	
	public Player mandarDatos() {
		return player;
	}

	public String generateinc() {
		String msg = "";
		double a = Math.random()*10 ;
		int b = (int) a; 
		if(b % 2 == 0) {
			msg = "/";
		}else {
			msg = "\\";
		}

		return msg;
	}

	public void generateMirror(int a, int b, int mirror) { // a = 3; b = 3; mirror = 4; mirror > 9
		String msg = "";
		//System.out.println(mirror);
		if(contador <= a * b) {     
			if(contador <= 0) {
			}else {
				Cell temp = generatePosition(a,b);  //1A ; 1A

				//System.out.println(temp);
				//System.out.println(temp.getMirror());

				if(temp.getMirror() == "") {    //1A = ""; 1A = "/" O 1A = "\\";  
					//System.out.println("true");
					temp.setMirror(generateinc());
					//System.out.println(temp.getMirror());
				}else {
					//System.out.println("Hay una position que ya tiene");
					generateMirror(a,b,contador);
				}
				//System.out.println("Siguiente espejo");
				contador = contador-1;
				generateMirror(a,b, contador);
			}
		}else {
			msg = "No se puede generar los espejos porque la cantidad supera las dimensiones de la matriz";
		}

	}
	
	public void mostrarEspejo(Cell temp, String incli) {
		if(temp.haveMirror() == true) {
			if(temp.getMirror().equals(incli)) {
				temp.setC(true);
				setMirrorCon(mirrorCon-1);
			}else {
				temp.setB(true);
			}
		}else {
			temp.setB(true);
		}
			
	}
	
	public void lanzarRayo(int row, char col, String orientation) {
		Cell temp = recorrerMatrix(row, col,getFirst());
				// Orientation null
			if(contadora == 0) { // Para prevenir que estemos en el inicio sea borde
				if(temp.getRow() == 1) { // Borde Superior
					if(temp.haveMirror() == true) {
						if(temp.getMirror().equals("/")) {
							if(recorrerMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow(), (char)(temp.getCol()-1), "HI");
								temp.setStart(true);
							}
						}else {
							if(recorrerMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow(), (char)(temp.getCol()+1), "HD");
								temp.setStart(true);
							}
						}
					}else {
						//Borde superior
						contadora = contadora + 1;
						if(recorrerMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
							lanzarRayo(temp.getRow()+1, temp.getCol(), "VD");
							temp.setStart(true);
						}
					}
					
				}else if (temp.getRow() == getRows()){   // borde inferior 
					if(temp.haveMirror() == true) {
						if(temp.getMirror().equals("/")) {
							if(recorrerMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow(), (char)(temp.getCol()+1), "HD");
								temp.setStart(true);
							}
						}else {
							if(recorrerMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow(), (char)(temp.getCol()-1), "HI");
								temp.setStart(true);
							}
						}
					}else {
						//Borde inferior
						contadora = contadora + 1;
						if(recorrerMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
							lanzarRayo(temp.getRow()-1, temp.getCol(), "VU");
							temp.setStart(true);
						}
					}
				}else if (temp.getCol() == 'A') { //Borde izquierdo
					if(temp.haveMirror() == true) {
						if(temp.getMirror().equals("/")) {
							if(recorrerMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow()-1, temp.getCol(), "VU");
								temp.setStart(true);
							}
						}else {
							if(recorrerMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow()+1, temp.getCol(), "VD");
								temp.setStart(true);
							}
						}
					}else {
						//Borde izquierdo
						contadora = contadora + 1;
						if(recorrerMatrix(temp.getRow(), (char) (temp.getCol()+1), getFirst()) != null) {
							lanzarRayo(temp.getRow(), (char) (temp.getCol()+1), "HD");
							temp.setStart(true);
						}
					}
					
				}else if (temp.getCol() == 'A' + (getCols()-1)) { //Borde derecho
					if(temp.haveMirror() == true) {
						if(temp.getMirror().equals("/")) {
							if(recorrerMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow()+1, temp.getCol(), "VD");
								temp.setStart(true);
							}
						}else {
							if(recorrerMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
								contadora = contadora + 1;
								lanzarRayo(temp.getRow()-1, temp.getCol(), "VU");
								temp.setStart(true);
							}
						}
					}else {
						//Borde derecho
						contadora = contadora + 1;
						if(recorrerMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) != null) {
							lanzarRayo(temp.getRow(), (char)(temp.getCol()-1), "HI");
							temp.setStart(true);
						}
					}
				}
			}else {// cuando la contadora == 1
				
			} //contadora debería aumentar
					
	}
}
