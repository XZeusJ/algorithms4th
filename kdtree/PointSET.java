/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    private final SET<Point2D> points;

    public PointSET() { // construct an empty set of points
        points = new SET<Point2D>();
    }

    public boolean isEmpty() { // is the set empty?
        return points.isEmpty();
    }

    public int size() { // number of points in the set
        return points.size();
    }

    public void insert(
            Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException();
        points.add(p);
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }

    public void draw() {  // draw all points to standard draw
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D p : points)
            p.draw();
    }

    public Iterable<Point2D> range(
            RectHV rect) {           // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException();

        Queue<Point2D> q = new Queue<>();

        for (Point2D p : points)
            if (rect.contains(p)) q.enqueue(p);

        return q;
    }

    public Point2D nearest(
            Point2D p) {  // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException();
        if (points.isEmpty()) return null;

        double dist;
        double min = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;
        for (Point2D point : points) {
            dist = p.distanceSquaredTo(point);
            if (min > dist) {
                min = dist;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) { // unit testing of the methods (optional)
        In in = new In("test.txt");
        PointSET points = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            points.insert(new Point2D(x, y));
        }

        // test size()
        // StdOut.println("size is " + points.size());

        // test draw()
        // points.draw();

        // test range()
        // for (Point2D p : points.range(new RectHV(0, 0, 0.5, 0.5)))
        //     StdOut.println(p);

        // test nearest()
        // StdOut.println("nearest point is " + points.nearest(new Point2D(0.5, 0.99)));

    }
}
