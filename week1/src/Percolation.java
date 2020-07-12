import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size; // size of created grid
    private final boolean[] status; // record the status of each site
    private final WeightedQuickUnionUF grid;
    private final WeightedQuickUnionUF gridNoVBottom;
    private int numSteps;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid size out of range");
        size = n;
        status = new boolean[n * n];
        grid = new WeightedQuickUnionUF(n * n + 2);
        gridNoVBottom = new WeightedQuickUnionUF(n * n + 1);
        numSteps = 0;
    }

    public void open(int row, int col) {
        validate(row, col);
        int idx = xyTo1D(row, col);
        if (!status[idx]) {
            status[idx] = true;
            numSteps += 1;
            if (row == 1) { // connect to the virtual site
                grid.union(idx, size * size);
                gridNoVBottom.union(idx, size * size);
            }
            if (row == size) grid.union(idx, size * size + 1);

            int[] xDiff = {-1, 1, 0, 0};
            int[] yDiff = {0, 0, -1, 1};

            int numDir = 4;
            for (int i = 0; i < numDir; i++) {
                int adjX = row + xDiff[i];
                int adjY = col + yDiff[i];

                if (adjX > 0 && adjX <= size && adjY > 0 && adjY <= size) {
                    int adjPosIdx = xyTo1D(adjX, adjY);
                    if (status[adjPosIdx]) {
                        grid.union(idx, adjPosIdx);
                        gridNoVBottom.union(idx, adjPosIdx);
                    }
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return status[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) && (gridNoVBottom.find(xyTo1D(row, col)) == gridNoVBottom.find(size * size));
    }

    public int numberOfOpenSites() {
        return numSteps;
    }

    public boolean percolates() {
        return grid.find(size * size) == grid.find(size * size + 1);
    }

    // map 2D coordinates to 1D coordinates
    private int xyTo1D(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    // validate that (row, col) is valid
    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IndexOutOfBoundsException("index: (" + row + ", " + col + ") are out of bounds");
    }

    public static void main(String[] args) {
        // test
    }
}