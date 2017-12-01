package edu.txstate.cs3354.kasten.hw4;

import java.util.ArrayList;

public class BoardDisplay {
	public static void display(Board myBoard) {
		ArrayList<ArrayList<Cell>> grid = myBoard.getGrid();
		int size = myBoard.getSize();
		System.out.print("\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + getCellChar(grid.get(i).get(j)) + " ");
			}
			System.out.print("\n");
		}
	}
	
	private static String getCellChar(Cell myCell) {
		if (myCell.getCellState() == 1) return "X";
		else return "O";
	}
}
