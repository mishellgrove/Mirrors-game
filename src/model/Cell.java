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
	
	

}
