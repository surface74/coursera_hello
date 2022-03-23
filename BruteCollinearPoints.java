/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 21, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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
        LineSegment[] newSegments = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++)
            newSegments[i] = segments[i];
        return newSegments;
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

        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        StdOut.printf("contains %s segments \"4 points in line\".%n", brute.numberOfSegments());
        for (LineSegment ls : brute.segments())
            StdOut.println(ls);
        StdOut.println();
    }
}
