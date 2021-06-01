
/*
 * Program to test the implementation of your ArrayQueue class
 */

import java.util.NoSuchElementException;

public class ArrayQueueTest {

	public static void main(String[] args) {
		QueueADT<Integer> queue = null;

		try {
			System.out.println("Testing default constructor.");
			queue = new ArrayQueue<Integer>();
			System.out.println("...passed");
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}

		QueueADT<String> queue2 = null;
		try {
			System.out.println("\nTesting constructor with parameter.");
			queue2 = new ArrayQueue<String>(5);
			System.out.println("...passed");
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}
		
		// Testing constructor with invalid parameter
		try {
			System.out.println("\nTesting constructor invalid parameter.");
			QueueADT<String> queue3 = new ArrayQueue<String>(-2);
			System.out.println("...failed");
			System.out.println("Capacity can not be passed as negative");
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.out.println("...passed");
			System.out.println("   threw exception: " + e);
		}

		// adding three elements
		try {
			System.out.println("\nTesting add");
			queue.add(6);
			queue.add(20);
			queue.add(13);
			System.out.println("After 6, 20, 13 added.");
			if (queue.size() == 3 && queue.toString().equals("[6, 20, 13]")) {
				System.out.println("...passed");
			} else {
				System.out.println("add or size failed");
				System.out.println("    correct queue: [6, 20, 13]");
				System.out.println("    your queue: " + queue);
				System.out.println("    correct size: 3");
				System.out.println("    your size method returned: " + queue.size());
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}

		// removing two elements
		try {
			System.out.println("\nTesting remove");
			int one = queue.remove();
			int two = queue.remove();
			System.out.println("After 6, 20 removed.");
			if (one == 6 && two == 20 && queue.size() == 1 && queue.toString().equals("[13]")) {
				System.out.println("...passed");
			} else {
				System.out.println("remove or size failed");
				System.out.println("    correct queue: [13]");
				System.out.println("    your queue: " + queue);
				System.out.println("    correct size: 1");
				System.out.println("    your size method returned: " + queue.size());
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}
		
		// removing last element
		try {
			System.out.println("\nTesting remove");
			int one = queue.remove();
			System.out.println("After 13 removed.");
			if (one == 13 && queue.size() == 0 && queue.toString().equals("[]")) {
				System.out.println("...passed");
			} else {
				System.out.println("remove or size failed");
				System.out.println("    correct queue: []");
				System.out.println("    your queue: " + queue);
				System.out.println("    correct size: 0");
				System.out.println("    your size method returned: " + queue.size());
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}
		
		// adding elements to default capacity
		try {
			System.out.println("\nTesting add to fill default capacity");
			queue.add(14);
			queue.add(5);
			queue.add(16);
			queue.add(7);
			System.out.println("After 14, 5, 16, 7 added.");
			if (queue.toString().equals("[14, 5, 16, 7]")) {
				System.out.println("...passed");
			} else {
				System.out.println("add failed");
				System.out.println("    correct queue: [14, 5, 16, 7]");
				System.out.println("    your queue: " + queue);
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}
		
		// removing when size == capacity
		try {
			System.out.println("\nTesting remove when size = capacity");
			int removedValue = queue.remove();
			System.out.println("After 14 removed");
			if (queue.toString().equals("[5, 16, 7]") && removedValue == 14 && queue.size() == 3) {
				System.out.println("...passed");
			} else {
				System.out.println("remove or size failed");
				System.out.println("    correct removed element: 14");
				System.out.println("    your removed element: " + removedValue);
				System.out.println("    correct size: 3");
				System.out.println("    your size: " + queue.size());			
				System.out.println("    correct queue: [5, 16, 7]");
				System.out.println("    your queue: " + queue);
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}
		
		// adding elements requiring ensureCapacity
		try {
			System.out.println("\nTesting add with enlarging");
			queue.add(15);
			queue.add(19);
			queue.add(25);;
			System.out.println("After 15, 19, 25 added.");
			if (queue.toString().equals("[5, 16, 7, 15, 19, 25]")) {
				System.out.println("...passed");
			} else {
				System.out.println("add failed");
				System.out.println("    correct queue: [5, 16, 7, 15, 19, 25]");
				System.out.println("    your queue: " + queue);
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}

		// testing peek
		try {
			int first = queue.peek();
			if (first == 5 & queue.size() == 6 && queue.toString().equals("[5, 16, 7, 15, 19, 25]")) {
				System.out.println("\nPeek was correct.");
			} else {
				System.out.println("\nPeek failed.");
				System.out.println("    correct peek element: 5");
				System.out.println("    your peek element: " + first);
				System.out.println("    correct size: 6");
				System.out.println("    your size method returned: " + queue.size());
				System.out.println("    correct queue: [5, 16, 7, 15, 19, 25]");
				System.out.println("    your queue: " + queue);
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}

		// testing clear
		try {
			System.out.println("\nTesting clear.");
			queue.clear();
			int size = queue.size();
			if (queue.size() == 0 && queue.toString().equals("[]")) {
				System.out.println("...passed");
			} else {
				System.out.println("clear or size failed");
				System.out.println("    correct size: 0");
				System.out.println("    your size method returned: " + size);
				System.out.println("    correct queue: []");
				System.out.println("    your queue: " + queue);
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayQueue line#" + line);
			System.exit(1);
		}

		// Testing removing from an empty queue
		System.out.println("\nTesting removing from an empty queue.");
		try {
			queue.remove();
			System.out.println("If the queue prints, this is an error.");
			System.out.println("remove front to rear: " + queue);
			System.exit(1);
		} catch (NoSuchElementException e) {
			System.out.println("The queue should throw an exception. Can't remove from an empty queue.");
			System.out.println("Threw NoSuchElementException: " + e.getMessage());
		}

		// Testing peeking from an empty queue
		System.out.println("\nTesting peeking an empty queue.");
		try {
			int number = queue.peek();
			System.out.println("If the element prints, this is an error.");
			System.out.println("The element at the front of the queue is " + number);
			System.exit(1);
		} catch (NoSuchElementException e) {
			System.out.println("The queue should throw an exception. Can't peek an empty queue.");
			System.out.println("Threw NoSuchElementException: " + e.getMessage());
		}

		// Testing isEmpty
		System.out.println("\nTesting isEmpty.");
		if (queue.isEmpty()) {
			System.out.println("This is the correct output: The queue is empty.");
		} else {
			System.out.println("This is incorrect output: The queue is not empty.");
			System.exit(1);
		}

		// Manipulating a queue of Strings
		try {
			System.out.println("\nTesting queue of strings");
			queue2.add("This");
			queue2.add("is");
			queue2.add("the");
			queue2.add("last");
			queue2.add("test");
			queue2.add("for");
			queue2.add("queues");
			String element1 = queue2.remove();
			String element2 = queue2.remove();
			queue2.add("!");
			if (queue2.size() == 6 && queue2.toString().equals("[the, last, test, for, queues, !]")) {
				System.out.println("...passed");
			} else {
				System.out.println("Manipulating queue of String failed");
				System.out.println("    correct queue: [the, last, test, for, queues, !]");
				System.out.println("    your queue: " + queue2);
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in ArrayStack line#" + line);
			System.exit(1);
		}

		System.out.println("\n\nPassed all tests");
	}
}
