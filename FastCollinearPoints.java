/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 23, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

        for (int i = 0; i < points.length; i++) {
            Point[] candidates = Arrays.copyOf(points, points.length);
            Arrays.sort(candidates, points[i].slopeOrder());
            printArray(candidates);
            Point basePoint = points[i];
            int begin = 1;
            double startSlope = basePoint.slopeTo(candidates[1]);
            StdOut.printf("startSlope: %s%n", startSlope);
            for (int j = 2; j < candidates.length; j++) {
                StdOut.printf("%s-%s: %s%n", basePoint, candidates[j],
                              basePoint.slopeTo(candidates[j]));
                if (startSlope != basePoint.slopeTo(candidates[j]) ||
                        j == candidates.length - 1) {
                    if (j - begin >= 3) {
                        Point[] line = Arrays.copyOfRange(candidates, begin, j + 1);
                        line[line.length - 1] = basePoint;
                        Arrays.sort(line);
                        LineSegment lineSegment = new LineSegment(line[0], line[line.length - 1]);
                        addSegments(lineSegment);
                    }
                    begin = j;
                    startSlope = basePoint.slopeTo(candidates[j]);
                }

            }
        }
    }

    private void printArray(Point[] array) {
        for (Point p : array)
            StdOut.printf("%s; ", p);
        StdOut.println();
    }

    private void addSegments(LineSegment segment) {
        for (int i = 0; i < segments.length; i++)
            if (segments[i].toString().equals(segment.toString()))
                return;

        LineSegment[] newSegments = Arrays.copyOf(segments, segments.length + 1);
        newSegments[newSegments.length - 1] = segment;
        segments = newSegments;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
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
    //             new Point(60, 60), new Point(70, 70), new Point(20, 50)
    //     };
    //
    //     StdOut.printf("Set of points:%n");
    //     for (Point p : points)
    //         StdOut.printf("%s; ", p);
    //     StdOut.println();
    //
    //     FastCollinearPoints fast = new FastCollinearPoints(points);
    //
    //     StdOut.printf("contains %s segments \"N points in line\".%n", fast.numberOfSegments());
    //     for (LineSegment ls : fast.segments())
    //         StdOut.println(ls);
    //     StdOut.println();
    // }
}
