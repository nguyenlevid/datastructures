/*
 * Name: Viet Nguyen 
 * Date: 7th Mar 2021
 * CSC 202
 * Project 1-CaveMaze.java
 * 
 * Incomplete class that models a maze of caves for the "Hunt the Wumpus" game.
 * The maze of caves is defined in a file where the first line of the file is 
 * the number of caves and each subsequent line is the cave number, number of
 * adjacent caves followed by the cave number of each adjacent cave, and the 
 * name of the cave. 
 * 
 * One cave will be initialized to store CaveContents.BAT.
 * One cave will be initialized to store CaveContents.PIT.
 * Randomly one to three caves will be initialized to store CaveContents.WUMPUS.
 * 
 * @author Dave Reed
 * @revised by Diane Mueller
 * 
 * Document Assistance(who and describe; if no assistance, declare that fact):
 * 
 */
import java.util.*;
import java.io.*;

public class CaveMaze {
	private Random randGen = new Random();   //used to generate random numbers
	
	private Cave currentCave;   // The cave the player is in
	private Cave[] caves;       // All of the caves in the maze
	private int numWumpi;       // Number of wumpi currently in the caves
	

	/**
	 * Constructs a CaveMaze from the data found in a file.
	 * 
	 * @param filename the name of the cave data file
	 * @throws FileNotFoundException is the filename received as a parameter
	 *   is not found in the project.
	 */
	public CaveMaze(String filename) throws FileNotFoundException {
		Scanner infile = new Scanner(new File(filename));

		int numCaves = infile.nextInt();
		this.caves = new Cave[numCaves];

		for (int i = 0; i < caves.length; i++) {
			int num = infile.nextInt();
			int numAdj = infile.nextInt();
			int[] adj = new int[numAdj];
			for (int a = 0; a < adj.length; a++) {
				adj[a] = infile.nextInt();
			}
			String name = infile.nextLine().trim();
			this.caves[i] = new Cave(name, num, adj);
		}		
		// Place bats, a pit and wumpi
		this.caves[randomEmptyCave()].setContents(CaveContents.BATS);
		this.caves[randomEmptyCave()].setContents(CaveContents.PIT);
		int numCavesWumpi = randGen.nextInt(3)+1;
		this.numWumpi = numCavesWumpi;
		for (int wumpiCave = 1; wumpiCave <= numCavesWumpi; wumpiCave++) {
			this.caves[randomEmptyCave()].setContents(CaveContents.WUMPUS);
		}
		currentCave = caves[0];
		currentCave.markAsVisited();
		
		
	}

	/**
	 * Moves the player from the current cave along the specified tunnel, marking
	 * the new cave as visited.
	 * 
	 * @param tunnel - the number of the tunnel to be traversed (1-number of tunnels)
	 * @param player - the player roaming the caves
	 * 
	 * @return the message depending on the tunnel and the result depending on the
	 * contents of the cave the tunnel leads to.
	 */
	public String move(int tunnel, Player player) {
		if (tunnel < 1 || tunnel > currentCave.getNumAdjacent()) {
			return "There is no tunnel number " + tunnel;
		} else {
			
			int caveNum = currentCave.getAdjCaveNumber(tunnel);
			currentCave = caves[caveNum];
			currentCave.markAsVisited();
			String message = "Moving down tunnel " + tunnel + "...";
			
			if (currentCave.getContents() == CaveContents.PIT) {
				message += "\nYou've fallen into the bottomless pit!";
				player.die();
			}
			else if (currentCave.getContents() == CaveContents.WUMPUS) {
				message += "\nYou've entered a cave with a wumpus…CHOMP CHOMP CHOMP!";
				player.die();
			}
			else if (currentCave.getContents() == CaveContents.BATS) {
				int randomNumCaveNum = randomEmptyCave();
				this.currentCave = caves[randomNumCaveNum];
				currentCave.markAsVisited();
				message += "\nThe bats dropped you into another cave!";
			}
			
			return message;
		}
	}
	/**
	 * helper that helps with finding an empty cave to be dropped in by bats.
	 * 
	 * @return empty cave index
	 */
	private int randomEmptyCave() {
		int index = randGen.nextInt(caves.length-1) + 1;
		while (caves[index].getContents() != CaveContents.EMPTY) {
			index = randGen.nextInt(caves.length-1) + 1;
		}
		return index;
	}

