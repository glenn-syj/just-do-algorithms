package boj_15685_치킨배달;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* B15685. 치킨 배달
 * 
 * 1. 조건
 * 	1-1. 도시에 있는 치킨 집 중 최대 M개를 고르고, 나머지 치킨집은 모두 폐업
 * 		-> 집과 가장 가까운 치킨집 사이의 거리이므로, M은 많을 수록 좋고, 결과에도 영향을 미치지 않는다
 * 	1-2. 각 치킨집에 대해서, 모든 집과의 거리를 미리 계산해두면 편할듯?
 * 		-> 그리고 각 점에서, 남아있는 M개의 치킨집 각각에 대해서 최단 거리를 선택
 * 2. 풀이
 */

public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N, M;
	static List<int[]> houses = new ArrayList<>();
	static List<int[]> chickens = new ArrayList<>();
	static int minDist = Integer.MAX_VALUE;
	static int[][] distances;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int input;
		
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<N; col++) {
				input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					houses.add(new int[] {row, col});
				} else if (input == 2) {
					chickens.add(new int[] {row, col});
				}
			}
		}
		
		// houses의 index와 chickens의 index 사이의 거리를 측정해서 저장
		distances = new int[houses.size()][chickens.size()];
		
		for (int i=0; i<houses.size(); i++) {
			for (int j=0; j<chickens.size(); j++) {
				distances[i][j] = findDist(houses.get(i), chickens.get(j));
			}
		}		
		
		
		recur(0, 0, 0);
		
		sb.append(minDist);
		bw.write(sb.toString());
		bw.close();
		
	}
	
	public static int findDist(int[] arr1, int[] arr2) {
		
		return Math.abs(arr1[0]-arr2[0]) + Math.abs(arr1[1]-arr2[1]);
	}
	
	public static void recur(int idx, int sidx, int bitmask) {
		
		
		
		if (sidx >= M) {
			int distance = 0;
			int localDistance = Integer.MAX_VALUE;
			
			for (int h=0; h<houses.size(); h++) {
				localDistance = Integer.MAX_VALUE;
				for (int i=0; i<chickens.size(); i++) {
					if ((bitmask & (1 << i)) > 0) {
						if (localDistance > distances[h][i]) {
							localDistance = distances[h][i];
						}
					}
				}
				if (localDistance == Integer.MAX_VALUE) {
				}
				distance += localDistance;
			}
			
			
			if (distance < minDist) {
				minDist = distance;
			}
			
			return;
		}
		
		if (idx >= chickens.size()) {
			return;
		}
		
		
		recur(idx+1, sidx+1, bitmask + (1 << idx));
		recur(idx+1, sidx, bitmask);
	}
	
}