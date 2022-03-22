/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            segments[i] = new LineSegment(points[0], points[i]);
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // // the line segments
    public LineSegment[] segments() {
        return new LineSegment[1];
    }

    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(50, 40);
        Point p2 = new Point(60, 60);
        Point p3 = new Point(80, 60);
        Point[] points = { p0, p1, p2, p3 };

        StdDraw.setScale(0, 100);
        StdDraw.setPenRadius(0.02);

        StdDraw.setPenColor(StdDraw.BLUE);
        p0.draw();
        StdDraw.setPenColor(StdDraw.RED);
        p1.draw();
        StdDraw.setPenColor(StdDraw.GREEN);
        p2.draw();
        StdDraw.setPenColor(StdDraw.GRAY);
        p3.draw();

        BruteCollinearPoints brute = new BruteCollinearPoints(points);
    }
}
