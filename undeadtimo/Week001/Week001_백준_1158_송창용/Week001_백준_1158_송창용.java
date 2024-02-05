import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 총 인원을 할당받을 변수
		int people = sc.nextInt();

		// 몇 번째마다 제거할 것인지 결정하는 수를 할당받을 변수
		int jump = sc.nextInt();

		// 인원 수 만큼 크기의 배열 두 개를 만들 것이다.

		// 죽은 사람들을 차례로 담아줄 배열. 마지막 정답 출력을 위한 것이다.
		int[] dead = new int[people];

		// 사람들을 번호 순서대로 앉힌 것을 표현할 배열. 차례로 번호를 뽑아내기 위한 것이다.
		int[] sit = new int[people];

		// sit 배열에 1부터 people까지 요소를 채워줄 for문
		// people이 4라고 친다면 sit 배열은 {1, 2, 3, 4} 이렇게 될 것이다.
		for (int i = 0; i < people; i++) {
			sit[i] = i + 1;
		}

		int iterator = 0;
		// 흔히 for문이나 while문에서 쓰이는 i의 풀네임 iterator 변수를 이용해서,
		// while문에서 논리를 전개할 것이다.

		// 사람을 죽이는 횟수를 세서, 모든 인원이 죽으면 반복이 끝나도록 할 것이다.
		int cnt = 0;

		// 인원을 셀 때마다 횟수를 추가하여 jump와 일치하면 제거하기 위한 변수이다.
		int jumpCnt = 0;

		while (cnt != people) { 
			// cnt가 사람 수에 도달하면, 즉 모든 사람이 죽을 경우 while문이 끝나도록 하였다.
			
			
			// 한 바퀴를 돌았을 경우 다시 처음부터 탐색하기 위한 방식.
			iterator = iterator % people;
			// ex) 마지막 인덱스 6을 지나 7번째 탐색이 되면 7로 나눈 나머지 0으로 만들어주어 첫 번째 요소부터 다시 탐색 가능

			// 해당 위치에, 사람이 죽지 않았을 경우 제거를 위한 jumpCnt횟수를 세준다.
			if (sit[iterator] != 0) {
				jumpCnt++;
				
			// 횟수(jumpCnt)를 jump로 나눠서 나머지가 0이면 jump번째 숫자를 골라낼 수 있다.
				if (jumpCnt % jump == 0) {
					// 죽이면서 죽은 사람 목록에 번호를 추가해주었다.
					dead[cnt++] = sit[iterator];

					// 배열에서 죽은 사람의 자리는 0으로 바꿔주어 다음 탐색 때 파악할 수 있도록 해주었다.
					sit[iterator] = 0;
				}
			}
			

			// 다음 탐색을 위한 반복자 증가
			iterator++;

		}
		
		// 출력 형식
		for(int i = 0; i < people; i++) {
			
			// 사람이 한 명밖에 없을 경우 아래의 방식으로는 올바른 출력 형식이 불가능하다.
			if(dead.length == 1) {
				System.out.printf("<%d>", dead[0]);
				break;
			}
			
			// 첫 번째와 마지막 요소에는 꺽쇠가 있어야하기 때문에 따로 설정해주었다.
			if(i == 0) {
				System.out.printf("<%d, ", dead[i]);
			}else if(i == people - 1) {
				System.out.printf("%d>", dead[i]);
			}else {
				System.out.printf("%d, ", dead[i]);
			}
		}

	}

}
