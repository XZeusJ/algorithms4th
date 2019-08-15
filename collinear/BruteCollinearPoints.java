/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments; // record segments
    private int n;    // record number of segments

    public BruteCollinearPoints(Point[] pointSet) { // finds all line segments containing 4 points
        Point[] points = pointSet.clone();
        checkNull(points);      // corner case
        checkRepeat(points);    // corner case

        n = 0;
        int len = points.length;
        // List<LineSegment> list = new LinkedList<>();
        segments = new LineSegment[0];


        Point p, q, r, s;
        double slopepq, slopepr, slopeps;
        for (int i = 0; i < len - 3; i++) {
            p = points[i];

            for (int j = i + 1; j < len - 2; j++) {
                q = points[j];
                slopepq = p.slopeTo(q);

                for (int k = j + 1; k < len - 1; k++) {
                    r = points[k];
                    slopepr = p.slopeTo(r);
                    if (slopepq == slopepr) {

                        for (int l = k + 1; l < len; l++) {
                            s = points[l];
                            slopeps = p.slopeTo(s);

                            if (slopepr == slopeps) {
                                // list.add(new LineSegment(p, s));
                                if (n == segments.length) resize(segments.length + 1);
                                segments[n++] = new LineSegment(p, s);
                            }
                        }
                    }
                }
            }
        }
        // segments = list.toArray(new LineSegment[0]);
    }

    private void resize(int capacity) {
        assert capacity >= n;
        LineSegment[] temp = new LineSegment[capacity];
        for (int i = 0; i < n; i++)
            temp[i] = segments[i];
        segments = temp;
    }

    private void checkNull(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException();
        }
    }

    private void checkRepeat(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Duplicated.");
        }
    }

    public int numberOfSegments() { // the number of line segments
        return n;
    }

    public LineSegment[] segments() { // the line segments
        return segments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        // for (int i = 0; i < collinear.numberOfSegments(); i++) {
        //     LineSegment segment = collinear.segments[i];
        //     StdOut.println(segment);
        //     segment.draw();
        // }

        StdDraw.show();
    }
}
