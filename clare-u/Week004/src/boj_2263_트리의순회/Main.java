package boj_2263_트리의순회;

import java.util.Scanner;

// boj_2263_트리의순회
//Try 상태로 코드제출 (구현을 못하는중) (하 문제인 1991도 못출어서 이것도 못 푸는듯 함)
public class Main {

	// 메소드에서 인덱스값만으로 탐색해 나갈 수 있도록 배열 새로 생성하지 않게 구현해야한다!

	static int N;
	static int[] inorder;
	static int[] postorder;

	static int root;

	static int rootIdx;

	static int leftIdx;
	static int rightIdx;

	static int startIdx;
	static int endIdx;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		inorder = new int[N];
		postorder = new int[N];

		startIdx = 0;
		endIdx = N - 1;

		for (int i = 0; i < N; i++) {
			inorder[i] = sc.nextInt();
		}

		for (int i = 0; i < N; i++) {
			postorder[i] = sc.nextInt();
		}

		root = postorder[N - 1]; // postorder의 맨 마지막 애가 root임 (레벨1)
		// 2

		// root기준으로 인오더를 자르기 2층의 왼 오 찾으려면
		// 그 왼쪽범위 / 오른쪽범위 / 루트 이렇게 포스트오더를 자르기 -> 자른거에서 각각 맨 오른쪽인덱스가 레벨2의 왼 오임

		findVLR(root); // 2

	} // main
		// 이러면 배열너무많이 만들어져서 무조건 시간초과 뜰거같은데

	static void findVLR(int V) {

		// inorder에서 root의 인덱스 찾기
		for (int i = 0; i < N; i++) {
			if (inorder[i] == root) {
				// L 인덱스 범위 : 0 1 2 -> startIdx ~ 루트전
				rootIdx = i;
			}
		}

		leftIdx = rootIdx - 1; // L 인덱스 범위
		rightIdx = rootIdx + 1; // R 인덱스 범위

		// V : 자기자신
		System.out.print("V는");
		System.out.print(V + " ");

		// L 인덱스 범위 :
		if (rootIdx == 1) {
			System.out.print("L이1개");
			System.out.print(inorder[0] + " ");
		} else {
			root = postorder[leftIdx];
			endIdx = rootIdx - 1;
			findVLR(root);
		}

		// 오른쪽 몇개인지
		if (rightIdx == endIdx) {
			System.out.print("R이1개");
			System.out.print(inorder[endIdx] + " ");
		} else {
			root = postorder[endIdx - 1];
			findVLR(root);
		}

	}

}