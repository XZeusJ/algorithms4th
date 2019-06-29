/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;       // array of items
    private int n;          // number of elements on stack

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;

        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }


    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("No item add");
        if (n == a.length) resize(2 * a.length);  // double size of array
        a[n++] = item;                          // add item
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randIndex = StdRandom.uniform(n);
        Item item = a[randIndex];
        a[randIndex] = a[n - 1];
        a[n - 1] = null;              // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randIndex = StdRandom.uniform(n);
        return a[randIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // an iterator, doesn't implement remove() sice it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int copyN;
        private final Item[] copyA;

        public RandomizedQueueIterator() {
            copyN = n;
            copyA = (Item[]) new Object[copyN];
            for (int i = 0; i < copyN; i++) {
                copyA[i] = a[i];
            }
        }

        public boolean hasNext() {
            return copyN > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int randIndex = StdRandom.uniform(copyN);
            Item item = copyA[randIndex];
            copyA[randIndex] = copyA[copyN - 1];
            copyA[copyN - 1] = null;              // to avoid loitering
            copyN--;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}
