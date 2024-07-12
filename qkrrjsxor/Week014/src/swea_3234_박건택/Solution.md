# 문제 정보
문제번호 : 3234
난이도: D4
분류 : dfs, 순열, 백트래킹?
url: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw

# 고찰
우선 배열을 놓을 수 있는 모든 경우의 수에 대해서 반복을 하며, 주어진 배열에서 left, right를 각각 더해나가며 right가 더 커지면 return을 하려는 생각을 하였다. 하지만 배열을 놓을 수 있는 모든 순서를 구하는 것에서 막혀서 peek 하였습니다.

# Logic
1. 배열을 나열할 수 있는 모든 경우의 수에 대해 순열을 구하고, 해당 배열에 대해 left와 right를 더해 비교하며 가능한 경우의 수를 count 한다.
2. 순열을 구하기 위해 select boolean 배열을 사용하고, 재귀를 이용한다.
3. for문으로 i= 0부터 N까지 반복하며 select 배열에서 선택을 한지 조건을 보고, 선택하지 않았다면 order 배열에 추가, cnt를 추가하고 재귀 호출을 한다.
4. N개를 모두 선택 했으면 break 하여 select 해제 하고 다음 i에 대해서 반복한다.

# What I learned
- 배열의 모든 항목을 가능한 순서대로 나열하는 순열을 구하는 방법
- 왼쪽과 오른쪽의 무게를 더해 비교하는 코드도 원래 제가 생각했던 코드보다 낫다는 것을 느꼈습니다.

# Java Code
```java
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

```
