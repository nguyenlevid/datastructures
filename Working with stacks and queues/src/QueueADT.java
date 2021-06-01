/**
 * An interface that describes a queue abstract data type
 *
 */

public interface QueueADT<E> {
	/**
	 * Add an item to the back of the queue
	 * 
	 * @param item
	 *            the data item to add (of type E)
	 */
	public void add(E item);

	/**
	 * Remove the item from the front of the queue
	 * 
	 * @return the first element is the queue
	 */
	public E remove();

	/**
	 * Return the first item from the front of the queue without removing it
	 * 
	 * @return the first item from the front of the queue
	 */
	public E peek();

	/**
	 * Find how many items are in the queue
	 * 
	 * @return the number of items in the queue
	 */
	public int size();

	/**
	 * Determine if the queue is empty
	 * 
	 * @return true if the size is 0, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Clear out the data structure
	 */
	public void clear();

}
