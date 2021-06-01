import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A static class that provides a List of colors
 * 
 * @author Joel (adapted from Chuck Hommel)
 */
public class Palette {
	public static final int NUMBER_COLORS = 20;
	private static ArrayList<Color> colors = initColors(NUMBER_COLORS);
	private static Random rand = new Random();

	// constants for sizing the palette when displayed
	private static final int BOX_SIZE = 20;
	private static final int SPACING = 5;

	/**
	 * Initialize the ArrayList of Colors
	 */
	private static ArrayList<Color> initColors(int n) {
		// color generation algorithm from
		// http://stackoverflow.com/questions/223971/how-to-generate-spectrum-color-palettes
		ArrayList<Color> temp = new ArrayList<Color>();
		for (int i = 0; i < n; i++) {
			temp.add(Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f));
		}
		return temp;
	}

	/**
	 * Get a randomly selected Color
	 * 
	 * @return the Color selected
	 */
	public static Color getRandomColor() {
		return colors.get(rand.nextInt(colors.size()));
	}

	/**
	 * Get index of this Color in the palette (for ordering)
	 * 
	 * @param col the Color to find
	 * @return the index of this Color in the ArrayList
	 */
	public static int getIndex(Color col) {
		return colors.indexOf(col);
	}

	/**
	 * Shows the colors in ascending order on a frame, for testing & prettiness
	 */
	public static void showPalette() {
		JFrame frame = new JFrame("Color Palette");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel() { // anonymous inner class
			public void paintComponent(Graphics g) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 800, 50);
				for (int i = 0; i < NUMBER_COLORS; i++) {
					g.setColor(colors.get(i));
					g.fillRect(i * (BOX_SIZE + SPACING) + SPACING, 0, BOX_SIZE, BOX_SIZE);
				}
			}
		};
		panel.setPreferredSize(new Dimension(NUMBER_COLORS * (BOX_SIZE + SPACING) + SPACING, BOX_SIZE));
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Get the full spectrum of colors This is used for testing
	 * 
	 * @return array of Colors in the Spectrum in ascending order
	 */
	public static Color[] getPalette() {
		Color[] temp = new Color[colors.size()];
		return colors.toArray(temp);
	}

}
