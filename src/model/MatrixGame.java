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

	public Cell traverseMatrix(int i, char c, Cell temp) {    /// 3B
		try {
			if(i == temp.getRow()) { /// 1A

				if(c == temp.getCol()) {
					return temp;
				}else {
					temp = temp.getNext();
					return traverseMatrix(i, c, temp);
				}
			}else {
				temp = temp.getDown();
				return traverseMatrix(i,c,temp);
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
		return traverseMatrix(rows,cols,getFirst());
	}

	public void initializeMirror(int mirror, int a , int b) {
		setMirrorCon(mirror);
		setMirror(mirror);
		setContador(mirror);
		generateMirror(a,b,mirror);
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
			msg = "The mirrors cannot be generated because the quantity exceeds the dimensions of the matrix";
		}

	}
	
	/*
	* The method is to show a mirror
	* @pre: Cell! = null
	* @param: Cell temp, String incli
	* @return: void
	* @post: show a mirror to user
	*/

	public void showMirror(Cell temp, String incli) {
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
	
	/*
	* The method is to shoot a ray
	* @pre: first! = null;
	* @param: Int row, char col, string orientation
	* @return: void
	* @post: shot ray
	*/

	public void shootRay(int row, char col, String orientation) {
		Cell temp = traverseMatrix(row, col,getFirst());
		// Orientation null
		if(contadora == 0) { // Para prevenir que estemos en el inicio sea borde
			if(temp.getRow() == 1) { // Borde Superior
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow(), (char)(temp.getCol()-1), "HI");
							temp.setStart(true);
						}
					}else {
						if(traverseMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow(), (char)(temp.getCol()+1), "HD");
							temp.setStart(true);
						}
					}
				}else {
					//Borde superior
					contadora = contadora + 1;
					if(traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
						shootRay(temp.getRow()+1, temp.getCol(), "VD");
						temp.setStart(true);
					}
				}

			}else if (temp.getRow() == getRows()){   // borde inferior 
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow(), (char)(temp.getCol()+1), "HD");
							temp.setStart(true);
						}
					}else {
						if(traverseMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow(), (char)(temp.getCol()-1), "HI");
							temp.setStart(true);
						}
					}
				}else {
					//Borde inferior
					contadora = contadora + 1;
					if(traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
						shootRay(temp.getRow()-1, temp.getCol(), "VU");
						temp.setStart(true);
					}
				}
			}else if (temp.getCol() == 'A') { //Borde izquierdo
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow()-1, temp.getCol(), "VU");
							temp.setStart(true);
						}
					}else {
						if(traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow()+1, temp.getCol(), "VD");
							temp.setStart(true);
						}
					}
				}else {
					//Borde izquierdo
					contadora = contadora + 1;
					if(traverseMatrix(temp.getRow(), (char) (temp.getCol()+1), getFirst()) != null) {
						shootRay(temp.getRow(), (char) (temp.getCol()+1), "HD");
						temp.setStart(true);
					}
				}

			}else if (temp.getCol() == 'A' + (getCols()-1)) { //Borde derecho
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow()+1, temp.getCol(), "VD");
							temp.setStart(true);
						}
					}else {
						if(traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
							contadora = contadora + 1;
							shootRay(temp.getRow()-1, temp.getCol(), "VU");
							temp.setStart(true);
						}
					}
				}else {
					//Borde derecho
					contadora = contadora + 1;
					if(traverseMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) != null) {
						shootRay(temp.getRow(), (char)(temp.getCol()-1), "HI");
						temp.setStart(true);
					}
				}
			}
		}else { //contadora == 1
			if(orientation == "HD") {
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "VU");
						}else {
							temp.setStop(true);
							setContadora(0);

						}
					}else {
						if(traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "VD");
						}else {

							temp.setStop(true);
							setContadora(0);


						}
					}
				}else {
					if(traverseMatrix(temp.getRow(), (char) (temp.getCol()+1), getFirst()) != null) {
						Cell temp1 = traverseMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst());
						shootRay(temp1.getRow(), temp1.getCol(), "HD");
					}else {

						temp.setStop(true);
						setContadora(0);


					}	
				}
			}else if(orientation == "HI") {
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "VD");
						}else {

							temp.setStop(true);
							setContadora(0);


						}
					}else {
						if(traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "VU");
						}else {
							temp.setStop(true);
							setContadora(0);


						}
					}
				}else {
					if(traverseMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst()) != null) {
						Cell temp1 = traverseMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst());
						shootRay(temp1.getRow(), temp1.getCol(), "HI");
					}else {

						temp.setStop(true);
						setContadora(0);


					}	
				}
			}else if(orientation == "VU") {
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow(), (char) (temp.getCol()+1), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow(), (char) (temp.getCol()+1), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "HD");
						}else {

							temp.setStop(true);
							setContadora(0);


						}
					}else {
						if(traverseMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "HI");
						}else {

							temp.setStop(true);
							setContadora(0);


						}
					}
				}else {
					if(traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) != null) {
						Cell temp1 = traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst());
						shootRay(temp1.getRow(), temp1.getCol(), "VU");
					}else {
						temp.setStop(true);
						setContadora(0);


					}	
				}
			}else if(orientation == "VD") {
				if(temp.haveMirror() == true) {
					if(temp.getMirror().equals("/")) {
						if(traverseMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "HI");
						}else {
							temp.setStop(true);
							setContadora(0);


						}
					}else {
						if(traverseMatrix(temp.getRow(), (char) (temp.getCol()+1), getFirst()) != null) {
							Cell temp1 = traverseMatrix(temp.getRow(), (char) (temp.getCol()+1), getFirst());
							shootRay(temp1.getRow(), temp1.getCol(), "HD");
						}else {
							setContadora(0);
							temp.setStop(true);


						}
					}
				}else {
					if(traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) != null) {
						Cell temp1 = traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst());
						shootRay(temp1.getRow(), temp1.getCol(), "VD");
					}else {
						setContadora(0);
						temp.setStop(true);

					}	
				}
			}
		} //contadora 

	}

	public String identificarEsquina(Cell temp) {
		if((traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) == null) && (traverseMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) ==null)) { //Get up == null, get prev == null
			return "SI";
			//Esquina superior izquierda
		}else if((traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) == null) && (traverseMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) ==null)){
			return "SD";
			//Esquina superior derecha
		}else if((traverseMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst()) == null) && (traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) ==null)) {
			//System.out.println((recorrerMatrix(temp.getRow(), (char) (temp.getCol()-1), getFirst())));
			//System.out.println((recorrerMatrix(temp.getRow()+1, temp.getCol(), getFirst())));
			return "II";
			//Esquina inferior izquierda
		}else if((traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) == null) && (traverseMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) ==null)) {
			//System.out.println((recorrerMatrix(temp.getRow()+1, temp.getCol(), getFirst())));
			//System.out.println((recorrerMatrix(temp.getRow()+1, (char) (temp.getCol()+1), getFirst())));
			return "ID";
			//Esquina inferior derecha4

		}else {
			return "Corner not identified";
		}

	}

	public boolean identificarEsquina1(Cell temp) {
		if((traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) == null) && (traverseMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) ==null)) { //Get up == null, get prev == null
			return true;
			//Esquina superior izquierda
		}else if((traverseMatrix(temp.getRow()-1, temp.getCol(), getFirst()) == null) && (traverseMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) ==null)){
			return true;
			//Esquina superior derecha
		}else if((traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) == null) && (traverseMatrix(temp.getRow(), (char)(temp.getCol()-1), getFirst()) ==null)) {
			return true;
			//Esquina inferior izquierda
		}else if((traverseMatrix(temp.getRow()+1, temp.getCol(), getFirst()) == null) && (traverseMatrix(temp.getRow(), (char)(temp.getCol()+1), getFirst()) ==null)) {
			return true;
			//Esquina inferior derecha4

		}else {
			return false;
		}

	}

	public String identificarComms(String comms) {
		String msg = "";
		if((comms.length() == 4) && (comms.charAt(0) == 'L')) {

			String a = comms.charAt(1) + "";
			int b = Integer.parseInt(a);
			char c = comms.charAt(2);
			String incli = comms.charAt(3)+""; 
			if(traverseMatrix(b,c,getFirst()) != null ) {
				Cell temp1 = traverseMatrix(b,c,getFirst());
				if(incli.equals("R")) {
					//Llame metodo de validar espejo
					showMirror(temp1,"/");

				}else if(incli.equals("L")) {
					showMirror(temp1, "\\");
				}else {
					msg = "It is not recognized if it is R or L in the command";
				}
			}else {
				msg = "Cell position does not exist";
			}
		}else if(comms.length() == 3){ //1AH
			char col = comms.charAt(1);
			String ab = comms.charAt(0)+"";

			int row = Integer.parseInt(ab);
			String orien = comms.charAt(2) + "";

			if(traverseMatrix(row,col, getFirst()) != null ) {
				if((comms.charAt(comms.length()-1) == 'H')) {
					Cell temp = traverseMatrix(row,col,getFirst());
					if(identificarEsquina(temp).equals("SI")) {
						temp.setStart(true);
						contadora = contadora + 1;
						shootRay(row,col, "HD");
						disparos = disparos +1;
					}else if(identificarEsquina(temp).equals("SD")){
						temp.setStart(true);
						contadora = contadora + 1;
						shootRay(row,col, "HI");
						disparos = disparos +1;
					}else if(identificarEsquina(temp).equals("II")) {
						temp.setStart(true);
						contadora = contadora + 1;
						shootRay(row,col, "HD");
						disparos = disparos +1;
					}else if(identificarEsquina(temp).equals("ID")) {
						temp.setStart(true);
						contadora = contadora + 1;
						shootRay(row,col, "HI");
						disparos = disparos +1;
					}
				}else if((comms.charAt(comms.length()-1) == 'V')){
					Cell temp = traverseMatrix(row,col,getFirst());
					if(identificarEsquina(temp).equals("SI")) {
						temp.setStart(true);
						contadora = contadora + 1;
						shootRay(row,col, "VD");
						disparos = disparos +1;
					}else if(identificarEsquina(temp).equals("SD")){
						temp.setStart(true);
						contadora = contadora + 1;
						shootRay(row,col, "VD");
						disparos = disparos +1;
					}else if(identificarEsquina(temp).equals("II")) {
						temp.setStart(true);
						contadora = contadora + 1;
						shootRay(row,col, "VU");
						disparos = disparos +1;
					}else if(identificarEsquina(temp).equals("ID"))
						temp.setStart(true);
					contadora = contadora + 1;
					shootRay(row,col, "VU");
					disparos = disparos +1;
				}else {
					msg = "It is not recognized if it is H or V in the command";
				}
			}else {
				msg = "Cell position does not exist";
			}
		}else {
			String a = comms.charAt(0) + "";
			int b = Integer.parseInt(a);
			char c = comms.charAt(1);

			if(traverseMatrix(b,c, getFirst()) != null ) {
				//Llame método de lanzar rayo
				toString();
				disparos = disparos +1;
				Cell temp = traverseMatrix(b,c,getFirst());
				if(identificarEsquina1(temp) == true){
					toString();
				}else {
					shootRay(b,c, "");
				}

			}

		}
		return msg;
	}
	
	public String colsString(Cell current) {
		String msg = "";
		if(current != null) {
			msg = current.toString();
			current.setStart(false);
			current.setStop(false);
			current.setStop(false);
			msg += colsString(current.getNext());
		}
		return msg;
	}
	
	public String colsString2(Cell current) {
		String msg = "";
		if(current != null) {
			msg = current.toString(true);
			msg += colsString2(current.getNext());
		}
		return msg;
	}
	
	public String rowsString(Cell firstRow) {
		String msg = "";
		if(firstRow != null) {
			msg = colsString(firstRow) + "\n";
			msg += rowsString(firstRow.getDown());
		}
		
		return msg;
	}
	
	public String rowsString2(Cell firstRow) {
		String msg = "";
		if(firstRow != null) {
			msg = colsString2(firstRow) + "\n";
			msg += rowsString2(firstRow.getDown());
		}
		
		return msg;
	}
	
	public String showAll() {
		String msg = "";
		msg = rowsString2(first);
		
		return msg;
	}
	
	public String toString() {
		String msg = "";
		msg = rowsString(first);
		
		return msg;
	}
	
	public String toString(boolean a) {
		String msg = "";
		msg = rowsString2(first);
		
		return msg;
	}

}
