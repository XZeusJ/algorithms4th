package ch13_Bags_Stacks_Queues;

import edu.princeton.cs.algs4.ResizingArrayQueue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class ResizingArrayQueueOfStrings {
    private String[] q;
    private int n;
    private int first;
    private int last;

    public ResizingArrayQueueOfStrings() {
        q = new String[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size () {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        String[] temp = new String[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public String dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        String item = q[first];
        q[first] = null;
        n--;
        first++;
        if (first == q.length) first =0;
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return item;
    }

    public static void main(String[] args) {
        ResizingArrayQueue queue = new ResizingArrayQueue();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
