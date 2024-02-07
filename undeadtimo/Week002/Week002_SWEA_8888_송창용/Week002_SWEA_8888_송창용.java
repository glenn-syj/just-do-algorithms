import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 저번 스터디 모임 때 영준이가 알려준 StringTokenizer를 st라는 이름으로 선언해보았다.
		StringTokenizer st;

		// Scanner 객체 생성.
		Scanner sc = new Scanner(System.in);

		// Scanner 객체를 이용하여 nextLine으로 한 줄 전체를 가져왔다.
		String tt = sc.nextLine().trim();

		// 가져온 줄을 StringTokenizer의 매개변수로 넣어주면서 객체를 생성했다.
		st = new StringTokenizer(tt);

		// 생성한 객체를 이용하여 token을 가져와 테스트 케이스의 개수를 가져왔다.
		int T = Integer.parseInt(st.nextToken());


		// 테스트 케이스만큼 반복하는 for문
		for (int test_case = 1; test_case <= T; test_case++) {

			// 한 줄 전체를 가져와서 str 변수에 저장하였다.
			String str = sc.nextLine().trim();
			
			// 가져온 줄 전체를 StringTokenizer 객체에 넣어준다.
			// 이 때, delimeter(구분자)가 각 토큰을 구분해주기 때문에
			// 5 3 2 라는 입력값이 있으면 token은 각각 5, 3, 2가 된다.
			// 즉, 자동으로 공백과 개행문자를 기준으로 구분하여 token을 가져오는 것이다. 
			st = new StringTokenizer(str);

			// 첫 번째 토큰으로 사람의 수를 가져온다.
			int people = Integer.parseInt(st.nextToken());

			// 두 번째 토큰으로 문제의 수를 가져온다.
			int problem= Integer.parseInt(st.nextToken());

			// 세 번째 토큰으로 지학이의 번호를 가져온다.
			int jh = Integer.parseInt(st.nextToken());

			
			// 지학이보다 잘 본 사람 수를 세기 위한 moreThanJh 변수를 생성하였다.
			int moreThanJh = 0;

			// 문제 만큼의 행을, 사람 만큼의 열을 갖는 2차원 배열을 생성하였다.
			int[][] info = new int[people][problem];

			/*
			* 이 부분에서 시간초과를 일으키게 문제가 의도하고 있다.
			* 과도하게 많은 입력값을 주었을 때 어떻게 해결할 것인지를,
			* 즉, StringTokenizer 또는 BufferedReader, InputStreamReader를 사용할 수 있는지를 보고있다.
			* 여기서 단순하게 nextInt로 입력값을 받아오면 시간초과로 문제를 풀 수 없다.
			*/

			// 2차원 배열에 각 개인의 문제 해결 여부를 할당할 것이다.
			// 사람 수만큼(행의 개수) 반복할 것이다.
			for (int i = 0; i < people; i++) {
				// 우선 Scanner 객체를 이용해서 한 줄을 전부 가져와서 저장한다.
				String infoLine = sc.nextLine().trim();

				// StringTokenizer 객체에 가져온 줄을 매개변수로 주면서 객체를 생성해준다.
				st = new StringTokenizer(infoLine);

				// 문제의 수만큼(열의 개수) 반복하면서 각 개인의 문제 해결 여부를 할당해줄 것이다.
				for (int j = 0; j < problem; j++) {
					// 공백, 개행문자는 StringTokenizer가 알아서 제외해주니
					// 바로 nextToken으로 가져오면서 int 타입으로 형변환해주면 된다.
					info[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 위의 부분을 StringTokenizer 또는 BufferedReader, InputStreaReader를 사용하여 풀 수 있다면,
			// 시간 초과에 관한 부분은 해결이다.

			// 각 문제의 점수를 저장할 배열을 생성한다.
			int[] score = new int[problem];

			// 문제의 점수는 해당 문제를 풀지 못한 '사람의 수'이기 때문에,
			// 열 우선 순회를 돌면서 문제를 풀지 못한 사람의 수를 세어 문제의 점수를 파악해 저장할 것이다.
			// 열 우선 순회를 위해 열을 이용한 반복을 바깥에서 돌아주고,
			// 행을 이용한 반복을 안쪽에서 돌아준다.
			for (int j = 0; j < problem; j++) {
				// 하나의 열 -> 하나의 문제
				// 하나의 문제에 해당하는 점수를 파악하기 위한 tempScore 변수를 생성하였다.
				// 문제를 풀지 못한 사람의 수만큼 1씩 더해줄 것이다.
				int tempScore = 0;
				for (int i = 0; i < people; i++) {
					if (info[i][j] == 0) {
						tempScore++;
					}
				}
				// 문제를 풀지 못한 사람의 수를 score 배열에 할당해주었다.
				score[j] = tempScore;
			}

			// 각 점수가 잘 배정되었는지 확인했다.
//		for (int i = 0; i < problem; i++) {
//			System.out.printf("%d ", score[i]);
//		}

			// 각 인원이 얻은 총 점수를 저장한 배열 peopleScore.
			int[] peopleScore = new int[people];

			// 각 인원이 푼 문제의 갯수를 저장한 배열 peopleSolve.
			int[] peopleSolve = new int[people];

			// 사람의 번호를 i, 문제 번호를 j라 할 때,
			// 문제 j에서 각 사람들(i)를 보면서 문제를 풀었다면 즉, '1' 이라면.
			// 해당 문제의 배점만큼 peopleScore의 사람 번호 i에 점수를 추가해준다.
			for (int i = 0; i < people; i++) {
				for (int j = 0; j < problem; j++) {
					if (info[i][j] == 1) {
						// 각 개인의 총 점수 배열
						peopleScore[i] += score[j];
						// 각 개인의 맞춘 문제 수 배열
						peopleSolve[i] += 1;
					}
				}
			}

			// 각 사람에게 총 점수가 잘 들어갔는지 확인
//		System.out.println(Arrays.toString(peopleScore));

			// 모든 사람들의 점수와 지학이의 점수를 비교한다.
			for (int i = 0; i < people; i++) {
				// 만약 지학이가 첫 번째라면 해당 인덱스는 0이기 때문에,
				// 지학이의 번호는 1을 빼주거나 비교되는 인덱스에 1을 더해줘야 한다.
				if (i + 1 == jh) {
					// 지학이와 지학이를 비교하지 않게 넘어가준다.
					continue;
				}

				// 지학이보다 많은 점수를 얻은 사람의 수를 구한다.
				if (peopleScore[i] > peopleScore[jh - 1]) {
					moreThanJh++;
				}

				// 지학이와 점수는 같지만 더 많은 문제를 푼 사람의 수를 구한다.
				if (peopleScore[i] == peopleScore[jh - 1] && peopleSolve[i] > peopleSolve[jh - 1]) {
					moreThanJh++;
				}

				// 지학이와 점수도 같고 문제를 푼 개수도 같지만, 번호가 지학이보다 앞에 있는 사람의 수를 구한다.
				if (peopleScore[i] == peopleScore[jh - 1] && peopleSolve[i] == peopleSolve[jh - 1] && i < jh - 1) {
					moreThanJh++;
				}
			}

			// 등수란 자기보다 위에 있는 사람 + 1임을 유념하여 결과를 출력해주었다.
			System.out.printf("#%d %d %d%n", test_case, peopleScore[jh - 1], moreThanJh + 1);
		}
	}
}