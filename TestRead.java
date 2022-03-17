/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestRead {
    public static void main(String[] args) {
        String[] a = In((String) args[0]).readAllStrings();
        for (String s : a)
            StdOut.println(s);
    }
}
