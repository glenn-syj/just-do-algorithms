package boj_2527_직사각형;
/*
 *  [Logic]
 *  - 좌표를 이용하여 각 조건을 만족하는 좌표의 상태를 조건문으로 만든다.
 *  0. d : 겹치는 부분이 없는 경우
 *          x2 > p1 || y2 > q1 || p2 < x1 || q2 < y1
 *  1. c: 점인 경우
 *          (x1 == p2 && q1 == y2) || (x1 == p2 && y1 == q2) || (p1 == x2 && y1 == q2) || (p1 == x2 && q1 == y2)
 *  2. b: 선분인 경우
 *          p1 == x2 || q1 == y2 || p2 == x1 || y1 == q2
 *  3. a: 그 외의 경우 = 겹치는 부분이 직사각형인 경우
 *         
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int p1 = sc.nextInt();
            int q1 = sc.nextInt();

            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int p2 = sc.nextInt();
            int q2 = sc.nextInt();
            
            if (x2 > p1 || y2 > q1 || p2 < x1 || q2 < y1) {
                System.out.println("d");
            } else if ((x1 == p2 && q1 == y2) || (x1 == p2 && y1 == q2) || (p1 == x2 && y1 == q2) || (p1 == x2 && q1 == y2)) {
                System.out.println("c");
            } else if (p1 == x2 || q1 == y2 || p2 == x1 || y1 == q2) {
                System.out.println("b");
            } else {
                System.out.println("a");
            }

        }
    }
}