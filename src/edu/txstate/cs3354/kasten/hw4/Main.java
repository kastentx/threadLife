package edu.txstate.cs3354.kasten.hw4;

public class Main {
	public static void main (String[] args) {
		// create new board with certain size
		Board myBoard = new Board(10);
		
		// output board to the console
		BoardDisplay.display(myBoard);
	}
}