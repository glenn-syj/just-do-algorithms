package APS.BOJ.드래곤커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int result = 0;
    static int N, x, y, d, g;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            dragon(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    public static void dragon(int x, int y, int d, int g) {
        List<Integer> direction = new ArrayList<>();
        direction.add(d);

        for (int i = 1; i <= g ; i++) {
            for (int j = direction.size() - 1; j >= 0 ; j--) {
                direction.add((direction.get(j) + 1) % 4);
            }
        }

        map[x][y] = true;
        for (Integer dir : direction) {
            x += dx[dir];
            y += dy[dir];
            map[x][y] = true;
        }
    }
}
