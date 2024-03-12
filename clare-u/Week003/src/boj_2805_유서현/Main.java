package boj_2805_유서현;

import java.util.Scanner;

public class Main {
	// boj_2805_나무자르기 // 스터디 문제

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 나무의 수 N 4
		int M = sc.nextInt(); // 가져갈 길이 M 7
		int[] trees = new int[N]; // 배열에다가 나무길이 N개사이즈로 20 15 10 17
		int H = Integer.MIN_VALUE; // 최대값 찾기
		// 높이 H설정 : N개 중 최대값 -1 19
		for (int i = 0; i < N; i++) {
			trees[i] = sc.nextInt();

			if (trees[i] > H) {
				H = trees[i];
			}
		}

		sc.close();

		// 이분 탐색 시작
		// 시작범위: 1~H
		int start = 1;
		int end = H - 1; // 19

		int mid; // 중간값
		long cutTree; // 가져갈 나무

		while (start <= end) { //

			mid = (start + end) / 2; // 10
			// 이 부분 코드를 while문안에서 안하고 int mid; 여기서 초기화하면 시간초과가뜨는데 왜지,,?
			
			cutTree = 0;

			for (int i = 0; i < N; i++) {
				if (trees[i] > mid)
					cutTree += (trees[i] - mid);
			}

			if (cutTree < M) { // cutTree가 M보다 모자르면
				end = mid - 1; // end지점을 9로 수정 -> 1~9 탐색 (왼쪽을 더 탐색)
			} else { // cutTree가 M보다 같거나 크면
				start = mid + 1; // start지점을 11으로 수정 -> 11~19 탐색 (오른쪽을 더 탐색)
				// 이부분을 mid로했었는데, 답이 안 나오고 무한루프에 빠지는 듯 해서 mid+1로 수정하니 잘 돌아간다
				// 아직 이분탐색 이해가 부족한듯
			}
		}

		System.out.println(end);
	} // main

}