package boj_2606_바이러스;

// 오류 : 1에서부터 이어져있는 애들만 감염 수를 세어야 하는데, 1에서 이어지지 않은 애들도 세고 있음.
// 메서드를 만들어서 재귀 호출하여, 1에서부터 이어져 내려가도록 만들어보자.

// 오류 : 1 3, 2 3의 경우를 탐색하지 못했다.
// 나는 첫 번째 요소에서 뒤에 요소로 이동하는 것만 구현하였기에, 위의 예제에서 3 -> 2가 되지 못해 카운트를 놓친 것이다.
// 배열을 만들어줄 때, 2차원 배열에 양방향 모두 저장하도록 만들어주었고,
// 1에 돌아와서 1을 세어버리는 오류가 발생하였기에,
// boolean 배열에서 1은 이미 탐색한 것으로 미리 처리해주었다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] infection;
	static List<Integer>[] lineList;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 노드의 수
		int computerCnt = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());

		// 간선의 수
		int linkCnt = Integer.parseInt(st.nextToken());
		
		// 인덱스로 바로 수치를 알아볼 수 있도록 배열의 크기를 하나 더 크게 만들어주었다.
		lineList = new ArrayList[computerCnt + 1];
		
		for(int i = 0; i < linkCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			// List 형태의 자료구조를 담는 배열을 형성했지만,
			// 배열내부에 각 인덱스마다 리스트들이 들어있는 것은 아니기 때문에,
			// 처음 요소를 넣을 때는 list를 만들어서 넣어줘야 한다.
			if(lineList[start] == null) {
				List<Integer> temp = new ArrayList<>();
				temp.add(end);
				lineList[start] = temp;
			}else {
				lineList[start].add(end);
			}
			
			if(lineList[end] == null) {
				List<Integer> temp = new ArrayList<>();
				temp.add(start);
				lineList[end] = temp;
			}else {
				lineList[end].add(start);
			}
			
		}
		
		// 잘 들어오고 있다. 확인 완료
		// 이어진 곳이 없는 인덱스에는 리스트가 아예 없고, null 값이 들어가있다.
//		for(int i = 0; i < computerCnt; i++) {
//			System.out.println(lineList[i]);
//		}
		
		// 감염된 컴퓨터 수를 세기 위한 cnt 변수
		cnt = 0;
		
		// 값을 넣는 것까지 성공했으니, 2중 for문을 구현하여 각 오염된 컴퓨터의 수를 세어보자.
		// 그 전에 탐색이 되었는지 확인하기 위한 boolean 배열이 필요하다.
		
		infection = new boolean[computerCnt + 1];
		// 기본값은 전부 false.
		// 탐색이 이루어지면 해당 인덱스에 있는 값을 true로 바꿔줄 것이다.
		infection[1] = true;
		
		bfs(1);
		
		// 오류 : 1에서부터 이어져있는 애들만 감염 수를 세어야 하는데, 1에서 이어지지 않은 애들도 세고 있음.
		// 메서드를 만들어서 재귀 호출하여, 1에서부터 이어져 내려가도록 만들어보자.
		
		if(computerCnt == 1) {
			System.out.println(0);
		}else {
			System.out.println(cnt);
		}
		
	}
	
	static void bfs(int inf) {
		
		if(lineList[inf] == null) {
			return;
		}
		
		int size = lineList[inf].size();
		
		for(int j = 0; j < size; j++) {
			// 각 인덱스별로 연결된 노드들을 전부 가져온다.
			if(infection[lineList[inf].get(j)] == false) {
				infection[lineList[inf].get(j)] = true;
				cnt++;
				bfs(lineList[inf].get(j));
			}else {
				continue;
			}
		}
	}
}