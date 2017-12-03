package edu.txstate.cs3354.kasten.hw4;

public class Main {
	public static void main (String[] args) throws InterruptedException {
		// load from a file
		//System.out.println(BoardReader.read());
		//Board myBoard = new Board(BoardReader.read());
		Board myBoard = new Board(20);
		BoardDisplay.display(myBoard);
		//BoardDisplay.GUIdisplay(myBoard);
		
		//create new board with certain size
		//Board myBoard = new Board(15);
		
		// output board to the console
		//BoardDisplay.display(myBoard);
		
		// run cell threads to advance board to next generation
		// to advance multiple generations... put this in a loop
		myBoard.runCellThreads(5);
		
		//BoardDisplay.display(myBoard);
		
		//myBoard.runCellThreads();
		
		// output board to the console
		//BoardDisplay.display(myBoard);
		
		// show GUI display instead
		BoardDisplay.GUIdisplay(myBoard);
	}
}