/*
 * 0. 자리 입력 받기
 * 1. S, L 몇 개인지 
 * 2. 답 계산하기
 *  2-1. L이 하나라도 존재하는 경우
 *  2-2. 모두 S일 경우
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 0.자리 입력 받기
        int N = sc.nextInt();
        String seats = sc.next();

        int cntS = 0;
        int cntL = 0;

        //  1. S, L 몇 개인지 
        for (int i = 0; i < N; i++) {
            if (seats.charAt(i) == 'S') {
                cntS++;
            } else {
                cntL++;
            }
        }
        
        // 2. 답 계산하기
        // 2-1. L이 하나라도 존재하는 경우
        if (cntS != N) {
        	System.out.println(cntS + cntL - (cntL / 2) + 1);
        } 
        // 2-2. 모두 S일 경우
        else {
        	System.out.println(cntS);
        }
        

    }
}