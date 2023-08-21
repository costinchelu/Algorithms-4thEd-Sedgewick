package edu.princeton.cs.algs4.section_1_3;

import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly linked list with a static nested class for
 *  linked-list nodes. See {@link LinkedQueue} for the version from the
 *  textbook that uses a non-static nested class.
 *  See {@link ResizingArrayQueue} for a version that uses a resizing array.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of each item in this queue
 */
public class Queue<Item> implements Iterable<Item> {
    
    private Node<Item> head;    // beginning of queue
    
    private Node<Item> tail;     // end of queue
    
    private int n;               // number of elements on queue

    /** helper linked list class */
    private static class Node<Item> {
        
        private Item item;
        
        private Node<Item> next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        head = null;
        tail = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(Item item) {
        Node<Item> oldTail = tail;
        tail = new Node<>();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) head = tail;
        else           oldTail.next = tail;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = head.item;
        head = head.next;
        n--;
        if (isEmpty()) tail = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return head.item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new LinkedIterator(head);
    }

    /** an iterator, doesn't implement remove() since it's optional */
    private class LinkedIterator implements Iterator<Item> {
        
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
           return current != null;
        }

        public void remove() {
           throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    /**
     * Unit tests the {@code Queue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        In getFileName = new In();
        In in = new In(getFileName.getFileFromResources("strings.txt"));
        String[] read = in.readAllLines();

        Queue<String> queue = new Queue<>();
        for (String s : read) {
            queue.enqueue(s);
        }

        StdOut.println(queue);

        StdOut.println("peek: " + queue.peek());
        StdOut.println("(" + queue.size() + " left on queue)");

        while (!queue.isEmpty()) {
            queue.dequeue();
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
