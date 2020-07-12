import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private final double[] nPercolates;
    private static final double c = 1.96f;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        nPercolates = new double[trials];
        for (int t = 0; t < trials; t++) {
            Percolation perc = new Percolation(n);
            int[] arr = new int[n * n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i * n + j] = i * n + j;
                }
            }
            int ptr = n * n - 1;
            while (ptr >= 0) {
                int choose = StdRandom.uniform(ptr + 1);
                int r = arr[choose] / n;
                int c = arr[choose] % n;
                perc.open(r + 1, c + 1);
                if (perc.percolates()) {
                    nPercolates[t] = (double) perc.numberOfOpenSites() / (n * n);
                    break;
                } else {
                    arr[choose] = arr[choose] + arr[ptr];
                    arr[ptr] = arr[choose] - arr[ptr];
                    arr[choose] = arr[choose] - arr[ptr];
                    ptr -= 1;
                }
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(nPercolates);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(nPercolates);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - c * stddev() / Math.sqrt(nPercolates.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + c * stddev() / Math.sqrt(nPercolates.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int ptr = 9;
        while (ptr >= 0) {
            int choose = StdRandom.uniform(ptr + 1);
            System.out.print(arr[choose] + ", ");
            arr[choose] = arr[choose] + arr[ptr];
            arr[ptr] = arr[choose] - arr[ptr];
            arr[choose] = arr[choose] - arr[ptr];
            ptr -= 1;
        }
        System.out.println();
        PercolationStats ps = new PercolationStats(200, 1000);
        System.out.println(ps.mean() + " " + ps.stddev() + " " + ps.confidenceLo() + " " + ps.confidenceHi());
    }

}