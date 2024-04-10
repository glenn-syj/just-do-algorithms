package boj_20040_사이클게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* B20040. 사이클게임
 * 
 * 1. 조건
 * 	1-1. 선이 홀수 번째, 후가 짝수 번째
 * 	1-2. 사이클이 완성되면 게임이 종료됨
 * 	1-3. 점의 개수 n과 m 번째 차례까지의 게임 진행상황이 주어지면 사이클이 완성되었는지를 판단
 * 		-> 완성되었다면 몇 번째 차례에서 처음으로 사이클이 완성되었는지 출력
 * 
 * 2. 풀이
 * 	2-1. 유니온 파인드 이용
 * 	2-2. rank를 이용하는 것보다, path compression을 잉ㅇ
 * 		-> rank가 얼마인지가 중요하지 않은 문제
 */

public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		p = new int[N];
		int ans = 0;
		
		// makeset 대신
		for (int i=0; i<N; i++) {
			p[i] = i;
		}
		
		int A, B, px, py;
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			px = findset(A);
			py = findset(B);
			
			if (px == py) {
				ans = i;
				break;
			}
			
			union(px, py);
		}
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.close();
		
		
	}
	
	public static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}
		
		return p[x];
	}
	
	// 대표자들만 들어온닥 가정
	public static void union(int x, int y) {
		
		p[y] = x;
		
		
	}
	
	
	
}