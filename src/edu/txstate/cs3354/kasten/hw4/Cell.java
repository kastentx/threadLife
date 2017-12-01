package edu.txstate.cs3354.kasten.hw4;

public class Cell extends Thread {
	private static int counter = 0;
	private final int NUM_PHASES = 2;
	private int id;
	private boolean state;
	private boolean nextState;
	
	public Signal phaseRunning = new Signal(); 
	public Signal phaseComplete = new Signal(); 
	public Signal goForPhase = new Signal();

	public Cell(boolean isLiving) {
		super();
		this.state = isLiving;
		this.id = counter++;
	}
	
	public void resetRunning() { phaseRunning = new Signal(); }
	public void resetComplete() { phaseComplete = new Signal(); }
	
	public int getNumPhases() { 
		return NUM_PHASES; 
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
			try { 
				goForPhase.waitForSignal(); goForPhase = new Signal();
				
				phaseRunning.setSignal(); System.out.printf("Thread %d beginning phase %d\n", id, i);
				doPhase(i); 
				phaseComplete.setSignal(); System.out.printf("Thread %d completed phase %d\n", id, i);	
			} catch (InterruptedException exception) { exception.printStackTrace(); }	
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
