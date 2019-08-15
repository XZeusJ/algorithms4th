/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] segments; // record segments
    private int n;  // number of segments

    public FastCollinearPoints(
            Point[] pointSet) { // finds all line segments containing 4 or more points
        Point[] points = pointSet.clone();
        checkNull(points);      // corner case
        checkRepeat(points);    // corner case

        n = 0;
        int len = points.length;
        final List<LineSegment> maxLineSegments = new LinkedList<>();

        segments = new LineSegment[0];
        Point[] clonePoints = points.clone();  // clone points for not changing origin point data

        // Point testP = new Point(1000,17000);

        Point p, q;         // start and end point of segment
        Point curr, next;   // used for check if any 3 (or more) adjacent points in the sorted order
        for (int i = 0; i < len; i++) {
            p = points[i];
            // q = null;

            // let first i elements to be p
            // which prevent finding repeat segments
            // for (int j = 0; j < i; j++) clonePoints[j] = p;

            // StdOut.println("prev points By slope:");
            // showA(clonePoints);
            Arrays.sort(clonePoints, p.slopeOrder());
            // StdOut.println("we choose " + p + " as origin point.");
            // StdOut.println("next points By slope:");
            // showA(clonePoints, p);

            // OLD VERSION
            // int count = 1;  // record how many adjacent points
            // for (int j = i+1; j < len - 1; j++) { // j = i+1 ensure all dirctions are same
            //     curr = clonePoints[j];
            //     next = clonePoints[j + 1];
            //     double slope1 = p.slopeTo(curr);
            //     double slope2 = p.slopeTo(next);
            //
            //     if (slope1 == slope2) count++;
            //     else count = 1;
            //
            //     if (count >= 3) q = clonePoints[j + 1];
            //
            //     if ((count == 1 && q != null) || (count >= 3 && j == len - 2)) {
            //         if (n == segments.length) resize(segments.length + 1);
            //         segments[n++] = new LineSegment(p, q);
            //         q = null;
            //     }
            // }

            int x = 1;
            while (x < len) {
                StdOut.println(x);
                showA(clonePoints);
                LinkedList<Point> candidates = new LinkedList<>();
                final double SLOPE_REF = p.slopeTo(clonePoints[x]);
                do {
                    candidates.add(clonePoints[x++]);
                } while (x < len && p.slopeTo(clonePoints[x]) == SLOPE_REF);

                // Candidates have a max line segment if ...
                // 1. Candidates are collinear: At least 4 points are located
                //    at the same line, so at least 3 without "p".
                // 2. The max line segment is created by the point "p" and the
                //    last point in candidates: so "p" must be the smallest
                //    point having this slope comparing to all candidates.
                if (candidates.size() >= 3
                        && p.compareTo(candidates.peek()) < 0) {
                    Point min = p;
                    Point max = candidates.removeLast();
                    maxLineSegments.add(new LineSegment(min, max));
                }
            }
        }
        segments = maxLineSegments.toArray(new LineSegment[0]);
    }

    private void showA(Point[] points) {
        for (Point p : points) {
            StdOut.println(p);
        }
        StdOut.println();
    }

    private void showA(Point[] points, Point op) {
        for (Point p : points) {
            StdOut.println(p + " " + p.slopeTo(op));
        }
        StdOut.println();
    }

    private void resize(int capacity) {
        assert capacity >= n;
        LineSegment[] temp = new LineSegment[capacity];
        for (int i = 0; i < n; i++)
            temp[i] = segments[i];
        segments = temp;
    }

    private void checkNull(Point[] points) {
        if (points == null) throw new NullPointerException("The array \"Points\" is null.");

        for (Point p : points)
            if (p == null)
                throw new NullPointerException("The array \"Points\" contains null element.");

    }

    private void checkRepeat(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Duplicate.");
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
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // Arrays.sort(points);
        // for (Point p : points) {
        //     StdOut.println("point" + p);
        //     // p.draw();
        // }
        // StdOut.println();
        // StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }

        // StdDraw.show();
    }
}
