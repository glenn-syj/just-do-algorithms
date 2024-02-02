import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// 스캐너 객체 생성해주기
		Scanner sc = new Scanner(System.in);

		// 테스트 케이스 횟수를 입력받았다.
		int test = sc.nextInt();
		
		// 테스트 케이스만큼 반복하도록 for문 구축
		for(int t = 1; t <= test; t++) {
			// 입력값이 50개이상의 0과 1로 이루어진 데이터이기 때문에
			// 정수형으로 받기에는 너무 크다
			// String 형태로 받아온 이후, 하나의 문자로 짤라서 parseInt해준 이후 배열에 할당해줄 것이다.
			String memory = sc.next();
			
			// 입력값의 길이
			int memoryLen = memory.length();
			
			// 입력값의 길이만큼 배열 크기 생성
			int[] memories = new int[memoryLen];
			
			// 하나씩 잘라서 int형으로 바꿔준 이후 배열에 할당
			for(int i = 0; i < memoryLen; i++) {
				memories[i] = Integer.parseInt(memory.substring(i, i + 1));
			}
			
			// 비교하기 위해 초기화된 정수형 배열 생성
			int[] newMemory = new int[memoryLen];
			
			// 정답을 맞추기 위한 cnt 변수
			int cnt = 0;
			
			// 배열을 처음부터 끝까지 탐색하도록 하기 위한 바깥 for 문
			for(int i = 0; i < memoryLen; i++) {
				// 만약 새로운 메모리와 기존의 메모리의 요소가 같지 않으면서, 새로운 메모리의 값이 0이라면,
				if(newMemory[i] != memories[i] && newMemory[i] == 0) {
					// newMemory의 해당 위치부터 끝까지 1로 만들어준다.
					for(int j = i; j < memoryLen; j++) {
						newMemory[j] = 1;
					}
					// 한 차례 기능을 수행하면 cnt 하나 추가.
					cnt++;
				// 만약 새로운 메모리와 기존의 메모리의 요소가 같지 않으면서, 새로운 메모리의 값이 1이라면,
				}else if(newMemory[i] != memories[i] && newMemory[i] == 1) {
					// newMemory의 해당 위치부터 끝까지 0으로 만들어준다.
					for(int j = i; j < memoryLen; j++) {
						newMemory[j] = 0;
					}
					// 한 차례 기능을 수행하면 cnt 하나 추가.
					cnt++;
				}
			}
			
			// 출력
			System.out.printf("#%d %d%n", t, cnt);
		}
	}

}