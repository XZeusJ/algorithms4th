/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    public Deque() { // construct an enmty deque
        q = (Item[]) new Object[1];
        n = 0;
        first = 0;
        last = 0;
    }


    public boolean isEmpty() { // is the deque empty?
        return n == 0;
    }

    public int size() { // return the number of items on the deque
        return n;
    }

    private void resize(int capacity) { // Move stack to a new array of size max.
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public void addFirst(Item item) {
        if (n == q.length) resize(2 * q.length);  // double size of array if necessary
        q[first--] = item; // add item at first position
        if (first == 0) first = q.length;
        n++;
    }

    public void addLast(Item item) {
        // double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2 * q.length);  // double size of array if necessary
        q[last++] = item;                       // add item at last position
        if (last == q.length) last = 0;         // wrap-around
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;                        // to avoid loitering
        n--;
        first++;
        if (first == q.length) first = 0;       // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[last];
        q[last] = null;                         // to avoid loitering
        n--;
        last--;
        if (last == 0) last = q.length;         // wrap-around
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }


    public static void main(String[] args) { // unit testing (optional)
        Deque<String> queue = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                queue.addLast(item);
            }
            // else if (!queue.isEmpty()) StdOut.print(queue.removeLast() + " ");
            else if (!queue.isEmpty()) {
                // queue.removeLast();
                StdOut.print(queue.removeLast() + " ");
            }
            // StdOut.println();

        }
        StdOut.println();
        for (String s : queue)
            StdOut.print(s+" ");
        StdOut.println();
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
