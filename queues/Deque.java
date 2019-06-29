/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int n;          // number of elements on queue
    private Node<Item> first;      // beginning of queue
    private Node<Item> last;       // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    public Deque() { // construct an enmty deque
        first = null;
        last = null;
        n = 0;
    }


    public boolean isEmpty() { // is the deque empty?
        return first == null || last == null;
    }

    public int size() { // return the number of items on the deque
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException("No item add");}
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.prev = null;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        else oldfirst.prev = first;
        n++;
    }

    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException("No item add");}
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.prev = oldlast;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null; // to avoid loitering
        else first.prev = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

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


    public static void main(String[] args) { // unit testing (optional)
        Deque<Integer> deque = new Deque<Integer>();
        StdOut.println(deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        // StdOut.println(deque.removeFirst());
        // StdOut.println(deque.removeFirst());
        // deque.addLast(1);
        // deque.addLast(2);
        for (int n: deque) {
            StdOut.print(n+" ");
        }

        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());

        for (int n: deque) {
            StdOut.print(n+" ");
        }
    }
}
