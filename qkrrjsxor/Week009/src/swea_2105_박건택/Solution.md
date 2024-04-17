# 문제 정보
난이도: 모의 역량평가
분류 : 구현, 백트래킹(?)
url: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu

# 고찰
처음 문제를 접하였을 때, 반복적인 행위를 반복하고 특정 조건일 때 빠져나가야 하는 백트래킹 문제라 생각하고 접근을 하였다. 하지만 방향 전환을 총 3번 하게 되는데, 어떠한 조건일 때 방향을 바꾸고 return을 하여야 하는지 생각이 나지 않았다. 결국 방향마다 for문을 반복하여 풀어서 제출하였지만, 재귀적인 방법을 이용하여 푸는 방법을 타인의 코드를 참고하여 풀어보았다.

# Logic
1. 출발할 수 있는 범위가 0 <= r < N-2, 0 <= c < N-1 까지이므로 해당 범위에서 출발을 반복 한다.
2. dfs로 재귀 호출을 하며 방향을 바꾼다.
	a. 재귀함수 내부에서 for문을 이용해 방향 전환을 한다.
	b. 경계조건을 만족하는지 확인한다.
	c. 시작 지점으로 돌아왔다면 max값을 갱신 후 return 
	d. 방문하지 않은 칸이거나, 같은 종류의 디저트를 먹지 않았다면 dfs 호출을 한다.


# What I learned
- 재귀 함수를 이용 할 때, 맨 처음에 base case 없이 for문으로 바로 들어가며 방향을 바꾸는 방법에 대해 생각하지 못하였는데, 새로운 방식을 터득할 수 있었다.
- 재귀 호출 부분에서, visit 처리를 한 후 재귀를 호출하고, return 시 다시 visit 배열을 초기화 하는 방법을 익힐 수 있었다.

# Java Code
```java
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


```