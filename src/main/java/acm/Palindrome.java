package acm;

import java.util.*;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Palindrome implements Problem {
    public Palindrome(String s) {
    }

    @Override
    public void solve() {
        output(partition("aab"));
//            m = new boolean[length][length];
//            for(int i = 0;i<length;i++){
//                m[i][i]=true;
//            }
//            for(int l = 1;l<length;l++) {
//                for(int i = 0;i<length-l;i++){
//                    int j = i+l;
//                    if(givenString.charAt(i)==givenString.charAt(j) &&(m[i+1][j-1]==true||l==1))
//                        m[i][j]=true;
//                }
//
//            }
//        dfs(0,length);


    }

    public List<List<String>> partition(String s) {

        LinkedList<String> stack = new LinkedList<>();
        List<List<String>> r = new ArrayList<>();
        int length = s.length();

        boolean[][] m = new boolean[length][length];

        for(int i = 0;i<length;i++){
            m[i][i]=true;
        }
        for(int l = 1;l<length;l++) {
            for(int i = 0;i<length-l;i++){
                int j = i+l;
                if(s.charAt(i)==s.charAt(j) &&(m[i+1][j-1]==true||l==1))
                    m[i][j]=true;
            }

        }
        dfs(0,length,s,m,stack,r);
        return r;
    }

    private void dfs(int dept, int limit, String str,boolean[][] m,LinkedList<String> stack, List<List<String>> r) {
        if(dept == limit){
            List<String> st = new ArrayList<>();
            for(String item : stack)
                st.add(item);
            r.add(st);
            return;
        }
        for(int i = dept;i<limit;i++){
            if(m[dept][i]==true){
                stack.add(str.substring(dept,i+1));
                dfs(i+1,limit,str, m,stack,r);
                stack.removeLast();
            }
        }
    }



    @Override
    public void printResult() {




    }

    private void output(List<List<String>> r) {
        if (r.size() == 0) {
            System.out.println("[]");
            return;
        }
        System.out.println("[");
        for (List<String> list : r) {
            System.out.print("    ");
            System.out.println(list.size());
            for(String item:list){
                System.out.print(item+" ");
            }
            System.out.println();
        }
        System.out.println("]");
    }
}
