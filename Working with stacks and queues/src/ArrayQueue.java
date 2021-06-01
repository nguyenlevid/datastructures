/*
 * Name(s): Viet Nguyen
 * CSC 202
 * Lab 7--ArrayQueue.java
 * 	
 * An array implementation of a queue data structure with generics
 */

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueADT<E> {
	
	// data fields
    private E[] elementData; // list of values
    private int size;        // current number of elements in the queue
    
    public static final int DEFAULT_CAPACITY = 4;
    /**
     * constructs a queue with DEFAULT_CAPACITY
     */
    public ArrayQueue() {
    	this(DEFAULT_CAPACITY);
    }
    
   /**
    * constructs a queue with the desired capacity
    * @param capacity - the desired initial capacity
    */
    public ArrayQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity: " + capacity);
		}
		elementData = (E[]) new Object[capacity];
		size = 0;
    }   
    
//    @Override  // add
	public void add(E value) {
		ensureCapacity(size + 1);
		elementData[size] = value;
		size++;
	}
    
    
//    @Override  // remove
	   public E remove() {
		   	if (size == 0) {
		   		throw new NoSuchElementException();
		   	}
		   	E first = elementData[0];
		   	for (int i = 1; i < size; i++) {
		   		elementData[i-1] = elementData[i];
		   	}
		   	elementData[size-1] = null;
		   	size--;
		   	return first;
		   }
    
    
//    @Override // peek
	   public E peek() {
		   	if (size == 0) {
		   		throw new NoSuchElementException();
		   	}
		   	return elementData[0];
		   }
    
    
//    @Override  // size
	   public int size() {
		   	return size;
		   }

    
//    @Override  // isEmpty
		public boolean isEmpty() {
			return (size == 0);
		}

    
//    @Override  // clear
		public void clear() {
			for (int i = 0; i < size; i++) {
				elementData[i] = null;
			}
			size = 0;
		}


    
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    
    // post: ensures that the underlying array has the given capacity; if not,
    //       the size is doubled (or more if given capacity is even larger)
    private void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

}
