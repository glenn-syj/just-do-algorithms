package boj_17404_박건택;
/*
 * 재귀를 이용해 조합으로 풀어보자
 * 
 * 우선 첫 행과 마지막 행은 조건이 조금 다르므로 따로 처리하자
 * 
 * 함수 호출 할 때부터 첫 행의 열을 기준으로 호출을 하고, 고른 열을 first로 저장해서 마지막 행이 선택 못하도록 변수 지정
 * 함수 인자로는 행, 열, 이전 열을 넘겨준다
 * 
 * 함수 내부 재귀에서 각 행에 대해 열을 돌아가면서 이전 선택한 열 인덱스면 패스,
 * 각 행에서 열을 순회하며 sum에 더하고, 다음 행의 0열부터 다시 재귀 반복
 * 마지막 행에 도달하면 이전 열 인덱스가 first인지 아닌지에 따라 구분하고 sum에 추가한 후, sum들의 최솟값을 ans 로 저장 후 sum 값을 다시 뺀 후에 return
 * return이 되면 해당 행 열 값을 뺀 후 다음 열 반복 순회, 재귀 반복
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static int[][] arr;
	static int first;
	static int sum;
	static int N;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < 3; t++) {
				arr[i][t] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 3; i++) {
			sum = arr[0][i];
			first = i;
			sumCount(1, 0, first);
		}
		System.out.println(ans);
	}

	public static void sumCount(int r, int c, int before) {
		// base case
		if (r == N - 1) {		//마지막 행에 도달하면
			
			int temp;
			if (before == first) {	//이전 행에서 선택한 열이 first이면 남은 둘 중 하나 중에서 작은 값. 남은 둘중 하나는 (first+1, 2)%3으로 구하자
				temp = Math.min(arr[N - 1][(first + 1) % 3], arr[N - 1][(first + 2) % 3]);
				sum += temp;
			} else {		//이전 행에서 선택한 열이 first가 아니면, first와 before이 아닌 열 인덱스
				temp = arr[N - 1][0] + arr[N - 1][1] + arr[N - 1][2] - arr[N - 1][first] - arr[N - 1][before];
				sum += temp;
			}
//			System.out.println("sum : " + sum + " ans : "  +ans);
			if (sum < ans) {
				ans = sum;
			}
			
			sum-= temp;
			return;
		}

		// recursive

		for (int i = 0; i < 3; i++) {
			if (i == before)	//이전에 선택한 열 인덱스는 패스
				continue;
			sum += arr[r][i];
//			System.out.println("r : " + r + " c : " + c + " before : " + before);
			sumCount(r + 1, 0, i);	//이번 행에서 선택한 열 인덱스를 다음 행에 대한 before 값으로 넘겨줘야지
			sum -= arr[r][i];
		}
	}
}
