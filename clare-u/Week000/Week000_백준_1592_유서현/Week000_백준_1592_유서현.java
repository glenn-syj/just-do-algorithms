import java.util.Scanner;

public class Week000_백준_1592_유서현 {
	public static void main(String[] args) {
		
		// 스캐너 객체 생성
		Scanner input = new Scanner(System.in);
		
		// N, M, L 받기
		int N = input.nextInt();
		int M = input.nextInt();
		int L = input.nextInt();
		
		
		// 배열 생성
		int[] intArray = new int[N]; 
		// intArray라는이름의 배열, 배열의길이는 N
		// 이 때 배열 값들 int기본값인 0으로초기화되있는건 왜 돼있는거지? 
		// System.out.println(intArray[i]); 해보니 다 0
		
		// 0번애가 1로 시작하기 위해서
		intArray[0] = 1; // do로 구현할수있나,,?
		
		// 총던진횟수(출력해야할변수)
		int ans = 0; // 얘도 걍 선언만해도 0인가?
		int index = 0; // 2트. index를 밖에서 해야하나?
		// 1트. for문안을  while로 감싸보기 -> for로하면 0 1 2.. 이렇게가니까 0->2로 봐야하는데 그게 안된다. 컴퓨터도 0->2 인덱스값을 바라보게 ㅅ수정해야함
		// 3트. while문 true로하고 if로 감싸기
			while ( intArray[index] < M ) { 
			if(index+L < N) { // 인덱스값에 문제없는상태
				intArray[index+L]++; // 횟수 +1
//				System.out.println("if문: " + intArray[index+L]);
				index += L; // 인덱스값 조절 0->2
				ans++;
			} else { // 인덱스값 문제있는상태
				intArray[(index+L)-N]++; // 인덱스 0~N으로 조절해주고 +1
//				System.out.println("else 문: " +intArray[(index+L)-N]);
				index = (index+L)-N ; // 인덱스값 조절 
				ans++;
			} 
			}
			 
		
		
		// ans를 ++하거나 배열값 다더하거나 둘중하나인데 일단 두번째꺼로 해봄
//		for (int i=0; i<intArray.length; i++) {
//			System.out.print("배열출력: ");
//			System.out.println(intArray[i]);
//		}
//		System.out.print("ans: ");
		System.out.println(ans); // 총던진횟수 출력 
	}


}
