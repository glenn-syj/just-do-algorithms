/* 1954. 달팽이 숫자
 *
 * 0. 입력 받기
 * 1. N*N 배열에서, 경계나 이미 지나간 곳에 닿을 때마다 방향 바꾸기
 *  1-1. 방향 배열 사용
 *  1-2. 범위 벗어나면 방향 오른쪽으로 틀기
 *      -> deltas[] 인덱스 + 1
 *  1-3. 종료 조건: 세 방향 다 돌아도 갈 곳이 없을 때
 *      -> controlCount가 4 이상 일 때
 * 2. 지나갈 때마다 value++;
 */
 
 import java.util.*;
 
 public class Week000_SWEA_1954_손영준 {
      
     public static void main(String[] args) {
          
         Scanner sc = new Scanner(System.in);
         StringTokenizer st;
          
         int T = Integer.parseInt(sc.nextLine().trim());
          
         for (int tc=1; tc<=T; tc++) {
             int N = Integer.parseInt(sc.nextLine().trim());
             int[][] board = new int[N][N];
              
             int[] directionParam = {0};
             int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
              
             for (int i=0; i<N; i++) {
                 for (int j=0; j<N; j++) {
                     board[i][j] = 0;
                 }
             }
 
             // 문제 풀이를 위한 initializing
             int val = 1;
             int[] ptr = {0, 0};
             board[0][0] = 1;
             int controlCount = 0;
 
             // 현재의 row, column value를 저장하는 배열
             int[] nextPtr = new int[2];
 
             while (val < N*N) {
                 // 종료 조건
                 if (controlCount >= 4) {
                     break;
                 }
                 if (isValidNextIndex(board, ptr[0], ptr[1], directionParam)) {
                     controlCount = 0;
                     int rVal = ptr[0] + deltas[directionParam[0]][0];
                     int cVal = ptr[1] + deltas[directionParam[0]][1];
                     val++;
                     board[rVal][cVal] = val;
                     ptr[0] = rVal;
                     ptr[1] = cVal;
                 } else {
                     // 방향을 틀어야 하므로 controlCount 증가
                     controlCount++;
                     // 방향 오른쪽으로 틀기
                     directionParam[0] = (directionParam[0]+1) % 4;
                 }
             }
              
              
             System.out.printf("#%d\n", tc);
             for (int i=0; i<N; i++) {
                 for (int j=0; j<N; j++) {
                     System.out.printf("%d ", board[i][j]);
                 }
                 System.out.print("\n");
             }
              
         }
         sc.close();
     }
      
     public static boolean isValidNextIndex(int[][] board, int row, int col, int[] directionParam) {
         int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
         int dirParam = directionParam[0];
         int rVal = row + deltas[dirParam][0];
         int cVal = col + deltas[dirParam][1];
         int[] ptr = { rVal, cVal };
         if ((rVal < 0) || (rVal >= board.length) || (cVal < 0) || (cVal >= board.length) || (board[rVal][cVal] != 0)) {
             return false;
         }
         return true;
     }
 }