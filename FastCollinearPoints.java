/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 23, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // check arguments
        if (points == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null || points[i + 1] == null)
                throw new IllegalArgumentException();
            String pointStr = points[i].toString();
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null || pointStr.equals(points[j].toString()))
                    throw new IllegalArgumentException();
            }
        }

        // Point p = points[0];
        // double[] slops = new double[points.length];
        // slops[0] = Double.NEGATIVE_INFINITY;
        // for (int i = 0; i < points.length; i++) {
        //     slops[i] = p.slopeTo(points[i]);
        //     StdOut.printf("%s;%s  ", points[i], slops[i]);
        // }
        // StdOut.println();
        //
        // Point[] copy = Arrays.copyOf(points, points.length);
        // Arrays.sort(copy, p.slopeOrder());
        // for (int i = 0; i < points.length; i++) {
        //     StdOut.printf("%s-%s", points[i], copy[i]);
        // }

        for (int i = 0; i < points.length - 3; i++) {
            double[] slops = new double[points.length];
            for (int j = 1; j < points.length; j++) {
                slops[j] = points[i].slopeTo(points[j]);
                // StdOut.printf("%s;%s  ", points[j], slops[j]);
            }
            StdOut.println();

            Arrays.sort(points, points[i].slopeOrder());
            for (int j = 0; j < points.length; j++) {
                StdOut.printf("%s;%s  ", points[j], slops[j]);
            }
            // for (Point p : points)
            //     StdOut.printf("%s ", p);
            // StdOut.println();


            // Arrays.sort(slops);
            // for (double d : slops)
            //     StdOut.printf("%s ", d);
            // StdOut.println();
            // for (int i)
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] newSegments = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++)
            newSegments[i] = segments[i];
        return newSegments;
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(60, 30), new Point(20, 10), new Point(40, 10),
                new Point(70, 10), new Point(10, 10), new Point(0, 10),
                new Point(10, 70), new Point(20, 20), new Point(30, 30),
                new Point(60, 60)
        };

        StdOut.print("Set of points: ");
        for (Point p : points)
            StdOut.printf("%s; ", p);
        StdOut.println();

        FastCollinearPoints fast = new FastCollinearPoints(points);
        StdOut.printf("contains %s segments \"4 points in line\".%n", fast.numberOfSegments());
        for (LineSegment ls : fast.segments())
            StdOut.println(ls);
        StdOut.println();
    }
}
