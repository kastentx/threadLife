package edu.txstate.cs3354.kasten.hw4;

public class Cell {
	private boolean isAlive;
	private int row;
	private int column;
	
	public Cell(boolean isAlive, int row, int column) {
		super();
		this.isAlive = isAlive;
		this.row = row;
		this.column = column;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
}
