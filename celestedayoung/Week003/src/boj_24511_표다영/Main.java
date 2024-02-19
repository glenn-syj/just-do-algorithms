package boj_24511_queuestack;
/*
 *  [Logic]
 * - Queue와 Stack에 들어갔다 나오는 문제이지만 사실상 Stack에 들어갔다 나오는 경우는 고려할 필요가 없다.
 * - Queue에 대해서만 enQueue & deQueue를 수행한다.
 * 
 *  0. 0이 Queue를 의미하므로 0을 만나면 Queue에 data를 넣는다.
 *  1. input 숫자를 차례대로 Queue에 앞에 넣는다.
 *  2. Queue의 뒤에는 이미 들어있던 숫자가 존재하므로 이것을 꺼낸다.
 * 
 */

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        String[] dataStructure =  br.readLine().split(" ");
        String[] data = br.readLine().split(" ");

        Deque<String> myQueue = new ArrayDeque<>();

        for (int i = 0; i < num; i++) {
            if (dataStructure[i].equals("0")) {
                myQueue.addLast(data[i]);
            }
        }

        int inputLength = Integer.parseInt(br.readLine());
        String[] inputNums = br.readLine().split(" ");

        for (int i = 0; i < inputLength; i++) {
            myQueue.addFirst(inputNums[i]);
            bw.write(myQueue.pollLast() + " ");
        }
        bw.flush();
    }
}