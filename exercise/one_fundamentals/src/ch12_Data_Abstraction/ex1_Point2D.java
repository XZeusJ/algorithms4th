package ch12_Data_Abstraction;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class ex1_Point2D {
    private static double distance(Point2D p1, Point2D p2) {
        return 0;
    }

    public static void main(String[] args) {
        int n;
        if (args.length == 0) n = 10;
        else n = Integer.parseInt(args[0]);

        // initialize point2d array
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point2D(Math.random(), Math.random());
        }

        // draw distance between point2d
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                points[i].drawTo(points[j]);
                StdDraw.show();
//                StdDraw.pause(100);
            }
        }

        // compare each distance and find the min
        double min = 1000;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double temp = points[i].distanceTo(points[j]);
//                StdOut.println("temp dist is:" + temp);
                if (min > temp) {
                    min = temp;
//                    StdOut.println("min distance is:" + min);
                }
            }
        }
        StdOut.println("The min distance is:" + min);
    }
}
