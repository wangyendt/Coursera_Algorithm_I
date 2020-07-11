package union_find;

public class Test {
    public static void main(String[] args) {
        int n = 10;
        QuickFindUF uf1 = new QuickFindUF(n);
        QuickUnionUF uf2 = new QuickUnionUF(n);
        WeightedQuickUnionPathCompressionUF uf3 = new WeightedQuickUnionPathCompressionUF(n);
        // 0-1-3-7, 2, 4-6-9, 8
        int[][] union_arr = new int[][]{
                {0, 1}, {1, 3}, {4, 6}, {7, 3}, {6, 9}
        };
        // T,F,F,F,F,F,T,F
        int[][] find_query_arr = new int[][]{
                {0, 7}, {2, 8}, {2, 8}, {9, 3}, {3, 9}, {9, 3}, {5, 5}, {0, 9}
        };
        for (int[] ua : union_arr) {
            uf1.union(ua[0], ua[1]);
            uf2.union(ua[0], ua[1]);
            uf3.union(ua[0], ua[1]);
        }
        for (int[] fqa : find_query_arr) {
            System.out.print(uf1.connected(fqa[0], fqa[1]) + ",");
        }
        System.out.println();
        for (int[] fqa : find_query_arr) {
            System.out.print(uf2.connected(fqa[0], fqa[1]) + ",");
        }
        System.out.println();
        for (int[] fqa : find_query_arr) {
            System.out.print(uf3.connected(fqa[0], fqa[1]) + ",");
        }
        System.out.println();
    }
}
