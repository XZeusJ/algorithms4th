/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments; // record segments
    private int n;  // number of segments

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        checkNull(points);      // corner case
        Arrays.sort(points);
        checkRepeat(points);    // corner case

        n = 0;
        int len = points.length;
        // List<LineSegment> list = new LinkedList<>();
        segments = new LineSegment[0];
        // Point[] newPoints = points.clone();


        Point p;
        Point q;
        Point curr;
        Point next;
        for (int i = 0; i < len - 3; i++) {
            Arrays.sort(points);
            p = points[i];
            q = null;

            Arrays.sort(points, p.slopeOrder());
            int count = 1;
            for (int j = i+1; j < len - 1; j++) {
                curr = points[j];
                next = points[j+1];
                double slope1 = p.slopeTo(curr);
                double slope2 = p.slopeTo(next);

                if (slope1 == slope2) count++;
                else count = 1;
                if (count >= 3) q = points[j + 1];
            }
            if (q != null) {
                if (n == segments.length) resize(segments.length + 1);
                segments[n++] = new LineSegment(p, q);
            }

        }
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
        for (int i = 0; i < points.length - 2; i++) {
            if (points[i] == points[i + 1])
                throw new IllegalArgumentException();
        }
    }


    public int numberOfSegments() { // the number of line segments
        return n;
    }

    public LineSegment[] segments() {  // the line segments
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
