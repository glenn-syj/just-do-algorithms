import java.util.Scanner;

public class Week001_SWEA_1289_유서현 {
	// swea_1289_원재의메모리복구하기
	// 설계시간 : 15분
	// 구현시간 : 40분
	// 느낀점 : for문의 인덱스를 i를넣을지 i+j를 넣을지 더 논리에 강해지고 싶다

	// 1. 0과 1로 이루어진 수 입력받기
	// - 길이는 1~50, 자료형은 String이나 int? (char로 바로 입력받고 싶은데)
	// - String nextLine으로 받아서 char[]로 전환하기
	// - Peek: dayoung의 코드를 읽고 int로 입력받으면 0011에서 00은 사라져버린다는 힌트를 얻어서 시행착오를 줄임
	// 2. 초기 메모리값 = 입력받은 length랑 같은 길이, 다 0으로 시작
	// - 초기배열은 char[]로
	// 3. 최소 수정 횟수를 구하기
	// - 직접 수정하면서 수정될때마다 ans에 ++ 해주면 될듯
	// - 각 배열의 0번 인덱스부터 시작해서 일치, 불일치 판단
	// - 일치하면 패스, 불일치하면 초기배열의 해당 인덱스~끝까지 싹다변경 (0->1, 1->0 이런식)해주고 ans++
	// - 일치 불일치 이부분에 메서드를 쓰면 구현이 쉬우면서 깔끔할듯
	// - 메소드명 recoverMemory() : 하려했는데 그냥 메서드 안쓰는게 더 나을듯
	// 4. ans를 양식에 맞게 출력

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 테스트케이스 개수 T 입력받기
		int T = sc.nextInt();
		sc.nextLine(); // 개행문자 제거

		// T번 반복
		for (int t = 1; t <= T; t++) {

			// 1. 0과 1로 이루어진 수 입력받기
			String str = sc.nextLine();
			// char[]로 전환
			char[] memory = str.toCharArray();

			// 2. 초기 메모리값 char[] = 입력받은 length랑 같은 길이, 다 0으로 시작
			// 다 0으로 초기화하는거 반복문말고 깔끔한방법이 없나
			char[] badWonjae = new char[memory.length];
			for (int i = 0; i < badWonjae.length; i++) {
				badWonjae[i] = '0';
				// 따옴표없이 생으로 0 넣으면 ascii code값이 0인 char 들어가는것 주의
			}

			// 3. 최소 수정 횟수를 구하기
			// - 각 배열의 0번 인덱스부터 시작해서 일치, 불일치 판단
			// - 일치하면 패스, 불일치하면 초기배열의 해당 인덱스~끝까지 싹다변경 (0->1, 1->0 이런식)해주고 ans++
			int ans = 0; // ans 초기화
			// - 직접 수정하면서 수정될때마다 ans에 ++ 해주면 될듯
			for (int i = 0; i < memory.length; i++) {
				if (memory[i] != badWonjae[i]) {
					// 불일치하면 해당인덱스 끝까지 싺다변경
					ans++;
//					System.out.println("바꿔치기");
					for (int j = 0; j < memory.length - i; j++) {
						// 0이면 1로 1이면 0으로
						if (badWonjae[i + j] == '0') {
							badWonjae[i + j] = '1';
						} else {
							badWonjae[i + j] = '0';
						}
					}
				}
			}
			// 4. ans를 양식에 맞게 출력
			System.out.println("#"+t+" "+ans);
		}
	} // main

}
