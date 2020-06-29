// TC: O(N!)
// SC: O(N^2)
// where N is the number of queens to be placed on the board

import java.util.*;

public class LC51 {
    boolean grid[][];
    List<List<String>> res;

    private boolean isSafe(int row, int col, int n) {
        // above
        for (int i = row; i >= 0; i--) {
            if (grid[i][col])
                return false;
        }

        // upper left diagnol
        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (grid[i][j])
                return false;
            i--;
            j--;
        }

        // upper right diagnol
        i = row;
        j = col;
        while (i >= 0 && j < n) {
            if (grid[i][j])
                return false;
            i--;
            j++;
        }

        return true;
    }

    private void recurse(int row, int n) {
        // BC
        if (row == n) {
            List<String> r = new ArrayList<>();
            for (boolean gridRow[] : grid) {
                StringBuffer sb = new StringBuffer();
                for (boolean flag : gridRow) {
                    sb.append(flag ? "Q" : ".");
                }
                r.add(sb.toString());
            }
            res.add(r);
            return;
        }

        // Logic
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, n)) {
                grid[row][col] = true;
                recurse(row + 1, n);
                grid[row][col] = false;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        if (n == 0)
            return new ArrayList<>();
        grid = new boolean[n][n];
        res = new ArrayList<>();
        recurse(0, n);
        return res;
    }
}