package edu.txstate.cs3354.kasten.hw4;

public class Main {
	public static void main (String[] args) {
		// System.out.println("hello homework.");
		Board myBoard = new Board(20);
		
		// output board to the console
		BoardDisplay.display(myBoard);
	}
}