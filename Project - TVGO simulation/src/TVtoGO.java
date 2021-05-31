/** TVtoGO - a simple graphical shell for 'downloading' movies and tv-episodes.
 * A scrollable list of available movies and tv-episodes is provided. The
 * list is searchable by a phrase in the movie or tv-episode title. The top
 * ranked video item is given and users are able to rank a video item.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TVtoGO implements ActionListener, ListSelectionListener {

	public static void main(String[] args) throws FileNotFoundException {
		// call the constructor - runs the application
		TVtoGO gui = new TVtoGO();
	}

	// constants
	private static final int FRAME_WIDTH = 760;
	private static final int FRAME_HEIGHT = 320;
	private static final int LOW_RANK = 0;
	private static final int HIGH_RANK = 10;
	private static String FILE_NAME = "contentA.txt";

	// frame to hold GUI
	private JFrame frame;

	// variable holding a Content object
	private Content videoContent;

	// variables for search field and area for displaying details
	private JTextField search;
	private JTextArea detail;

	// a scrollable list to display a set of VideoItems in the Content object
	private JList<VideoItem> listItems;

	// buttons
	private JButton buttonDownload;
	private JButton buttonSearch;
	private JButton exit;
	private JButton buttonRank;
	private JLabel labelTopRanked;
	private JLabel labelMostDownloads;
	

	/** Constructor */
	public TVtoGO() throws FileNotFoundException {
		// initialize instance variables
		search = new JTextField("", 20);
		detail = new JTextArea(20, 5);
		buttonDownload = new JButton("Download");
		buttonSearch = new JButton("Search");
		exit = new JButton("Exit");
		buttonRank = new JButton("Rank");

		// Create a Content object
		Scanner input = new Scanner(new File(FILE_NAME));
		ArrayList<VideoItem> contentList = readFile(input);
		videoContent = new Content("CSC202 Sample Library", contentList);

		// set a few properties of the window
		frame = new JFrame("TV to GO");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(10, 10));

		JLabel label = new JLabel("    Movies and TV-series episodes");
		labelTopRanked = new JLabel("#1 Ranked: " + videoContent.topRanked().getFullTitle());
		labelMostDownloads = new JLabel("#1 Downloads: " + videoContent.mostDownloads().getFullTitle());
		label.setFont(new Font("Times", Font.BOLD, 16));
		frame.add(label, BorderLayout.NORTH);

		// create a JPanel to put in the center, add subpanels
		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 5));
		centerPanel.add(constructLeftPanel());
		centerPanel.add(constructRightPanel());

		// create a JPanel to put in the south, add exit button
		JPanel southPanel = new JPanel();
		southPanel.add(exit);

		// Add the centerPanel and southPanel to the window
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.SOUTH);

		// set the selection in the list to the first item
		listItems.setSelectedIndex(0);

		// Register to listen to button events
		registerListeners();

		frame.setVisible(true);
	}

	/**
	 * Constructs the left-side panel with search field and button, the list of
	 * items for selection and the display of the top rated item
	 * 
	 * @return the left-side panel
	 */
	public JPanel constructLeftPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		// construct panel for search
		JPanel north = new JPanel(new FlowLayout());
		north.add(search);
		north.add(buttonSearch);
		
		// construct panel for top ranked
		JPanel south = new JPanel(new GridLayout(2, 1));
		south.add(labelTopRanked);
		south.add(labelMostDownloads);

		// create the search text field
		search.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		search.setFont(new Font("Times", Font.ITALIC, 13));

		// create a list, fill it with array of items from videoContent
		ArrayList<VideoItem> myList = videoContent.getItems();
		VideoItem[] myArray = new VideoItem[myList.size()];
		for (int i = 0; i < myArray.length; i++) {
			myArray[i] = myList.get(i);
		}
		listItems = new JList<VideoItem>(myArray);
		listItems.addListSelectionListener(this);
		// create a scroll pane for the list
		JScrollPane scrollPane = new JScrollPane(listItems);

		scrollPane.setSize(400, 250);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		// finally, add the components to the panel

		panel.add(north, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(south, BorderLayout.SOUTH);

		// .. and return the panel
		return panel;
	}

	/**
	 * Construct the right-side panel displaying the details of the selected
	 * item, the download button, and the rank button
	 * 
	 * @return the right-side panel
	 */
	public JPanel constructRightPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		// create the detail text area, uneditable
		detail = new JTextArea(8, 22);
		detail.setBackground(frame.getBackground());
		detail.setEditable(false);
		detail.setFont(new Font("Times", Font.ITALIC, 13));
		detail.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Details:"));
		// add components to the panel
		panel.add(detail);
		panel.add(buttonDownload);
		panel.add(buttonRank);

		return panel;
	}
	
	/**
	 * Registers listeners on necessary components
	 */
	public void registerListeners(){
		buttonDownload.addActionListener(this);
		buttonSearch.addActionListener(this);
		search.addActionListener(this);
		exit.addActionListener(this);
		buttonRank.addActionListener(this);
	}

	/**
	 * Called when a selection in the list box changes. Updates the detail text
	 * area with the information from the selected item.
	 * 
	 * @param event
	 *            which signaled the selected of a new item
	 */
	public void valueChanged(ListSelectionEvent event) {
		String detailText = ((VideoItem) listItems.getSelectedValue()).getItemDetails();
		detail.setText(detailText);

	}

	/**
	 * Called when a button is clicked and calls follow up action
	 * 
	 * @param event
	 *            which signaled the button click
	 */
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == buttonDownload) { // download button pressed
			downloadAction();
		} else if (event.getSource() == search || event.getSource() == buttonSearch) {
			searchAction();
		} else if (event.getSource() == buttonRank) {
			rankAction();
		} else if (event.getSource() == exit) {
			System.exit(0);
		}

		listItems.repaint();

	}

	/**
	 * action following click of download button to simulate downloading
	 * a video item.
	 */
	private void downloadAction() {
		// get the selected item and call method download on it
		VideoItem videoSelected = (VideoItem) listItems.getSelectedValue();

		String downloadMessage = videoSelected.download();
		if (downloadMessage != null) {
			JOptionPane.showMessageDialog(null, downloadMessage);
		}
		JOptionPane.showMessageDialog(null, "Downloading  " + videoSelected.getFullTitle());
		// update the detail data so the new download count can be displayed
		detail.setText(videoSelected.getItemDetails());
		labelMostDownloads.setText("#1 Downloads: " + videoContent.mostDownloads().getFullTitle());
	}

	/**
	 * action following click of the search button. Searches for title
	 * with the phrase entered. If the phrase is found in a title,
	 * the item with the title is selected. Otherwise, the user is
	 * alerted of the failed search.
	 */
	private void searchAction() {
		// first, get the content of the search text field
		String searchPhrase = search.getText();
		VideoItem viFound = null;
		// check that search phrase isn't empty
		if (searchPhrase != null && !searchPhrase.trim().isEmpty()) {
			// search the videoContent for an item with searchPhrase
			viFound = videoContent.findVideoItem(searchPhrase);
			if (viFound != null)
				listItems.setSelectedValue(viFound, true);
			else
				JOptionPane.showMessageDialog(null, "No title matched search phrase \"" + searchPhrase + "\"");
		}
	}

	/**
	 * action following the click of the rank button. The user enters
	 * a rank for the item, and if valid, the ranking of the item
	 * is updated to include this ranking.
	 */
	private void rankAction() {
		VideoItem videoSelected = (VideoItem) listItems.getSelectedValue();
		int ranking = getValidRank(videoSelected);
		videoSelected.processAnotherRanking(ranking);
		// update the detail data so the updated ranking can be displayed
		detail.setText(videoSelected.getItemDetails());
		labelTopRanked.setText("#1 Ranked: " + videoContent.topRanked().getFullTitle());
	}

	/**
	 * requires the user to continue to enter a rank for the selected item
	 * until the rank is a valid integer
	 * @param videoSelected the item selected in the list which the user will rank
	 * @return the valid rank entered by the user
	 */
	public static int getValidRank(VideoItem videoSelected) {

		int rank = 0;
		boolean isValid = false;

		do {
			String userInput = JOptionPane.showInputDialog(null, "Please provide a ranking for \""
					+ videoSelected.getTitle() + "\"\n on a scale from " + LOW_RANK + " to " + HIGH_RANK);
			try {
				rank = Integer.parseInt(userInput);
				if (rank >= LOW_RANK && rank <= HIGH_RANK) {
					isValid = true;
				} else {
					JOptionPane.showMessageDialog(null, "Rank must be " + LOW_RANK + " - " + HIGH_RANK + ".");
				}
			} catch (NumberFormatException error) {
				JOptionPane.showMessageDialog(null, "Invalid. Must be whole number.");
			}

		} while (!isValid);
		return rank;
	}
	
	// reads a file to populate the content list
	private static ArrayList<VideoItem> readFile(Scanner input) {
		ArrayList<VideoItem> contentList = new ArrayList<VideoItem>();
		String type = "";
		TVSeries series = null;
		if (input.hasNext()) {
		    type = input.next();
		}
		while (input.hasNextLine()) {
			String[] data = input.nextLine().trim().split(",");
			if (type.equals("M")) {
				Movie movie = new Movie(data[0], data[1], Integer.parseInt(data[2]),
						Integer.parseInt(data[3]), Double.parseDouble(data[4]), Integer.parseInt(data[5]));
				contentList.add(movie);
			} else if (type.equals("S")) {
				int[] episodesPerSeason = new int[data.length - 1];
				for (int i = 0; i < data.length - 1; i++) {
					episodesPerSeason[i] = Integer.parseInt(data[i + 1]);
					
				}
				series = new TVSeries(data[0], episodesPerSeason);
			} else {
				Episode episode = new Episode(series, data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]),
						Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5]), Double.parseDouble(data[6]),
						Integer.parseInt(data[7]));
				contentList.add(episode);
			}
			
			if (input.hasNext()) {
				type = input.next();
			}
			
		}
		
		return contentList;
	}

}
