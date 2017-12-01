package edu.txstate.cs3354.kasten.hw4;

public class Cell {
	private boolean isAlive;
	
	public Cell(boolean isAlive) {
		super();
		this.isAlive = isAlive;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
