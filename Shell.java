/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Shell {
    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...
        while (h >= 1) { // h-sort the array.
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }
    // для n = 15, h0 = 4
    // pass 1
    //     while(h >=1)  { // h-sort the array.
    //     for (int i = 4; i < 15; i++) {
    //         for (int j = i; j >= 4 && less(a[j], a[j - h]); j -= h)
    //             exch(a, j, j - h);
    //     }
    //     h = 4 / 3 = 1;
    // }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args) {
        String inFile = args[0];
        In in = new In(inFile);
        int[] a = in.readAllInts();
        for (int s : a)
            StdOut.printf("%s ", s);
        StdOut.println();
        sort((Comparable[]) a);
        for (int s : a)
            StdOut.printf("%s ", s);
        StdOut.println();
    }
}
