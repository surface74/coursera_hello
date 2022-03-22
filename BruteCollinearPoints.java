/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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
                            // for (Point p : line)
                            //     System.out.printf("%s", p);
                            System.out.println(lineSegment);
                        }
                    }
    }


    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // // the line segments
    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args) {
        Point p0 = new Point(60, 30);
        Point p1 = new Point(20, 10);
        Point p2 = new Point(40, 10);
        Point p3 = new Point(70, 10);
        Point p4 = new Point(10, 10);
        Point p5 = new Point(0, 10);
        Point[] points = { p0, p1, p2, p3, p4, p5 };

        // StdDraw.setScale(0, 100);
        // StdDraw.setPenRadius(0.02);
        //
        // StdDraw.setPenColor(StdDraw.BLUE);
        // p0.draw();
        // StdDraw.setPenColor(StdDraw.RED);
        // p1.draw();
        // StdDraw.setPenColor(StdDraw.GREEN);
        // p2.draw();
        // StdDraw.setPenColor(StdDraw.GRAY);
        // p3.draw();

        BruteCollinearPoints brute = new BruteCollinearPoints(points);
    }
}
