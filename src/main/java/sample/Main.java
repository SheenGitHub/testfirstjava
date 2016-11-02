package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try {
            if((s =reader.readLine())!=null){
                s =s.trim();
                if(s.equals("")){
                    System.out.println("Dislikes");
                    return;
                }else{
                    int l = s.length();
                    for (int i = 0; i <l ; i++)
                        if(s.charAt(i)<'A'||s.charAt(i)>'Z'){
                            System.out.println("Dislikes");
                            return;
                        }
                    for(int i=0,j=1;j<l;i++,j++)
                        if(s.charAt(i)==s.charAt(j)){
                            System.out.println("Dislikes");
                            return;
                        }
                    for(int len = 1;len<l-1;len++)
                        for(int i= 0;i<l-len;i++){
                            int j = i+len;
                            String sub = s.substring(j+1);
                            int ii =sub.indexOf(s.charAt(i));
                            int jj = sub.indexOf(s.charAt(j));
                            if(ii!=-1&&jj!=-1&&ii<jj){
                                System.out.println("Dislikes");
                                return;
                            }
                        }

                }

                System.out.println("Likes");
            }
        } catch (IOException e) {

        }
    }
}
