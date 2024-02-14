package boj_11729_황민욱;

/*
 * [문제 풀이 과정]
 * *하노이 탑의 규칙을 점화식 처럼 찾아야하는 문제*
 * 	- 일단 하노이탑은 만약 N개를 1 -> 3으로 옮겨야 한다.
 * 	- 그러면 젤 큰 마지막이 3으로 가야하기 때문에 N-1개가 2로 가야한다.
 *  - N-1개가 2로 가야하면 N-2개는 3으로 가야한다.
 *  - N-2개가 3으로 가야하면 N-3개는 2로 가야한다.
 *  ...
 *  - N-1개가 2로 가고 제일 큰 마지막이 3으로 오게 되면 다시 N-1개가 3으로 가야한다.
 *  - N-1개가 3으로 가려면 N-2개가 1로 가야한다.
 *  - N-2개가 1로 가려면 N-3개가 3으로 가야한다.
 *  ...
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int count = 0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 25);

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		hanoiTower(N, 1, 3, 2);
		System.out.println(count);
		bw.flush();
	}

	// n -> 하노이 탑의 층의 개수
	// from -> 출발 위치
	// to -> 도착 위치
	// other -> from, to가 아닌 남아 있는 하나의 대안
	public static void hanoiTower(int n, int from, int to, int other) throws IOException {
		// 함수를 들어올 때마다 개수를 세면 옮긴 횟수.
		count++;

		// 기저 조건 -> 만약 1개만 남으면 그냥 옮기면 되므로 옮기는 위치를 print
		if (n == 1) {
			bw.write(from + " " + to + "\n");
			return;
		}

		// 일단 처음에 제일 큰 하노이 블럭이 목적지로 갈 때까지 재귀적으로 옮기기
		hanoiTower(n - 1, from, other, to);

		// 제일 큰 블록 옮기기
		bw.write(from + " " + to + "\n");
		
		// 다시 n-1을 목적지로 재귀적으로 옮기기
		hanoiTower(n - 1, other, to, from);
	}
}
