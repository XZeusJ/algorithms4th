package ch24_Priority_Queues;

import edu.princeton.cs.algs4.StdOut;

public class UnorderedListMaxPQ<Key extends Comparable<Key>> {
    private Node<Key> first;    // start of pq
    private Node<Key> last;     // end of pq
    private int n;              // number of elements

    private static class Node<Key> {
        private Key key;
        private Node<Key> next;
    }

    public UnorderedListMaxPQ(int capacity) {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }


    public Key delMax() {
        Node<Key> max = first;

        // find max node key
        for (Node<Key> current = first.next; current != null; current = current.next) {
            if (less(max, current)) max = current;
        }

        // handle special case
        if (max == first) first = first.next;

        // find and take max key node from list
        Node<Key> oldcurrent = first;
        for (Node<Key> current = first; current != null; current = current.next) {
            if (max.key == current.key) {
                oldcurrent.next = max.next;
                max.next = null;
                break;
            } else oldcurrent = current;
        }

        n--;
        return max.key;
    }


    public void insert(Key key) {
        Node<Key> oldlast = last;
        last = new Node<Key>();
        last.key = key;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;

//        show();
    }

    private void show() {
        for (Node<Key> current = first; current != null; current = current.next) {
            StdOut.print(current.key + " ");
        }
        StdOut.println();
    }

    private boolean less(Node<Key> v, Node<Key> w) {
        return v.key.compareTo(w.key) < 0;
    }


    public static void main(String[] args) {
        UnorderedListMaxPQ<String> pq = new UnorderedListMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
}
