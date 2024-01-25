/*
* 0. 사다리 입력받기
* 1. 도착 지점 찾기
* 2. 도착 지점부터 거꾸로 올라가기
*   2-1. 좌우로 이동
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int t = 0; t < 10; t++) {
            int testNum = sc.nextInt();
            int[][] ladder = new int[100][100];

            // 0. 사다리 입력받기
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = sc.nextInt();
                }
            }
            
            // 1. 도착 지점 찾기
            int colPoint = 0;
    
            for (int i = 0; i < 100; i++) {
                if (ladder[99][i] == 2) {
                    colPoint = i;
                    break;
                }
            }
            
            // 2. 도착 지점부터 거꾸로 올라가기
            int rowPoint = 99;

            while (rowPoint > 0) {

                if (colPoint > 0 && ladder[rowPoint][colPoint - 1] == 1) {
                    // 왼쪽으로 이동
                    colPoint--;
                    
                    while (rowPoint > 0 && ladder[rowPoint - 1][colPoint] == 0) {
                        colPoint--;
                    }
                } else if (colPoint < 99 && ladder[rowPoint][colPoint + 1] == 1) {
                    // 오른쪽으로 이동
                    colPoint++;

                    while (rowPoint > 0 && ladder[rowPoint - 1][colPoint] == 0) {
                        colPoint++;
                    }
                }
                rowPoint--;
            }
            System.out.printf("#%d %d", testNum, colPoint);
        }
    }
}