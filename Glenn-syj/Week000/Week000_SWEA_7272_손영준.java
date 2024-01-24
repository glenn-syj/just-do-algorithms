import java.util.*;
import java.io.*;

/* 7272_안경이 없어 v2
* 1. B인 경우, 구멍이 한 개인 경우, 구멍이 하나도 없는 경우
* 2. 일단 길이 = 중간에 공백이 있고, 두 문자열 길이가 같다면 2*length 이므로 check 가능 -> 안됨(5 공백 3) -> 무조건 길이로
* 3. 그냥 바로 
*/

public class Week000_SWEA_7272_손영준 {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        int[] alphabetVals = new int[27];
        
        for (int i=0; i<=26; i++) {
            switch (i+'A') {
            case 'A': case 'D': case 'O': case 'P': case 'Q': case 'R':
                alphabetVals[i] = 1;
                break;
            case 'B':
                alphabetVals[i] = 2;
                break;
            default:
                break;
            }
        }
        
        
        String line;
        int length;
        String left;
        String right;
        StringTokenizer st;
        
        loop:
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            left = st.nextToken();
            right = st.nextToken();
            
            // 길이가 다르면 우선 같을 수가 없음
            if (left.length() != right.length()) {
                bw.write("#" + tc + " DIFF\n");
                continue loop;
            }
            
            for (int i=0; i < left.length(); i++) {
            // 공백 기준 나뉜 두 String을 비교하는 코드
                if (alphabetVals[(left.charAt(i)-'A')] != alphabetVals[(right.charAt(i)-'A')]) {
                    bw.write("#" + tc + " DIFF\n");
                    continue loop;
                }
            }
            
            bw.write("#" + tc + " SAME\n");
        }
        
        bw.flush();
        bw.close();
    
        
    }
}