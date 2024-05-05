package boj_4803_트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/* B4803. 트리
 * 
 * 1. 조건
 * 	1-1. 사이클이 생기면 트리가 아님
 * 	1-2. 트리가 2개 이상이면 트리의 개수, 1개면 해당 문자열, 0개면 
 * 2. 풀이
 * 	2-1. 시작할 때 트리의 개수는 정점의 개수와 동일
 * 	2-2. unionfind를 이용해서 연결될 때마다 1개씩 제거
 * 		-> 만약 대표가 같은 곳으로 연결이 된다면 연결하고 2개 제거
 */

public class Main {
	
	static int N, M, start, end;
	static int[] parents;
	static boolean[] checked, setChecked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
	
		StringTokenizer st;
		N = -1;
		int tc = 1;
		while (N != 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if (N == 0) {
				break;
			}
			
			parents = new int[N+1];
			checked = new boolean[N+1];
			setChecked = new boolean[N+1];
			
			for (int i=1; i<=N; i++) {
				parents[i] = i;
			}
			
			int edgeNum = 0;
			int ans = 0;
			int px, py;
			int X, Y;
			while (edgeNum < M) {
				st = new StringTokenizer(br.readLine());
				
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				
				start = findset(X);
				end = findset(Y);
				
				px = Math.min(start, end);
				py = Math.max(start, end);
				
				boolean same = false;
				if (px==py) {
					same = true;
				}
				
				if (same && !checked[px]){
					checked[px] = true;
				} else if (!same) {
					unionfind(px, py);
					if (checked[px] || checked[py] == true) {
						checked[px] = true;
						checked[py] = true;
					}
				}
				for (int i=1; i<=N; i++) {
					if (parents[i] == py) {
						parents[i] = px;
					}
				}
				
				edgeNum++;
			}
			for (int i=1; i<=N; i++) {
				if (!checked[parents[i]] && !setChecked[parents[i]]) {
					setChecked[parents[i]] = true;
					ans++;
				}
			}
			
			
			if (ans >= 2) {
				sb.append("Case ").append(tc).append(": A forest of ").append(ans).append(' ').append("trees.").append('\n');
			} else if (ans == 1) {
				sb.append("Case ").append(tc).append(": There is one tree.").append('\n');
			} else {
				sb.append("Case ").append(tc).append(": No trees.").append('\n');
			}
			
			bw.write(sb.toString());
			bw.flush();
			sb.setLength(0);
			tc++;
		}
		
		bw.close();
		
		
		
	}
	
	public static void unionfind(int x, int y) {
		parents[y] = x;
	}
	
	public static int findset(int x) {
		if (parents[x] != x) {
			parents[x] = findset(parents[x]);
		}
		
		return parents[x];
	}
	
	
}