
import java.util.*;

class Microbe {

    int num, direction;

    Microbe() {}

    Microbe(int num, int direction) {
        this.num = num;
        this.direction = direction;
    }

}

public class Solution {

    static Scanner sc;
    static int tc, N, M, K, r, c, num, direction;
    static Microbe[] tmp;
    static Deque<Microbe>[][] plate;

    static int[] deltaR = {-1, 1, 0, 0};
    static int[] deltaC = {0, 0, -1, 1};

    public static void main(String[] args) {

        sc = new Scanner(System.in);

        tc = sc.nextInt();

        for (int t = 1; t <= tc; t++) {

            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            plate = new Deque[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    plate[r][c] = new ArrayDeque<>();
                }
            }

            for (int i = 0; i < K; i++) {
                r = sc.nextInt();
                c = sc.nextInt();
                num = sc.nextInt();
                direction = sc.nextInt() - 1;
                plate[r][c].addLast(new Microbe(num, direction));
            }

            int answer = 0;

            experiment();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!plate[r][c].isEmpty()) {
                        while (!plate[r][c].isEmpty()) {
                            answer += plate[r][c].pollFirst().num;
                        }
                    }
                }
            }

            System.out.printf("#%d %d%n", t, answer);
        }

    }
    public static void experiment() {
        int time = 0;

        while (time < M) {
            Deque<Microbe>[][] newPlate = new Deque[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    newPlate[r][c] = new ArrayDeque<>();
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    while (!plate[r][c].isEmpty()) {
                        Microbe current = plate[r][c].pollFirst();
                        if (current.num != 0) {
                            int newR = r + deltaR[current.direction];
                            int newC = c + deltaC[current.direction];

                            if (chemicalRange(newR, newC)) {
                                if (current.direction == 0 || current.direction == 2) current.direction += 1;
                                else if (current.direction == 1 || current.direction == 3) current.direction -= 1;
                                current.num /= 2;
                            }

                            newPlate[newR][newC].addLast(current);
                        }
                    }
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!newPlate[r][c].isEmpty() && newPlate[r][c].size() > 1) {
                        newPlate[r][c].addLast(findMaxMicrobe(newPlate[r][c]));
                    }
                }
            }

            plate = newPlate;

            time++;
        }
    }

    public static Microbe findMaxMicrobe(Deque<Microbe> plate) {

        int max = 0;
        int sum = 0;
        int maxDirection = 0;

        while (!plate.isEmpty()) {
            Microbe tmp = plate.pollFirst();
            sum += tmp.num;
            if (tmp.num > max) {
                max = tmp.num;
                maxDirection = tmp.direction;
            }
        }
        return new Microbe(sum, maxDirection);
    }


    public static boolean chemicalRange(int r, int c) {

        if (r == 0 && c >= 0 && c < N) return true;
        if (r == N - 1 && c >= 0 && c < N) return true;
        if (c == 0 && r >= 0 && r < N) return true;
        if (c == N - 1 && r >= 0 && r < N) return true;

        return false;

    }
}
