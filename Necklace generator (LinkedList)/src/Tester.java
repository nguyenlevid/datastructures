import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 * A tester for the Necklace class
 * 
 * @author Joel Ross (adapted from Chuck Hommel)
 * @author revised by Diane Mueller
 * @version Sp21
 */
public class Tester {
	Necklace necklace;
	NecklaceFrame frame;

	public static void main(String[] args) {
		Tester test = new Tester();
		String[] opts = { " Create new necklace ", " Remove from front ", " Random remove ", " Show Spectrum ",
				" Quit " };
		int messageType = JOptionPane.QUESTION_MESSAGE; // type of dialog box
		int choice; // user choice
		do // keep asking until user wants to quit
		{
			choice = JOptionPane.showOptionDialog(null, "Choose", "CSC 202 Necklace tester", 0, messageType, null, opts,
					opts[0]);
			switch (choice) {
			case 0:
				test.makeNewNecklace();
				break;
			case 1:
				test.removeFromFront();
				break;
			case 2:
				test.randomRemove();
				break;
			case 3:
				test.showPalette();
				break;
			}
		} while (choice != 4);
	}

	/**
	 * Constructor for objects of class Tester
	 */
	public Tester() {
	}

	/**
	 * run test to add Beads to a necklace
	 */
	public void makeNewNecklace() {
		// test 1: add beads to necklace and display
		ArrayList<Bead> theBeads = new ArrayList<Bead>();
		for (int i = 0; i < 20; i++) {
			Bead bead = new Bead();
			theBeads.add(bead);
		}
		necklace = new Necklace();
		if (frame != null) {
			frame.setVisible(false);
			frame.dispose();
		}
		frame = new NecklaceFrame(necklace);
		System.out.println("Running tests on " + theBeads.size() + " Beads.");
		System.out.println("*********************************\n");
		System.out.println("Test: Adding Beads...");
		System.out.println("*********************************\n");
		for (Bead bead : theBeads) {
			System.out.println("Adding a Bead with color " + bead.color);
			necklace.addBead(bead);
			frame.refreshNecklace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		System.out.print("Expected number of Beads in necklace: " + theBeads.size() + " ...");

		if (necklace.numBeads() == theBeads.size()) {
			System.out.println("Pass.");
		} else {
			System.out.println("Necklace reports " + necklace.numBeads() + ". Fail.");
		}
	}

	/**
	 * Create a necklace and remove beads from front until empty.
	 */
	public void removeFromFront() {
		// test 2: remove some beads, displaying
		necklace = new Necklace();
		if (frame != null) {
			frame.setVisible(false);
			frame.dispose();
		}
		frame = new NecklaceFrame(necklace);
		ArrayList<Bead> theBeads = new ArrayList<Bead>();
		for (int i = 0; i < 20; i++) {
			Bead bead = new Bead();
			theBeads.add(bead);
		}
		System.out.println("*********************************\n");
		System.out.println("Test: Removing Beads...");
		System.out.println("*********************************\n");
		for (Bead bead : theBeads) // make a necklace
		{
			necklace.addBead(bead);
		}

		frame.refreshNecklace();
		System.out.println("Testing removing all Beads from front.");
		Color[] testColors = Palette.getPalette();
		int numRemoved = 0;
		int expectedCount = theBeads.size();
		for (Color color : testColors) {
			int count = 0;
			System.out.println("Removing Beads of color : " + color + " ...");
			Bead bead = necklace.removeBead(color);
			while (bead != null) {
				if (bead.color.getRGB() != color.getRGB()) {
					System.out.println("Incorrect color Bead returned.");
				} else {
					System.out.println("Correct Bead returned.");
					count++;
				}
				frame.refreshNecklace();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				bead = necklace.removeBead(color);
			}
			System.out.println("Done. " + count + " Beads removed.");
			numRemoved += count;
		}
		System.out.println("Expected to remove " + expectedCount + " Beads.");
		System.out.print("Actually removed " + numRemoved + " Beads...");
		if (expectedCount == numRemoved) {
			System.out.println("Pass. ");
		} else {
			System.out.println("Fail. ");
		}
	}

	/**
	 * Randomly remove Beads from necklace
	 */
	private void randomRemove() {
		// test 3. Remove beads randomly by selecting colors to remove
		necklace = new Necklace();
		if (frame != null) {
			frame.setVisible(false);
			frame.dispose();
		}
		frame = new NecklaceFrame(necklace);
		ArrayList<Bead> theBeads = new ArrayList<Bead>();
		for (int i = 0; i < 20; i++) {
			Bead b = new Bead();
			theBeads.add(b);
		}
		System.out.println("*********************************\n");
		System.out.println("Test: Randomly Removing Beads...");
		System.out.println("*********************************\n");
		for (Bead b : theBeads) // make a necklace
		{
			necklace.addBead(b);
		}

		frame.refreshNecklace();
		System.out.println("Testing removing Beads randomly.");
		Color[] testColors = Palette.getPalette();
		int numRemoved = 0;
		int startingCount = necklace.numBeads();
		Random randGen = new Random(); // generate random indices to testColors
		for (int i = 0; i < 20; i++) // run 20 tests)
		{
			int count = 0;
			Color color = testColors[randGen.nextInt(testColors.length)];
			System.out.println("Removing Bead of color : " + color + " ...");
			Bead bead = necklace.removeBead(color);
			if (bead != null) {
				if (bead.color.getRGB() != color.getRGB()) {
					System.out.println("Incorrect color Bead returned.");
				} else {
					System.out.println("Correct Bead returned.");
					count++;
				}
			} else {
				System.out.println("No Bead returned.");
			}
			frame.refreshNecklace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println("Done. " + count + " Beads removed.");
			numRemoved += count;
		}

		System.out.println("Initial necklace size: " + startingCount);
		System.out.println("Final necklace size:  " + necklace.numBeads());
		System.out.print("Actually removed " + numRemoved + " Beads...");

		if (numRemoved + necklace.numBeads() == startingCount) {
			System.out.println("Pass. ");
		} else {
			System.out.println("Fail. ");
		}

	}

	/**
	 * Show the palette for testing
	 * 
	 */
	private void showPalette() {
		Palette.showPalette();
	}
}
