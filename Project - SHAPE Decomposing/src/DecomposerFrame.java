/**
 * A Frame that draws a polygon shape with a slider to determine what percent of
 * the shaper original points will be used in the drawn shape.
 * 
 * Don't change this code
 * 
 * @author Diane Mueller
 * 
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.*;
import java.io.*;

public class DecomposerFrame extends JFrame {
	
	public static void main(String[] args) throws FileNotFoundException {
		String currentDir = System.getProperty("user.dir") + File.separator + RESOURCE_FOLDER;
		if (!(new File(currentDir).exists())) {
			currentDir = System.getProperty("user.dir");
		}
		JFileChooser fileChooser = new JFileChooser(currentDir);
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		int returnValue = fileChooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			Scanner input = new Scanner(selectedFile);
			DecomposableShape shape = new DecomposableShape(input);
			new DecomposerFrame(shape);
		}

	}
	
	private static final String RESOURCE_FOLDER = "res";

	private static final int PANEL_HEIGHT = 800; // the height of the frame
	private static final int PANEL_WIDTH = 800; // the width of the frame;
	private static final int LOW_PERCENT = 5;
	private static final int HIGH_PERCENT = 100;
	private static final int INITIAL_PERCENT = 100;
	private static final int TICK_SPACING = 5;

	private ShapePanel panel; // the panel we'll draw the necklace on
	private JSlider slider;   // slider to adjust the % of the initial points to be drawn
	private DecomposableShape shape;

	public DecomposerFrame(DecomposableShape shape) {
		this.shape = shape;
		
		slider = makeSlider();
		
		panel = new ShapePanel();
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		panel.setBackground(Color.WHITE);
		
		Container pane = this.getContentPane();
		pane.add(slider, BorderLayout.WEST);
		pane.add(panel, BorderLayout.CENTER);

		this.setTitle("Shape Decomposer");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setVisible(true);
	}
	/**
	 * Creates the slider to set the percent of points to use
	 * and sets up its listener.
	 * @return the slider to set the percent of points to use
	 */
	private JSlider makeSlider() {
		JSlider slider = new JSlider(SwingConstants.VERTICAL, LOW_PERCENT, HIGH_PERCENT, INITIAL_PERCENT);
		slider.setMajorTickSpacing(TICK_SPACING);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider slider = (JSlider) event.getSource();
				if (!slider.getValueIsAdjusting()) {
					int value = slider.getValue();
					shape.setToSize(value);
					refreshShape();
				}
			}
		});
		
		return slider;
	}

	/**
	 * A private inner class that contains the panel that we're drawing on. We've
	 * overwritten paintComponent to specify our own painting.
	 */
	private class ShapePanel extends JPanel {
		public void paintComponent(Graphics g) {
			// this will "white out" the background of the Panel
			g.setColor(Color.WHITE);
			g.clearRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT); // erase what has come before

			g.setColor(Color.BLACK);
			g.drawPolygon(shape.toPolygon());
		}
	}

	/**
	 * A method that "redraws" the frame to update the shown necklace.
	 */
	public void refreshShape() {
		panel.repaint();
	}
}
