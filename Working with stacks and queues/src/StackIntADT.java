/**
 * An interface that describes a stack abstract data type
 *
 */

public interface StackIntADT {
	/**
	 * Add an item onto the stack
	 * 
	 * @param item
	 *            the data item to add
	 */
	public void push(int item);

	/**
	 * Remove the top item from the stack
	 * 
	 * @return the top item in the stack
	 */
	public int pop();

	/**
	 * Return the top item from the stack without removing it
	 * 
	 * @return the top item in the stack
	 */
	public int peek();

	/**
	 * Find how many items are in the stack
	 * 
	 * @return the number of items in the stack
	 */
	public int size();

	/**
	 * Determine if the stack is empty
	 * 
	 * @return true if the size is 0, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Clear out the data structure
	 */
	public void clear();

}

