/*
 * An array implementation of a stack data structure
 */
import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<E> implements StackADT<E>{
	
	// data fields
    private E[] elementData; // list of values
    private int size;        // current number of elements in the stack
    
    public static final int DEFAULT_CAPACITY = 4;
    
    /**
     * constructs a stack with DEFAULT_CAPACITY
     */
    public ArrayStack() {
    	this(DEFAULT_CAPACITY);
    }
    
   /**
    * constructs a stack with the desired capacity
    * @param capacity - the desired initial capacity
    */
    public ArrayStack(int capacity) {
    	if (capacity < 0) {
    		throw new IllegalArgumentException("Invalid capacity: capacity = " + capacity);
    	}
    	elementData = (E[]) new Object[capacity];
    	size = 0;
    }
    
    @Override
	public void push(E item) {
		ensureCapacity(size + 1);
		elementData[size] = item;
		size++;
	}

	@Override
	public E pop(){
		if (size == 0) {
			throw new EmptyStackException();
		}
		E item = elementData[size - 1];
		elementData[size - 1] = null;
		size--;
		return item;
	}

	@Override
	public E peek() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elementData[size - 1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
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
