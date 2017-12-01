package edu.txstate.cs3354.kasten.hw4;

import java.util.ArrayList;

public class Board {
	private ArrayList<ArrayList<Cell>> grid;

	public Board(ArrayList<ArrayList<Cell>> grid) {
		super();
		this.grid = grid;
	}
	
	public Board(int size) {
		super();
		grid = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			grid.add(new ArrayList<>());
			for (int j = 0; j < size; j++) {
				grid.get(i).add(new Cell(false));
			}
		}
	}

	public ArrayList<ArrayList<Cell>> getGrid() {
		return grid;
	}

	public void setGrid(ArrayList<ArrayList<Cell>> grid) {
		this.grid = grid;
	}
}
