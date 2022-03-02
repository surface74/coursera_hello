/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 1, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int counter = 0;
        String champoin = "";
        while (!StdIn.isEmpty()) {
            String candidate = StdIn.readString();
            if (StdRandom.bernoulli((double) 1 / ++counter)) {
                champoin = candidate;
            }
        }
        StdOut.println(champoin);
    }
}
