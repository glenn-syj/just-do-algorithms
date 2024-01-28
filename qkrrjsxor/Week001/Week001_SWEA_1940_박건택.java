package SWEA;

import java.util.Scanner;

public class Week001_SWEA_1940_박건택 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		 * 가속 / 감속이 되면 선형적으로 속도가 증가하는게 아니라 속도가 바로 바뀐다는 것 같다
		 * 그러면 1초 마다 입력이 되므로  -> 거리총합 += 속력*1
		 * N번동안 0/1/2 에 따라 속력을 변화 시키고, 거리총합 += 속력 하자
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int distance = 0;
			int speed = 0;
			int select = 0;	//0, 1, 2 중 case 변수
			
			for(int i=0; i<N; i++) {
				
				select = sc.nextInt();	// for 문 N번 하면서 -> 0,1,2 중 하나 입력
				
				if(select ==0)
					
					distance += speed;
				
				else if(select == 1) {		// 가속의 경우
					
					int accel = sc.nextInt();
					speed += accel;			// speed를 accel 값 만큼 증가
					distance += speed;
					
				}
				else if(select == 2) {		// 감속의 경우
					
					int deccel = sc.nextInt();	//감속할 값
					speed -= deccel;		
					
					if(speed <0)			//현재 speed가 감속값 보다 작을 때
						speed = 0;
					distance += speed;
				}
				
			}
			System.out.print("#" + test_case + " ");
			System.out.println(distance);
			
		}

	}

}
