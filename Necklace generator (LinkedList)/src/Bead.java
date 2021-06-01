
/**
 * Name: Viet Nguyen
 * Date: 14th April 2021
 * CSC 202
 * Lab 9--Bead.java
 * 
 * This class represents a Bead for a necklace with a color, size, and reference to the
 * next bead. Beads can be compared by color and within color, by size. 
 */
import java.awt.Color;
import java.util.Random;

public class Bead implements Comparable<Bead> {
	public static final int SIZE_RANGE = 21;
	public static final int MIN_SIZE = 10;

	private static Random randGenerator = new Random();
	
	// Your code goes here
	public Color color;
	public int size;
	public Bead next;
	
	/** 
	 * default constructor - set color from Palette class
	 * 						- set size randomly from 10 to 30 inclusively
	 * 						- reference to next is null
	 */
	public Bead() {
		this.color = Palette.getRandomColor();
		this.size = randGenerator.nextInt(20) + 10;
		this.next = null;
	}
	
	/** 
	 * constructor 
	 * @param color - color of the bead
	 * @param size - size of the bead
	 * 
	 */
	public Bead(Color color, int size) {
		this.color = color;
		this.size = size;
		this.next = null;
	}
	
	/**
	 * toString
	 * return a String in the form:
	 * color = index of the color from the Palette class, size = the bead's size
	 */
	public String toString() {
		int colorIndex = Palette.getIndex(color);
		return "color = " + colorIndex + ", size = " + size;
	}
	
	//@override
	public int compareTo(Bead other) {
		if (Palette.getIndex(this.color) != Palette.getIndex(other.color)) {
			return Palette.getIndex(this.color) - Palette.getIndex(other.color);
		}
		return other.size - this.size;

	}
	

	// Test code for Bead class
	public static void main(String[] args) {
		System.out.println("****************************************************");
		System.out.println("Create 20 random beads. All sizes should be [10, 30]");
		System.out.println("****************************************************");
		for (int i = 0; i < 20; i++) {
			Bead randBead = new Bead();
			System.out.println("Random bead: " + randBead);
		}
		
		System.out.println("\n**********************************************************");
		System.out.println("Test constructor with parameters & data fields color, size");
		System.out.println("**********************************************************");
		Color color1 = new Color(38, 255, 60);
		Bead bead1 = new Bead(color1, 20);
		System.out.println(bead1);
		System.out.println(bead1.color);
		System.out.println(bead1.size);
		
		System.out.println("\n****************************************************");
		System.out.println("Test compareTo");
		System.out.println("****************************************************");
		Color color2 = new Color(38, 190, 255);
		Bead bead5 = new Bead(color2, 15);
		System.out.println(bead1);
		System.out.println(bead5);
		System.out.println("Different color, smaller index in Palette means before, return negative: " + bead1.compareTo(bead5));
		Color color3 = new Color(147, 38, 255);
		Bead bead6 = new Bead(color3, 35);
		System.out.println(bead6);
		System.out.println(bead1);
		System.out.println("Different color, larger index in Palette means after, return positive: " + bead6.compareTo(bead1));
		Bead bead2 = new Bead(color1, 30);
		System.out.println(bead1);
		System.out.println(bead2);
		System.out.println("Same color, smaller size means after, return positive: " + bead1.compareTo(bead2));
		Bead bead3 = new Bead(color1, 18);
		System.out.println(bead1);
		System.out.println(bead3);
		System.out.println("Same color, larger size means before, return negative: " + bead1.compareTo(bead3));
		Bead bead4 = new Bead(color1, 20);
		System.out.println(bead1);
		System.out.println(bead4);
		System.out.println("Same color, same size, should return 0: " + bead1.compareTo(bead4));	
	}

}
