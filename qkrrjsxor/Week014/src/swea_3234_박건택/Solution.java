package swea_3234_박건택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution {
     
    static int N, answer;
    static int[] weight, order;
    static boolean[] select;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int testCase=1;  testCase<=T; testCase++) {
            N = Integer.parseInt(br.readLine());
             
            weight = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }
             
            order = new int[N];
            select = new boolean[N];
            answer = 0;
            perm(0);
             
            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.print(sb.toString());
    }
     
    static void perm(int cnt) {
        if (cnt == N) {
            calc(0, 0, 0);
            return;
        }
         
        for (int i=0; i<N; i++) {
            if (!select[i]) {
                order[cnt] = weight[i];
                select[i] = true;
                perm(cnt+1);
                select[i] = false;
            }
        }
    }
     
    static void calc(int cnt, int left, int right) {
        if (left < right)    return;
         
        if (cnt == N) {
            answer++;
            return;
        }
        calc(cnt+1, left, right+order[cnt]);
        calc(cnt+1, left+order[cnt], right);
    }
}