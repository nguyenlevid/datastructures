/*
 * Name: Viet Nguyen 
 * Date: 6th May 2021
 * CSC 202
 * Project 4-StarChart.java
 * 
 * This class represents information about stars to be displayed including
 * the position of each star, names of stars, and constellations. The
 * distance between two stars can be determined. A supernova can be 
 * simulated creating dead stars.
 * 
 * Document Assistance(who and describe; if no assistance, declare that fact):
 */
 
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class StarChart {
	private static double SUPERNOVA_DISTANCE = 0.25;
	private int width;
	private int height;
	private HashMap<String, Star> starListByName;
	private HashMap<Star, String> starListByStar;
	private HashMap<String, String[]> constellationList;
	private HashSet<Star> deadStars;
	
	/**
	 * Initialize a star chart with given dimensions of the screen (sky view)
	 * Initialize a map of names by stars
	 * 			  a map of stars by names
	 * 			  a set of dead stars
	 * 			  a map of constellation by its stars' name
	 * 
	 * @param width - width of the screen
	 * @param height - height of the screen
	 */
	public StarChart(int width, int height) {
		this.width = width;
		this.height = height;
		starListByName = new HashMap<>();
		starListByStar = new HashMap<>();
		deadStars = new HashSet<>();
		constellationList = new HashMap<>();
	}
	
	/**
	 * Adds stars to the sky. 
	 * Used 2 maps for storing: 
	 * 				starListByName - for later referring to star by name
	 * 								 (saves efficiency when stars without name
	 * 									are added)
	 * 				starListByStar - for later referring to name by star
	 * 								 (a full list of stars, with some stars 
	 * 									without a name)
	 * @param star - the given star
	 * @param name - the given star's name 
	 * 					(if no name is given, star is stored as null)
	 */
	public void addStar(Star star, String name) {
		if (name != "") {
			starListByName.put(name, star);	
			starListByStar.put(star, name);
		}
		else {
			starListByStar.put(star, null);
		} 
	}
	
	/**
	 * Adds constellations with its array of star names.
	 * 
	 * @param constellationName - the name of the constellation
	 * @param starNames - its array of star names
	 */
	public void addConstellation(String
			constellationName, String[] starNames) {
		constellationList.put(constellationName, starNames);
	}
	
	/**
	 * Gets the name of the star given
	 * 
	 * @param star - the star given
	 * @return name of the star
	 */
	public String getName(Star star) {
		return starListByStar.get(star);
	}
	
	/**
	 * Gets the star by the given name
	 * 
	 * @param name - name of the star
	 * @return the star called 
	 */
	public Star getStar(String name) {
		return starListByName.get(name);
	}
	
	/**
	 * Makes a stars to go supernova
	 * if any surrounding star is closer than the SUPERNOVA_DISTANCE
	 * it will be dead, too
	 * 
	 * @param star - the star to go supernova
	 * @return the number of stars having gone dead by that supernova
	 */
	public int supernova(Star star) {
		if (deadStars.contains(star)) {
			return 0;
		}
		deadStars.add(star);
		int deadStarNum = findAffectedStars(star) + 1;
		return deadStarNum;
	}
	
	/**
	 * Helps identifies affected (dead) stars in the supernova
	 * 
	 * @param star - the star that goes supernova
	 * @return the number of affected stars
	 */
	private int findAffectedStars(Star star) {
		int affectedStarCount = 0;
		for (Star item : starListByStar.keySet()) {
			if (star.distance(item) <= SUPERNOVA_DISTANCE
					&& !deadStars.contains(item)) 
			{
				deadStars.add(item);
				affectedStarCount++;
			}
		}	
		return affectedStarCount;
	}
	
	/**
	 * Draws stars and constellations
	 * (if stars are dead, they're red)
	 * shows stars' name if applicable
	 * 
	 * @param g - the currently used graphics
	 * @param showStarNames - true if stars in a constellation need to show their names
	 * 						  false otherwise
	 */
	public void draw(Graphics g, boolean showStarNames) {
		// draw all stars
		drawStars(g);
		
		// draw constellations (if any) (shows star names if required)
		for (String constelName : constellationList.keySet()) {
			drawConstellations(g, constelName, showStarNames);
		}
	}
	
	/**
	 * Helps draw the stars
	 * 
	 * @param g - the currently used graphics
	 */
	private void drawStars(Graphics g) {
		for (Star star : starListByStar.keySet()) {
			if (!deadStars.contains(star)) {
				g.setColor(star.pixelColor());
			}
			else {
				g.setColor(Color.RED);
			}
			g.fillRect(star.pixelX(width), star.pixelY(height), star.pixelSize(),  star.pixelSize());

		}
	}
	
	/**
	 * Helps draw the constellations and shows their stars' name if needed
	 * 
	 * @param g - the currently used graphics
	 * @param constelName - name of the constellation to be drawn
	 * @param showStarNames - true if stars in a constellation need to show their names
	 * 						  false otherwise
	 */
	private void drawConstellations(Graphics g, String constelName, boolean showStarNames) {
		String[] starNames = constellationList.get(constelName);
		Set<String> starNamesInConstel = new HashSet<>();
		for (int i = 0; i < starNames.length-1; i+=2) {
			starNamesInConstel.add(starNames[i]);
			starNamesInConstel.add(starNames[i+1]);
			Star firstStar = starListByName.get(starNames[i]);
			Star secondStar = starListByName.get(starNames[i+1]);
			g.setColor(Color.YELLOW);
			g.drawLine(firstStar.pixelX(width), firstStar.pixelY(height), secondStar.pixelX(width), secondStar.pixelY(height));
			
			if (i+1 == starNames.length-1) {
				g.drawString(constelName, secondStar.pixelX(width), secondStar.pixelY(height));
			}
		}
		if (showStarNames) {
			for (String starName : starNamesInConstel) {
				Star theStar = getStar(starName);
				g.setColor(Color.WHITE);
				g.drawString(starName, theStar.pixelX(width), theStar.pixelY(height));
			}
		}
	}
}
