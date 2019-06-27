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

public class RandomizedQueue<Item> implements Iterable<Item> {


    // helper linked list class
    private static class Node<Item> {
    }

    public RandomizedQueue()                 // construct an empty randomized queue
    {
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

    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

    }

    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

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
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
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
