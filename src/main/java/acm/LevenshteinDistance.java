package acm;

/**
 * Created by Administrator on 2016/11/29.
 */
public class LevenshteinDistance {

    static int[][] dist;
    static int[][] oper;
    static int  noneoper = 0, substitution = 1,insertation = 2,deletion = 3;
    public static void main(String[] args) {
        int distance = editDistance("item12","item32");
        System.out.println(distance);
        backtrace();
    }

    /**
     *
     * @param a
     * @param b
     * @return
     *
     */
    static int editDistance(String a, String b) {
        int al = a.length();
        int bl = b.length();

        dist = new int[al+1][bl+1];
        oper = new int[al+1][bl+1];

        for (int i = 0; i <= al; i++) {
            dist[i][0] = i;
            oper[i][0] = deletion;
        }

        for (int i = 0; i <=bl; i++) {
            dist[0][i]=i;
            oper[0][i]=i;
        }

        for (int i = 1; i <=al; i++) {
            for (int j = 1; j <= bl; j++) {
                int cost = a.charAt(i-1) == b.charAt(j-1)?0:1;

                    int iop = dist[i][j-1]+1;
                    int dop = dist[i-1][j]+1;
                    int sop = dist[i-1][j-1]+cost;

                if (iop > dop) {
                    if (dop > sop) {
                        dist[i][j] = sop;
                        oper[i][j] = cost;
                    } else {
                        dist[i][j]=dop;
                        oper[i][j]=deletion;
                    }
                }else {
                    if (iop > sop) {
                        dist[i][j] = sop;
                        oper[i][j] = cost;
                    } else {
                        dist[i][j] = iop;
                        oper[i][j]=insertation;
                    }
                }
            }
        }
        
        return dist[al][bl];
    }

    static void backtrace() {
        int al = oper.length;
        int bl = oper[0].length;
        int i ,j;
        for (i = al-1,j=bl-1;i>=0&&j>=0;) {
            switch (oper[i][j]) {
                case 0:
                    System.out.println("no poperation:"+i+","+j);
                    i--;
                    j--;
                    break;
                case 1:
                    System.out.println("sub operation:"+i+","+j);
                    i--;
                    j--;
                    break;
                case 2:
                    System.out.println("insert operation:"+i+","+j);
                    j--;
                    break;
                case 3:
                    i--;
                    System.out.println("delete operation:"+i+","+j);
                    break;
                default:break;
            }
        }
    }


}
