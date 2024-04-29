import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Island implements Comparable<Island> {

    int start, end, weight;
    List<int[]> coordinate = new ArrayList<>();

    Island() {}

    Island(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public void makeCoordinate(int r, int c) {
        coordinate.add(new int[]{r, c});
    }

    @Override
    public int compareTo(Island island) {
        return this.weight - island.weight;
    }

    @Override
    public String toString() {
        return "Island{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[][] map;
    static Deque<int[]> myQueue;
    static List<Island> islands;
    static List<Island> information;

    static int[] parent;

    static int[] deltaR = {-1, 1, 0, 0};
    static int[] deltaC = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
           st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        islands = new ArrayList<>();
        myQueue = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    islands.add(findIsland(r, c));
                }
            }
        }

        information = new ArrayList<>();
        for (int i = 0; i < islands.size(); i++) {
            for (int j = 0; j < islands.size(); j++) {
                if (i != j) {
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < islands.get(i).coordinate.size(); k++) {
                        loop:
                        for (int l = 0; l < islands.get(j).coordinate.size(); l++) {

                            int r1 = Math.min(islands.get(i).coordinate.get(k)[0], islands.get(j).coordinate.get(l)[0]);
                            int c1 = Math.min(islands.get(i).coordinate.get(k)[1], islands.get(j).coordinate.get(l)[1]);
                            int r2 = Math.max(islands.get(i).coordinate.get(k)[0], islands.get(j).coordinate.get(l)[0]);
                            int c2 = Math.max(islands.get(i).coordinate.get(k)[1], islands.get(j).coordinate.get(l)[1]);

                            if (r1 == r2) {
                                for (int checkC=c1+1; checkC<c2; checkC++) {
                                    if (map[r1][checkC] == -1) {
                                        continue loop;
                                    }
                                }

                                if (Math.abs(c1-c2) > 2) {
                                    min = min > Math.abs(c1 - c2) ? Math.abs(c1 - c2) : min;
                                }



                            } else if (c1 == c2) {
                                for (int checkR=r1+1; checkR<r2; checkR++) {
                                    if (map[checkR][c1] == -1) {
                                        continue loop;
                                    }
                                }

                                if (Math.abs(r1-r2) > 2) {
                                    min = min > Math.abs(r1 - r2) ? Math.abs(r1 - r2) : min;
                                }
                            }


                        }
                    }

                    if (min != Integer.MAX_VALUE) {
                        information.add(new Island(i, j, min - 1));
                    }
                }
            }
        }

        Collections.sort(information);


        parent = new int[islands.size()];
        for (int i = 0; i < islands.size(); i++) {
            makeSet(i);
        }

        int result = 0;
        int pick = 0;

        for (int i = 0; i < information.size(); i++) {

            int parent1 = information.get(i).start;
            int parent2 = information.get(i).end;

            if (information.get(i).weight < 2) {
                continue;
            }

            int repParent1 = findSet(parent1);
            int repParent2 = findSet(parent2);

            if (repParent1 != repParent2) {
                union(repParent1, repParent2);
                result += information.get(i).weight;
                pick++;
            }

            if (pick == islands.size() - 1) {
                break;
            }
        }
        if (pick != islands.size()-1) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }


    }

    static Island findIsland(int r, int c) {

        Island island = new Island();

        myQueue.addLast(new int[]{r, c});
        island.makeCoordinate(r, c);
        map[r][c] = -1;
        while (!myQueue.isEmpty()) {

            int[] current = myQueue.pollFirst();

            for (int d = 0; d < 4; d++) {
                int newR = current[0] + deltaR[d];
                int newC = current[1] + deltaC[d];

                if (rangeCheck(newR, newC) && map[newR][newC] == 1) {
                    myQueue.addLast(new int[]{newR, newC});
                    island.makeCoordinate(newR, newC);
                    map[newR][newC] = -1;
                }
            }
        }
        return island;
    }

    static boolean rangeCheck(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < M) return true;
        return false;
    }

    static void makeSet(int x) {
        parent[x] = x;
    }
    static int findSet(int x) {
        if (parent[x] != x) {
            parent[x] = findSet(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        parent[y] = x;
    }
}
