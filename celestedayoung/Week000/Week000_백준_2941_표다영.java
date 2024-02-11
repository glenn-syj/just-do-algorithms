/*
 * 0. 알파벳 규칙을 담은 리스트 만들기
 * 1. 문자열 입력 받기
 * 2. 알파벳 규칙 리스트에서 하나씩 꺼내서 문자열과 비교
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        //  0. 알파벳 규칙을 담은 리스트 만들기
        String[] letters = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        // 1. 문자열 입력 받기
        String testCase = sc.next();

        int cnt = 0;
        int left = testCase.length();
        
        // 2. 알파벳 규칙 리스트에서 하나씩 꺼내서 문자열과 비교
        for (int l = 0; l < letters.length; l++) {
            // 문자열에서 어느 인덱스에 위치할 것인지 결정
            int pointer = 0;

            for (int t = 0; t <= testCase.length() - letters[l].length(); t++) {
                // 문자열에서 알파벳 길이만큼 가져와서 비교하기
                String compare = testCase.substring(pointer, pointer + letters[l].length());

                if (letters[l].equals(compare)) {
                    left -= letters[l].length();
                    // 일치하는 문자는 x로 마스킹
                    testCase = testCase.replaceFirst(compare, "x");
                    cnt++;
                }
                pointer++;
            }
        }
        System.out.println(cnt + left);
    }
}