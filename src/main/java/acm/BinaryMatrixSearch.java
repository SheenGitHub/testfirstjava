package acm;

/**
 * Created by Administrator on 2016/11/30.
 */
public class BinaryMatrixSearch {
    static int[][] input;

    public static void main(String[] args) {
        input = new int[][]{
            {1, 3, 4, 6, 8, 9,13,15},
            {2,6, 7, 7, 9,12,14,16},
            {5,8, 9,10,11,17,18,20},
            {6,7,13,14,14,18,20,23},
            {7,10,13,15,19,21,24,25}
        };
        searchFromMatrix(input,13);

    }

    public static void searchFromMatrix(int[][] mat, int val) {
        boolean flag =false;
        int r =mat.length;
        int c = mat[0].length;
        for(int i =0,j =c-1;i<r&&j>=0;) {
            if(val == mat[i][j]){
                System.out.println("Find "+val +" int the matrix["+i+"]["+j+"]");
                return;
            }else if (val<mat[i][j])
                j--;
            else
                i++;
        }
        System.out.println("Can not find " + val + " in the matrix");
    }
}
