package boj_12789_표다영;
/*
 *	[Logic]
 *	0. '현재 줄 서 있는 곳'과 '한 명씩만 설 수 있는 공간'을 각각 stack으로 만든다.
 *		- place1: '현재 줄 서 있는 곳' 
 *		- place2: '한 명씩만 설 수 있는 공간'
 *	1. 승환이의 앞에 서 있는 학생들의 수를 거꾸로 place1에 push한다.
 *	2. 1번이 place1의 top에 위치할 때까지 place1의 숫자들을 pop하여 place2에 push한다.
 *		- 조건: place1에서 pop한 값 < place2의 top에 위치한 값 : 이 조건을 만족해야 번호 순서대로 들어갔다가 나올 수 있다.
 *		- 위 조건을 만족하지 않는 경우 순서대로 들어가고 나올 수 없으므로 "Sad".
 *	3. 1번을 pop한 후, 2번 과정을 조건을 고려하여 반복하며 이후 번호들을 차례대로 place1 또는 place2에서 pop한다.
 *	4. place1과 place2 모두 비었다면 모든 학생이 순서대로 간식을 받은 것이므로 "Nice".
 *
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int studentNum = sc.nextInt();

        IntStack place1 = new IntStack(studentNum);
        IntStack place2 = new IntStack(studentNum);

        int[] tmpArr = new int[studentNum];

        for (int i = 0; i < studentNum; i++) {
            tmpArr[i] = sc.nextInt();
        }

        for (int i = tmpArr.length - 1; i >= 0; i--) {
            place1.push(tmpArr[i]);
        }
        boolean flag = true;
        while (place1.peak() != 1) {
 
            if (place2.isEmpty()) {
                place2.push(place1.pop());
            } else if (place2.peak() > place1.peak()) {
                place2.push(place1.pop());
            } else {
                System.out.println("Sad");
                flag = false;
                break;
            }
        }
        
        place1.pop();

        int nums = 2;
        if (flag) {
            while (true) {

                if (!place1.isEmpty() && place1.peak() == nums) {
                    place1.pop();
                    nums++;
                } else if (!place2.isEmpty() && place2.peak() == nums) {
                    place2.pop();
                    nums++;
                } else {
                    if (place2.isEmpty()){
                        place2.push(place1.pop());
                    } else if(place1.peak() < place2.peak()) {
                        place2.push(place1.pop());
                    } else {
                        System.out.println("Sad");
                        break;
                    }
                }

                if (place1.isEmpty() && place2.isEmpty()) {
                    System.out.println("Nice");
                    break;
                }

            }
        }


    }

    public static class IntStack {

        public int[] arr = new int[100];
        public int top = -1;

        public IntStack() {}

        public IntStack(int size) {
            this.top = -1;
            this.arr = new int[size];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == arr.length - 1;
        }

        public int pop() {

            if (isEmpty()) {
                return '$';
            }
            int tmp = arr[top];
            arr[top--] = 0;
            return tmp;
        }

        public void push(int item) {

            if (isFull()) {
                return;
            }
            arr[++top] = item;
        }

        public int peak() {
            return arr[top];
        }
    }
}
