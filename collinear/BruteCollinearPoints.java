/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private LineSegment[] segments; // record segments
    private int n;    // record number of segments

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if (points == null) throw new IllegalArgumentException();
        int len = points.length;
        segments = new LineSegment[1000];
        n = 0;
        // Arrays.sort(points);

        Point p, q, r, s;
        double slp1, slp2, slp3;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    for (int l = 0; l < len; l++) {

                        p = points[i];
                        q = points[j];
                        r = points[k];
                        s = points[l];
                        slp1 = p.slopeTo(q);
                        slp2 = p.slopeTo(r);
                        slp3 = p.slopeTo(s);

                        if (slp1 == Double.NEGATIVE_INFINITY || slp2 == Double.NEGATIVE_INFINITY
                                || slp3 == Double.NEGATIVE_INFINITY)
                            continue;

                        if (slp1 == slp2 && slp2 == slp3 && slp3 == slp1)
                            segments[n++] = new LineSegment(p, s);
                    }
                }
            }
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
        StdDraw.show();
    }
}
