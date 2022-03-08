/* *****************************************************************************
 *  Name:              Siarhei Muliarenka
 *  Coursera User ID:  123456
 *  Last modified:     March 6, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private Percolation percolation;
    private double[] thresholds;
    private final int matrixSize;
    private final int repeats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 1 || trials < 1)
            throw new IllegalArgumentException();
        repeats = trials;
        matrixSize = n;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(thresholds.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        stats.thresholds = new double[stats.repeats];
        int numberOfElements = stats.matrixSize * stats.matrixSize;
        for (int i = 0; i < stats.repeats; i++) {
            stats.percolation = new Percolation(stats.matrixSize);
            while (!stats.percolation.percolates()) {
                stats.openNextSite();
            }
            stats.thresholds[i] = stats.percolation.numberOfOpenSites() /
                    (double) numberOfElements;
        }
        StdOut.printf("%-24s= %s%n", "mean", stats.mean());
        StdOut.printf("%-24s= %s%n", "stddev", stats.stddev());
        StdOut.printf("%-24s= [%s, %s]%n", "95% confidence interval",
                      stats.confidenceLo(),
                      stats.confidenceHi());
    }

    private void openNextSite() {
        int row = StdRandom.uniform(1, matrixSize + 1);
        int col = StdRandom.uniform(1, matrixSize + 1);
        percolation.open(row, col);
    }
}
