package acm;

/**
 * Created by Administrator on 2016/9/19.
 */
public class DFS {
    public static int max= 0,sum=0;
    public static int[][] mat = {
            {0,0,1,0,0,0,0,0,1,1},
            {0,1,1,0,0,1,1,1,1,1},
            {0,1,0,0,0,1,0,0,0,0},
            {0,0,1,1,1,1,0,1,1,1},
            {0,0,1,1,1,1,0,1,1,1}
    };
    public static void main(String[] args) {
        int r = solve(mat);
        System.out.println(r);
    }
    public static int solve(int[][] grid){
        int r = grid.length;
        int c = grid[0].length;
        for(int i =0;i<c;i++)
            for(int j=0;j<r;j++){
                sum=0;
                dfs(grid,i,j);
                if(sum>max) max = sum;
            }

        return max;


    }

    public static void dfs(int[][] grid,int x, int y){
        int r = grid.length;
        int c = grid[0].length;
        if(x<0||y<0||x>=c||y>=r||grid[y][x]==0)
            return;
        else{
            sum++;
            grid[y][x]=0;
            dfs(grid,x+1,y);
            dfs(grid,x,y+1);
            dfs(grid,x-1,y);
            dfs(grid,x,y-1);
        }

    }
}
