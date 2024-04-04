package boj_20040_사이클게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 문제를 이해하는 것 자체가 관건인 문제.
 * 
 * 문제에서 무엇을 요구하는지 알고,
 * union find에 대한 개념을 알고 코드로 구현할 줄만 안다면 금방 해결할 수 있다.
 * 
 * 문제를 보면 사이클이란, 유니온 파인드 식으로 설명하자면, 
 * 같은 최대 부모를 가진 사람끼리 직선을 이으면 형성되는 것이다.
 * 
 * 따라서 주어지는 입력값을 배열에 집어넣은 이후, 배열을 처음부터 탐색하면서,
 * union을 통해 둘을 같은 그룹으로 계속해서 묶어주다가,
 * 이으려는 두 점이 같은 부모를 갖고 있다면, 싸이클이 형성된 것이다. 
 * 
 */


public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		
		int e = Integer.parseInt(st.nextToken());
		
		// 각 점의 번호와 일치하는 인덱스에 해당 점의 부모를 할당할 배열 p이다. 
		p = new int[v];
		
		// 점 p의 부모를 p로 만들어주는 for 반복문이다.
		for(int i = 0; i < v; i++) {
			p[i] = i;
		}
		
		// 정답 출력을 위한 ans 변수이다.
		int ans = 0;
		
		// 싸이클이 형성됐는지 혹은 형성되지 않았는지 확인할 flag 변수이다.
		boolean flag = false;
		
		// 시간을 최소화하기 위해 입력값을 받자마자 두 점을 하나의 그룹으로 만들어주었다.
		for(int i = 0; i < e; i++) {
			// 게임의 차례를 세기 위해 ans 변수를 하나씩 증가시켜준다.
			ans++;
			
			// 입력값을 받기 위한 부분.
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 받은 입력값을 바탕으로,
			// x점의 최고 리더와 y점의 최고 리더가 동일인물이 아니면,
			// y의 최고리더를 x의 최고리더로 만들어준다.
			// x의 최고리더가 a, y의 최고리더가 b라면,
			// b의 리더가 a가 되는 것이다.
			if(find_set(x) != find_set(y)) {
				union(x, y);
			}else {
				// 만약 두 점의 최고 리더가 동일인물이라면,
				// 두 점을 잇는 순간 싸이클이 형성되는 것이다.
				// 따라서 싸이클이 형성됐음을 체크할 수 있도록,
				// flag 변수를 true로 바꿔준 이후,
				// 입력값을 받는 for문을 빠져나온다.
				flag = true;
				break;
			}
			
		}
		
		if(flag) {
			System.out.println(ans);
		}else {
			System.out.println(0);
		}
		
	}
	
	// 매개변수로 받은 a의 최고 리더를 반환하는 find_set 메소드.
	public static int find_set(int a){
		if(a == p[a]) {
			return a;
		}else {
			// 이 부분에서 코드를 잘못 작성하였다.
			// 처음에는 return p[a] = find_set(a);로 작성했다.
			// a의 부모를 a의 최고부모로 대신 할당하는 것이라 생각하였기 때문이다.
			// 그러나 이런 식으로 작성하면 정확히 똑같은 기능만 실행하는 재귀가 만들어지기 때문에,
			// 끝없이 반복된다.
			// return p[a] = find_set(p[a]);로 작성해야,
			// a의 리더에 대한 find_set을 실행하고,
			// a의 리더의 리더에 대한 find_set을 실행하게 되는 것이다.
			return p[a] = find_set(p[a]);
		}
	}
	
	public static void union(int a, int b) {
		p[find_set(b)] = find_set(a);
	}
	
}