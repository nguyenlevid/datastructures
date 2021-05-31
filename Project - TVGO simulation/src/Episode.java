/*
 * Name: Viet Nguyen
 * CSC 202-Project 2
 * Episode.java
 * Date: 22nd Mar 2021
 * 
 * This class represents a one episode of a TV series with 
 * the series, the episode number, and the season number of this episode.
 * 
 * Document Assistance(who and describe; if no assistance, declare that fact):
 * I didn't receive help from anyone.
 */
public class Episode extends VideoItem {
	// data fields
	private TVSeries series;
	private int episodeNumber;
	private int seasonNumber;
	
	/**
	 * Creates an Episode object with 9 arguments: tv series, rating, length,
	 * season number, episode number, title of episode, number of downloads,
	 * ranking, number of responses
	 * 
	 * @param series-a TVseries object
	 * @param rating-rating for the episode
	 * @param length-length in minutes
	 * @param seasonNumber-number of the season
	 * @param episodeNumber-number of the episode
	 * @param episodeTitle-title of the episode
	 * @param numDownloads-number of downloads
	 * @param ranking-ranking for the episode
	 * @param numResponses-number of responses
	 */
	public Episode(TVSeries series, String rating, int length, int seasonNumber, int episodeNumber, String episodeTitle, int numDownloads, double ranking, int numResponses) {
		super(episodeTitle, rating, length, numDownloads, ranking, numResponses);
		if (seasonNumber <= 0 || seasonNumber > series.getNumSeasons()) {
			throw new IllegalArgumentException("Invalid season number.");
		}
		if (episodeNumber <= 0 || episodeNumber > series.getNumEpisodesInSeason(seasonNumber)) {
			throw new IllegalArgumentException("Invalid episode number.");
		}
		this.series = series;
		this.seasonNumber = seasonNumber;
		this.episodeNumber = episodeNumber;		
	}
	
	/**
	 * Returns the episode number
	 * @return the episode number
	 */
	public int getEpisodeNum() {
		return this.episodeNumber;
	}
	
	/**
	 * Returns the TV series
	 * @return the TV series
	 */
	public TVSeries getSeries() {
		return this.series;
	}
	
	/**
	 * Returns the season number
	 * @return the season number
	 */
	public int getSeason() {
		return this.seasonNumber;
	}
	
	/**
	 * Returns the number of episodes left in the season
	 * @return the number of episodes left in the season
	 */
	public int episodesLeftThisSeason() {
		return series.getNumEpisodesInSeason(seasonNumber) - episodeNumber;
	}
	
	@Override //toString()
	/**
	 * Returns the Episode in a proper string form
	 * @return the Episode in a proper string form
	 */	
	public String toString() {
		return "\"" + series.getTitle() + "\" Season " + seasonNumber +", Episode: " + episodeNumber; 
	}
	
	@Override //setRating()
	/**
	 * Sets the rating for the Episode. If invalid ratings for Episode are passed, throw
	 * exception
	 * 
	 * @param rating-rating for the episode
	 */	
	public void setRating(String rating) {
		if (!rating.equals("TV-MA") && !rating.equals("TV-14") && !rating.equals("TV-PG") && !rating.equals("TV-G")) {
			throw new IllegalArgumentException("Invalid rating.");
		}
		super.setRating(rating);
	}
	
	@Override //getFullTitle()
	/**
	 * returns the title of the episode in a more convenient way for specific 
	 * circumstances
	 * 
	 * @return compatible version of the title of the episode for some circumstances
	 */	
	public String getFullTitle() {
		return this.toString() + ", " + super.getFullTitle();
	}
	
	@Override //getItemDetails()
	/**
	 * returns the details of the item (episode)
	 * 
	 * @return the details of the item (episode)
	 */	
	public String getItemDetails() {
		String seriesTitle = "\""+series.getTitle()+"\"";
		String seasonAndEp = " Season " + seasonNumber + ", Episode " + episodeNumber;
		String epTitle = super.getFullTitle();
		return seriesTitle + "\n" + seasonAndEp + "\n" + epTitle + "\n" + super.getItemDetails();
	}
	
	@Override //download()
	/**
	 * increments download numbers and returns a message informing 
	 * how many episodes left in the season
	 * 
	 * @return a message indicating the number of episodes left in the season 
	 */		
	public String download() {
		super.download();
		int numLeft = this.episodesLeftThisSeason();
		Object num = numLeft;
		if (numLeft == 0) {
			num = "No";
		}
		return num + " episodes left in this season.";
	}
}
