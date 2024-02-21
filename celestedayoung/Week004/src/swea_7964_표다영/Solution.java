import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int testNum = sc.nextInt();

        for (int t = 1; t <= testNum ; t++) {

            int cityNum = sc.nextInt();
            int distance = sc.nextInt();
            sc.nextLine();
            String[] map = sc.nextLine().split(" ");

            int idx = distance - 1;
            int cnt = 0;
            while (idx < cityNum) {
                if (map[idx].equals("1")) {
                    idx += distance;
                } else {

                    int door = 0;
                    for (int i = idx - distance + 1; i < idx; i++) {
                        if (map[i].equals("1")) {
                            door++;
                        }
                    }
                    if (door > 0) {
                        idx++;
                    } else {
                        map[idx] = "1";
                        cnt++;
                        idx++;
                    }
                }
            }
            System.out.printf("#%d %d%n", t, cnt);
        }
    }
}