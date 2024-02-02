import java.util.*;
 
/* 6485. 삼성시의 버스 노선
 * 
 * 10/40
 * 
 * 1. 조건
 *  1-1. 5000의 버스 정류장: 1~5000까지의 번호 범위 가능
 *  1-2. 버스 노선은 1<=N<=500
 *      -> i번째 버스 노선은 번호 범위가 [좌측에 주어진 수~우측에 주어진 수] 모든 정류장 다님
 *  1-3. P개의 버스 정류장
 *      -> 정렬되어 주어진다는 말 X
 * 2. 풀이 A
 *  2-1. int[] busStops = new int[5001]
 *      -> 1번 인덱스부터 사용
 *  2-2. 입력으로 받는 A, B에 대해서 index가 [A~B]에 포함되는 int[] busStops[index]++;
 *      -> StringTokenizer 이용해서 반복적으로 설정
 *      => 근데 사실 departure과 destination busStops를 따로 만들어서 구간합처럼 이용하는 게 빠를듯
 *      => 1~3, 2~5: {0, 1, 1, 0, 0, 0} / {0, 0, 0, 0, -1, 0}
 *          (destination은 받아온 int b에 대해 +1인 경우에만 체크)
 *      => {0, 1, 2, 2, 2, 2} / {0, 0, 0, 0, -1, -1} 좌-우
 *      => {0, 1, 2, 2, 1, 1}
 *  2-3. P개의 버스 정류장에 대하여, int[] busStops[index]의 값 공백으로 구분하여 출력 
 * 
 * 개선 여지: 범위 간소화, 지양했던 br/bw
 * 
 */
 
public class Solution {
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
         
        int T = Integer.parseInt(sc.nextLine());
        // tc마다 주어질 버스 노선의 개수 등 변수 미리 선언: 메모리 절약..
        int N;
        int[] departures;
        int[] destinations;
        StringTokenizer st;
        StringBuilder sb;
         
        for (int tc=1; tc<=T; tc++) {
            departures = new int[5001];
            destinations = new int[5001];
            N = Integer.parseInt(sc.nextLine());
             
            sb = new StringBuilder("#" + tc + " ");
            
            // 다음 줄마다 주어질 노선 [a~b]에 대해 쓸 변수 미리 선언
            int a;
            int b;
             
            // a와 b에 대한 index
            for (int ab=1; ab<=N; ab++) {
                st = new StringTokenizer(sc.nextLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                departures[a]++;
                if (b < 5000) {
                    destinations[b+1]--;
                }
            }
             
            // departures 구간합
            for (int deptStop = 1; deptStop <= 5000; deptStop++) {
                departures[deptStop] = departures[deptStop-1] + departures[deptStop]; 
            }
             
            // destinations 구간합
            for (int destStop = 1; destStop <= 5000; destStop++) {
                destinations[destStop] = destinations[destStop-1] + destinations[destStop]; 
            }
             
            int P = Integer.parseInt(sc.nextLine());
            int C;
            for (int stopNum = 1; stopNum <= P; stopNum++) {
                C = Integer.parseInt(sc.nextLine());
                sb.append(departures[C]+destinations[C] + " ");
            }
            
            System.out.println(sb.toString());
             
        }
         
         
    }
}