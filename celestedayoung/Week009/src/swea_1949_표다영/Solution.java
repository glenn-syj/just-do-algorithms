package aps;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    static Scanner sc;
    static StringTokenizer st;
    static int testNum, N, K, maxHeight, maxLength;
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
            maxHeight = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(sc.nextLine());
                for (int c = 0; c < N; c++) {
                    mountain[r][c] = Integer.parseInt(st.nextToken());
                    if (maxHeight < mountain[r][c]) {
                        maxHeight = mountain[r][c];
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
                if (mountain[r][c] == maxHeight) {
                    visited[r][c] = true;
                    dfs(r, c, maxHeight, 1, false);
                    visited[r][c] = false;
                }
            }
        }
    }

    public static void dfs(int r, int c, int height, int length, boolean check) {

        for (int d = 0; d < 4; d++) {
            
            maxLength = maxLength > length ? maxLength : length;

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {

                if (height <= mountain[nr][nc]) {
                    if (!check) {
                        if (height > mountain[nr][nc] - K) {
                            visited[nr][nc] = true;
                            dfs(nr, nc, height - 1, length + 1, true);
                            visited[nr][nc] = false;
                        }
                    }
                } else {
                    visited[nr][nc] = true;
                    dfs(nr, nc, mountain[nr][nc], length + 1, check);
                    visited[nr][nc] = false;
                }
            }

        }
    }
}
