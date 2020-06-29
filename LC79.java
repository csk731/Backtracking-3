// TC: O(m * n * 4^L)
// SC: O((m * n) + L) includes the recursion stack and the visited array
// where m is the number of rows, n is the number of columns and L is the length of the word

public class LC79 {
    int x[] = {0,0,-1,1};
    int y[] = {1,-1,0,0};
    private boolean recurse(char[][] board, boolean visited[][], int i, int j, int wordPtr, int m, int n, String word, int wordLen){
        // BC
        if(wordPtr == wordLen) {
            return true;
        }
        if(i<0 || j<0 || i>=m || j>=n) return false;
        if(visited[i][j]) return false;

        // Logic
        char ch = word.charAt(wordPtr);
        boolean ans = false;
        if(board[i][j]==ch){
            visited[i][j] = true;
            for(int k=0;k<4;k++){
                int newI = i+x[k];
                int newJ = j+y[k];
                ans = ans || recurse(board, visited, newI, newJ, wordPtr+1, m, n, word, wordLen);
            }
            visited[i][j] = false;
        }
        return ans;
    }
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int wordLen = word.length();
        boolean visited[][] = new boolean[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word.charAt(0)){
                    if(recurse(board, visited, i, j, 0, m, n, word, wordLen)) return true;
                }
            }
        }
        return false;
    }
}
