import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;

/**
 * Program to illustrate counting cells in a blob
 * 
 * @author Diane Mueller modified from Koffman and Wolfgang
 */
public class BlobTest extends JFrame {

	// data field
	/** a 2-D grid of buttons */
	private TwoDimGrid theGrid;

	public static void main(String[] args) {
		int fileReply = JOptionPane.showConfirmDialog(null, "Input from a file?");
		try {
			if (fileReply != JOptionPane.YES_OPTION) {
				// no file name given
				String reply = JOptionPane.showInputDialog("Enter number of rows");
				int nRows = Integer.parseInt(reply);
				reply = JOptionPane.showInputDialog("Enter number of columns");
				int nCols = Integer.parseInt(reply);
				TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
				new BlobTest(aGrid);
			} else {
				String fileName = JOptionPane.showInputDialog("Enter the file name:");

				// Create array bitMap from a data file
				BufferedReader br = new BufferedReader(new FileReader(fileName));

				// Read each data line (a string) into
				// gridArrayList. Each element is a char array.
				ArrayList<char[]> gridArrayList = new ArrayList<char[]>();
				String line;
				while ((line = br.readLine()) != null) {
					char[] row = line.toCharArray();
					gridArrayList.add(row);
				}
				br.close();

				// bitMap is a 2-D array based on data in gridArrayList
				char[][] bitMap = gridArrayList.toArray(new char[gridArrayList.size()][]);
				int nRows = bitMap.length;
				int nCols = bitMap[0].length;

				// create a new TwoDimGrid and recolor it based on bitMap
				TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
				aGrid.recolor(bitMap, GridColors.NON_BACKGROUND);
				new BlobTest(aGrid);
			}
		} catch (Exception ex) {
			System.err.println("Exception " + ex);
			ex.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Builds the GUI
	 * 
	 * @param aGrid - The TwoDimGrid that contains the blob
	 */
	private BlobTest(TwoDimGrid aGrid) {
		theGrid = aGrid;
		getContentPane().add(aGrid, BorderLayout.CENTER);
		Blob aBlob = new Blob(aGrid);
		JTextArea instruct = new JTextArea(2, 10);
		instruct.setText("Toggle a button to change its color" + "\nPress SOLVE when ready");
		getContentPane().add(instruct, BorderLayout.NORTH);
		JPanel bottomPanel = new JPanel();
		JButton solveButton = new JButton("SOLVE");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solve();
			}
		});
		JButton resetButton = new JButton("RESET");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				(new Blob(theGrid)).restore();
			}
		});
		bottomPanel.add(solveButton);
		bottomPanel.add(resetButton);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * Respond to pressing SOLVE button
	 */
	public void solve() {
		boolean valid = false;
		int x = 0;
		int y = 0;
		do {
			try {
				String reply = JOptionPane.showInputDialog("Enter blob cell as x, y");
				String[] data = reply.split(",");
				x = Integer.parseInt(data[0].trim());
				y = Integer.parseInt(data[1].trim());
				if (x >=0 && x < theGrid.getNCols() && y >=0 && y < theGrid.getNRows() && theGrid.getColor(x, y) == GridColors.ABNORMAL) {
					valid = true;					
				} else {
					throw new Exception();
				}
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Input invalid.");
			}

		} while (!valid);
		Blob aBlob = new Blob(theGrid);
		JOptionPane.showMessageDialog(null, "For blob at (" + x + "," + y + ") " + "\ncount is "
				+ aBlob.countCells(x, y) + "\nReset blob and try again");
	}

}
