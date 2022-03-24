/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 21, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // check arguments
        if (points == null)
            throw new IllegalArgumentException();
        if (points[0] == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < points.length - 3; i++)
            for (int j = i + 1; j < points.length - 2; j++)
                for (int k = j + 1; k < points.length - 1; k++)
                    for (int h = k + 1; h < points.length; h++) {
                        double slope = points[i].slopeTo(points[j]);
                        if (slope == points[i].slopeTo(points[k]) &&
                                slope == points[i].slopeTo(points[j]) &&
                                slope == points[i].slopeTo(points[h])) {
                            Point[] line = { points[i], points[j], points[k], points[h] };
                            Arrays.sort(line);

                            LineSegment lineSegment = new LineSegment(line[0], line[3]);
                            addSegments(lineSegment);
                        }
                    }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    private void addSegments(LineSegment segment) {
        for (int i = 0; i < segments.length; i++)
            if (segments[i].toString().equals(segment.toString()))
                return;

        LineSegment[] newSegments = Arrays.copyOf(segments, segments.length + 1);
        newSegments[newSegments.length - 1] = segment;
        segments = newSegments;
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
        StdDraw.setPenRadius(0.02);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        StdDraw.setPenRadius();
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    // public static void main(String[] args) {
    //     Point[] points = {
    //             new Point(60, 30), new Point(20, 10), new Point(40, 10),
    //             new Point(70, 10), new Point(10, 10), new Point(0, 10),
    //             new Point(10, 70), new Point(20, 20), new Point(30, 30),
    //             new Point(60, 60)
    //     };
    //
    //     StdOut.print("Set of points: ");
    //     for (Point p : points)
    //         StdOut.printf("%s; ", p);
    //     StdOut.println();
    //
    //     BruteCollinearPoints brute = new BruteCollinearPoints(points);
    //     StdOut.printf("contains %s segments \"4 points in line\".%n", brute.numberOfSegments());
    //     for (LineSegment ls : brute.segments())
    //         StdOut.println(ls);
    //     StdOut.println();
    // }
}
