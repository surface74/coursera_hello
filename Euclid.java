/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Euclid {
    public static void main(String[] args) {
        System.out.printf("GCD(%s,%s) = %d",
                          args[0],
                          args[1],
                          gcd(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }

    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;

        return gcd(q, r);
    }
}
