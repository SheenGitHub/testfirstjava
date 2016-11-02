package acm;

/**
 * Created by Administrator on 2016/9/23.
 */
public class LCS {
    public int[][] c;
    public char[][] d;
    char[] a, b;
    int m, n;

//    public LCS() {
//        this({'A','B','C','D','B','D','A','B'},{'B','D','C','A','B','A'});
//    }
    public LCS(char[] a, char[] b) {
        this.a = a;
        this.b = b;
        this.m = a.length;
        this.n = b.length;
    }

    public void solve(){
        c = new int[m+1][n+1];
        d = new char[m+1][n+1];
        for(int i = 0; i<=m;i++)
            c[i][0]=0;
        for(int j = 0;j<=n;j++)
            c[0][j]=0;

        for(int i = 1; i <=m;i++)
            for(int j = 1; j<=n;j++){
                if(a[i-1]==b[j-1]){
                    c[i][j]=c[i-1][j-1]+1;
                    d[i][j]='f';
                }else if(c[i-1][j]<c[i][j-1]){
                    c[i][j]=c[i][j-1];
                    d[i][j]='l';
                }else {
                    c[i][j]=c[i-1][j];
                    d[i][j]='u';
                }
            }
    }

    public void printResult() {
        System.out.println(m+","+n);
        printLCS(d,a,m,n);
    }
    public void printLCS(char[][] d,char[] a,int i, int j){
        if(i==0||j==0)
            return;
        if(d[i][j]=='f'){
            printLCS(d,a,i-1,j-1);
            System.out.print(" "+a[i-1]);
        }else if(d[i][j]=='u')
            printLCS(d,a,i-1,j);
        else
            printLCS(d,a,i,j-1);
    }
}
