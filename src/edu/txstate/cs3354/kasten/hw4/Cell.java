package edu.txstate.cs3354.kasten.hw4;

import java.util.ArrayList;

public class Cell implements Runnable {
	private static int counter = 0;
	private final int NUM_PHASES = 2;
	private int id;
	private int row;
	private int col;
	private int state;
	private int nextState;
	private Board board;
	
	public Signal phaseRunning = new Signal(); 
	public Signal phaseComplete = new Signal(); 
	public Signal goForPhase = new Signal();

	public Cell(Board board, int isLiving, int row, int col) {
		super();
		this.board = board;
		this.state = isLiving;
		this.id = counter++;
		this.row = row;
		this.col = col;
	}
	
	public void resetRunning() { phaseRunning = new Signal(); }
	
	public void resetComplete() { phaseComplete = new Signal(); }
	
	public int getNumPhases() { return NUM_PHASES; }
	
	public int getID() { return id; }
	
	public int getCellState() { return state; }
	
	public void setCellState(int newState) { this.state = newState; }
		
	private void setNextState() {
		int score = getNeighborScore();
		if (state == 1 && (score < 2 || score > 3)) nextState = 0;
		if (state == 0 && score == 3) nextState = 1;
	}
	
	private void advanceState() { setCellState(nextState); }
	
	public void run() {
		for (int i = 1; i <= NUM_PHASES; i++)
			try { 
				goForPhase.waitForSignal(); goForPhase = new Signal();
				phaseRunning.setSignal(); 
				doPhase(i); 
				phaseComplete.setSignal(); 
			} catch (InterruptedException exception) { exception.printStackTrace(); }	
	}
	
	public void doPhase(int phaseNum) throws InterruptedException {	
		if (phaseNum == 1) setNextState();
		if (phaseNum == 2) advanceState();
	}
	
	public int getNeighborScore() {
		  int score = 0;
		  int lastPos = board.getSize()-1;
		  ArrayList<ArrayList<Cell>> grid = board.getGrid();

		  // check for living neighbors
		  if (row > 0) 							// top
			  score += grid.get(row-1).get(col).getCellState();
		  if (row < lastPos) 						// bottom
			  score += grid.get(row+1).get(col).getCellState();
		  if (col > 0) 							// left
			  score += grid.get(row).get(col-1).getCellState();
		  if (col < lastPos) 			 			// right
			  score += grid.get(row).get(col+1).getCellState();
		  if (col > 0 && row > 0)					// upper-left
			  score += grid.get(row-1).get(col-1).getCellState();
		  if (col > 0 && row < lastPos)		 			// lower-left
			  score += grid.get(row+1).get(col-1).getCellState();
		  if (col < lastPos && row > 0)		 			// upper-right
			  score += grid.get(row-1).get(col+1).getCellState();
		  if (col < lastPos && row < lastPos) 				// lower-right
			  score += grid.get(row+1).get(col+1).getCellState();
		
		  return score;
	}
}
