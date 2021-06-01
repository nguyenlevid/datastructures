/**
 * A class is the graphical display of a (finite) board for Conway's Game of Life.
 * 
 * @author Phil Howard
 * Revised by Diane Mueller
 */
import javax.swing.*;
import java.awt.*;

public class LifePanel extends JPanel {

	// The size of one cell one the screen, in pixels. You can make this
	// smaller if you make a big board.
	private final static int CELL_SIZE = 20;
	private final static int BOARD_WIDTH = 40;
	private final static int BOARD_HEIGHT = 25;

	public final static int FRAME_WIDTH = BOARD_WIDTH * CELL_SIZE;
	public final static int FRAME_HEIGHT = BOARD_HEIGHT * CELL_SIZE;

	private LifeGrid board;

	/**
	 * A constructor for a new Life game
	 */
	public LifePanel() {
		board = new LifeGrid(BOARD_HEIGHT, BOARD_WIDTH);
	}

	/**
	 * Updates the board for the next generation
	 */
	public void nextGeneration() {
		board.nextGeneration();
	}

	/**
	 * Fills the board with random cells
	 */
	public void fillRandom() {
		board.fillRandom();
	}

	/**
	 * Draws the board
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		for (int rowIndex = 0; rowIndex < BOARD_HEIGHT; rowIndex++) {
			for (int colIndex = 0; colIndex < BOARD_WIDTH; colIndex++) {
				if (board.isCellAlive(rowIndex, colIndex)) {
					g.setColor(Color.BLACK);
					g.fillRect(colIndex * CELL_SIZE, rowIndex * CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
	}

}
