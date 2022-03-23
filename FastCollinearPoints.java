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

        for (int i = 0; i < points.length - 3; i++) {
            Point[] candidates = Arrays.copyOfRange(points, i + 1, points.length);
            printArray(candidates);
            Arrays.sort(candidates, points[i].slopeOrder());

            Point basePoint = points[i];
            int begin = 0;
            double startSlope = basePoint.slopeTo(candidates[0]);

            for (int j = 1; j < candidates.length; j++) {
                if (startSlope != basePoint.slopeTo(candidates[j])) {
                    if (j - begin >= 3) {
                        Point[] line = Arrays.copyOfRange(candidates, begin, j + 1);
                        StdOut.println("Before sort:");
                        printArray(line);
                        line[line.length - 1] = basePoint;
                        Arrays.sort(line);
                        StdOut.println("After sort:");
                        printArray(line);
                        StdOut.println();
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

        LineSegment[] newSegments = new LineSegment[segments.length + 1];
        for (int i = 0; i < segments.length; i++) {
            newSegments[i] = segments[i];
        }
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
        Point[] points = {
                new Point(60, 30), new Point(20, 10), new Point(40, 10),
                new Point(70, 10), new Point(10, 10), new Point(0, 10),
                new Point(10, 70), new Point(20, 20), new Point(30, 30),
                new Point(60, 60)
        };

        StdOut.printf("Set of points:%n");
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
