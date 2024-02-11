/*
 * SWEA_6485. 삼성시의 버스 노선
 * 	- Logic
 * 		0. 주어진 정류장 번호만큼의 길이를 가지는 배열을 만들고, 해당 정류장을 지나는 버스가 지나가면 1씩 증가시킨다.
 *          ex) 정류장이 5개일때 1번 버스는 2, 3번 정류장을 지난다면 [0, 1, 1, 0, 0]
 * 		1. j번째 정주는 Cj 버스 정류장을 지나는 버스 노선의 개수이므로, j와 일치하는 버스count 배열의 idx의 요소를 가져온다. 
 * 
 *  - Point
 * 		- input의 범위
 *        : 버스 정류장의 번호는 1 이상 500 이하이므로, 지나간 버스의 수를 count하는 배열의 크기를 5000이 아닌 5001로 설정해야 한다.
 * 	    - input & output 형식
 *        : test case의 Cj의 경우 정류장 5개에 대하여 1, 2, 3, 4, 5번 정류장을 지나는 버스의 수를 출력하도록 한다.
 *          그러나 5개라고 해서 1~5가 순서대로 나올 것이라는 보장이 없기 때문에 유의해야한다.
 *          ex) P=6일 때 1 3 4 4 2 6 과 같이 중복된 번호, 오름차순이 아닌 번호대로 제시될 수 있다.
 */

 import java.util.Arrays;
 import java.util.Scanner;
 
 public class Solution {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int testNum = sc.nextInt();
 
 
         for (int t = 1; t <= testNum; t++) {
 
             int N = sc.nextInt();
 
             // 버스 정류장은 1번부터 5000번까지이므로 배열의 길이를 5000이 아닌 5001로 설정
             int[] busCnt = new int[5001];
 
             for (int i = 0; i < N; i++) {
                 int A = sc.nextInt();
                 int B = sc.nextInt();
 
                 // 해당 버스가 지나가는 범위 (A이상 B이하)에 해당하는 busCnt의 요소를 1 증가 시킨다.
                 for (int j = A; j <= B ; j++) {
                     busCnt[j] += 1;
                 }
             }
 
             int P = sc.nextInt();
 
             int[] cj = new int[P];
             for (int p = 0; p < P; p++) {
                 cj[p] = sc.nextInt();
             }
 
             System.out.printf("#%d", t);
             for (int i = 0; i < P; i++) {
                 System.out.printf(" %d", busCnt[cj[i]]);
             }
             System.out.println();
         }
     }
 }