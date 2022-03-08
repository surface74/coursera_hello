/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Date;

public class Euclid {
    public static void main(String[] args) {
        Date a = new Date(12, 31, 2011);

        Date b = new Date(1, 1, 2012);
        b = a;
        System.out.printf("%s %s", b, a);
        // throw new RuntimeException("Error message here.");
    }

    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    private static double sqrt(double c) {
        if (c < 0) return Double.NaN;
        double err = 1.0e-15;
        double t = c;
        while (Math.abs(t - c / t) > err * t) {
            t = (c / t + t) / 2.0;
            System.out.println(t);
        }
        return t;
    }
}
