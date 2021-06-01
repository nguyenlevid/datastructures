/**
 * Name(s): Viet Nguyen
 * Date: 3 Mar 2021
 * CSC202
 * Lab03--Cell.java
 * 
 * Cell class represents one cell on the board of Conway's Game of Life.
 * A cell may either be dead or alive.
 */
import java.util.Random;

public class Cell {
	public static Random randGenerator = new Random();
	/**
	 * data field
	 */
	//TODO
	private boolean status;
	
	
	/**
	 * Constructs a Cell set to alive or dead
	 * @param status-whether the cell is alive or dead
	 */
	//TODO
	public Cell(boolean status) {
		this.status = status;
	}
	
	/**
	 * Constructs a Cell to alive or deal with a 50-50 chance.
	 * 
	 */
	//TODO
	public Cell() {
		this.status = randGenerator.nextBoolean();
	}	
	
	/**
	 * isAlive returns true if the cell is alive; false if dead
	 * @return true is the cell is alive; false if dead
	 */
	//TODO
	public boolean isAlive() {
		return status;
	}
	
	/**
	 *  Test code for Cell class implementation
	 */
//	public static void main(String[] args) {
//		Cell cell1 = new Cell(true);
//		System.out.println("cell1 should be true: " + cell1.isAlive());
//		Cell cell2 = new Cell(false);
//		System.out.println("cell2 should be false: " + cell2.isAlive());
//		
//		System.out.println("Create 25 random cells and print status");
//		for (int i = 0; i < 25; i++) {
//			Cell cell = new Cell();
//			System.out.println(cell.isAlive());
//		}
//				
//	}
}
