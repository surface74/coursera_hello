/* *****************************************************************************
 *  Name:              Siarhei Muliarenka
 *  Coursera User ID:  123456
 *  Last modified:     March 6, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation percolation;
    private double[] thresholds;
    private int matrixSize;
    private int repeats;

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
        return mean() - (1.96 * stddev()) / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(thresholds.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        for (int i = 0; i < stats.repeats; i++) {
            stats.percolation = new Percolation(stats.matrixSize);
            int watchdog = stats.matrixSize * stats.matrixSize * 2;
            while (!stats.percolation.percolates() && watchdog > 0) {
                int row = getRandom(1, stats.matrixSize);
                int col = getRandom(1, stats.matrixSize);

                stats.percolation.open(row, col);
                watchdog--;
            }
            int quantity = stats.matrixSize * stats.matrixSize;
            StdOut.printf("Threshold (%d/%d): ",
                          stats.percolation.numberOfOpenSites(),
                          quantity);
            StdOut.println(stats.percolation.numberOfOpenSites() / (double) quantity);
            StdOut.printf("Watchdog: %d%n", watchdog);
        }

        StdOut.printf("Sets: %d", stats.percolation.count());
    }

    private static int getRandom(int min, int max) {
        return (int) Math.floor(min + Math.random() * (max - min + 1));
    }
}
