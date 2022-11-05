/**
 * Implementation of lists, using doubly linked elements, and dummy nodes.
 * Starter class for "9.10 Laboratory: Lists with Dummy Nodes"
 * Please modify this code by following the directions in  on page 216 of
 * Java Structures sqrt(7) edition by Duane Bailey.
 */

import structure5.*;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E> {

	// Use these variables inherited from DoublyLinkedList
	// Do not uncomment this!  Just use the variables as if they were uncommented
	/**
	* Number of elements within the list.
	*	protected int count;
	*/

	/**
	* Reference to head of the list.
	*
	protected DoublyLinkedNode<E> head;
	*/

	/**
	* Reference to tail of the list.
	*
	protected DoublyLinkedNode<E> tail;
	*/


	/**
	* Constructs an empty list.
	*
	* @post constructs an empty list
	* @big-o O(1).
	*/
	public LinkedList() {
		head = new DoublyLinkedNode<E>(null, tail, null);
		tail = new DoublyLinkedNode<E>(null, null, head);
		count = 0;
		Assert.post(this.isEmpty(), "should be empty at first");
	}

	/**
	* Determine the number of elements in the list.
	*st Prints 10 - low numbers.
	* @post returns the number of elements in list
	*
	* @return The number of elements found in the list.
	* @big-o O(1)
	*/
	public int size() {
		return count;
	}

	/**
	* Determine if the list is empty.
	*
	* @post returns true iff the list has no elements.
	* @big-o O(1)
	* @return True iff list has no values.
	*/
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	* Remove all values from list.
	*
	* @post removes all the elements from the list
	* @big-o O(1)
	*/
	public void clear() {
		head = new DoublyLinkedNode<E>(null, tail, null);
		tail = new DoublyLinkedNode<E>(null, null, head);
		count = 0;
		Assert.post(this.isEmpty(), "should be empty when cleared");
	}

	/**
	* A private routine to add an element after a node.
	* @param value the value to be added
	* @param previous the node the come before the one holding value
	* @pre previous is non null
	* @post list contains a node following previous that contains value
	* @big-o O(1).
	*/
	protected void insertAfter(E value, DoublyLinkedNode<E> previous) {
		//precondition
		Assert.pre(previous != null, "previous cant be null");
		//make a new node with value in it with next = to previous's next and previous = previous
		DoublyLinkedNode<E> node = new DoublyLinkedNode<E>(value, previous.next(), previous);
		//make the node after node have a previous that points to node
		node.next().setPrevious(node);
		//make previous point to node next
		previous.setNext(node);
		//increment count
		count++;

		//postcondition
		Assert.post(previous.next().value() == value, "has to contain a node that contains value following previous");
	}

	/**
	* A private routine to remove a node.
	* @param node the node to be removed
	* @pre node is non null
	* @post node node is removed from the list
	* @post returns the value of the node removed
	* @return the value of the node removed
	* @big-o O(1).
	*/
	protected E remove(DoublyLinkedNode<E> node) {
		//precondition
		Assert.pre(node != null, "node cant be null");

		//System.out.println(node.next().previous() + " first");
		//unlink but node still points to its previous and next, they just don't point to it
		DoublyLinkedNode<E> nex = node.next();
		//System.out.println("next = " + nex);
		DoublyLinkedNode<E> prev = node.previous();
		//System.out.println("prev = " + prev);
		nex.setPrevious(prev);
		//System.out.println(node.next().previous() + " then");

		node.previous().setNext(node.next());
		
		//decrement count
		count--;

		//postcondition
		Assert.post(node.previous().next() != node, "the node cannot be referenced by the node before");
		Assert.post(node.next().previous() != node, "the node cannot be referenced by the node after");
		return node.value();
	}


	/**
	* Add a value to the head of the list.
	*
	* @param value The value to be added.
	* @pre value is not null
	* @post adds element to head of list
	* @big-o O(1)
	*/
	public void addFirst(E value) {
		Assert.pre(value != null, "value cant be null");
		//insert the value after head
		insertAfter(value, head);
		Assert.post(head.next().value() == value, "first element should have value");
	}

	/**
	* Add a value to the tail of the list.
	*
	* @param value The value to be added.
	* @pre value is not null
	* @post adds new value to tail of list
	* @big-o O(1)
	*/
	public void addLast(E value) {
		//precondition
		Assert.pre(value != null, "value cant be null");

		//insert the element after the element before the tail
		insertAfter(value, tail.previous());

		Assert.post(tail.previous().value() == value, "has to add new value to tail of list");
	}

	/**
	* Remove a value from the head of the list.
	* Value is returned.
	*
	* @pre list is not empty
	* @post removes first value from list
	*
	* @post Returns the value removed from the list.
	* @return The value removed from the list.
	* @big-o O(1).
	*/
	public E removeFirst() {
		//precondition
		Assert.pre(!isEmpty(), "List is empty.");
		//remove first non dummy node
		E r = remove(head.next());

		return r;
	}

	/**
	* Remove a value from the tail of the list.
	*
	* @pre list is not empty
	* @post removes value from tail of list
	* @post Returns the value removed from the list.
	* @big-o O(1)
	* @return The value removed from the list.
	*/
	public E removeLast() {
		//precodition
		Assert.pre(!isEmpty(), "List is empty.");

		E r = remove(tail.previous());

		return r;
	}

	/**
	* Get a copy of the first value found in the list.
	*
	* @pre list is not empty
	* @post returns first value in list.
	* @big-o O(1)
	* @return A reference to first value in list.
	*/
	public E getFirst() {
		//precondition
		Assert.pre(!isEmpty(), "this should not be empty");

		return head.next().value();
	}

	/**
	* Get a copy of the last value found in the list.
	*
	* @pre list is not empty
	* @post returns last value in list.
	* @big-o O(1)
	* @return A reference to the last value in the list.
	*/
	public E getLast() {
		//precondition
		Assert.pre(!isEmpty(), "this should not be empty");

		return tail.previous().value();
	}

	/**
	* Insert the value at location.
	*
	* @pre 0 <= i <= size()
	* @post adds the ith entry of the list to value o
	* @param i the index of this new value
	* @param o the the value to be store
	* @big-o O(n) might go through each element but only once
	*/
	public void add(int i, E o) {
		//precondition
		Assert.pre((0 <= i) && (i <= size()), "Index out of range.");

		//start at head
		DoublyLinkedNode<E> n = head;
		//walk through the list "index" number of times
		for (int j = 0; j < i; j++) {
			n = n.next();
		}
		//then insert that element after that node
		insertAfter(o, n);
		Assert.post(n.next().value() == o, "should add the value to a node after n");
	}

	/**
	* Remove and return the value at location i.
	*
	* @pre 0 <= i < size()
	* @post removes and returns the object found at that location.
	* @big-o O(n) might go through each element but only once
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from locatinullon i (returns null if i invalid)
	*/
	public E remove(int i) {
		//precondition
		Assert.pre((0 <= i) && (i < size()), "Index out of range.");
		//start at first element
		DoublyLinkedNode<E> n = head.next();
		//walk through the list "index" number of times
		for (int j = 0; j < i; j++) {
			n = n.next();
		}
		//then return that element that is removed
		return remove(n);
	}

	/**
	* Get the value at location i.
	*
	* @pre 0 <= i < size()
	* @post returns the object found at that location.
	* @big-o O(n) might go through each element but only once
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from location i (returns null if i invalid)
	*/
	public E get(int i) {
		//precondition
		Assert.pre((0 <= i) && (i < size()), "Index out of range.");
		//start at first element
		DoublyLinkedNode<E> n = head.next();
		//walk through the list "index" number of times
		for (int j = 0; j < i; j++) {
			n = n.next();
		}
		//then return that element's value
		return n.value();
	}

	/**
	* Set the value stored at location i to object o, returning the old value.
	*
	* @pre 0 <= i < size()
	* @post sets the ith entry of the list to value o, returns the old value.
	* @param i the location of the entry to be changed.
	* @param o the new value
	* @big-o O(n) might go through each element but only once
	* @return the former value of the ith entry of the list.
	*/
	public E set(int i, E o) {
		//precondition
		Assert.pre((0 <= i) && (i < size()), "Index out of range.");
		//start at first element
		DoublyLinkedNode<E> n = head.next();
		//walk through the list "index" number of times
		for (int j = 0; j < i; j++) {
			n = n.next();
		}
		//then store the old value
		E old = n.value();
		//set n's value to the new value
		n.setValue(o);
		//and return the old value
		return old;
	}

	/**
	* Determine the first location of a value in the list.
	*
	* @pre value is not null
	* @post returns the (0-origin) index of the value,
	*   or -1 if the value is not found
	* @big-o O(n) might go through each element but only once
	* @param value The value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int indexOf(E value) {
		//precondition
		Assert.pre(value != null, "value cant be null");
		//start at first element
		DoublyLinkedNode<E> n = head.next();
		//walk through the list count number of times
		for (int j = 0; j < count; j++) {
			//check if that nodes value is the value we are looking for
			if (n.value().equals(value)) {
				return j;
			}
			n = n.next();
		}
		return -1;
	}

	/**
	* Determine the last location of a value in the list.
	*
	* @pre value is not null
	* @post returns the (0-origin) index of the value,
	*   or -1 if the value is not found
	* @big-o O(n) might go through each element but only once
	* @param value the value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int lastIndexOf(E value) {
		//precondition
		Assert.pre(value != null, "value cant be null");
		//start at first element
		DoublyLinkedNode<E> n = tail.previous();
		//walk through the list count number of times
		for (int j = count - 1; j >= 0; j--) {
			//check if that nodes value is the value we are looking for
			if (n.value().equals(value)) {
				return j;
			}
			n = n.previous();
		}
		return -1;
	}

	/**
	* Check to see if a value is within the list.
	*
	* @pre value not null
	* @post returns true iff value is in the list
	* @big-o O(n) might go through each element but only once
	* @param value A value to be found in the list.
	* @return True if value is in list.
	*/
	public boolean contains(E value) {
		//precondition
		Assert.pre(value != null, "value cant be null");
		if (indexOf(value) >= 0) {
			return true;
		}
		return false;
	}

	/**
	* Remove a value from the list.  At most one value is removed.
	* Any duplicates remain.  Because comparison is done with "equals,"
	* the actual value removed is returned for inspection.
	*
	* @pre value is not null.  List can be empty.
	* @post first element matching value is removed from list
	* @big-o O(n) might go through each element but only once
	* @param value The value to be removed.
	* @return The value actually removed, returns null if not found
	*/
	public E remove(E value) {
		//precondition
		Assert.pre(value != null, "value cant be null");
		//start at first element
		DoublyLinkedNode<E> n = head.next();

		//walk through the list count number of times
		for (int j = 0; j < count; j++) {
			//check if that nodes value is the value we are looking for
			if (n.value().equals(value)) {
				return remove(n);
			}
			n = n.next();
		}
		return null;
	}

	/**
	* Construct an iterator to traverse the list.
	*
	* @post returns iterator that allows the traversal of list.
	* @big-o O(1), gives back one object
	* @return An iterator that traverses the list from head to tail.
	*/
	public Iterator<E> iterator() {
		/**
		 * something to do: once you have incorporated dummy nodes
		 * into your list implementation, please toggle the
		 * comments below. To understand why the lines below
		 * must be swapped, please consult the structure5
		 * source code for DoublyLinkedListIterator class.
		 */

		return new DoublyLinkedListIterator<E>(head, tail);
		// return new DoublyLinkedListIterator<E>(head);
	}

	/**
	* Construct a string representation of the list.
	*
	* @post returns a string representing list
	* @big-o O(n), goes through each element
	* @return A string representing the elements of the list.
	*/
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("<LinkedList (" + count + "):");

		Iterator<E> li = iterator();
		while (li.hasNext()) {
			s.append(" " + li.next());
		}
		s.append(">");

		return s.toString();
	}

	/**
	* Main method that tests out linkedlist
	*/
	// public static void main(String[] args) {
	// 	LinkedList<Integer> l = new LinkedList<Integer>();
	// 	l.insertAfter(1, l.head);
	// 	l.insertAfter(2, l.head);
	// 	l.insertAfter(3, l.head);

	// 	System.out.println(l.toString());
	// 	l.insertAfter(15, l.head.next());
	// 	System.out.println(l.toString());
	// 	//int t = l.removeFirst();
	// 	int r = l.remove(l.head.next());
	// 	System.out.println(l.toString());
	// 	System.out.println(r);

	// 	System.out.println(l.toString());
	// 	System.out.println("remove first: " + l.removeFirst());

	// 	System.out.println(l.toString());
	// 	System.out.println("remove last: " + l.removeLast());
		
	// 	//more tests but was an error in checkstyle for too many lines in method
	// 	System.out.println(l.toString());
	// 	l.addFirst(67);
	// 	System.out.println("add first: " + l.toString());
		
	// 	System.out.println(l.toString());
	// 	l.addLast(69);
	// 	System.out.println("add last: " + l.toString());

	// 	System.out.println(l.getFirst() + " is first");
	// 	System.out.println(l.getLast() + " is last");

	// 	l.add(3, 19);
	// 	System.out.println("should have added 19 to index 3: " + l.toString());

	// 	System.out.println("removed element at index 3: " + l.remove(3));
	// 	System.out.println(l.toString());

	// 	System.out.println("got " + l.get(2) + " at pos 2");

	// 	int res = l.set(2,78);
	// 	System.out.println("old is " + res + " " + l.toString());

	// 	System.out.println("index of 78 is " + l.indexOf(78));

	// 	l.addFirst(22);
	// 	l.addLast(22);

	// 	System.out.println(l.toString());
	// 	System.out.println("first index of 22 is " + l.indexOf(22));
	// 	System.out.println("last index of 22 is " + l.lastIndexOf(22));

	// 	System.out.println("does l contain 22? " + l.contains(22));
	// 	System.out.println("does l contain 23? " + l.contains(23));

	// 	Integer b = 22;
	// 	l.remove(b);
	// 	System.out.println(l.toString());

	// 	System.out.println("removing hi");
	// 	LinkedList<String> nl = new LinkedList<String>();
	// 	nl.addFirst("hi");
	// 	nl.addFirst("you");
	// 	nl.addFirst("me");
	// 	nl.remove("hi");
	// 	System.out.println(nl.toString());
	// }
}
