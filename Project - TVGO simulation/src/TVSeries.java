/*
 * Name: Viet Nguyen
 * CSC 202-Project 2
 * TVSeries.java
 * Date: 22nd Mar 2021
 * 
 * This class represents a TV series with a title
 * and the number of episodes per season
 * 
 * Document Assistance(who and describe; if no assistance, declare that fact):
 * I didn't receive help from anyone.
 */
public class TVSeries {
	private String title;
	private int[] numEpisodesInSeason;

	/**
	 * Creates a TV series with a title and a list of episodes 
	 * in each season
	 * 
	 * @param title-the  name of this content collection
	 * @param numEpisodesInSeason-the list of episodes in season
	 */
	public TVSeries(String title, int[] numEpisodesInSeason) {
		this.title = title;
		this.numEpisodesInSeason = numEpisodesInSeason;
	}
	
	/**
	 * Returns the title of the series
	 * @return the title of the series
	 */
	public String getTitle() {
		return this.title;
	}

	
	/**
	 * Returns the number of seasons in the series
	 * @return the number of seasons in the series
	 */
	public int getNumSeasons() {
		return numEpisodesInSeason.length;
	}
	
	/**
	 * Returns the number of seasons in the series
	 * @return the number of seasons in the series
	 */
	public int getNumEpisodesInSeason(int seasonNumber) {
		if (seasonNumber < 1 || seasonNumber > numEpisodesInSeason.length) {
			throw new IllegalArgumentException("Season number invalid.");
		}
		return numEpisodesInSeason[seasonNumber-1];
	}	
	
	/**
	 * Returns the TVSeries in a proper string form
	 * @return the TVSeries in a proper string form
	 */	
	public String toString() {
		String output = "\"" + title + "\" TV Series";
		for (int i = 0; i < numEpisodesInSeason.length; i++) {
			output += "\n   Season " + (i+1) + " has " + getNumEpisodesInSeason(i+1) + " episodes.";
		}
		return output;
	}
}
