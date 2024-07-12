# SWEA 1949 등산로 조성

## ✓ Difficult Point
- 산의 높이를 수정할 수 있는 경우 경로를 만드는 로직을 처리해야 한다.
- K만큼 깎을 수 있다 하더라도 K만큼 반복문을 돌 필요가 없다.
- dfs가 제약 조건을 고려하여 최장 경로를 올바르게 찾도록 보장해야 한다.

## ✓ Used Method

- `dfs` : dfs를 이용해 최장 경로를 찾는 메서드

## ✓ Logic

1.   산의 행렬을 초기화하고 가장 높은 봉우리를 찾는다.
2.   각 최고 지점에 대해 최장 경로를 찾기 위해 DFS를 수행한다.
3.   DFS를 진행하며 max 값을 갱신한다.

## ✓ Code

```java
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class Solution {
 
    static Scanner sc;
    static StringTokenizer st;
    static int testNum, N, K, top, maxLength;
    static int[][] mountain;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
 
    public static void main(String[] args) {
 
        sc = new Scanner(System.in);
        testNum = sc.nextInt();
        sc.nextLine();
 
        for (int t = 1; t <= testNum ; t++) {
 
            st = new StringTokenizer(sc.nextLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            mountain = new int[N][N];
            top = 0;
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(sc.nextLine());
                for (int c = 0; c < N; c++) {
                    mountain[r][c] = Integer.parseInt(st.nextToken());
                    if (top < mountain[r][c]) {
                        top = mountain[r][c];
                    }
                }
            }
 
            visited = new boolean[N][N];
            maxLength = 0;
 
            start();
 
            System.out.printf("#%d %s%n", t, maxLength);
        }
 
    }
 
    public static void start() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (mountain[r][c] == top) {
                    visited[r][c] = true;
                    dfs(r, c, top, 1, 0);
                    visited[r][c] = false;
                }
            }
        }
    }
 
 
    public static void dfs(int r, int c, int height, int length, int cnt) {
 
        for (int d = 0; d < 4; d++) {
 
            if (maxLength < length) {
                maxLength = length;
            }
 
            int nr = r + dr[d];
            int nc = c + dc[d];
 
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
 
                if (height <= mountain[nr][nc]) {
                    if (cnt == 0) {
                        if (height > mountain[nr][nc] - K) {
                            visited[nr][nc] = true;
                            dfs(nr, nc, height - 1, length + 1, cnt + 1);
                            visited[nr][nc] = false;
                        }
                    }
                } else {
                    visited[nr][nc] = true;
                    dfs(nr, nc, mountain[nr][nc], length + 1, cnt);
                    visited[nr][nc] = false;
                }
            }
 
        }
    }
}

```
