/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class LookForThree {
    public static void main(String[] args) {
        int[] sizes = new In(args[0]).readAllInts();
        for (int i = 0; i < sizes.length; i++) {
            int[] data = getArray(sizes[i]);
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(ThreeSum.count(data));
            StdOut.printf("%s: %ss%n", data.length, stopwatch.elapsedTime());
        }
    }

    private static int[] getArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniform(-50, 51);
        }
        return a;
    }
}
