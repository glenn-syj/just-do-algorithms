/*
 * 백준_1158. 요세푸스 문제
 *  - Logic
 *      0. 주어진 사람 수를 담는 배열을 만든다.
 *      1. 죽일 사람의 번호(n번째)에 해당하는 사람을 죽이고 josephus 배열에 담는다.
 *          1-1. 죽은 사람은 건너뛰고 n번째 사람을 죽여야 한다.
 * 
 *  - Point
 *      - Out of index 유의하기
 *        : n번째 사람을 죽이기 위해 index를 증가시키다 보면 주어진 사람이 담긴 배열의 길이를 초과하게 된다.
 *          사람들은 원 모양으로 앉기 때문에 마지막 사람 다음 사람은 처음에 위치한 사람이 된다.
 *          따라서 index > arr.length가 되면 index - arr.length를 통해 index를 다시 0으로 초기화 시킨다.
 *      
 *      - 죽은 사람은 건너뛰고 n번째 사람에게 접근하기   
 *        : 죽은 사람을 고려하지 않고 n번째 사람에게 접근하면 죽인 사람을 또 죽이게 되거나, 원하는 사람을 죽일 수 없게 된다.
 *          따라서, 죽은 사람을 0(사람의 번호는 > 0이므로)으로 바꿔주고, 0을 만나면 pass하도록 한다.
 *          ex) [1, 0, 0, 4, 0, 6]일 때 3번째 사람은 6이고, 0이 3개 존재하므로 총 5칸 이동해야 한다.
 *          계속 진행하다가 살아있는 사람을 만났을 때만 count하고, count가 n번이 되면 죽이도록 한다.
 */      

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int peopleNum = sc.nextInt();
        int killNum = sc.nextInt();

        int[] peopleList = new int[peopleNum];
        for (int i = 0; i < peopleNum; i++) {
            peopleList[i] = i + 1;
        }

        int[] josephus  = new int[peopleNum];
        // 1번(peopleList[0])부터 첫 번째 사람이 되어야 하므로 -1부터 시작해서 killNum칸 이동해야 죽일 사람에게 도착한다.
        int idx  = -1;

        for (int k = 0; k < peopleNum; k++) {
            // 살아있는 사람을 만났을 때만 count하는 변수
            int idxCnt = 0;
            
            // idxCnt가 죽일 사람의 번호가 되면 해당 사람을 죽이도록 한다.
            while (idxCnt < killNum) {
                // idx가 peopleList의 index를 초과하면 0으로 초기화
                if (idx >= peopleNum - 1) {
                    idx -= peopleNum;
                }
                idx++;
                // 살아있는 사람을 만나면 idxCnt 증가
                if (peopleList[idx] != 0) {
                    idxCnt++;
                }
            }
            josephus[k] = peopleList[idx];
            // 죽인 사람은 0으로 바꾼다.
            peopleList[idx] = 0;
        }

        // 출력 형식은 < 죽은 사람들 >
        for (int i = 0; i < peopleList.length; i++) {
            // 만약 입력이 '1, 1'일 경우 출력 형식 유의
            if (josephus.length == 1) {
                System.out.print("<" + josephus[0] + ">");
            } else {
                if (i == peopleList.length - 1) {
                    System.out.print(josephus[i] + ">");
                } else if (i == 0) {
                    System.out.print("<"+ josephus[i] + ", ");
                } else {
                    System.out.print(josephus[i] + ", ");
                }
            }
        }
    }
}

