import java.util.Scanner;


public class Week000_백준_1157_유서현 {
	// [ALG-000] Peek: 백준 문제 1592번 풀이
	
		// 선택지1. 단어를 String으로 입력받고 char배열로 변경 -> 이건 가능할듯
		// 선택지2. 한 자 한자를 char배열로 변경 -> 띄어쓰기나 엔터 없는데 이것도 가능한가? 그리고 스캐너에서 char입력받는건없는듯?
		// 2. 번 다시 검색해보니 구분자를 ""로하고 InputStreamReader, BufferedReader 쓰는게있는데 버퍼드는 쓰지말라한거같아서 1.로 해보기로함
		
		public static void main(String[] args) {
			// 스캐너 객체 생성
			Scanner input = new Scanner(System.in);
			
			// 문자열 word 받기
			String word = input.nextLine();
			
			// 대문자로 변경
			word = word.toUpperCase();
			
			// 대문자 문자열 -> 한 자씩 char 배열로 변경     
			char[] words = word.toCharArray();
			
			
			// char 배열 -> int 아스키코드 배열로 변경
			int[] asciiArray = new int[words.length];
			
			for (int i=0; i<words.length; i++) {
			    asciiArray[i] = Character.getNumericValue(words[i]); // 더 깔끔한 방법없나;;
			}
			
			
			// 시험삼아 배열출력
			for (int i=0; i<asciiArray.length; i++) {
//				System.out.print("배열 " +i + " 인덱스 : ");
//				System.out.println(asciiArray[i]);
			}
			
			
			// asciiArray 배열에서 최빈값 frequencyNum 찾기
			int frequencyNum = 0; // 아직 사용도 안한 변수
			
			// 찾기용 count 배열 생성 (발생한 횟수를 카운트)
			// count 길이생성용으로 asciiArray의 최대값찾기
			int asciiArraymax = Integer.MIN_VALUE; // max의 초기값은 정수형의 최소값
			
			for(int num: asciiArray) {
				if(num>asciiArraymax) {
					asciiArraymax = num;
				}
			}

			
				
			int[] countArray = new int[asciiArraymax+1];
			// 배열 돌면서 만날 때마다 +1 해주면 빈도 수 찾을 수 있음
			for(int i=0; i<asciiArray.length; i++) {
//				System.out.println("아스키i: " + asciiArray[i]);
	            //각 수의 index에 count
				for (int j=0; j<countArray.length; j++) {
					if (asciiArray[i] == j) {
						countArray[j]++;
						System.out.println("countArray[j]: "+ countArray[j]);
					}
					}
				} // 너무 더럽게 구현하긴했는데 어쨌든 countArray 구한듯;;
				
			// countArray 최대값 구해서 그 인덱스를 출력 (최대값이 2개이상이면 오류 출력은 어떻게하지)
			
			int countArraymax = Integer.MIN_VALUE;
			
			for(int num: countArray) {
				if(num>countArraymax) {
					countArraymax = num;
				}
			}
			// 중복체크
			// ... 어케함
			
			int ans = 0;
			for (int j=0; j<countArray.length; j++)	{
				if (countArraymax == countArray[j]) {
					ans = j;
				}
						
			} 
			
// 진심 구현한거 맘에안든다
//				    System.out.println("int value : " + ans);
				    String ansStr = Character.toString(ans+55);
//				    System.out.println("ASCII Value : " + ansStr);
				    System.out.println(ansStr);
				  
				
			// 최대값이 겹치면 ? 출력하는걸 구현 못한채로 마무리..
		

	
			
			
		}		
			
		}
		
		
		

	


