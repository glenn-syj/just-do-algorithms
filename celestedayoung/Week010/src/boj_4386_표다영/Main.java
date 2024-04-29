import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, M;
    static List<Integer>[] adjList;
    static int[] degree;
    static boolean[] visited;
    static Deque<Integer> myStack;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for(int i = 1 ; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        degree = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            degree[end]++;
        }

        myStack = new ArrayDeque<>();

        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0 && !visited[i]) {
                dfs(i);
            }
        }

        while (!myStack.isEmpty()) {
            sb.append(myStack.pollFirst()).append(" ");
        }

        System.out.println(sb);

    }

    public static void dfs(int v) {

        visited[v] = true;

        for (int next : adjList[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        myStack.addFirst(v);
    }
}
