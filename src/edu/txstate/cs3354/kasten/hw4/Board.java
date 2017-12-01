package edu.txstate.cs3354.kasten.hw4;

import java.util.ArrayList;

public class Board {
	private ArrayList<ArrayList<Cell>> grid;
	private int size;
	
	// there will be a phase that determines the next state and it will be saved to this
	// before getting switched over?
	// private ArrayList<ArrayList<Cell>> nextState;
	
	public Board(int size) {
		super();
		this.size = size;
		grid = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			grid.add(new ArrayList<>());
			for (int j = 0; j < size; j++) {
				grid.get(i).add(new Cell(this, 0, i, j));
			}
		}
	}

	public ArrayList<ArrayList<Cell>> getGrid() {
		return grid;
	}
	
	public int getSize() {
		return size;
	}

	public void setGrid(ArrayList<ArrayList<Cell>> grid) {
		this.grid = grid;
	}
	
	public void runCellThreads() throws InterruptedException {
		final int numPhases = grid.get(0).get(0).getNumPhases();
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
			System.out.println("'running' signals reset");

			for (Cell cell : cells) cell.phaseComplete.waitForSignal();
			System.out.printf("\nBoard thinks all threads have completed phase %d\n", phase);
			
			for (Cell cell : cells) cell.resetComplete();
			System.out.println("'complete' signals reset");
		}
		System.out.println("\nBoard fully advanced.");
		
	}
}
