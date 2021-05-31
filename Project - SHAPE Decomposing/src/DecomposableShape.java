/*
 * Name: Viet Nguyen
 * Date: 19th April 2021
 * CSC 202
 * Project 3-DecomposableShape.java
 * 
 * Description: this class helps organize the shape as
 * 				a circular doubly linked list. 
 * 
 * 
 * Cite Assistance: (who and describe what; if no assistance, declare that fact)
 *  I received no help on this project.
 * 
 */

import java.util.Scanner; 
import java.awt.Polygon;

public class DecomposableShape {
	// data fields
	private int initialNumPoint;
	private int currentNumPoint;
	private PointNode front;
	private ArrayStack<PointNode> storeRemoved = new ArrayStack<PointNode>();

	// inner PointNode class
	private class PointNode {
		// inner class PointNode data fields
		private int x;
		private int y;
		private double importance;
		private PointNode prev;
		private PointNode next;
		
		/**
		 * Constructor
		 * Creates a PointNode with given x, y and default importance, prev, next.
		 * @param x - x-coordinate of the PointNode
		 * @param y - y-coordinate of the PointNode
		 */
		public PointNode(int x, int y) {
			this.x = x;
			this.y = y;
			this.importance = 0;
			this.prev = null;
			this.next = null;
		}
		
		/**
		 * Calculates importance of one point and sets 
		 * its importance to the value.
		 * if point has no left or right neighbor, importance remains default.
		 */
		public void calculateImportance() {
				if (this.next != null && this.prev != null) {
					double lp = Math.sqrt((this.x - prev.x) * (this.x - prev.x) + (this.y - prev.y) * (this.y - prev.y));
					double pr = Math.sqrt((this.x - next.x) * (this.x - next.x) + (this.y - next.y) * (this.y - next.y));
					double lr = Math.sqrt((next.x - prev.x) * (next.x - prev.x) + (next.y - prev.y) * (next.y - prev.y));
					this.importance = lp + pr - lr;
				}
		}
		
		/**
		 * @return a String representing a PointNode in the right form.
		 * Calculates importance or it remains default.
		 */
		public String toString() {
			return "x = " + x + ", y = " + y + ", importance = " + importance;
		}
	}

	/**
	 * Constructor
	 * Reads from files containing lines of X and Y coordinates.
	 * Creates a circular doubly linked list where each node is 
	 * one point from the files.
	 * @param input
	 */
	public DecomposableShape(Scanner input) {

		PointNode previous = front;
		while (input.hasNextLine()) {
			this.initialNumPoint++;
			String[] data = input.nextLine().split(",");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			PointNode current = new PointNode(x, y); // create a Node to add from x , y

			if (front == null) { // when list is empty
				front = current;
				previous = front;
			} else {
				current.prev = previous;
				previous.next = current;
				previous.calculateImportance();
				previous = current;
			}
			if (!input.hasNextLine()) { // connect last Node to front
				current.next = front;
				front.prev = current;
				current.calculateImportance();
			}
			
		}
		front.calculateImportance();
		currentNumPoint = initialNumPoint;
		System.out.println("Initial Points");
		System.out.println(this.toString());
		
	}

	/**
	 * Creates a polygon with arrays of x's, y's and the current number of points.
	 * @return a polygon with given data
	 */
	public Polygon toPolygon() {
		int[] xpoints = new int[currentNumPoint];
		int[] ypoints = new int[currentNumPoint];
		PointNode current = front;
		for (int i = 0; i < currentNumPoint; i++) {
			xpoints[i] = current.x;
			ypoints[i] = current.y;
			current = current.next;
		}
		return new Polygon(xpoints, ypoints, currentNumPoint);
	}

	/**
	 * Increases or decreases the number of points in the polygon 
	 * depending on the target
	 * Eliminates the least important points first and store for later
	 * adding to meet the target.
	 *  
	 * @param target - percentage of points that should appear in the polygon
	 */
	public void setToSize(int target) {
		int numToSize = initialNumPoint * target / 100;
		if (numToSize < currentNumPoint) {
			int numGap = currentNumPoint - numToSize;
			for (int i = 0; i < numGap; i++) {
				PointNode out = findLeastImportant(front); //find least important point
				removeLeastImportant(out); //remove least important point upon finding
				currentNumPoint--;
			}
		} else {
			int numGap = numToSize - currentNumPoint;
			for (int i = 0; i < numGap; i++) {
				restoreImportant(); // restore the most recent removed PointNode
				currentNumPoint++;
			}
		}
		System.out.println("Current Points");
		System.out.println(this.toString());
	}
	
	/**
	 * Helps find and return the least important PointNode for removing.
	 * @param first - the first PointNode to traverse the linked list from
	 * @return - a PointNode that has the least importance to remove
	 */
	private PointNode findLeastImportant(PointNode first) {
		PointNode current = first;
		PointNode out = current;
		double min = out.importance;
		do {
			if (current.importance < min) {
				min = current.importance;
				out = current;
			}
			current = current.next;
		} while (current != first);
		return out;
	}

	/**
	 * Helps remove the least important PointNode upon finding.
	 * Also stores the removed PointNode for later restoring
	 * @param out - the least important PointNode to be removed
	 */
	private void removeLeastImportant(PointNode out) {
		if (out == front) { // removing front without reassigning can cause early halt 
							// in traversing process
			front = front.next;
		}
		storeRemoved.push(out); // store the to-be-removed PointNode for later restoring
		out.prev.next = out.next;
		out.next.prev = out.prev;
		out.prev.calculateImportance();
		out.next.calculateImportance();
	}
	
	/**
	 * Helps restore the most recent important PointNode stored in "storeRemoved" stack. 
	 */
	private void restoreImportant() {
		PointNode toAdd = storeRemoved.pop();
		PointNode previous = front;
		PointNode after = front.next;
		while (after != toAdd.next || previous != toAdd.prev) {
			previous = after;
			after = after.next;
		}
		toAdd.next = after;
		toAdd.prev = previous;
		previous.next = toAdd;
		after.prev = toAdd;
	}
	/**
	 * @return a String representing all the PointNode in the right form.
	 */
	public String toString() {
		String message = "";
		PointNode current = front;
		do {
			message += current.toString() + "\n";
			current = current.next;
		} while (current != front);
		return message;
	}
}
