import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
 
/*
 * 굳이 클래스를 만들지 않아도 된다.
 * 
 * 필요한 것은 탈출구에 도착했을 때의 시간이다.
 * 
 * 클래스를 만든다면, Comparable 인터페이스를 상속받아서, compareTo 메서드를 오버라이딩 해줘야 도착시간별로 정렬해줄 수 있다.
 * 
 * 간단하게 리스트 혹은 배열을 사용한다면 라이브러리에서 제공하는 정렬 메서드를 활용할 수 있다.
 * 
 */
 
public class Solution {
     
    /*
     * 
     * 
     * 
     */
     
    // 사람의 수를 할당할 변수 personCnt
    static int personCnt;
     
    // 사람의 좌표를 담을 이중 List
    static List<List<Integer>> person;
 
    // 출구의 좌표를 담을 이중 List
    static List<List<Integer>> exit;
     
    // 조합하여 각 사람에게 출구를 지정해주었을 때,
    // 각 사람이 해당 출구까지 도달하는 시간을 List에 넣어주기 위한 두 List이다.
    static List<Integer> exitA;
    static List<Integer> exitB;
     
    // 인덱스가 '초'를 의미하는 배열이다.
    // 이를통해 해당 '초'에 몇 명이 출구를 사용하고 있는지 파악할 수 있다.
    static int[] exitA_timeLine;
    static int[] exitB_timeLine;
     
    // 조합에 사용하기 위한 boolean 배열이다.
    // 각 인덱스에 true or false 값을 지정해주어, true면 a출구로, false면 b출구로 가게 하였다.
    static boolean[] flags;
     
    // 정답 도출을 위한 ans 변수이다.
    // 테스트 케이스 안에서 생성하지 않고 밖에서 생성하는 실수를 저질렀다.
    static int ans;
     
    // A 탈출구에서 마지막 사람이 탈출할 때의 시간을 나타내는 escapingTimeA 변수.
    static int escapingTimeA;
 
    // B 탈출구에서 마지막 사람이 탈출할 때의 시간을 나타내는 escapingTimeA 변수.
    static int escapingTimeB;
         
    // A 탈출구 혹은 B 탈출구의 탈출 시간을 비교하여 더 오래 걸린 시간을 할당할 escapingTime 변수.
    static int escapingTime;
 
    // test를 위한 main 함수이다.
    public static void main(String[] args) {
         
        // 입력값을 받아오기 위한 Scanner 객체를 생성해주었다.
        Scanner sc = new Scanner(System.in);
         
        // 입력값을 빠르게 가져오기 위한 StringTokenizer 객체를 생성.
        StringTokenizer st;
         
        st = new StringTokenizer(sc.nextLine());
         
        // 테스트 케이스의 갯수를 가져와 T 변수에 할당해주었다.
        int T = Integer.parseInt(st.nextToken());
         
        // 테스트 케이스 반복문 시작.
        for(int test_case = 1; test_case <= T; test_case++) {
            // 최솟값을 찾아야 하는 것이기 때문에 정답 도출을 위한 ans 변수를 Integer.Max_Value로 초기화해주었다.
            // 처음에는 이 코드가 테스트 케이스 밖에서 작성되어서 여러 테스트 케이스의 정답이 동일하게 출력되는 일이 발생했다.
            ans = Integer.MAX_VALUE;
             
            st = new StringTokenizer(sc.nextLine());
 
            // 건물의 크기를 나타낼 N.
            int N = Integer.parseInt(st.nextToken());
             
            // 건물의 상태값을 할당해줄 map 2차원 배열이다.
            int[][] map = new int[N][N];
            person = new ArrayList<>();
             
            personCnt = 0;
             
            exit = new ArrayList<>();
             
            // 건물의 상태값을 2차원 배열에 구현하기 위한 반복문.
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(sc.nextLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                     
                    // 사람이면 person 리스트에 정보를 저장한다.
                    if(map[i][j] == 1) {
                        personCnt++;
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(i);
                        tmp.add(j);
                        person.add(tmp);
                    }
                     
                    // 탈출구면 exit 리스트에 정보를 저장한다.
                    if(map[i][j] > 1) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(i);
                        tmp.add(j);
                        tmp.add(map[i][j]);
                        exit.add(tmp);
                    }
                     
                }
            }
             
            // 조합을 위해 사용할 불리언 배열 생성.
            flags = new boolean[personCnt];
             
            // 방의 상태를 2차원 배열 map을 통해 구현했다.
            // 각 사람의 위치를 저장하였고,
            // 각 탈출구의 위치와 해당 탈출구를 이용할 때 걸리는 시간을 저장하였다.
             
            // 사람을 어떤 탈출구에 배정할 것인가를 조합하여,
            // 모든 조합에 대해 탈출하는 시간을 계산할 것이다.
            // 이렇게 계산된 탈출 시간들 중에서 최소 시간을 ans 변수에 할당하여 출력할 것이다.
             
            // 조합 및 각 조합마다 탈출시간 계산.
            comb(0);
             
