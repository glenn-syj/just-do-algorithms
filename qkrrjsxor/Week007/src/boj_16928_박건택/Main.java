package boj_16928_박건택;

/*
 * DP를 이용해 풀어보자
 * 2x2 배열을 만들 필요가 있는가? 아닐것 같다
 * 1~ 100 까지 있는 1차원 배열을 만들고 사다리 / 뱀으로 이동하자
 * 
 * DP
 * N 번 까지 이동하는 최단 횟수는? 
 * (N-6) ~ (N-1) 까지 이동하는 횟수 +1 또는 if 사다리 도착지점이라면 사다리 + 1
 * 
 * 만약 뱀이 6칸 이상 연속해있다면? 이걸 건너뛸 수 있는 사다리를 타는 방법 뿐
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] array;
	static int[] dp;
	static int[][] ladder; // 출발 - 도착 순
	static int[][] snake; // 출발 - 도착 순
	static int ladderNum, snakeNum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		ladderNum = Integer.parseInt(st.nextToken());
		snakeNum = Integer.parseInt(st.nextToken());

		ladder = new int[ladderNum][2];
		snake = new int[snakeNum][2];
		array = new int[101];
		dp = new int[101];

		for (int i = 0; i < 101; i++) {
			array[i] = i;
		}
		for (int i = 0; i < ladderNum; i++) {
			st = new StringTokenizer(br.readLine());
			ladder[i][0] = Integer.parseInt(st.nextToken());
			ladder[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < snakeNum; i++) {
			st = new StringTokenizer(br.readLine());
			snake[i][0] = Integer.parseInt(st.nextToken());
			snake[i][1] = Integer.parseInt(st.nextToken());
		}
		// --- 여기까지 입력 받고 사다리 좌표랑 뱀 좌표 입력 끝 ---

		// dp 시작?

		for (int i = 2; i < 8; i++) {
			dp[i] = 1;
		}

		for (int i = 8; i <= 100; i++) {
			int min = 1000;
			for (int j = i - 6; j < i; j++) {
				if (dp[j] < min)
					min = dp[j];
			}
			dp[i] = min+1;

			//뱀 도착지점이라면
			for (int t = 0; t < snakeNum; t++) {
				if (i == snake[t][1]) {
					min = 1000;
					for (int j = i - 6; j < i; j++) {
						if (dp[j] < min)
							min = dp[j];
					}
					dp[i] = Math.min(dp[snake[t][0]], min + 1);
					break;
				}
			}
			
			// 사다리 도착지점 이라면
			for (int t = 0; t < ladderNum; t++) {
				if (i == ladder[t][1]) {
					min = 1000;
					for (int j = i - 6; j < i; j++) {
						if (dp[j] < min)
							min = dp[j];
					}
					dp[i] = Math.min(dp[ladder[t][0]], min + 1);
					break;
				}
			}

		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[100]);
		
	}

}
