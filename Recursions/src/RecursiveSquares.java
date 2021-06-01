/**
 * Name(s): Viet Nguyen
 * CSC 202
 * Lab10-RecursiveSquares.java
 * 
 * Program to draw pattern of recursive square. The use enters the order of the 
 * recursive squares to be drawn. The pattern is drawn centered in a square
 * window.
 */
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JOptionPane;

public class RecursiveSquares {
	public static final int SIZE = 600;
	public static final int MAX_LEVEL = 6;

	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(SIZE, SIZE);
		Graphics g = panel.getGraphics();

		int halfSize = SIZE / 2;
		int x = halfSize;
		int y = halfSize;
		int length = halfSize;

		int order = 0;
		do {
			try {
				String reply = JOptionPane.showInputDialog("Enter order of Recursive Squares");
				order = Integer.parseInt(reply);
				if (order < 1 || order > MAX_LEVEL) {
					JOptionPane.showMessageDialog(null,
							"The order must be at least 1 " + "and less than or equal to " + MAX_LEVEL);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter an integer!");
			}
		} while (order < 1 || order > MAX_LEVEL);

		drawRecursiveSquares(order, g, x, y, length);
	}

	/**
	 * Draws recursive squares centered at (centerX, centerY) with sides of the given length
	 * 
	 * @param order    - order of the recursive squares
	 * @param g        - facilitates drawing in the DrawingPanel
	 * @param centerX  - the x-coordinate of the center of the recursive squares pattern
	 * @param centerY  - the y-coordinate of the center of the recursive squares pattern
	 * @param sideLength - length of the side of the square
	 * 
	 */
	public static void drawRecursiveSquares(int order, Graphics g, int centerX, int centerY, int sideLength) {
		if (order == 1) {
			drawSquare(g, centerX, centerY, sideLength);
			return;
		}
		drawRecursiveSquares(order-1, g, centerX-sideLength/2, centerY-sideLength/2, sideLength/2);
		drawRecursiveSquares(order-1, g, centerX+sideLength/2, centerY-sideLength/2, sideLength/2);	
		drawSquare(g, centerX, centerY, sideLength);
		drawRecursiveSquares(order-1, g, centerX-sideLength/2, centerY+sideLength/2, sideLength/2);
		drawRecursiveSquares(order-1, g, centerX+sideLength/2, centerY+sideLength/2, sideLength/2);	
	}
	

	/**
	 * Draws one light gray filled square with a black outline
	 * @param g        - facilitates drawing in the DrawingPanel
	 * @param centerX  - the x-coordinate of the center of the square
	 * @param centerY  - the y-coordinate of the center of the square
	 * @param sideLength - length of the side of the square
	 */
	public static void drawSquare(Graphics g, int centerX, int centerY, int sideLength) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(centerX - sideLength/2, centerY - sideLength/2, sideLength, sideLength);
		g.setColor(Color.black);
		g.drawRect(centerX - sideLength/2, centerY - sideLength/2, sideLength, sideLength);
	}

	/**
	 * A "pause" function for use in slowing down drawing (so that you can see the
	 * growth). This method shows the current drawing after the pause ends.
	 * 
	 * @param milliseconds The number of milliseconds to wait
	 */
	public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// ignoring exception at the moment
		}
	}
}