            System.out.printf("#%d %d%n", test_case, ans + 1);
             
             
             
        }
         
    }
     
    public static void comb(int idx) {
         
        // 모든 사람에 대하여 고려했다면 해당 조합 상태에서 탈출 시간을 계산한다.
        if(idx == personCnt) {
             
            // 각 사람이 지정된 탈출구에 도착하는 시간을 넣어줄 두 리스트 exitA, exitB.
            exitA = new ArrayList<>();
            exitB = new ArrayList<>();
             
            // 각 사람들에게 조합에 따라 어디 탈출구로 가라고 지시하는 반복문.
            for(int i = 0; i < personCnt; i++) {
                if(flags[i]) {
                    // flags에서 해당하는 인덱스의 값이 참이라면 A 탈출구로 보낸다.
                    List<Integer> per = person.get(i);
                    int poX = per.get(0);
                    int poY = per.get(1);
                     
                    List<Integer> ex = exit.get(0);
                    int exX = ex.get(0);
                    int exY = ex.get(1);
                     
                    // 탈출구까지 가는데 걸리는 시간을 계산한다.
                    int dist = Math.abs(poX - exX) + Math.abs(poY - exY);
                     
                    // exitA 리스트에 시간을 넣어준다.
                    exitA.add(dist);
                }else {
                    // A 탈출구와 같은 로직이다.
                    List<Integer> per = person.get(i);
                    int poX = per.get(0);
                    int poY = per.get(1);
                     
                    List<Integer> ex = exit.get(1);
                    int exX = ex.get(0);
                    int exY = ex.get(1);
                     
                    int dist = Math.abs(poX - exX) + Math.abs(poY - exY);
                    exitB.add(dist);
                }
            }
 
             
            // 탈출 시간을 계산하는 메서드 호출.
            // 각각 A 탈출구에 대한 탈출시간과 B 탈출구에 대한 탈출시간을 계산한다.
            timeA();
            timeB();
             
            // A탈출구에서의 탈출 완료 시간이 B탈출구에서의 탈출 완료 시간보다 오래걸리면,
            // 총 탈출 시간은 A 탈출구에서의 탈출 시간이 된다.
            // 반대라면 B 탈출구에서의 탈출 시간이 총 탈출 시간이다.
            escapingTime = escapingTimeA > escapingTimeB ? escapingTimeA : escapingTimeB;
             
             
            // 총 탈출 시간을 ans와 비교하여 ans보다 작으면 ans 값으로 할당해준다.
            if(ans > escapingTime) {
                ans = escapingTime;
            }
            return;
        }
         
        flags[idx] = true;
        comb(idx + 1);
         
        flags[idx] = false;
        comb(idx + 1);
         
         
    }
     
    public static void timeA() {
        // A탈출구에서 탈출 완료까지 걸리는 시간을 할당할 변수 escapingTimeA
        escapingTimeA = 0;
         
        // a 탈출구를 사용하는 사람을 '초'별로 표현할 exit_timeLine 배열.
        // 가능한 최대 인원은 10명, 가능한 최대 탈출구 길이는 10, 방 한 변의 최대 길이는 10이기에
            // 20(방 끝에서 제일 먼 끝으로 가면 18) * 10(최대 인원) * 10(최대 탈출구) 크기의 배열을 만들었다.
        exitA_timeLine = new int[2000];
         
        // 탈출구에 도착하는 시간을 오름차순으로 정렬해주었다.
        exitA.sort(null);
         
        // 각 탈출 도착시간에 대한 계산을 해줄 반복문.
        for(int i = 0; i < exitA.size(); i++) {
             
            // 도착시간을 가져와 arrive 변수에 할당해준다.
            int arrive = exitA.get(i);
             
            // 만약 탈출구에 도착해서 1초후에 탈출하려고 하는데 3명이 이미 사용하고 있으면,
            // 사용인원이 3명이 아닐 때까지 기다리도록 해주는 while문이다.
            int m = 0;
            while(exitA_timeLine[arrive + 1 + m] == 3) {
                m++;
            }
             
            // 3명이 아닐 때까지 기다린 이후, 탈출구의 길이만큼 배열에 1씩 추가해준다.
            for(int j = arrive + 1; j <= arrive + exit.get(0).get(2); j++) {
                exitA_timeLine[j + m]++;
                 
                // 마지막 탈출자의 마지막 사용시간을 가져와서 A 탈출구의 탈출 시간으로 할당해주었다.
                // 이 부분에서 실수하였다. j == arrive + exit.get(0).get(2)라 해야 하지만,
                // j == arrive + exit.get(0).get(2) - 1로 조건을 지정하여, 1초 적은 시간을 가져왔다.
                if(i == exitA.size() - 1 && j == arrive + exit.get(0).get(2)) {
                    escapingTimeA = j + m;
                }
            }
        }
         
         
    }
 
    public static void timeB() {
        // A탈출구와 로직이 같다.
        escapingTimeB = 0;
        exitB_timeLine = new int[2000];
        exitB.sort(null);
         
        for(int i = 0; i < exitB.size(); i++) {
            int arrive = exitB.get(i);
             
            int m = 0;
            while(exitB_timeLine[arrive + 1 + m] == 3) {
                m++;
            }
             
            for(int j = arrive + 1; j <= arrive + exit.get(1).get(2); j++) {
                exitB_timeLine[j + m]++;
                 
                if(i == exitB.size() - 1 && j == arrive + exit.get(1).get(2)) {
                    escapingTimeB = j + m;
                }
            }
        }
         
         
    }
     
}