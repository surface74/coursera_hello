/* *****************************************************************************
 *  Name:              Siarhei Muliarenka
 *  Coursera User ID:  123456
 *  Last modified:     March 6, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // states of the grid's sites:
    // false - site is blocked, true - site is open (full or not)
    private boolean[][] grid;
    private int gridSize;
    private int openSites = 0;

    private WeightedQuickUnionUF uF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException();
        gridSize = n;
        grid = new boolean[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                grid[i][j] = false;
        initUF(gridSize);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        StdOut.printf("Before open site %s%n", percolates());
        StdOut.printf("(%d,%d) ", row, col);

        if (row < 1 || row > gridSize || col < 1 || col > gridSize)
            throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSites++;

            StdOut.printf("is opened.%n");
            printGrid();
            StdOut.printf("Sets before: %d%n", uF.count());
            printMap();
            lookAround(row, col);
            StdOut.printf("Sets after: %d%n", uF.count());
            printMap();
        }
        else {
            StdOut.printf("already opened.%n");
        }
        StdOut.printf("After open site %s%n", percolates());
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
        return uF.find(0) == uF.find(gridSize + gridSize * gridSize);
    }

    // number of sets
    public int count() {
        return uF.count();
    }

    public void printMap() {
        for (int i = 0; i < gridSize * (2 + gridSize); i++) {
            StdOut.printf("%2d ", uF.find(i));
            if (i > 0 && (i + 1) % gridSize == 0)
                StdOut.println();
        }
        StdOut.println();
    }

    public void printGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++)
                StdOut.printf("%d ", (grid[i][j]) ? 1 : 0);
            StdOut.println();
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    private void initUF(int size) {
        uF = new WeightedQuickUnionUF(size * (2 + size));
        for (int i = 1; i < size; i++) {
            // virtual row above the top row of grid
            uF.union(0, i);
            // virtual row under the bottom row of grid
            uF.union(size + size * size, size + size * size + i);
        }
        StdOut.printf("Sets: %d%n", uF.count());
        printMap();
    }

    // returns corresponding index from a grid's row and col
    private int getUfIndex(int row, int col) {
        return gridSize * row + col - 1;
    }

    // looks around for a open site
    private void lookAround(int row, int col) {
        if (row == gridSize) // union a site at the bottom line
            uF.union(getUfIndex(row, col), gridSize + gridSize * gridSize);
        if (row == 1)
            uF.union(0, getUfIndex(row, col));

        // top unit's index
        if (row > 1 && isOpen(row - 1, col))
            uF.union(getUfIndex(row, col), getUfIndex(row - 1, col));

        // left unit's index
        if (col > 1 && isOpen(row, col - 1))
            uF.union(getUfIndex(row, col), getUfIndex(row, col - 1));

        // right unit's index
        if (col < gridSize && isOpen(row, col + 1))
            uF.union(getUfIndex(row, col), getUfIndex(row, col + 1));

        // bottom unit's index
        if (row < gridSize && isOpen(row + 1, col))
            uF.union(getUfIndex(row, col), getUfIndex(row + 1, col));
    }
}
