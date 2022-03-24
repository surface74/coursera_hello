/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdArrayIO;

import java.util.Arrays;

public class TestArrayCopy {
    public static void main(String[] args) {
        int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] b = Arrays.copyOfRange(a,
                                     Integer.parseInt(args[0]),
                                     Integer.parseInt(args[1]));

        StdArrayIO.print(a);
        StdArrayIO.print(b);
    }
}
