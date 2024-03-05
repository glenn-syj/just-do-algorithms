package swea_1974_황민욱;

/*
 * [문제 풀이 과정]
 * 1. 횡 우선 탐색, 열 우선 탐색, 3*3 우선 탐색 진행
 * 2. 탐색 진행 시 카운트 배열을 형성하여 숫자들을 카운트
 * 3. 카운트 > 1이 되는 숫자가 있다면, 수도쿠 fail
 */

import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for(int t=1; t<=T; t++) {
            int answer = 1;
            int [][] sudoku = new int [9][9];
             
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    sudoku[i][j] = sc.nextInt();
                }
            }
             
            //3*3 탐색
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int [] arr = new int[9];
                    for(int k=0; k<3; k++) {
                        for(int l=0; l<3; l++) {
                            arr[sudoku[k+3*i][l+3*j]-1] += 1;
                             
                            if(arr[sudoku[k+3*i][l+3*j]-1]>1) {
                                answer = 0;
                                break;
                            }
                        }
                    }
                }
            }
             
            // 가로 탐색
            for(int i=0; i<9; i++) {
                int [] arr = new int [9];
                for(int j=0; j<9; j++) {
                    arr[sudoku[i][j]-1] += 1;
                    if(arr[sudoku[i][j]-1] > 1) {
                        answer=0;
                        break;
                    }
                }
            }
             
            // 세로 탐색
            for(int i=0; i<9; i++) {
                int [] arr = new int [9];
                for(int j=0; j<9; j++) {
                    arr[sudoku[j][i]-1] += 1;
                    if(arr[sudoku[j][i]-1] > 1) {
                        answer=0;
                        break;
                    }
                }
            }
             
            System.out.printf("#%d %d%n", t, answer);
        }
        sc.close();
    }
}