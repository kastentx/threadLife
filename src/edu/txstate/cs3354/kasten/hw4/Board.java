package edu.txstate.cs3354.kasten.hw4;

import java.util.ArrayList;

public class Board {
	private ArrayList<ArrayList<Cell>> grid;
	private int size;

	public Board(ArrayList<String> lines) {
		super();
		this.size = lines.size();
		grid = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			// each element in the array represents a row in the grid
			grid.add(new ArrayList<>());
			for (int j = 0; j < size; j++) {
				// set each cell's initial state 
				int livingState = 0;
				char cellChar = lines.get(i).charAt(j);  
				if (cellChar == 'X') livingState = 1;
				grid.get(i).add(new Cell(this, livingState, i, j));
			}
		}
	}
	
	public Board(int size) {
		super();
		this.size = size;
		grid = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			grid.add(new ArrayList<>());
			for (int j = 0; j < size; j++) {
				// set some cells to living to make things interesting...
				Cell newCell = new Cell(this, 0, i, j);
				if (newCell.getID() % 3  == 0 || newCell.getID() % 5  == 0) newCell.setCellState(1);
				grid.get(i).add(newCell);
			}
		}
	}

	public ArrayList<ArrayList<Cell>> getGrid() { return grid; }
	
	public int getSize() { return size; }

	public void setGrid(ArrayList<ArrayList<Cell>> grid) { this.grid = grid; }
	
	public void runCellThreads() throws InterruptedException {
		runCellThreads(1);
	}
	
	public void runCellThreads(int iterations) throws InterruptedException {
		final int numPhases = grid.get(0).get(0).getNumPhases();
		for (int i = 0; i < iterations; i++) {
			System.out.printf("\nCell Iteration #%d\n", i+1);
			ArrayList<Cell> cells = new ArrayList<>();
			for (ArrayList<Cell> row : grid) {
				for (Cell cell : row) {
					Thread cellThread = new Thread(cell);
					cellThread.start();
					cells.add(cell);
				}
			}
			for (int phase = 1; phase <= numPhases; phase++) {
				System.out.printf("\nBoard says go for phase %d\n", phase);
				for (Cell cell : cells) cell.goForPhase.setSignal();
				
				for (Cell cell : cells) cell.phaseRunning.waitForSignal();
				System.out.printf("\nBoard thinks all threads are running phase %d\n", phase);
				for (Cell cell : cells) cell.resetRunning();

				for (Cell cell : cells) cell.phaseComplete.waitForSignal();
				System.out.printf("\nBoard thinks all threads have completed phase %d\n", phase);
				for (Cell cell : cells) cell.resetComplete();
			}
			System.out.println("\nBoard fully advanced.");	
		}
	}
}
