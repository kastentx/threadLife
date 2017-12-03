package edu.txstate.cs3354.kasten.hw4;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardDisplay {
	public static void display(Board myBoard) {
		ArrayList<ArrayList<Cell>> grid = myBoard.getGrid();
		int size = myBoard.getSize();
		System.out.print("\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				System.out.print(" " + getCellChar(grid.get(i).get(j)) + " ");
			System.out.print("\n");
		}
	}
	
	private static String getCellChar(Cell myCell) {
		if (myCell.getCellState() == 1) return "X";
		else return "O";
	}
	
	public static void GUIdisplay(Board myBoard) {
		JFrame mainFrame = new JFrame();
		int cellBorderOffset = 3;
		int windowMargin = 25;
		int boardSize = myBoard.getSize();
		int windowSize = 550;
		
		mainFrame.setSize(windowSize, windowSize+15);
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				JPanel cell = new JPanel();
				int cellSize = ((windowSize - (2*windowMargin)) 
					/ boardSize);
				int cellState = myBoard.getGrid().get(i).get(j).getCellState();
				if (cellState == 1) cell.setBackground(Color.RED);
				else cell.setBackground(Color.BLUE);
				int oldX = ((cellSize*j) + 20);
				int oldY = ((cellSize*i) + 20);
				cell.setBounds(oldX, oldY, cellSize - cellBorderOffset, cellSize - cellBorderOffset);
				mainFrame.add(cell);
			}
		}
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
	}
}
