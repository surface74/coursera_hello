/* *****************************************************************************
 *  Name:              Siarhei Muliarenka
 *  Coursera User ID:  123456
 *  Last modified:     March 6, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 1 || trials < 1)
            throw new IllegalArgumentException();

        double[] thresholds = new double[trials];
        int numberOfElements = n * n;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                while (!percolation.isOpen(row, col))
                    percolation.open(row, col);
            }
            thresholds[i] = percolation.numberOfOpenSites() /
                    (double) numberOfElements;
        }
        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        confidenceLo = mean - (CONFIDENCE_95 * stddev) / Math.sqrt(thresholds.length);
        confidenceHi = mean + (CONFIDENCE_95 * stddev) / Math.sqrt(thresholds.length);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int attempts = Integer.parseInt(args[1]);
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats stats = new PercolationStats(n, attempts);
        double time = stopwatch.elapsedTime() / attempts;
        StdOut.printf("Mean running time: %s%n", time);
        StdOut.printf("%-24s= %s%n", "mean", stats.mean());
        StdOut.printf("%-24s= %s%n", "stddev", stats.stddev());
        StdOut.printf("%-24s= [%s, %s]%n", "95% confidence interval",
                      stats.confidenceLo(),
                      stats.confidenceHi());
    }

    // private void openNextSite() {
    //     int row = StdRandom.uniform(1, matrixSize + 1);
    //     int col = StdRandom.uniform(1, matrixSize + 1);
    //     while (!percolation.isOpen(row, col))
    //         percolation.open(row, col);
    // }
}
