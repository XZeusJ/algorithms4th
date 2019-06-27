/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListRandomizedQueue<Item> implements Iterable<Item> {
    private int n;          // number of elements on queue
    private Node<Item> first;      // beginning of queue
    private Node<Item> last;       // end of queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public ListRandomizedQueue()                 // construct an empty randomized queue
    {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return first == null || last == null;
    }

    public int size()                        // return the number of items on the randomized queue
    {
        return n;
    }

    public void enqueue(Item item)           // add the item
    {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randomNum = StdRandom.uniform(n);
        Node<Item> oldfisrt = first;
        Item item;
        if (n == 0) {
            item = first.item;
            first = first.next;
        }
        else {
            while (0 < randomNum) {
                oldfisrt = oldfisrt.next;
                randomNum--;
            }
            item = oldfisrt.next.item;
            oldfisrt.next = oldfisrt.next.next;
        }

        n--;
        if (isEmpty()) last = null; // to avoid loitering
        return item;
    }

    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randomNum = StdRandom.uniform(n);
        Node<Item> oldfisrt = first;
        Item item;
        if (n == 0) {
            item = first.item;
        }
        else {
            while (0 < randomNum) {
                oldfisrt = oldfisrt.next;
                randomNum--;
            }
            item = oldfisrt.next.item;
        }
        return item;
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() sice it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
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

    public static void main(String[] args) { // unit testing (optional)
        ListRandomizedQueue<String> queue = new ListRandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
