package model;

public class Cell {

	private int row;
	private char col;
	private String position;
	private String mirror;
	private boolean b; 
	private boolean c;
	private boolean start;
	private boolean stop;

	private Cell prev;
	private Cell next;
	private Cell down;
	private Cell up;

	public Cell(int row, char col) {
		this.row = row+1;
		this.col = col;
		
		this.prev = null;
		this.next = null;
		this.down = null;
		this.up = null;
		
		this.b = false;
		this.c = false;
		
		
		this.position = this.row + "" + this.col;
		this.mirror = "";
		
	}
	
	public boolean isC() {
		return c;
	}

	public void setC(boolean c) {
		this.c = c;
	}

	public boolean haveMirror() {
		if(mirror == "") {
			return false;
		}else {
			return true;
		}
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public char getCol() {
		return col;
	}

	public void setCol(char col) {
		this.col = col;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMirror() {
		return mirror;
	}

	public void setMirror(String mirror) {
		this.mirror = mirror;
	}

	public Cell getPrev() {
		return prev;
	}

	public void setPrev(Cell prev) {
		this.prev = prev;
	}

	public Cell getNext() {
		return next;
	}

	public void setNext(Cell next) {
		this.next = next;
	}

	public Cell getDown() {
		return down;
	}

	public void setDown(Cell down) {
		this.down = down;
	}

	public Cell getUp() {
		return up;
	}

	public void setUp(Cell up) {
		this.up = up;
	}
	
	

}
