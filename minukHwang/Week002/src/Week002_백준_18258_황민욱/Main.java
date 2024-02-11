package Week002_백준_18258_황민욱;

/*
 * 문제 풀이 과정
 * 1. 구현을 하기 위해서는 배열, 배열의 start 인덱스, 배열의 end 인덱스가 필요하다.
 * 2. start end를 움직이면서 queue의 기능들을 구현한다.
 * 3. 입력에 맞추어서 출력.
 * 4. 시간 엄청 빡빡해서 버퍼드로 입력받고 출력해야함.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(st.nextToken());

		int[] queue = new int[n];
		int start = 0;
		int end = -1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();

			if (str.equals("push")) {
				int x = Integer.parseInt(st.nextToken());
				// 정수 x를 큐에 넣는 연산
				// 숫자가 추가되는 것으로 끝점을 증가.
				queue[++end] = x;
			}

			else if (str.equals("pop")) {
				if (start > end) {
					bw.write("-1\n");
				} else {
					// 맨 앞의 숫자가 빠지는 것으로 시작점을 바꿔준다.
					bw.write(queue[start++] + "\n");
				}

			}

			else if (str.equals("size")) {
				bw.write(end - start + 1 + "\n");
			}

			else if (str.equals("empty")) {
				if (start > end) {
					bw.write("1\n");
				} else {
					bw.write("0\n");
				}
			}

			else if (str.equals("front")) {
				if (start > end) {
					bw.write("-1\n");
				} else {
					// 맨앞 정수 출력
					bw.write(queue[start] + "\n");
				}
			}

			else if (str.equals("back")) {
				if (start > end) {
					bw.write("-1\n");
				} else {
					// 맨뒤 정수 출력
					bw.write(queue[end] + "\n");
				}
			}
		}

		bw.flush();
	}

}
