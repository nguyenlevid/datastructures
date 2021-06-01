/**
 * Name(s): Viet Nguyen
 * Date: 3 Mar 2021
 * CSC 202
 * Lab03--LifeGrid.java
 * 
 * A class that represents a (finite) board for Conway's Game of Life.
 * Each cell of the board is either dead or alive.
 * 
 */

public class LifeGrid {
	/**
	 * data field
	 */
	//TODO
	private Cell[][] board;
	
	
	/**
	 * A constructor for a new Life game
	 * 
	 * @param numColumns  the number of columns of the board (in squares)
	 * @param numRows the number of rows of the board (in squares)
	 */
	//TODO
	public LifeGrid(int numRows, int numColumns) {
		// instantiate 2D array, all null elements
		this.board = new Cell[numRows][numColumns];
		fillRandom();
	}
	
	/**
	 * fillRandom fills the board with random cells
	 */
	//TODO
	public void fillRandom() {
		// loop to make each cell
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				board[row][col] = new Cell();
			}
		}
	}
	
	
	/**
	 * isCellAlive returns whether or not a cell is alive.
	 * 
	 * @param rowIndex the rowIndex of the cell
	 * @param colIndex the colIndex of the cell
	 * @return whether the cell is alive or not (true/false)
	 */
	//TODO
	public boolean isCellAlive(int rowIndex, int colIndex) {
		return board[rowIndex][colIndex].isAlive();
	}


	/**
	 * nextGeneration updates the board for the next generation
	 */
	public void nextGeneration() {
		//TODO
		Cell[][] nextBoard = new Cell[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				int livingNeighbors = countLivingNeighbors(row, col);
				if (board[row][col].isAlive()) {
					if (livingNeighbors < 2 || livingNeighbors >3) {
						nextBoard[row][col] = new Cell(false);
					}
					else {
						nextBoard[row][col] = new Cell(true);
					}
				}
				else {
					if (livingNeighbors == 3) {
						nextBoard[row][col] = new Cell(true);
					}
					else {
						nextBoard[row][col] = new Cell(false);
					}
				}
			}
		}
		board = nextBoard;
	}



	/**
	 * countLivingNeighbors returns the number of living neighbors next to a cell.
	 * 
	 * @param rowIndex the rowIndex of the cell
	 * @param colIndex the colIndex of the cell
	 * @return the number of living neighbors next to the cell
	 */
	//TODO
	private int countLivingNeighbors(int rowIndex, int colIndex) {
		int count = 0;
		for (int row = rowIndex-1; row <= rowIndex+1; row++) {
			for (int col = colIndex-1; col <= colIndex+1; col++) {
				if (row == rowIndex && col == colIndex) {
					continue;
				}
				if (isCellInBoundsAndAlive(row, col)) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * isCellInBoundsAndAlive - helper - returns true or false
	 * 
	 * @param row
	 * @param col
	 * @return true if both requirements are meet; otherwise, false
	 */
	private boolean isCellInBoundsAndAlive(int row, int col) {
		if (row < 0 || col < 0) {
			return false;
		}
		if (row >= board.length || col >= board[0].length) {
			return false;
		}
		if (!board[row][col].isAlive()) {
			return false;
		}
		return true;
	}
	
	/*
	 * Code to test your implementation of the LifeClass
	 */
	public static void main(String args[]) {
		LifeGrid game = new LifeGrid(4, 5);

		System.out.println("Initial random board");
		for (int rowIndex = 0; rowIndex < game.board.length; rowIndex++) {
			for (int colIndex = 0; colIndex < game.board[rowIndex].length; colIndex++) {
				System.out.printf("%7b", game.board[rowIndex][colIndex].isAlive() );
			}
			System.out.println();
		}

		System.out.println("\n\nNumber of living neighbors for each cell");
		for (int rowIndex = 0; rowIndex < game.board.length; rowIndex++) {
			for (int colIndex = 0; colIndex < game.board[rowIndex].length; colIndex++) {
				System.out.print(game.countLivingNeighbors(rowIndex, colIndex) + " ");
			}
			System.out.println();
		}
		
		game.nextGeneration();
		System.out.println("\n\nboard after nextGeneration is called");
		for (int rowIndex = 0; rowIndex < game.board.length; rowIndex++) {
			for (int colIndex = 0; colIndex < game.board[rowIndex].length; colIndex++) {
				System.out.printf("%7b", game.board[rowIndex][colIndex].isAlive() );
			}
			System.out.println();
		}
	}

}
