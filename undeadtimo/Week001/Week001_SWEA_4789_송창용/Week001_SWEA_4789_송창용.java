import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

class Solution {
	public static void main(String args[])  {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();

		for (int test = 1; test <= t; test++) {
			// 일어난 인원을 세어줄 변수 sum
			int sum = 0;

			// 고용한 인원을 세어줄 변수 hire
			int hire = 0;

			// 입력값이 문자열 형식으로 주어지기 때문에
			// next로 받아왔다.
			String line = sc.next();
			
			
			int lineLength = line.length();

			int[] ad = new int[lineLength];

			// 받아온 문자열을 하나씩 배열에 넣기 위해,
			// substring으로 한 문자씩 잘라서 정수로 만들어준 이후,
			// 배열에 할당해주었다.
			for (int i = 0; i < lineLength; i++) {
				ad[i] = Integer.parseInt(line.substring(i, i + 1));
			}

			// 배열의 첫 번째부터 탐색 시작
			for (int i = 0; i < lineLength; i++) {
				// 만약 전체 일어나있는 사람이 index값보다 적다면
				if (sum < i) {

					// 일어난 사람에서 index 수치만큼 부족한 사람을 고용한다.
					hire += i - sum;

					// 고용했으니 전체 일어난 사람은 해당 index값만큼이 되었고
					sum = i;
				}

				// 부끄러움을 탈출한 사람들이 일어날 것이다.
				sum += ad[i];
			}

			System.out.printf("%d%n", hire);

		}
	}
}
