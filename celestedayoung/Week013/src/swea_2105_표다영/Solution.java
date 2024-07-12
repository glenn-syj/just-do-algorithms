import java.util.Scanner;
import java.util.StringTokenizer;
  
/*
 * [Point]
 * - 같은 숫자의 디저트는 먹을 수 없다. => 먹은 디저트를 dessert로 check
 * - 왔던 길을 되돌아 갈 수 없다. => 지나온 길을 visited로 check
 * 
 * [Logic]
 * - 가장 위부터 시작해서 시계방향으로 순회
 * - DFS를 이용하여 가능한 경로를 탐색 & 최댓값 갱신
 *   - 순회 방법을 고려하여 최소 크기의 경로를 생각했을 때 (0, 1) ~ (N-3, N-2)까지 탐색 가능 
 *  - 순회를 시작한 지점으로 돌아왔는지 확인, 돌아왔다면 해당 경로에서 먹은 디저트 개수와 최댓값을 비교해 갱신
 *  - 아직 방문하지 않았고, 아직 먹지 않은 디저트라면 탐색 가능한 경로이므로 재귀를 통해 탐색
 *  - 무조건 방향을 바꾸는 것이 아니라, 탐색이 가능하다면 계속해서 이전 방향을 유지
 * 
 * */
  
public class Solution {
      
    static Scanner sc;
    static StringTokenizer st;
      
    static int testCase, N, max;
    static int[][] map;
    static boolean[] dessert;
    static boolean[][] visited;
      
    static int[] deltaR = {1, 1, -1, -1}; 
    static int[] deltaC = {1, -1, -1, 1};
  
    public static void main(String[] args) {
          
        sc = new Scanner(System.in);
        testCase = sc.nextInt();
  
        for (int t = 1; t <= testCase; t++) {
              
            N = sc.nextInt();
            sc.nextLine();
              
            map = new int[N][N];
              
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
              
            max = 0;
              
            for (int r = 0; r < N - 2; r++) {
                for (int c = 1; c < N - 1; c++) {
                    visited = new boolean[N][N];
                    dessert = new boolean[101];
                    visited[r][c] = true;
                    dessert[map[r][c]] = true;
                    dessertTour(r, c, r, c, 1, 0);
                }
            }
  
            int result = max == 0 ? -1 : max;
              
            System.out.printf("#%d %d%n", t, result);
        }
  
    }
  
    static void dessertTour(int r, int c, int initR, int initC, int cnt, int prevDirection) {
          
        for (int d = prevDirection; d < 4; d++) {
              
            int newR = r + deltaR[d];
            int newC = c + deltaC[d];
  
            if (newR >= 0 && newR < N && newC >= 0 && newC < N) {
                  
                if (newR == initR && newC == initC && cnt > 2) {
                    max = Math.max(max, cnt);
                    return;
                }
                  
                if (!visited[newR][newC] && !dessert[map[newR][newC]]) {
                    visited[newR][newC] = true;
                    dessert[map[newR][newC]] = true;
                    dessertTour(newR, newC, initR, initC, cnt + 1, d);
                    visited[newR][newC] = false;
                    dessert[map[newR][newC]] = false;
                }
            }
        }
    }
}