/* *****************************************************************************
 *  Name: zhangjin.xiao
 *  Date:
 *  Description: implementation of 2dTree, reference of BST.java
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private Node root;  // root of 2dTree
    private int size;

    private class Node {
        private Point2D point;    // sorted by key
        private Node left, right;   // left and right subtrees

        public Node(Point2D p) {
            this.point = p;
        }
    }

    public KdTree() { // construct an empty set of points
    }

    public boolean isEmpty() { // is the set empty?
        return size() == 0;
    }

    public int size() { // number of points in the set
        return size(root);
    }

    private int size(Node x) { // return number of keys in kdTree rooted at x
        if (x == null) return 0;
        else return size;
    }

    public void insert(
            Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException("calls insert() with a null key");
        root = insert(root, p, true);
    }

    private Node insert(Node currNode, Point2D point, boolean isEven) {
        if (currNode == null) return new Node(point);

        if (isEven) { // at even level we use x-coordinate to compare points
            // if (point.equals(currNode.point)) return currNode;
            double cmp = point.x() - currNode.point.x();
            if (cmp < 0) currNode.left = insert(currNode.left, point, !isEven);
            else if (cmp > 0) currNode.right = insert(currNode.right, point, !isEven);

        }
        else { // at odd level we use y-coordinate to compare points
            double cmp = point.y() - currNode.point.y();
            if (cmp < 0) currNode.left = insert(currNode.left, point, !isEven);
            else if (cmp > 0) currNode.right = insert(currNode.right, point, !isEven);
        }

        size++;
        return currNode;
    }

    public boolean contains(Point2D p) {  // does the set contain point p?
        if (p == null) throw new IllegalArgumentException("argument to contains() is null");
        return contains(root, p, true);
    }

    private boolean contains(Node currNode, Point2D point, boolean isEven) {
        if (currNode == null) return false;

        if (isEven) {
            double cmp = point.x() - currNode.point.x();
            if (cmp < 0) return contains(currNode.left, point, !isEven);
            else if (cmp > 0) return contains(currNode.right, point, !isEven);

        }
        else {
            double cmp = point.y() - currNode.point.y();
            if (cmp < 0) return contains(currNode.left, point, !isEven);
            else if (cmp > 0) return contains(currNode.right, point, !isEven);
        }

        return true;
    }

    public void draw() {  // draw all points to standard draw
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        drawPoint(root);

        RectHV rect = new RectHV(0, 0, 1, 1);
        StdDraw.setPenRadius();
        drawLine(root, rect, true);
    }

    private void drawPoint(Node x) {
        if (x == null) return;
        x.point.draw();
        drawPoint(x.left);
        drawPoint(x.right);
    }

    private void drawLine(Node currNode, RectHV rect, boolean isEven) {
        if (currNode == null) return;

        if (isEven) {
            StdDraw.setPenColor(StdDraw.RED);
            Point2D p1 = new Point2D(currNode.point.x(), rect.ymax());
            Point2D p2 = new Point2D(currNode.point.x(), rect.ymin());
            p1.drawTo(p2);

            drawLine(currNode.left, new RectHV(rect.xmin(), rect.ymin(), currNode.point.x(), rect.ymax()), !isEven);
            drawLine(currNode.right, new RectHV(currNode.point.x(), rect.ymin(), rect.xmax(), rect.ymax()), !isEven);
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            Point2D p1 = new Point2D(rect.xmin(), currNode.point.y());
            Point2D p2 = new Point2D(rect.xmax(), currNode.point.y());
            p1.drawTo(p2);

            drawLine(currNode.left, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), currNode.point.y()), !isEven);
            drawLine(currNode.right, new RectHV(rect.xmin(), currNode.point.y(), rect.xmax(), rect.ymax()), !isEven);
        }
    }

    public Iterable<Point2D> range(
            RectHV rect) {  // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> queue = new Queue<>();
        range(root, queue, rect, true);
        return queue;
    }

    private void range(Node currNode, Queue<Point2D> queue, RectHV rect, boolean isEven) {
        if (currNode == null) return;
        if (rect.contains(currNode.point)) queue.enqueue(currNode.point);

        // use x cooridinate to seperate the range to left or right side
        if (isEven) {
            // which means all points in rect is on the left side of currNode point
            if (currNode.point.x() > rect.xmax())
                range(currNode.left, queue, rect, !isEven);
                // which means all points in rect is on the right side of currNode point
            else if (currNode.point.x() <= rect.xmin())
                range(currNode.right, queue, rect, !isEven);
            else {
                range(currNode.left, queue, rect, !isEven);
                range(currNode.right, queue, rect, !isEven);
            }
        }
        // use y cooridinate to seperate the range to top or down side
        else {
            if (currNode.point.y() > rect.ymax())
                range(currNode.left, queue, rect, !isEven);
            else if (currNode.point.y() <= rect.ymin())
                range(currNode.right, queue, rect, !isEven);
            else {
                range(currNode.left, queue, rect, !isEven);
                range(currNode.right, queue, rect, !isEven);
            }
        }
    }

    public Point2D nearest(
            Point2D p) {  // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException();
        return nearest(root, p, Double.POSITIVE_INFINITY, null, true);
    }

    private Point2D nearest(Node currNode, Point2D point, double minDist, Point2D nearPoint,
                            boolean isEven) {
        if (currNode == null) return nearPoint;

        if (isEven) {
            double xDiff = point.x() - currNode.point.x();

            if (minDist >= xDiff * xDiff) {
                double currDist = point.distanceSquaredTo(currNode.point);
                if (currDist < minDist) {
                    minDist = currDist;
                    nearPoint = currNode.point;
                }
            }

            if (minDist < xDiff * xDiff) {
                if (xDiff < 0) return nearest(currNode.left, point, minDist, nearPoint, !isEven);
                else return nearest(currNode.right, point, minDist, nearPoint, !isEven);
            }
            else {
                if (xDiff < 0) {
                    nearPoint = nearest(currNode.left, point, minDist, nearPoint, !isEven);
                    minDist = nearPoint.distanceSquaredTo(point);
                    return nearest(currNode.right, point, minDist, nearPoint, !isEven);
                }
                else {
                    nearPoint = nearest(currNode.right, point, minDist, nearPoint, !isEven);
                    minDist = nearPoint.distanceSquaredTo(point);
                    return nearest(currNode.left, point, minDist, nearPoint, !isEven);
                }
            }

        }
        else {
            double yDiff = point.y() - currNode.point.y();

            if (minDist >= yDiff * yDiff) {
                double currDist = point.distanceSquaredTo(currNode.point);
                if (currDist < minDist) {
                    minDist = currDist;
                    nearPoint = currNode.point;
                }
            }

            if (minDist < yDiff * yDiff) {
                if (yDiff < 0) return nearest(currNode.left, point, minDist, nearPoint, !isEven);
                else return nearest(currNode.right, point, minDist, nearPoint, !isEven);
            }
            else {
                if (yDiff < 0) {
                    nearPoint = nearest(currNode.left, point, minDist, nearPoint, !isEven);
                    minDist = nearPoint.distanceSquaredTo(point);
                    return nearest(currNode.right, point, minDist, nearPoint, !isEven);
                }
                else {
                    nearPoint = nearest(currNode.right, point, minDist, nearPoint, !isEven);
                    minDist = nearPoint.distanceSquaredTo(point);
                    return nearest(currNode.left, point, minDist, nearPoint, !isEven);
                }
            }
        }
    }

    public static void main(String[] args) {  // unit testing of the methods (optional)

    }
}
