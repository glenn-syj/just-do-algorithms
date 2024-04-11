package swea_2105_박건택;
/*
 * 모의고사 때에는 for문을 이용하여 탐색을 했었는데, 재귀를 이용해서 풀어보자
 * 
 * 표다영님의 코드를 참고했습니다
 */
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] dessert;
	static int[] dr = { 1, 1, -1, -1 }; // 우하 좌하 좌상 우상
    static int[] dc = { 1, -1, -1, 1 };
    static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;

		int tc = Integer.parseInt(sc.nextLine());

		for (int test = 1; test <= tc; test++) {
			N = Integer.parseInt(sc.nextLine());

			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(sc.nextLine());
				for(int t = 0; t < N; t++) {
					map[i][t] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 0;
			for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    visited = new boolean[N][N]; // 초기화 위치 주의
                    dessert = new boolean[101]; // 디저트 번호 1~100
 
                    visited[i][j] = true;
                    dessert[map[i][j]] = true;
 
                    // 각 시작점부터 dfs 한다
                    dfs(1, i, j, i, j, 0);
                }
            }
 
            if (max == 0) {
                max = -1; // 투어 불가한 경우
            }
 
            System.out.printf("#%d %d\n", test, max);
			
		}
	}
	
	public static void dfs(int count, int r, int c, int startR, int startC, int d) {
		for (int i = d; i < 4; i++) { // 델타배열 방향으로 dfs
            int nr = r + dr[i];
            int nc = c + dc[i];
 
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                // 경계조건
                if ((nr == startR) && (nc == startC) && count > 2) {
                    // 시작점으로 왔으면 종료
                    if (max < count) {
                        max = count;
                    }
                    return;
                }
 
                if (!visited[nr][nc] && !dessert[map[nr][nc]]) {
                    // 방문하지 않았고 안 먹었으면
                    visited[nr][nc] = true;
                    dessert[map[nr][nc]] = true;
 
                    dfs(count + 1, nr, nc, startR, startC, i);
 
                    // 다시 안간척
                    visited[nr][nc] = false;
                    dessert[map[nr][nc]] = false;
                }
 
            }
 
        }
        
        
	}
}
