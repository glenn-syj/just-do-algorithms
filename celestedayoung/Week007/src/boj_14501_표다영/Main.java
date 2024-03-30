package APS.BOJ.퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int result = 0;
    static int[][] schedule;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        schedule = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(result);

    }

    public static void dfs(int idx, int money) {
        if(idx >= N) {
            result = Math.max(money, result);
            return;
        }

        if(idx + schedule[idx][0] <= N) {
            dfs(idx + schedule[idx][0], money + schedule[idx][1]);
        } else {
            dfs(idx + schedule[idx][0], money);
        }

        dfs(idx + 1, money);
    }

}
