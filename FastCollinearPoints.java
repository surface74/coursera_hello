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
    private static final int MIN_POINTS_IN_LINE = 4;
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
        if (points.length < MIN_POINTS_IN_LINE)
            return;
        
        for (int i = 0; i < points.length; i++) {
            Point[] candidates = Arrays.copyOf(points, points.length);
            Arrays.sort(candidates, points[i].slopeOrder());

            Point basePoint = points[i];
            int begin = 1;
            double startSlope = basePoint.slopeTo(candidates[begin]);
            for (int j = 2; j < candidates.length; j++) {
                if (startSlope != basePoint.slopeTo(candidates[j])) {
                    int lineLength = j - begin + 1;
                    if (lineLength >= MIN_POINTS_IN_LINE) {
                        Point[] line = Arrays.copyOfRange(candidates, begin, j + 1);
                        line[line.length - 1] = basePoint;
                        Arrays.sort(line);
                        LineSegment lineSegment = new LineSegment(line[0], line[line.length - 1]);
                        addSegments(lineSegment);
                    }
                    begin = j;
                    startSlope = basePoint.slopeTo(candidates[begin]);
                }
                else if (j == candidates.length - 1) {
                    int lineLength = j - begin + 2;
                    if (lineLength >= MIN_POINTS_IN_LINE) {
                        Point[] line = Arrays.copyOfRange(candidates, begin, j + 2);
                        line[line.length - 1] = basePoint;
                        Arrays.sort(line);
                        LineSegment lineSegment = new LineSegment(line[0], line[line.length - 1]);
                        addSegments(lineSegment);
                    }
                }
            }
        }
    }

    private void addSegments(LineSegment segment) {
        for (int i = 0; i < segments.length; i++)
            if (segments[i].toString().equals(segment.toString())) {
                return;
            }

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

    // private void printArray(Point[] array) {
    //     for (Point p : array)
    //         StdOut.printf("%s; ", p);
    //     StdOut.println();
    // }
    // private void drawLabel() {
    //     for (int i = 2; i < 32; i = i + 2) {
    //         StdDraw.line(i, 0, i, 1);
    //         StdDraw.line(0, i, 1, i);
    //     }
    // }

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
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.01);
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
        // collinear.drawLabel();
        StdDraw.show();
    }
}
