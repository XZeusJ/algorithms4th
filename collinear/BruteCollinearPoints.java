/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        isLegal(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        lineSegments = new ArrayList<>();
        Arrays.sort(pointsCopy);

        int l = pointsCopy.length;
        for (int i = 0; i < l - 3; i++) {
            for (int j = i + 1; j < l - 2; j++) {
                for (int k = j + 1; k < l - 1; k++) {
                    if (!isCollinear(pointsCopy, i, j, k)) continue;
                    for (int m = k + 1; m < l; m++) {
                        if (isCollinear(pointsCopy, i, k, m)) {
                            lineSegments.add(new LineSegment(pointsCopy[i], pointsCopy[m]));
                        }
                    }
                }
            }
        }
    }

    private boolean isCollinear(Point[] points, int i, int j, int k) {
        double slope1 = points[i].slopeTo(points[j]);
        double slope2 = points[i].slopeTo(points[k]);
        return slope1 == slope2;
    }

    private void isLegal(Point[] points) {
        if (points == null) throw new java.lang.IllegalArgumentException();

        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException();
        }

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        for (int i = 0; i < points.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) throw new IllegalArgumentException();
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numberOfSegments()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("test.txt");
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
