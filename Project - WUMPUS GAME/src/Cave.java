/*
 * Name: Viet Nguyen 
 * Date: 28 Feb
 * CSC 202
 * Project 1-Player.java
 * 
 * Player defines the "human" player for the Hunt the Wumpus game. 
 * The player gets 3 grenades for every wumpus in the maze.
 * 
 * Document Assistance(who and describe; if no assistance, declare that fact):
 * 
 */
import java.util.*;

public class Cave {
	
	// data fields
	private String caveName;
	private int caveNumber;
	private int[] adjCaveList;
	private CaveContents contents;
	private boolean visited;
	
	/*
	 * Constructor: creates a Cave with the name, 
	 * cave number and array received as parameters. 
	 * It should also initialize the cave contents to be empty, 
	 * and the cave to not be visited yet
	 * 
	 * @param name - name of the cave
	 * @param caveNumber - number of the cave
	 * @param adjCaveList - an array storing the number of each cave 
	 * 					that can be reached directly through a tunnel
	 */
	public Cave(String name, int caveNumber, int[] adjCaveList) {
		this.caveName = name;
		this.caveNumber = caveNumber;
		this.adjCaveList = adjCaveList;
		this.contents = CaveContents.EMPTY;
		this.visited = false;
	}
	
	/*
	 * this method returns the number of the cave 
	 * that can be reached via the tunnel number received
	 * 
	 * @param tunnel - tunnel number
	 * @return int - the number of the cave accessed through the tunnel
	 * 				invalid tunnel number causes 0 in return
	 */
	public int getAdjCaveNumber(int tunnel) {
		if (tunnel <= 0 || tunnel > adjCaveList.length) {
			return -1;
		}
		return adjCaveList[tunnel-1];
	}
	
	/*
	 * this method returns the name of this cave 
	 * 
	 * @return String - name of the cave
	 */
	public String getCaveName() {
	    if (!visited) {
	        return "unknown";
	    }
		return this.caveName;
	}

	/*
	 * this method returns the number of this cave 
	 * 
	 * @return int - number of the cave
	 */
	public int getCaveNumber() {
		return this.caveNumber;
	}
	
	/*
	 * this method returns the contents of this cave 
	 * 
	 * @return CaveContents - contents of the cave
	 */
	public CaveContents getContents() {
		return this.contents;
	}
	
	/**
	 * this method returns true if the cave is already visited; otherwise, false.
	 * 
	 * @return boolean
	 **/
	public boolean isVisited() {
		return this.visited;
	}
	
	/**
	 * this method returns the number of caves that can be reached
	 * directly from this cave
	 * 
	 * @return int - number of caves that can be reached
	 **/
	public int getNumAdjacent() {
		return adjCaveList.length;
	}
	
	/**
	 * this method marks this cave as visited
	 **/
	public void markAsVisited() {
		this.visited = true;
	}
	
	/**
	 * this method sets the cave's contents to the ones received
	 **/
	public void setContents(CaveContents contents) {
		this.contents = contents;
	}
	
	/**
	 * returns a String representation of a cave in the format
	 * Cave number: X, Cave name: X, Adj Cave List: X, Visited: X, Contents: X
	 **/
	public String toString() {
		return "Cave number: " + caveNumber + ", Cave name: " + caveName + ", Adj Cave List: " + Arrays.toString(adjCaveList) + ", Visited: " + visited + ", Contents: " + contents;
	}
}
