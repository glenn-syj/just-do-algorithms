/*
* 0. test case 입력
* 1. 달팽이 만들기
*   1-1. N*N번 반복하며 숫자 채우기
*   1-2. 조건에 따라 방향 결정
* 2. 달팽이 출력하기
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        // 0. test case 입력
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int[] testCase = new int[T];

        for (int t = 0; t < T; t++) {
            testCase[t] = sc.nextInt();
        }

        // 1. 달팽이 만들기
        for (int t = 0; t < testCase.length; t++) {
            int cnt = 1;

            int[][] snail = new int[testCase[t]][testCase[t]];
            int[][] visited = new int[testCase[t]][testCase[t]];    // 지나간 자리는 1로 채워서 재방문 하지 않기

            int r = 0;
            int c = 0;
            int[] dr = {0, 1, 0, -1};   // right -> down -> left -> up
            int[] dc = {1, 0, -1, 0};   
            int direction = 0;  // right -> down -> left -> up이 반복될 수 있도록 하는 조건

            // 1-1. N*N번 반복하며 숫자 채우기
            for (int i = 0; i < testCase[t] * testCase[t]; i++) {
                snail[r][c] = cnt++;
                visited[r][c] = 1;

                int nr = r + dr[direction];
                int nc = c + dc[direction];

                // 1-2. 조건에 따라 방향 설정
                 // 한번도 방문하지 않았고, 경계조건을 만족하면 숫자를 채움
                if (nr >= 0 && nr < testCase[t] && nc >= 0 && nc < testCase[t] && visited[nr][nc] == 0) {  
                    r = nr;
                    c = nc;
                } else {
                    // 위 조건을 만족하지 않는다면 방향 변경
                    direction = (direction + 1) % 4;    
                    r += dr[direction];
                    c += dc[direction];
                }
            }

            // 3. 달팽이 출력하기
            System.out.println("#" + (t+1));
            for (int i = 0; i < testCase[t]; i++) {
                for (int j = 0; j < testCase[t]; j++) {
                    System.out.print(snail[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}