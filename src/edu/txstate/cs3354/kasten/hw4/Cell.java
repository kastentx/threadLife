package edu.txstate.cs3354.kasten.hw4;

public class Cell extends Thread {
	private final int NUM_PHASES = 2;
	private boolean state;
	private boolean nextState;

	public Cell(boolean isLiving) {
		super();
		this.state = isLiving;
	}

	public boolean getCellState() {
		return state;
	}
	
	public boolean getNextState() {
		return nextState;
	}
	
	public void setCellState(boolean isLiving) {
		this.state = isLiving;
	}
	
	public void run() {
		for (int i = 1; i <= NUM_PHASES; i++) {
			try { doPhase(i); } 
			catch (InterruptedException exception) { exception.printStackTrace(); }	
		}
	}
	
	public void doPhase(int phaseNum) throws InterruptedException {
		if (phaseNum == 1) setNextState();
		if (phaseNum == 2) advanceState();
	}
	
	private void setNextState() {
		// FOR NOW this flips the current state
		// LATER this will determine if a cell lives or dies based 
		// on its neighbor count
		
		nextState = !getCellState();
	}
	
	private void advanceState() {
		// this should be simple.. just setting the state to the next one
		// LOOKING AHEAD this will occur after all cells have set their
		// next state in phase 1
		setCellState(getNextState());
	}
}
