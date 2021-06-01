/*
 * Name(s): Viet Nguyen
 * CSC 202
 * Lab 7--ArrayIntStack.java
 * 
 * An array implementation of a stack data structure with int elements
 */


import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayIntStack implements StackIntADT {
	
	// data fields
    private int[] elementData; // list of values
    private int size;        // current number of elements in the stack
    
    public static final int DEFAULT_CAPACITY = 4;
    
    /**
     * constructs a stack with DEFAULT_CAPACITY
     */
    public ArrayIntStack() {
    	this(DEFAULT_CAPACITY);
    }
    
   /**
    * constructs a stack with the desired capacity
    * @param capacity - the desired initial capacity
    */
    public ArrayIntStack(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity: " + capacity);
		}
		elementData = new int[capacity];
		size = 0;
    }
    
    
//   @Override // push
    public void push(int item) {
		ensureCapacity(size + 1);
		elementData[size] = item;
		size++;
    }
	
   
//   @Override // pop
    public int pop() {
    	if (size == 0) {
    		throw new EmptyStackException();
    	}
    	int top = elementData[size-1];
    	elementData[size-1] = 0;
    	size--;
    	return top;
    }
	
   
//   @Override // peek
    public int peek() {
    	if (size == 0) {
    		throw new EmptyStackException();
    	}
    	return elementData[size-1];
    }
   
   
//   @Override // size
    public int size() {
    	return size;
    }
	
   
//   @Override // isEmpty
	public boolean isEmpty() {
		return (size == 0);
	}
	
   
//   @Override // clear
	public void clear() {
		for (int i = 0; i < size; i++) {
			elementData[i] = 0;
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

    
    // ensures that the underlying array has the given capacity; if not,
    // the size is doubled (or more if given capacity is even larger)
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
