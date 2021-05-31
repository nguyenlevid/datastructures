
/**
 * Do not modify this code!
 */

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * This program runs the star chart simulator. It reads the input data from the
 * star and constellation files that will be passed in to your StarChart object.
 */
public class StarDisplay {
	// constants for important file names and settings;
	// these can be changed to alter the program's default behavior
	private static final String STARS_FILENAME = "stars.txt";
	private static final int DEFAULT_SIZE = 500;
	private static final boolean ALWAYS_ON_TOP = true;

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Star Display");
		System.out.println("============");
		System.out.println("This program displays stars in the sky and");
		System.out.println("constellations based on an input data file.");
		System.out.println();

		Scanner console = new Scanner(System.in);
		DrawingPanel panel = createWindow(console);
		StarChart chart = new StarChart(panel.getWidth(), panel.getHeight());
		readStarsFile(STARS_FILENAME, chart);

		// draw the stars on a DrawingPanel on screen
		chart.draw(panel.getGraphics(), false);

		// text menu for user commands
		menu(console, panel, chart);

		System.out.println("Exiting.");
		panel.setVisible(false);
		System.exit(0);
	}

	/**
	 * Displays a menu of text commands and executes the command
	 * 
	 * @param console - facilitates console input
	 * @param panel   - window to draw the stars and constellations
	 * @param chart   - information about the stars
	 * @throws FileNotFoundException when constellation file cannot be found
	 */
	public static void menu(Scanner console, DrawingPanel panel, StarChart chart) throws FileNotFoundException {
		boolean starNamesEnabled = false;
		Graphics g = panel.getGraphics();
		String command = "";
		while (!command.equals("Q")) {
			System.out.print("C)onstellation, D)istance, N)ames, S)upernova, Q)uit? ");
			command = console.nextLine().trim().toUpperCase();
			if (command.equals("C")) {
				System.out.print("Constellation filename to add? ");
				String fileName = console.nextLine().trim();
				readConstellationFile(fileName, chart);
			} else if (command.equals("N")) {
				starNamesEnabled = !starNamesEnabled;
				System.out.println("Star names are now " + (starNamesEnabled ? "enabled." : "disabled."));
			} else if (command.equals("D")) {
				findDistanceBetweenStars(console, chart);
			} else if (command.equals("S")) {
				goSupernova(console, chart);
			}
			System.out.println();
			panel.clear();
			chart.draw(g, starNamesEnabled);
		}
	}
	
	// Prompts user for two stars and, if found, outputs the distance between them
	private static void findDistanceBetweenStars(Scanner console, StarChart chart) {
		System.out.print("Star name for star #1? ");
		Star star1 = promptForStar(console, chart);
		System.out.print("Star name for star #2? ");
		Star star2 = promptForStar(console, chart);
		if (star1 != null && star2 != null) {
			double distance = star1.distance(star2);
			System.out.printf("Distance from %s to %s is roughly %.4f\n", star1, star2, distance);
		}
	}
	
	// Prompts user to edner a star and, if found, outputs the number of stars destroyed
	private static void goSupernova(Scanner console, StarChart chart) {
		System.out.print("Star name to go supernova? ");
		Star novaStar = promptForStar(console, chart);
		if (novaStar != null) {
			int destroyed = chart.supernova(novaStar);
			System.out.println(destroyed + " star(s) were destroyed!");
		}
	}

	/**
	 * Creates and returns a new drawing panel based on the user's preferred size.
	 * 
	 * @param console - facilitates input from console window
	 * @return a window in which to draw the stars and constellations
	 */
	public static DrawingPanel createWindow(Scanner console) {
		System.out.print("Window width and height (Enter for default " + DEFAULT_SIZE + " x " + DEFAULT_SIZE + ")? ");
		String line = console.nextLine();
		int width = DEFAULT_SIZE;
		int height = DEFAULT_SIZE;
		Scanner tokens = new Scanner(line);
		if (tokens.hasNextInt()) {
			width = tokens.nextInt();
		}
		// allow skipping a comma or x between w and h
		while (tokens.hasNext() && !tokens.hasNextInt()) {
			tokens.next();
		}
		if (tokens.hasNextInt()) {
			height = tokens.nextInt();
		}
		tokens.close();
		DrawingPanel panel = new DrawingPanel(width, height);
		panel.setBackground(Color.BLACK);
		panel.setAlwaysOnTop(ALWAYS_ON_TOP);
		return panel;
	}

	/**
	 * Prompts the user to type the name of a star and returns it
	 * 
	 * @param console - facilitates input from the console window
	 * @param chart   - information about the stars
	 * @return the star name entered by the user
	 */
	public static Star promptForStar(Scanner console, StarChart chart) {
		String starName = console.nextLine().trim();
		Star star = chart.getStar(starName);

		if (star == null) {
			System.out.println("Star \"" + starName + "\" not found.");
		}
		return star;
	}

	/**
	 * Read star input data from the given file and add it to the star chart
	 * 
	 * @param fileName - name of the star input data file
	 * @param chart    - information about all of the stars
	 * @throws FileNotFoundException if the file with star data isn't found
	 */
	public static void readStarsFile(String fileName, StarChart chart) throws FileNotFoundException {
		System.out.println("Reading stars input data ...");
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("File \"" + fileName + "\" not found.");
		} else {
			// Each line contains the x/y/z, an ID, a magnitude,
			// another ID, and possibly a star name at the end. Examples:
			// 0.783 0.312 -0.537 887 6.57 423
			// 0.010 0.007 0.999 889 1.97 424 Polaris
			Scanner input = new Scanner(new File(fileName));
			while (input.hasNextLine()) {
				Scanner tokens = new Scanner(input.nextLine());
				double x = tokens.nextDouble();
				double y = tokens.nextDouble();
				double z = tokens.nextDouble();
				tokens.nextInt(); // Draper id; ignored
				double magnitude = tokens.nextDouble();
				tokens.nextInt(); // Harvard revised id; ignored
				String name = tokens.hasNext() ? tokens.nextLine().trim() : null;
				Star star = new Star(x, y, z, magnitude);
				chart.addStar(star, name);
			}
		}
	}

	/**
	 * Reads constellation data from the given file and adds it to the star chart.
	 */
	/**
	 * Reads constellation data from the given file and adds it to the star chart
	 * 
	 * @param fileName - file name with constellation data
	 * @param chart    - information about all of the stars
	 * @throws FileNotFoundException when the file with constellation data can't be
	 *                               found
	 */
	public static void readConstellationFile(String fileName, StarChart chart) throws FileNotFoundException {
		if (!fileName.endsWith(".txt") && !fileName.contains(".")) {
			fileName += ".txt";
		}
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("File \"" + fileName + "\" not found.");
		} else {

			// Each line of the input is two stars, such as "Sadr,Cyg Epsilon"
			Scanner input = new Scanner(new File(fileName));
			String constellationName = input.nextLine();
			List<String> starNameList = new ArrayList<String>();
			while (input.hasNextLine()) {
				String[] tokens = input.nextLine().split(",");
				if (tokens.length >= 2) {
					starNameList.add(tokens[0]);
					starNameList.add(tokens[1]);
				}
			}
			String[] starNames = starNameList.toArray(new String[0]);
			chart.addConstellation(constellationName, starNames);
		}
	}
}
