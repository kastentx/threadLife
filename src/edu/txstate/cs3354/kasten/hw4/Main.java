package edu.txstate.cs3354.kasten.hw4;

public class Main {
	public static void main (String[] args) throws InterruptedException {
		// create new board with certain size
		Board myBoard = new Board(10);
		
		// output board to the console
		BoardDisplay.display(myBoard);
		
		// run cell threads to advance board to next generation
		// to advance multiple generations... put this in a loop
		myBoard.runCellThreads();
		
		BoardDisplay.display(myBoard);
		
		myBoard.runCellThreads();
		
		// output board to the console
		BoardDisplay.display(myBoard);
	}
}