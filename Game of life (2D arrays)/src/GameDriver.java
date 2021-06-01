/**
 * A GUI to run Conway's Game of Life. It has 
 * a frame to hold and display a LifePanel board and 
 * next, start, stop, random, and quit buttons.
 * 
 * @author Joel Ross, Phil Howard, Dan Drake.
 * Revised by Diane Mueller
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameDriver implements ActionListener {

	public static void main(String[] args) {
		new GameDriver();
	}

	private JFrame frame; // the actual frame we'll be showing
	private LifePanel board; // the life board to draw
	private Timer animator; // a timer to control animation
	private JButton goButton;
	private JButton stepButton;
	private JButton stopButton;
	private JButton quitButton;
	private JButton randomButton;

	// The delay between frames of the animation, in milliseconds. You
	// can change this if you want it to run faster or slower.
	private final static int ANIMATION_DELAY = 150;

	/**
	 * Creates a new GameDriver object.
	 */
	public GameDriver() {
		frame = new JFrame("Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		board = new LifePanel();
		board.setPreferredSize(new Dimension(LifePanel.FRAME_WIDTH, LifePanel.FRAME_HEIGHT));

		JPanel buttonPanel = new JPanel();
		stepButton = new JButton("Next");
		stepButton.addActionListener(this);
		buttonPanel.add(stepButton);
		goButton = new JButton("Start");
		goButton.addActionListener(this);
		buttonPanel.add(goButton);
		stopButton = new JButton("Stop");
		stopButton.addActionListener(this);
		buttonPanel.add(stopButton);
		randomButton = new JButton("Random");
		randomButton.addActionListener(this);
		buttonPanel.add(randomButton);
		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		buttonPanel.add(quitButton);

		frame.setLayout(new BorderLayout());
		frame.add(board, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		animator = new Timer(ANIMATION_DELAY, this); // create the timer, with this object controlling it

		frame.pack(); // make everything the preferred size
		frame.setVisible(true); // show the frame
	}

	/**
	 * Responds to the button
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stepButton) {
			board.nextGeneration();
			board.repaint();
		} else if (e.getSource() == goButton) {
			animator.start();
		} else if (e.getSource() == stopButton) {
			animator.stop();
		} else if (e.getSource() == quitButton) {
			System.exit(0); // hard quit
		} else if (e.getSource() == randomButton) {
			board.fillRandom();
			board.repaint();
		} else if (e.getSource() == animator) {
			board.nextGeneration();
			board.repaint();
		}
	}

	/**
	 * Starts the animation timer
	 */
	public void startAnimation() {
		animator.start();
	}

	/**
	 * Stops the animation timer
	 */
	public void pauseAnimation() {
		animator.stop();
	}
}
