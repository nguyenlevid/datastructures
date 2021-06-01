
/**
 * Name: Viet Nguyen
 * Date: 14TH April 2021
 * CSC 202
 * Lab 9--Necklace.java
 * 
 * This class implements an ordered singly linked list which represents a
 * necklace of beads. Bead can be added to the necklace, but must remain ordered.
 * Beads of a desired color can be removed. Beads can be accessed by index.
 */
import java.awt.*; 
import java.util.*;



public class Necklace {
	// Your code goes here!
	private Bead front;
	private int numBeads;
	
	/**
	 * 
	 */
	public Necklace() {
		this.front = null;
	}
	
	/**
	 * 
	 */
	public int numBeads() {
		return this.numBeads;
	}
	
	/**
	 * 
	 */
	public Bead getBead(int index) {
		if (index < 0 || index >= numBeads) {
			throw new IllegalArgumentException();
		}
		Bead current = front;
		for (int i = 1; i <= index; i++) {
			current = current.next;
		}
		Color color = current.color;
		int size = current.size;
		return new Bead(color, size);
	}
	
	/**
	 * 
	 */
	public void addBead(Bead bead) {
		if (front == null) {
			front = bead;
			numBeads++;
			return;
		}
		if (bead.compareTo(front) < 0) {
			bead.next = front;
			front = bead;
			numBeads++;
			return;
		}

		Bead before = front;
		while (before.next != null) {
			if (bead.compareTo(before.next) < 0) {
				bead.next = before.next;
				before.next = bead;
				numBeads++;
				return;
			}
			before = before.next;
		}
		before.next = bead;
		numBeads++;
	}
	
	/**
	 * 
	 */
	public Bead removeBead(Color color) {
		if (front == null) {
			return null;
		}
		if (front.color.equals(color)) {
			Bead bead = front;
			front = front.next;
			numBeads--;
			return bead;
		}
		Bead current = front;
		while (current.next != null) {
			if (current.next.color.equals(color)) {
				Bead bead = current.next;
				current.next = current.next.next;
				numBeads--;
				return bead;
			}
			current = current.next;
		}
		return null;
	}
	

	@Override
	public String toString() {
		if (front == null) {
			return "[]";
		} else {
			String beads = "Front: [" + front;
			Bead current = front.next;
			while (current != null) {
				beads = beads + ";\n    " + current;
				current = current.next;
			}
			beads = beads + "]";
			return beads;
		}
	}

	
}
