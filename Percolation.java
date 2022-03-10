/* *****************************************************************************
 *  Name:              Siarhei Muliarenka
 *  Coursera User ID:  123456
 *  Last modified:     March 6, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // states of the grid's sites:
    // false - site is blocked, true - site is open (full or not)
    private boolean[][] grid;
    private final int gridSize;
    private int openSites = 0;

    private WeightedQuickUnionUF uF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException();
        gridSize = n;
        grid = new boolean[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = false;
            }
        initUF(gridSize);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize)
            throw new IllegalArgumentException();
        grid[row - 1][col - 1] = true;
        openSites++;
        lookAround(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize)
            throw new IllegalArgumentException();
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize)
            throw new IllegalArgumentException();
        return uF.find(getUfIndex(row, col)) == uF.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < gridSize; i++) {
            if (grid[gridSize - 1][i] && isFull(gridSize, i + 1))
                // if (isFull(gridSize, i + 1))
                return true;
        }
        return false;
    }

    // private void printIsTrue() {
    //     StdOut.println("isFull state:");
    //     int total = 0;
    //     for (int i = 0; i < gridSize; i++) {
    //         for (int j = 0; j < gridSize; j++) {
    //             StdOut.printf("%2s ", (isFull(i + 1, j + 1)) ? "T" : "F");
    //             if (isFull(i + 1, j + 1))
    //                 total++;
    //         }
    //         StdOut.println();
    //     }
    //     StdOut.println(total);
    // }

    // private void printMap() {
    //     for (int i = 0; i < gridSize * (2 + gridSize); i++) {
    //         StdOut.printf("%2d ", uF.find(i));
    //         if (i > 0 && (i + 1) % gridSize == 0)
    //             StdOut.println();
    //     }
    // }

    // private void printGrid() {
    //     StdOut.println("isOpen state:");
    //     int total = 0;
    //     for (int i = 0; i < gridSize; i++) {
    //         for (int j = 0; j < gridSize; j++) {
    //             StdOut.printf("%2d ", (grid[i][j]) ? 1 : 0);
    //             if (grid[i][j])
    //                 total++;
    //         }
    //         StdOut.println();
    //     }
    //     StdOut.println(total);
    // }

    // // test client (optional)
    // public static void main(String[] args) {
    //
    // }

    private void initUF(int size) {
        uF = new WeightedQuickUnionUF(size * (2 + size));
        for (int i = 1; i < size; i++) {
            // virtual row above the top row of grid
            uF.union(0, i);
            // virtual row under the bottom row of grid
            uF.union(size + size * size, size + size * size + i);
        }
    }

    // returns corresponding index from a grid's row and col
    private int getUfIndex(int row, int col) {
        return gridSize * row + col - 1;
    }

    // looks around for open sites and unions them
    private void lookAround(int row, int col) {
        if (row == 1)   // union a site at the top row
            uF.union(0, getUfIndex(row, col));

        // check the upper site
        if (row > 1 && isOpen(row - 1, col))
            uF.union(getUfIndex(row, col), getUfIndex(row - 1, col));

        // check the left site
        if (col > 1 && isOpen(row, col - 1))
            uF.union(getUfIndex(row, col), getUfIndex(row, col - 1));

        // check the right site
        if (col < gridSize && isOpen(row, col + 1))
            uF.union(getUfIndex(row, col), getUfIndex(row, col + 1));

        // check the bottom site
        if (row < gridSize && isOpen(row + 1, col))
            uF.union(getUfIndex(row, col), getUfIndex(row + 1, col));
    }
}
