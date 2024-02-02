import java.util.Scanner;

/*
 * 에라토스테네스의 체로 풀어야함
 */

public class Week001_SWEA_4698_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        // 에라토스테네스의 체로 소수 판별(참조) 배열 만들어주기.
		boolean[] isNotPrime = primeNumberSieve(100000);

		int T = sc.nextInt();

		// 1. 테스트 케이스만큼 연산 시작.
		for (int t = 1; t <= T; t++) {

			// 2. 문제 조건들 받아오기
			// 여기서 d는 문자열로 받아오는 이유는 나중에 해당 문자열을 포함하고 있는지 판별하기 위함.
			String d = sc.next();
			int a = sc.nextInt();
			int b = sc.nextInt();

			// 3. 최종적으로 조건에 맞는 수의 갯수를 count.
			int count = 0;

			for (int i = a; i <= b; i++) {
				// 만약 소수가 아니라면 패스
				if (isNotPrime[i]) {
					continue;
				}

				// 소수라면 문자열로 바꿔주고, d를 포함하고 있다면 count.
				String primeNum = Integer.toString(i);

				if (primeNum.contains(d)) {
					count++;

				}
			}

			System.out.printf("#%d %d\n", t, count);
		}
	}

	// 에라토스테네스의 체 구현
	// 시간 복잡도 nloglogn
	public static boolean[] primeNumberSieve(int num) {
		// 1. 배열을 만들어서 초기화를 진행한다.
		// (참조만 할 것이기 때문에 체에 걸러졌는지 여부만 표시하기 위해 boolean)
		// 배열은 0부터 시작하기 때문에 범위는 0~num까지
		boolean[] isNotPrime = new boolean[num + 1];

		// 2. 0,1은 소수가 아니기 때문에 true.
		isNotPrime[0] = true;
		isNotPrime[1] = true;

		// 3. 2부터 순차적으로 제거
		for (int i = 2; i < isNotPrime.length; i++) {
			// 이미 걸러진 수라면 배수를 굳이 계산하지 않는다.
			if (isNotPrime[i] == true) {
				continue;
			}

			// 본인을 제외하고, 그 다음 본인*2부터 본인의 배수들을 체에서 걸러낸다.
			for (int j = i * 2; j < isNotPrime.length; j += i) {
				isNotPrime[j] = true;
			}
		}

		return isNotPrime;
	}
}
