import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    static Scanner sc;
    static StringTokenizer st;
    static int testNum;
    static int[][] board;
    static int[] numCheck;
    static boolean flag;
    static int result;

    public static void main(String[] args) {

        sc = new Scanner(System.in);

        testNum = Integer.parseInt(sc.nextLine());

        for (int t = 1; t <= testNum; t++) {

            board = new int[9][9];
            for (int i = 0; i < 9; i++) {
                st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < 9; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            flag = true;
            result = 1;

            //  행 우선 순회
            if (flag) {
                for (int r = 0; r < 9; r++) {
                    numCheck = new int[10];
                    for (int c = 0; c < 9; c++) {
                        if (numCheck[board[r][c]] == 0) {
                            numCheck[board[r][c]]++;
                        } else {
                            result = 0;
                            flag = false;
                            break;
                        }
                    }
                }
            }

            // 열 우선 순회
            if (flag) {
                for (int c = 0; c < 9; c++) {
                    numCheck = new int[10];
                    for (int r = 0; r < 9; r++) {
                        if (numCheck[board[r][c]] == 0) {
                            numCheck[board[r][c]]++;
                        } else {
                            result = 0;
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) {
                for (int i = 0; i < 7; i += 3) {
                    for (int j = 0; j < 7; j+= 3) {
                        numCheck = new int[10];

                        for (int r = i; r < i + 3; r ++) {
                            for (int c = j; c < j + 3; c++) {
                                if (numCheck[board[r][c]] == 0) {
                                    numCheck[board[r][c]]++;
                                } else {
                                    result = 0;
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            System.out.printf("#%d %d%n", t, result);
        }
    }
}


