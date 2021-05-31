/*
 * Name: Viet Nguyen
 * CSC 202-Project 2
 * Movie.java
 * Date: 22nd Mar 2021
 * 
 * This class represents a movie.
 * 
 * Document Assistance(who and describe; if no assistance, declare that fact):
 * I didn't receive help from anyone.
 */


public class Movie extends VideoItem {
	
	/**
	 * Creates a Movie object with 6 arguments: title, rating, length,
	 * number of downloads, ranking, and number of responses.
	 * 
	 * @param title-title of the movie
	 * @param rating-rating for the movie
	 * @param length-length in minutes
	 * @param numDownloads-number of downloads
	 * @param ranking-ranking for the episode
	 * @param numResponses-number of responses
	 */
	public Movie(String title, String rating, int length, int numDownloads, double ranking, int numResponses) {
		super(title, rating, length, numDownloads, ranking, numResponses);
	}
	
	@Override //toString()
	public String toString() {
		return this.getFullTitle();
	}
	@Override //setRating()
	public void setRating(String rating) {
		if (!rating.equals("G") && !rating.equals("PG") && !rating.equals("PG-13") && !rating.equals("R")) {
			throw new IllegalArgumentException("Invalid rating.");
		}
		super.setRating(rating);
	}
	@Override //getItemDetails()
	public String getItemDetails() {
		return this.toString() + "\n" + super.getItemDetails();
	}
}

