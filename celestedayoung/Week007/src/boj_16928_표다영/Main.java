package APS.BOJ.뱀과사다리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, result;
    static int start = 1, end = 100;
    static boolean[] visited = new boolean[101];
    static Map<Integer, Integer> gameInfo;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gameInfo = new HashMap<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            gameInfo.put(x, y);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            gameInfo.put(x, y);
        }

        bfs();

        System.out.println(result);

    }

    static void bfs() {
        Deque<Integer> myQueue = new ArrayDeque<>();
        myQueue.add(start);
        visited[start] = true;

        while (!myQueue.isEmpty()) {
            result++;
            for (int i = 0, qSize = myQueue.size(); i < qSize; i++) {
                int now = myQueue.pollFirst();

                for (int j = 1; j <= 6; j++) {
                    int move = now + j;
                    if (move == end) return;

                    if (move > end) continue;
                    if (visited[move]) continue;

                    visited[move] = true;

                    if (gameInfo.containsKey(move)) {
                        move = gameInfo.get(move);
                    }
                    myQueue.add(move);
                }
            }
        }
    }
}
