package swea_7964_부먹왕국의차원관문;

import java.util.Scanner;

// swea_7964_부먹왕국의차원관문
// 테케 3개는 맞는데 (13개중 3개정답) 런타임에러상태로 제출

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt(); // 도시수 (1~300,000)
			int D = sc.nextInt(); // 이동제한거리 (1<=D<=N)

			int[] map = new int[N + 1 + 1]; // 도시 차원관문 맵
			// 0번과 N+1번에는 차원관문 존재
			map[0] = 1;
			map[N + 1] = 1;

			for (int i = 1; i < N + 1; i++) {
				map[i] = sc.nextInt();
			}

			// 모든도시 이동해야되고 모든 차원관문사이와 직접적으로 이동이 가능하도록
			// 니넨그냥 침략당해라 뭔 부먹이야

			int ans = 0; // 추가건설해야하는 차원관문의 최소개수
			int idx = 1;// while문 이용하기위해서 인덱스 생성
			while (idx < N + 1) {
				if (map[idx] == 1) { // 관문이 있으면
					idx += D; // D만큼 이동
				} else { // 관문이 없으면
					for (int i = idx - D + 1; i < idx; i++) {
						// 이전지점의 한칸 앞부터 탐색
						if (map[i] == 1) { // 그사이에 관문이 있으면
							idx += D; // D만큼 이동
							break;
						}
					} // 다돌았는데도 없으면
					map[idx++] = 1; // 관문생성해주고 ans++
					ans++;
				}
			}
			// 양식에 맞게 출력
			System.out.println("#" + t + " " + ans);
		}

	}

}