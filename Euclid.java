/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Euclid {
    public static void main(String[] args) {
        double result = sqrt(Double.parseDouble(args[0]));
        System.out.printf("SQRT(%s) = %f (%f)",
                          args[0],
                          result,
                          result * result);
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