	/**
	 * Attempts to toss a stun grenade into the specified tunnel, but currently no
	 * grenades.
	 * 
	 * @param tunnel - the number of the tunnel(1-number of tunnels) leading to cave to be bombed
	 * @param player - the player roaming the caves
	 * 
	 * @return the message depending on the result of the tunnel and the cave's contents
	 */
	public String toss(int tunnel, Player player) {
		String message = "";
		if (tunnel < 1 || tunnel > currentCave.getNumAdjacent()) {
			return "There is no tunnel number " + tunnel;
		} else {
			if (!player.throwGrenade()) {
				player.die();
				return "You have no grenades left, which makes it impossible to catch any more wumpus.";
			}
			
			int caveNum = currentCave.getAdjCaveNumber(tunnel);
			if (caves[caveNum].getContents() == CaveContents.WUMPUS) {
				this.numWumpi -= 1;
				caves[caveNum].setContents(CaveContents.EMPTY);
				message += "You've caught one wumpus. Congrats!";
			}
			else {
				message += "Missed, dagnabit!";
			}
			for (int i = 1; i <= currentCave.getNumAdjacent(); i++) {
				if (caves[currentCave.getAdjCaveNumber(i)].getContents() == CaveContents.WUMPUS) {
					int thatCaveIndex = currentCave.getAdjCaveNumber(i);
					int numAdj = caves[thatCaveIndex].getNumAdjacent();
					int indexMoveTo = randGen.nextInt(numAdj) + 1;
					if (caves[indexMoveTo] == currentCave) {
						message += "\nA startled wumpus charges into your cave...CHOMP CHOMP CHOMP";
						player.die();
					}
					
				}
			}
			
		}
		return message;
	
	}

	/**
	 * Displays the current cave name and the names of adjacent caves. Caves that
	 * have not yet been visited are displayed as "unknown".
	 * 
	 * @return the message giving the current location and clues from the adjacent caves 
	 */
	public String showLocation() {
		String message = "You are currently in " + this.currentCave.getCaveName();

		ArrayList<String> clues = new ArrayList<String>();
		boolean wumpusFound = false;
		for (int i = 1; i <= currentCave.getNumAdjacent(); i++) {
			int caveIndex = currentCave.getAdjCaveNumber(i);
			Cave adjCave = caves[caveIndex];
			message += "\n    (" + i + ") " + adjCave.getCaveName();
			if (adjCave.getContents() == CaveContents.WUMPUS && !wumpusFound) {
				clues.add("You smell an awful stench coming from somewhere nearby.");
				wumpusFound = true;
			}
			if (adjCave.getContents() == CaveContents.BATS) {
				clues.add("You hear the flapping of wings close by");
			}
			if (adjCave.getContents() == CaveContents.PIT) {
				clues.add("You feel a draft coming from one of the tunnels.");
			}
		}
		Collections.shuffle(clues);
		for (String warning: clues) {
			message += "\n" + warning;
		}

		return message;
	}

	/**
	 * Reports the number of wumpi remaining in the maze
	 * 
	 * @return the number of wumpi remaining in the maze
	 */
	public int getNumWumpi() {
		return numWumpi;
	}

	/**
	 * Reports whether there are any wumpi remaining.
	 * 
	 * @return true if there is still a wumpus in some cave
	 */
	public boolean stillWumpi() {
		return (numWumpi != 0);
	}

	
	
	// returns a string with one cave's information per line
	// Prof Mueller used this to help in debugging her program!
	private String cavesAsString() {
		String caveList = "";
		for (int i = 0; i < caves.length; i++) {
			caveList = caveList + caves[i] + "\n";
			
		}
		return caveList;
	}
}