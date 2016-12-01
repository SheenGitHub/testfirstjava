package acm;

/**
 * Created by Administrator on 2016/11/30.
 */
public class NumberOfIsland {
    public static void main(String[] args) {
        char[][] g = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        int n = numIslands(new char[0][0]);
        System.out.println(n);
    }
    
    public static int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        int ans = 0;
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(grid[i][j]=='1'){
                    ans++;
                    dsf(grid,i,j);
                }

            }
        }
        return ans;
    }

    private static void dsf(char[][] grid, int i, int j) {
        int r = grid.length;
        int c = grid[0].length;
        if(i<0||i==r||j<0||j==c) return;
        if (grid[i][j] == '1') {
            grid[i][j]='2';
            dsf(grid,i-1,j);
            dsf(grid,i+1,j);
            dsf(grid,i,j-1);
            dsf(grid,i,j+1);
        }
    }
}
