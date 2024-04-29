package boj_2252_유서현;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 인접 리스트
        ArrayList<Integer>[] adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        int[] degree = new int[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList[A].add(B); // A에서 B로 가는 간선 추가
            degree[B]++; // B의 진입차수 증가
        }

        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 정점 찾기
        for (int i = 1; i <= V; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sb.append(curr).append(" ");

            // 현재 정점에서 출발하는 모든 간선에 대해
            for (int next : adjList[curr]) {
                degree[next]--; // 도착 정점의 진입 차수 감소

                if (degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        System.out.println(sb.toString());
    }
}